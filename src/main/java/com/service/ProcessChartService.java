package com.service;

import basic.result.page.PageQuery;
import com.DTO.ProcessChartDTO;

import java.util.List;
import java.util.Map;

public interface ProcessChartService {
    List<ProcessChartDTO> getBasicModuleProcess(String produceOrderNo);

    List<Map<String, Integer>> getOverallShirtProcess(String produceNo);

    List<ProcessChartDTO> getExportList(String produceNo);

}
