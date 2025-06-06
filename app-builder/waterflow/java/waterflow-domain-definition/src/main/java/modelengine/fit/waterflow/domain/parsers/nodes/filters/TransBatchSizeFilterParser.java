/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fit.waterflow.domain.parsers.nodes.filters;

import modelengine.fit.waterflow.domain.definitions.nodes.filters.FlowFilter;
import modelengine.fit.waterflow.domain.definitions.nodes.filters.FlowTransBatchSizeFilter;
import modelengine.fit.waterflow.domain.enums.FlowFilterType;
import modelengine.fit.waterflow.domain.parsers.FlowGraphData;

/**
 * ClassName
 * 简述
 *
 * @author 夏斐
 * @since 1.0
 */
public class TransBatchSizeFilterParser implements FilterParser {
    @Override
    public FlowFilter parseFilter(FlowGraphData flowGraphData, int nodeIndex, String filterKey) {
        FlowFilter filter = new FlowTransBatchSizeFilter();
        filter.setFilterType(FlowFilterType.TRANS_BATCH_FILTER);
        commonParse(filter, flowGraphData, nodeIndex, filterKey);
        return filter;
    }
}
