/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fit.waterflow.domain.validators.rules.tasks;

import modelengine.fit.jade.waterflow.exceptions.WaterflowParamException;
import modelengine.fit.waterflow.domain.definitions.nodes.tasks.FlowTask;
import modelengine.fit.waterflow.domain.validators.rules.Rules;

/**
 * 不同流程节点手动任务类型校验规则
 *
 * @author 高诗意
 * @since 1.0
 */
public interface TaskRule extends Rules {
    /**
     * 校验不同流程节点任务类型的合法性
     * 当校验不通过时，抛出运行时异常{@link WaterflowParamException}
     *
     * @param flowTask 流程节点人工任务
     */
    void apply(FlowTask flowTask);
}
