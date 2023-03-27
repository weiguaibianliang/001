package com.service.impl;

import com.Model.SleeveCuffSewModel;
import com.assembly.oauth.web.BaseService;
import com.service.SleeveCuffSewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SleeveCuffSewServiceImpl extends BaseService<SleeveCuffSewModel> implements SleeveCuffSewService {


    @Override
    public void addSleeveCuff(List<SleeveCuffSewModel> sleeveCuffSewModels) {
        insertList(sleeveCuffSewModels);
    }
}
