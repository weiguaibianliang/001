package com.controller;

import basic.result.RestResponse;
import basic.result.Success;
import com.Model.ShirtFrontPieceModel;
import com.Model.ShirtOverShoulderModel;
import com.service.ShirtOverShoulderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/overShoulder/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫过肩")
public class ShirtOverShoulderController {


    @Autowired
    private ShirtOverShoulderService shirtOverShoulderService;
    @ApiOperation("增加过肩信息")
    @PostMapping("addOverShoulder")
    public RestResponse addOverShoulder(@RequestBody ShirtOverShoulderModel shirtOverShoulderModel){
        shirtOverShoulderService.addOverShoulder(shirtOverShoulderModel);
        return new Success<>();
    }

}
