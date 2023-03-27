package com.controller;


import basic.result.RestResponse;
import basic.result.Success;
import com.service.ProcessChartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ShirtProcess/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫款式工序表")
public class ProcessChartController {


    @Autowired
    private ProcessChartService processChartService;


    /**
     * 假设款式制作特点:
     * 尖领角、明门襟、6粒扣、左胸明贴袋1个、后肩育克、直下摆、长袖、袖口开衩、圆角袖克夫
     */


    /**
     *基本款式工艺模块：前片、后片（包含下摆）、门襟、
     */
    @ApiOperation("获取工基本模块工序")
    @GetMapping("getBasicModuleProcess")
    public RestResponse getBasicModuleProcess(@RequestParam("produceOrderNo") String produceOrderNo){
        return new Success<>(processChartService.getBasicModuleProcess(produceOrderNo));
    }

    /**
     * 获取可选模块工序:育克、袖、领、胸袋
     */
    @ApiOperation("获取可选模块工序")
    @GetMapping("getOptionalModuleProcess")
    public RestResponse getOptionalModuleProcess(@RequestParam("produceOrderNo") String produceOrderNo){
        return new Success<>(processChartService.getOptionalModuleProcess(produceOrderNo));
    }

    /**
     * 获取特殊工艺模块工序：省、褶裥等
     */
    @ApiOperation("获取特殊工艺模块")
    @GetMapping("getSpecialModuleProcess")
    public RestResponse getSpecialModuleProcess(@RequestParam("produceOrderNo") String produceNo){
        return new Success<>(processChartService.getSpecialModuleProcess(produceNo));
    }

    /**
     * 获取连接组合工序：合肩缝、上领、合摆缝、上袖、上袖头、订胸袋
     */
    @ApiOperation("获取连接组合模块")
    @GetMapping("getConnectModuleProcess")
    public RestResponse getConnectModuleProcess(@RequestParam("produceNo") String produceNo){
        return new Success<>(processChartService.getConnectModuleProcess(produceNo));
    }

    /**
     * 获取收尾模块
     */
    @ApiOperation("获取收尾模块")
    @GetMapping("getCloseModuleProcess")
    public RestResponse getCloseModuleProcess(@RequestParam("produceNo") String produceNo){
        return new Success<>(processChartService.getCloseModuleProcess(produceNo));
    }

    @ApiOperation("获取整体衬衫工序")
    @GetMapping("getOverallShirtProcess")
    public RestResponse getOverallShirtProcess(@RequestParam("produceNo") String produceNo){
        return new Success<>(processChartService.getOverallShirtProcess(produceNo));
    }




}
