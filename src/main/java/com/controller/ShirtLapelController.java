package com.controller;

import basic.result.RestResponse;
import basic.result.Success;
import com.Model.ShirtFrontPieceModel;
import com.Model.ShirtLapelModel;
import com.service.ShirtLapelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/lapel/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫门襟")
public class ShirtLapelController {

    @Autowired
    private ShirtLapelService shirtLapelService;

    @ApiOperation("增加门襟信息")
    @PostMapping("addLapel")
    public RestResponse addLapel(@RequestBody ShirtLapelModel shirtLapelModel){
        shirtLapelService.addLapel(shirtLapelModel);
        return new Success<>();
    }
}
