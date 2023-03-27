package com.service.impl;

import basic.model.base.FileBaseModel;
import basic.model.base.IdModel;
import com.Model.FrontPieceSewModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.service.FrontPieceSewService;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FrontPieceSewServiceImpl extends BaseService<FrontPieceSewModel> implements FrontPieceSewService {


    @Override
    public void addFrontPieceSew(List<FrontPieceSewModel> shirtFrontPieceModels) {
        insertList(shirtFrontPieceModels);
    }

    @Override
    public void deleteByMainId(Long mainId) {
        if(mainId == null){
            return;
        }
        Example example = new Example(FrontPieceSewModel.class);
        example.createCriteria()
                .andEqualTo("mainId",mainId);
        deleteByExample(example);
    }

}
