package com.service.impl;
import java.util.Date;

import basic.result.exception.BaseException;
import com.Enum.FiveSleeveEnum;
import com.Enum.LongSleeveEnum;
import com.Enum.SevenSleeveEnum;
import com.Enum.ShortSleeveEnum;
import com.Model.BackPieceSewModel;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtSleeveBodyPlacketModel;
import com.Model.SleeveBodyPlacketSewModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.google.common.collect.Lists;
import com.service.ShirtSleeveBodyPlacketService;
import com.service.SleeveBodyPlacketSewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShirtSleeveBodyPlacketServiceImpl extends BaseService<ShirtSleeveBodyPlacketModel> implements ShirtSleeveBodyPlacketService {


    @Autowired
    private SleeveBodyPlacketSewService sleeveBodyPlacketSewService;
    @Override
    public void addSleeveBodyPlacket(ShirtSleeveBodyPlacketModel shirtSleeveBodyPlacketModel) {
        insertSelective(shirtSleeveBodyPlacketModel);
        List<SleeveBodyPlacketSewModel> sleeveBodyPlacketSewModels = shirtSleeveBodyPlacketModel.getSleeveBodyPlacketModelList();
        if(CollectionUtils.isEmpty(sleeveBodyPlacketSewModels)){
            throw new BaseException("后片缝制工艺数据不能为空");
        }
        sleeveBodyPlacketSewModels.forEach(sleeveBodyPlacketSewModel -> sleeveBodyPlacketSewModel.setMainId(shirtSleeveBodyPlacketModel.getId()));
        sleeveBodyPlacketSewService.addSleeveBodyPlacketSew(sleeveBodyPlacketSewModels);
    }

    @Override
    public void updateSleeveBodyPlacket(ShirtSleeveBodyPlacketModel shirtSleeveBodyPlacketModel) {
        updateByPrimaryKeySelective(shirtSleeveBodyPlacketModel);
        List<SleeveBodyPlacketSewModel> sleeveBodyPlacketSewModels = shirtSleeveBodyPlacketModel.getSleeveBodyPlacketModelList();
        if(CollectionUtils.isEmpty(sleeveBodyPlacketSewModels)){
            throw new BaseException("袖身袖衩工艺信息不能为空");
        }
        sleeveBodyPlacketSewModels.forEach(sleeveBodyPlacketSewModel -> sleeveBodyPlacketSewModel.setMainId(shirtSleeveBodyPlacketModel.getId()));
        sleeveBodyPlacketSewService.updateSleeveBodyPlacketSew(sleeveBodyPlacketSewModels);

    }

    @Override
    public List<String> getLongSleeveEnumName() {
        List<String> list = new ArrayList<>();
        for (LongSleeveEnum longSleeveEnum : LongSleeveEnum.values()) {
            list.add(longSleeveEnum.getName());
        }
        return list;
    }

    @Override
    public List<String> getSevenEnumName() {
        List<String> list = new ArrayList<>();
        for (SevenSleeveEnum sevenSleeveEnum : SevenSleeveEnum.values()) {
            list.add(sevenSleeveEnum.getName());
        }
        return list;
    }

    @Override
    public List<String> getFiveSleeveEnumName() {
        List<String> list = new ArrayList<>();
        for (FiveSleeveEnum fiveSleeveEnum : FiveSleeveEnum.values()) {
            list.add(fiveSleeveEnum.getName());
        }
        return list;
    }

    @Override
    public List<String> getShortSleeveEnumName() {
        List<String> list = new ArrayList<>();
        for (ShortSleeveEnum shortSleeveEnum : ShortSleeveEnum.values()) {
            list.add(shortSleeveEnum.getName());
        }
        return list;
    }
}
