package com.controller;


import basic.result.RestResponse;
import basic.result.Success;
import com.service.ProduceOrderService;
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
@RequestMapping("/ProduceOrder/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫生产订单信息")
public class ProduceOrderController {

    @Autowired
    private ProduceOrderService produceOrderService;


    @ApiOperation("获取订单信息")
    @GetMapping("findByProduceOrderNo")
    public RestResponse getById(@RequestParam("produceOrderNo") String produceOrderNo){
        return new Success<>(produceOrderService.findByProduceOrderNo(produceOrderNo));
    }

}
