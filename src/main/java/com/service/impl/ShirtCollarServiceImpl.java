package com.service.impl;
import java.util.Date;

import basic.result.exception.BaseException;
import com.Enum.CollarEnum;
import com.Model.BackPieceSewModel;
import com.Model.CollarSewModel;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtCollarModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.google.common.collect.Lists;
import com.service.CollarSewService;
import com.service.ShirtCollarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShirtCollarServiceImpl extends BaseService<ShirtCollarModel> implements ShirtCollarService {


    @Autowired
    private CollarSewService collarSewService;
    @Override
    public void addCollar(ShirtCollarModel shirtCollarModel) {
        insertSelective(shirtCollarModel);
        List<CollarSewModel> collarSewModels = shirtCollarModel.getCollarSewDTOList();
        if(CollectionUtils.isEmpty(collarSewModels)){
            throw new BaseException("领子缝制工艺数据不能为空");
        }
        collarSewModels.forEach(collarSewModel -> collarSewModel.setMainId(shirtCollarModel.getId()));
        collarSewService.addCollarSew(collarSewModels);
    }

    @Override
    public List<String> getCollEnumName() {
        List<String> list = new ArrayList<>();
        for (CollarEnum collarEnum : CollarEnum.values()) {
            list.add(collarEnum.getName());
        }
        return list;
    }
}
