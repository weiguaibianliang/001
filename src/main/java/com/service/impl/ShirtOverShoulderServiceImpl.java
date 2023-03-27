package com.service.impl;
import java.util.Date;

import basic.result.exception.BaseException;
import com.Enum.OverShoulderEnum;
import com.Model.BackPieceSewModel;
import com.Model.OverShoulderSewModel;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtOverShoulderModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.google.common.collect.Lists;
import com.service.OverShoulderSewService;
import com.service.ShirtOverShoulderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShirtOverShoulderServiceImpl extends BaseService<ShirtOverShoulderModel> implements ShirtOverShoulderService {


    @Autowired
    private OverShoulderSewService overShoulderSewService;
    @Override
    public void addOverShoulder(ShirtOverShoulderModel shirtOverShoulderModel) {
        insertSelective(shirtOverShoulderModel);
        List<OverShoulderSewModel> overShoulderSewModels = shirtOverShoulderModel.getOverShoulderSewModelList();
        if(CollectionUtils.isEmpty(overShoulderSewModels)){
            throw new BaseException("后片缝制工艺数据不能为空");
        }
        overShoulderSewModels.forEach(overShoulderSewModel -> overShoulderSewModel.setMainId(shirtOverShoulderModel.getId()));
        overShoulderSewService.addOverShoulderSew(overShoulderSewModels);
    }

    @Override
    public List<String> getOverShoulderEnumName() {
        List<String> list = new ArrayList<>();
        for (OverShoulderEnum overShoulderEnum : OverShoulderEnum.values()) {
            list.add(overShoulderEnum.getName());
        }
        return list;
    }

}
