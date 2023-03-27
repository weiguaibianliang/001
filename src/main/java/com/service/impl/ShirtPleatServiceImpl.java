package com.service.impl;

import com.Enum.PleatEnum;
import com.Model.ShirtPleatModel;
import com.Model.ShirtProvinceModel;
import com.assembly.oauth.web.BaseService;
import com.service.ShirtPleatService;
import com.service.ShirtProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShirtPleatServiceImpl extends BaseService<ShirtPleatModel> implements ShirtPleatService {


    @Override
    public List<String> getShortPleatEnumName() {
        List<String> list = new ArrayList<>();
        for (PleatEnum pleatEnum : PleatEnum.values()) {
            list.add(pleatEnum.getName());
        }
        return list;
    }
}
