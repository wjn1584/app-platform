/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fit.waterflow.domain.parsers.nodes;

import static modelengine.fit.waterflow.domain.enums.FlowNodeType.END;

import modelengine.fit.waterflow.domain.definitions.nodes.FlowEndNode;
import modelengine.fit.waterflow.domain.definitions.nodes.FlowNode;
import modelengine.fit.waterflow.domain.parsers.FlowGraphData;

/**
 * 结束节点解析类
 *
 * @author 杨祥宇
 * @since 1.0
 */
public class EndNodeParser implements NodeParser {
    @Override
    public FlowNode parseNode(FlowGraphData flowGraphData, int index) {
        FlowNode flowNode = new FlowEndNode();
        flowNode.setType(END);
        commonParse(flowNode, flowGraphData, index);
        return flowNode;
    }
}
