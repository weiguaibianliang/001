package com.controller;

import basic.result.RestResponse;
import basic.result.Success;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtFrontPieceModel;
import com.service.ShirtBackPieceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/backPiece/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫后片")
public class ShirtBackPieceController {

    @Autowired
    private ShirtBackPieceService shirtBackPieceService;

    @ApiOperation("增加后片信息")
    @PostMapping("addBackPiece")
    public RestResponse addBackPiece(@RequestBody ShirtBackPieceModel shirtBackPieceModel){
        shirtBackPieceService.addBackPiece(shirtBackPieceModel);
        return new Success<>();
    }


    @ApiOperation("analysisModel")
    @PostMapping("analysisModel")
    public RestResponse analysisModel(){
        Long id = 2L;
        shirtBackPieceService.updateById(id);
        return new Success<>();
    }

}
