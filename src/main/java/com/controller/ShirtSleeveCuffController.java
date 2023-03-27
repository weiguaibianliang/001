package com.controller;

import basic.result.RestResponse;
import basic.result.Success;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtSleeveCuffModel;
import com.service.ShirtSleeveCuffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sleeveCuff/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫袖克夫")
public class ShirtSleeveCuffController {

    @Autowired
    private ShirtSleeveCuffService shirtSleeveCuffService;

    @ApiOperation("增加袖克夫信息")
    @PostMapping("addSleeveCuff")
    public RestResponse addSleeveCuff(@RequestBody ShirtSleeveCuffModel shirtSleeveCuffModel){
        shirtSleeveCuffService.addSleeveCuff(shirtSleeveCuffModel);
        return new Success<>();
    }

    @ApiOperation("test")
    @GetMapping("test")
    public RestResponse test(){
        shirtSleeveCuffService.test();
        return new Success<>();
    }
}
