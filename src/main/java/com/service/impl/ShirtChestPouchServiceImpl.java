package com.service.impl;

import com.Enum.ChestPouchEnum;
import com.Model.ShirtChestPouchModel;
import com.assembly.oauth.web.BaseService;
import com.service.ShirtChestPouchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShirtChestPouchServiceImpl extends BaseService<ShirtChestPouchModel> implements ShirtChestPouchService {

    @Override
    public List<String> getChestPouchEnumName() {
        List<String> list = new ArrayList<>();
        for (ChestPouchEnum chestPouchEnum : ChestPouchEnum.values()) {
            list.add(chestPouchEnum.getName());
        }
        return list;
    }
}
