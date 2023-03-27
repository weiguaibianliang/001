package com.service.impl;


import basic.model.base.FileBaseModel;
import basic.model.base.IdModel;
import com.Model.OverShoulderSewModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.service.OverShoulderSewService;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OverShoulderSewServiceImpl extends BaseService<OverShoulderSewModel> implements OverShoulderSewService {


    @Override
    public void addOverShoulderSew(List<OverShoulderSewModel> overShoulderSewModels) {
        insertList(overShoulderSewModels);
    }
}
