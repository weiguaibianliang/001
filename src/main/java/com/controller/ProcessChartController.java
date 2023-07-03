package com.controller;


import basic.result.RestResponse;
import basic.result.Success;
import basic.result.page.PageQuery;
import com.DTO.ProcessChartDTO;
import com.service.ProcessChartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.ExcelHeadUtil;
import util.util.io.ExcelOperate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ShirtProcess/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫款式工序表")
public class ProcessChartController {


    @Autowired
    private ProcessChartService processChartService;

    /**
     *基本款式工艺模块：前片、后片（包含下摆）、门襟、
     */
    @ApiOperation("获取基本模块工序")
    @GetMapping("getBasicModuleProcess")
    public RestResponse getBasicModuleProcess(@RequestParam("produceOrderNo") String produceOrderNo){
        return new Success<>(processChartService.getBasicModuleProcess(produceOrderNo));
    }


    @ApiOperation("获取整体衬衫工序")
    @GetMapping("getOverallShirtProcess")
    public RestResponse getOverallShirtProcess(@RequestParam("produceNo") String produceNo){
        return new Success<>(processChartService.getOverallShirtProcess(produceNo));
    }
    @ApiOperation("导出衬衫工序表分页")
    @PostMapping("exportProcessChartPage")
    public void exportDetailPage(@RequestBody PageQuery query,HttpServletResponse response,@RequestParam("produceNo") String produceNo) {
        StringBuilder name = new StringBuilder();
        name.append("衬衫工序表");
        String fileName = name.toString();
        List<ProcessChartDTO> exportList = processChartService.getExportList(produceNo);
        try {
            ExcelOperate.exportExcel(response, exportList, ExcelHeadUtil.getExcelHead(ProcessChartDTO.class), fileName);
        } catch (Exception e) {
            log.error("导出入库明细失败", e);
        }
    }
}




