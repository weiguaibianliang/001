package com.service.impl;

import com.Model.BackPieceSewModel;
import com.Model.FrontPieceSewModel;
import com.assembly.oauth.web.BaseService;
import com.service.BackPieceSewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class BackPieceSewServiceImpl extends BaseService<BackPieceSewModel> implements BackPieceSewService {


    @Override
    public void addBackPieceSew(List<BackPieceSewModel> backPieceSewModels) {
        insertList(backPieceSewModels);
    }

    @Override
    public List<BackPieceSewModel> getListByMainId(Long mainId) {

        if(mainId == null){
            return new ArrayList<>();
        }
        Example example = new Example(BackPieceSewModel.class);
        example.createCriteria()
                .andEqualTo("mainId",mainId);

        return selectByExample(example);
    }
}
