package com.service.impl;
import java.math.BigDecimal;

import com.Enum.FrontPieceEnum;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import basic.result.exception.BaseException;
import com.Model.FrontPieceSewModel;
import com.Model.ShirtFrontPieceModel;
import com.assembly.oauth.web.BaseService;
import com.mapper.ShirtFrontPieceMapper;
import com.service.FrontPieceSewService;
import com.service.ShirtFrontPieceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShirtFrontPieceServiceImpl extends BaseService<ShirtFrontPieceModel> implements ShirtFrontPieceService {


    @Autowired
    private FrontPieceSewService frontPieceSewService;

    @Autowired
    private ShirtFrontPieceMapper shirtFrontPieceMapper;

    @Override
    public void addFrontPiece(ShirtFrontPieceModel shirtFrontPieceModel) {
        insertSelective(shirtFrontPieceModel);
        List<FrontPieceSewModel> shirtFrontPieceModels = shirtFrontPieceModel.getFrontPieceSewModelList();
        if(CollectionUtils.isEmpty(shirtFrontPieceModels)){
            throw new BaseException("前片缝制工艺数据不能为空");
        }
        shirtFrontPieceModels.forEach(frontPieceSewModel -> frontPieceSewModel.setMainId(shirtFrontPieceModel.getId()));
        frontPieceSewService.addFrontPieceSew(shirtFrontPieceModels);
    }



    @Override
    public void deleteById(Long id) {
        deleteByPrimaryKey(id);
        frontPieceSewService.deleteByMainId(id);
    }

    @Override
    public ShirtFrontPieceModel findByRemark(Integer sexName, String sizeInfo, Integer frontPieceFeatures) {
        if(StringUtils.isBlank(sizeInfo)){
            return null;
        }
        Example example = new Example(ShirtFrontPieceModel.class);
        example.createCriteria()
                .andEqualTo("sexName",sexName)
                .andEqualTo("sizeInfo",sizeInfo)
                .andEqualTo("frontPieceFeatures",frontPieceFeatures);
        return selectOneByExample(example);
    }

    @Override
    public List<String> getFrontPieceEnumName() {
        List<String> list = new ArrayList<>();
        for (FrontPieceEnum frontPieceEnum : FrontPieceEnum.values()) {
            list.add(frontPieceEnum.getName());
        }
        return list;
    }
}
