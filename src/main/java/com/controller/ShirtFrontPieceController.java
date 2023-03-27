package com.controller;

import basic.result.RestResponse;
import basic.result.Success;
import com.Model.ShirtFrontPieceModel;
import com.service.ShirtFrontPieceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/frontPiece/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫前片")
public class ShirtFrontPieceController {

    @Autowired
    private ShirtFrontPieceService shirtFrontPieceService;

    @ApiOperation("增加前片信息")
    @PostMapping("addFrontPiece")
    public RestResponse addFrontPiece(@RequestBody ShirtFrontPieceModel shirtFrontPieceModel){
        shirtFrontPieceService.addFrontPiece(shirtFrontPieceModel);
        return new Success<>();
    }

    @ApiOperation("删除前片信息")
    @PostMapping("deleteById")
    public RestResponse deleteById(@RequestParam("id") Long id){
        shirtFrontPieceService.deleteById(id);
        return new Success<>();
    }
}
