package com.service.impl;

import com.Model.SleeveBodyPlacketSewModel;
import com.assembly.oauth.web.BaseService;
import com.mapper.SleeveBodyPlacketSewMapper;
import com.service.SleeveBodyPlacketSewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SleeveBodyPlacketSewServiceImpl extends BaseService<SleeveBodyPlacketSewModel> implements SleeveBodyPlacketSewService {

    @Autowired
    private SleeveBodyPlacketSewMapper sleeveBodyPlacketSewMapper;


    @Override
    public void addSleeveBodyPlacketSew(List<SleeveBodyPlacketSewModel> sleeveBodyPlacketSewModels) {
        insertList(sleeveBodyPlacketSewModels);
    }

    @Override
    //批量处理缝制工艺数据
    public void updateSleeveBodyPlacketSew(List<SleeveBodyPlacketSewModel> sleeveBodyPlacketSewModels) {
        if(CollectionUtils.isEmpty(sleeveBodyPlacketSewModels)){
            return;
        }
        List<SleeveBodyPlacketSewModel> addList = new ArrayList<>();
        List<SleeveBodyPlacketSewModel> editList = new ArrayList<>();
        sleeveBodyPlacketSewModels.forEach(sleeveBodyPlacketSewModel -> {
                if(sleeveBodyPlacketSewModel.getId() == null){
                    addList.add(sleeveBodyPlacketSewModel);
                }else {
                    updateByPrimaryKeySelective(sleeveBodyPlacketSewModel);
                    editList.add(sleeveBodyPlacketSewModel);
                }
        });
        //删除不是需要批量更新里面id的List
        List<Long> idList = editList.stream().map(SleeveBodyPlacketSewModel::getId).collect(Collectors.toList());
        deleteNotInIdList(idList);
        insertList(addList);
    }

    private void deleteNotInIdList(List<Long> idList) {

        Example example = new Example(SleeveBodyPlacketSewModel.class);
        if (CollectionUtils.isNotEmpty(idList)) {
            example.createCriteria()
                    .andNotIn("id", idList);
        }
        deleteByExample(example);
    }

    @Override
    public void delete(List<SleeveBodyPlacketSewModel> list){
        for (SleeveBodyPlacketSewModel model : list) {
            deleteByPrimaryKey(model.getId());
        }
    }

    @Override
    public List<SleeveBodyPlacketSewModel> getListByMainId(Long mainId) {
        if(mainId == null){
            return new ArrayList<>();
        }
        Example example = new Example(SleeveBodyPlacketSewModel.class);
        example.createCriteria()
                .andEqualTo("mainId",mainId);
        return selectByExample(example);
    }

    @Override
    public List<SleeveBodyPlacketSewModel> getListByMainIdList(List<Long> mainIdList) {
        if(CollectionUtils.isEmpty(mainIdList)){
            return new ArrayList<>();
        }
        Example example = new Example(SleeveBodyPlacketSewModel.class);
        example.createCriteria()
                .andIn("mainId",mainIdList);
        return selectByExample(example);
    }
}
