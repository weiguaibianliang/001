package com.service.impl;
import java.util.Date;

import basic.result.exception.BaseException;
import com.Enum.BackPieceEnum;
import com.Model.BackPieceSewModel;
import com.Model.FrontPieceSewModel;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtFrontPieceModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.google.common.collect.Lists;
import com.service.BackPieceSewService;
import com.service.ShirtBackPieceService;
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
public class ShirtBackPieceServiceImpl extends BaseService<ShirtBackPieceModel> implements ShirtBackPieceService {

    @Autowired
    private BackPieceSewService backPieceSewService;

    @Override
    public void addBackPiece(ShirtBackPieceModel shirtBackPieceModel) {
        insertSelective(shirtBackPieceModel);
        List<BackPieceSewModel> backPieceSewModels = shirtBackPieceModel.getBackPieceSewModelList();
        if(CollectionUtils.isEmpty(backPieceSewModels)){
            throw new BaseException("后片缝制工艺数据不能为空");
        }
        backPieceSewModels.forEach(backPieceSewModel -> backPieceSewModel.setMainId(shirtBackPieceModel.getId()));
        backPieceSewService.addBackPieceSew(backPieceSewModels);

    }

    @Override
    public void updateById(Long id) {
        ShirtBackPieceModel model = selectByPrimaryKey(id);
        List<BackPieceSewModel> backPieceSewModels = backPieceSewService.getListByMainId(id);
        if(CollectionUtils.isEmpty(backPieceSewModels)){
            model.setBackPieceSewModelList(new ArrayList<>());
        }
        model.setBackPieceSewModelList(backPieceSewModels);
        updateByPrimaryKeySelective(model);
    }

    @Override
    public ShirtBackPieceModel findByRemark(Integer sexName, String sizeInfo, Integer backPleatShapeCharacter) {

        if(StringUtils.isBlank(sizeInfo)){
            return null;
        }
        Example example = new Example(ShirtBackPieceModel.class);
        example.createCriteria()
                .andEqualTo("sexName",sexName)
                .andEqualTo("sizeInfo",sizeInfo)
                .andEqualTo("backPleatShapeCharacter",backPleatShapeCharacter);

        return selectOneByExample(example);
    }

    @Override
    public List<String> getBackPieceEnumName() {
        List<String> list = new ArrayList<>();
        for (BackPieceEnum backPieceEnum : BackPieceEnum.values()) {
            list.add(backPieceEnum.getName());
        }
        return list;
    }
}
