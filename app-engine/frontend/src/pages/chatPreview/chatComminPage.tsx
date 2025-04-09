
/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

import qs from 'qs';
import React, { useEffect, useMemo, useRef } from "react";
import { useParams, useHistory } from 'react-router-dom';
import useSearchParams from "@/shared/hooks/useSearchParams";
import usePlugin from "@/shared/hooks/usePlugin";
import { AippContext } from '../aippIndex/context';
import ChatPreview from './index';
import { useAppSelector } from "@/store/hook";
import { TENANT_ID } from "@/pages/chatPreview/components/send-editor/common/config";
import { findConfigValue } from "@/shared/utils/common";

// 公共参数，公共聊天界面
const CommonChat = (props: any) => {
  const { contextProvider, previewBack, showElsa } = props;
  const iframeRef = useRef<HTMLIFrameElement | null>(null);
  const appInfo = useAppSelector((state) => state.appStore.appInfo);
  const isDebug = useAppSelector((state) => state.commonStore.isDebug);
  const appRef = useRef<any>({});

  const history = useHistory();

    const { uid } = useParams();
    const isPreview = useMemo(() => !!uid, [uid]);

    const { plugin_name, ...params } = useSearchParams();
    const plugin = usePlugin();

    const iframeUrl = useMemo(() => {
        let url = plugin?.url;
        const hasSearch = url?.includes('?');
        const search = qs.stringify({
            ...params,
            back: isPreview ? undefined : '1'
        });
        if (search) {
            url += hasSearch ? `&${search}` : `?${search}`
        }
        return url;
    }, [plugin, isPreview, params])

    useEffect(() => {
        const handler = (e: { data: string }) => {
            const data = JSON.parse(e.data);
            if (data.type === 'back') {
                if (isPreview) {
                    history.goBack();
                } else {
                    history.push({
                        pathname: '/app'
                    })
                }
            } else if (data.type === 'navigate') {
                const search = new URLSearchParams(history.location.search);
                Object.entries(data.params).forEach(([key, value]) => {
                    if (value === null) {
                        search.delete(key);
                    } else {
                        search.set(key, String(value));
                    }
                });

                history.push({
                    search: search.toString()
                });
            }
        };
        window.addEventListener('message', handler);
        return () => window.removeEventListener('message', handler);
    });

    // 给iframe的对话界面传递参数
    const sendMessageToIframe = () => {
      const appInfo = appRef.current;
      let params = {
        tenantId: TENANT_ID,
        appId: appInfo.id,
        useMemory: findConfigValue(appInfo, 'memory').memorySwitch,
        isDebug
      }
      iframeRef.current?.contentWindow.postMessage({...params}, '*');
    }

  useEffect(() => {
    if (Object.keys(appInfo).length === 0) {
      return;
    }
    appRef.current = appInfo;
  }, [appInfo]);

  return (
    (plugin && !showElsa)
    ? <iframe
        ref={iframeRef}
        src={iframeUrl}
        style={ {border: 'none', height: '100%'} }
        width="100%"
        onLoad={sendMessageToIframe}
      />
    : <AippContext.Provider value={{ ...contextProvider }}>
        <ChatPreview previewBack={previewBack} />
      </AippContext.Provider>
  )
};

export default CommonChat;
