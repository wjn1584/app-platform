/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fit.waterflow.flowsengine.domain.flows.validators.rules.jobers;



import modelengine.fit.waterflow.flowsengine.domain.flows.definitions.nodes.jobers.FlowJober;
import modelengine.fit.waterflow.flowsengine.domain.flows.validators.rules.Rules;

/**
 * 不同流程节点自动任务类型校验规则
 *
 * @author 高诗意
 * @since 2023/08/29
 */
public interface JoberRule extends Rules {
    /**
     * 校验不同流程节点自动任务类型的合法性
     * 当校验不通过时，抛出运行时异常{@link WaterflowParamException}
     *
     * @param flowJober 流程节点自动任务
     */
    void apply(FlowJober flowJober);
}
