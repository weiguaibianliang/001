package com.controller;

import basic.result.RestResponse;
import basic.result.Success;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtSleeveBodyPlacketModel;
import com.service.ShirtSleeveBodyPlacketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sleeveBodyPlacket/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫袖身袖衩")
public class ShirtSleeveBodyPlacketController {


    @Autowired
    private ShirtSleeveBodyPlacketService shirtSleeveBodyPlacketService;

    @ApiOperation("增加袖身袖衩信息")
    @PostMapping("addSleeveBodyPlacket")
    public RestResponse addSleeveBodyPlacket(@RequestBody ShirtSleeveBodyPlacketModel shirtSleeveBodyPlacketModel){
        shirtSleeveBodyPlacketService.addSleeveBodyPlacket(shirtSleeveBodyPlacketModel);
        return new Success<>();
    }

    @ApiOperation("更新袖身袖衩信息")
    @PostMapping("updateSleeveBodyPlacket")
    public RestResponse updateSleeveBodyPlacket(@RequestBody ShirtSleeveBodyPlacketModel shirtSleeveBodyPlacketModel){
        shirtSleeveBodyPlacketService.updateSleeveBodyPlacket(shirtSleeveBodyPlacketModel);
        return new Success<>();
    }



}
