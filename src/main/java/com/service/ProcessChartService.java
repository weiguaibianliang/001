package com.service;

import com.DTO.ProcessChartDTO;

import java.util.List;

public interface ProcessChartService {
    List<ProcessChartDTO> getBasicModuleProcess(String produceOrderNo);

    List<ProcessChartDTO> getOptionalModuleProcess(String produceOrderNo);

    List<ProcessChartDTO> getSpecialModuleProcess(String produceNo);

    List<ProcessChartDTO> getConnectModuleProcess(String produceNo);

    List<ProcessChartDTO> getCloseModuleProcess(String produceNo);

    List<ProcessChartDTO> getOverallShirtProcess(String produceNo);

}
