package com.controller;

import basic.result.RestResponse;
import basic.result.Success;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtCollarModel;
import com.service.ShirtCollarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/collar/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫领子")
public class ShirtCollarController {

    @Autowired
    private ShirtCollarService shirtCollarService;
    @ApiOperation("增加领子信息")
    @PostMapping("addCollar")
    public RestResponse addCollar(@RequestBody ShirtCollarModel shirtCollarModel){
        shirtCollarService.addCollar(shirtCollarModel);
        return new Success<>();
    }

}
