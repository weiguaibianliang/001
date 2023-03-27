package com.service.impl;


import com.Model.ProduceOrderModel;
import com.assembly.oauth.web.BaseService;
import com.service.ProduceOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Slf4j
@Service
public class ProduceOrderServiceImpl extends BaseService<ProduceOrderModel> implements ProduceOrderService {

    @Override
    public ProduceOrderModel findByProduceOrderNo(String produceOrderNo) {

        if(StringUtils.isBlank(produceOrderNo)){
            return null;
        }
        Example example = new Example(ProduceOrderModel.class);
        example.createCriteria()
                .andEqualTo("produceOrderNo",produceOrderNo);

        return selectOneByExample(example);
    }
}
