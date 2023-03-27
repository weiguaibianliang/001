package com.service.impl;
import java.util.Date;

import basic.result.exception.BaseException;
import com.Enum.LapelEnum;
import com.Model.FrontPieceSewModel;
import com.Model.LapelSewModel;
import com.Model.ShirtFrontPieceModel;
import com.Model.ShirtLapelModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.google.common.collect.Lists;
import com.service.LapelSewService;
import com.service.ShirtLapelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShirtLapelServiceImpl extends BaseService<ShirtLapelModel> implements ShirtLapelService {

    @Autowired
    private LapelSewService lapelSewService;
    @Override
    public void addLapel(ShirtLapelModel shirtLapelModel) {
        insertSelective(shirtLapelModel);
        List<LapelSewModel> lapelSewModels = shirtLapelModel.getLapelSewModelList();
        if(CollectionUtils.isEmpty(lapelSewModels)){
            throw new BaseException("门襟缝制工艺数据不能为空");
        }
        lapelSewModels.forEach(lapelSewModel -> lapelSewModel.setMainId(shirtLapelModel.getId()));
        lapelSewService.addLapelSew(lapelSewModels);
    }

    @Override
    public ShirtLapelModel findByRemark(Integer sexName, String sizeInfo, Integer lapelCharacter) {

        if(StringUtils.isBlank(sizeInfo)){
            return null;
        }
        Example example = new Example(ShirtLapelModel.class);
        example.createCriteria()
                .andEqualTo("sexName",sexName)
                .andEqualTo("sizeInfo",sizeInfo)
                .andEqualTo("lapelCharacter",lapelCharacter);

        return selectOneByExample(example);
    }

    @Override
    public List<String> getLapelEnumName() {
        List<String> list = new ArrayList<>();
        for (LapelEnum lapelEnum  : LapelEnum.values()) {
            list.add(lapelEnum.getName());
        }
        return list;
    }
}
