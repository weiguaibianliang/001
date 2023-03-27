package com.service.impl;


import basic.model.base.FileBaseModel;
import basic.model.base.IdModel;
import com.Model.LapelSewModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.service.LapelSewService;
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
public class LapelSewServiceImpl extends BaseService<LapelSewModel> implements LapelSewService {


    @Override
    public void addLapelSew(List<LapelSewModel> lapelSewModels) {
        insertList(lapelSewModels);
    }
}
