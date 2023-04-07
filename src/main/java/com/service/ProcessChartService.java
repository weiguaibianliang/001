package com.service;

import com.DTO.ProcessChartDTO;

import java.util.List;

public interface ProcessChartService {
    List<ProcessChartDTO> getBasicModuleProcess(String produceOrderNo);

    List<ProcessChartDTO> getOverallShirtProcess(String produceNo);

}
