package com.service.impl;

import basic.result.exception.BaseException;
import com.Enum.BackPieceEnum;
import com.Enum.ProvinceEnum;
import com.Model.BackPieceSewModel;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtProvinceModel;
import com.assembly.oauth.web.BaseService;
import com.service.BackPieceSewService;
import com.service.ShirtBackPieceService;
import com.service.ShirtProvinceService;
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
public class ShirtProvinceServiceImpl extends BaseService<ShirtProvinceModel> implements ShirtProvinceService {


    @Override
    public List<String> getProvinceEnumName() {
        List<String> list = new ArrayList<>();
        for (ProvinceEnum provinceEnum : ProvinceEnum.values()) {
            list.add(provinceEnum.getName());
        }
        return list;
    }
}
