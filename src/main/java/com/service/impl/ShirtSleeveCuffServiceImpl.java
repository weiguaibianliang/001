package com.service.impl;
import java.util.Date;

import basic.result.exception.BaseException;
import com.Model.BackPieceSewModel;
import com.Model.ShirtBackPieceModel;
import com.Model.ShirtSleeveCuffModel;
import com.Model.SleeveCuffSewModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.web.BaseService;
import com.google.common.collect.Lists;
import com.mapper.ShirtSleeveCuffMapper;
import com.service.ShirtSleeveCuffService;
import com.service.SleeveCuffSewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ShirtSleeveCuffServiceImpl extends BaseService<ShirtSleeveCuffModel> implements ShirtSleeveCuffService {

    @Autowired
    private SleeveCuffSewService sleeveCuffSewService;


    @Override
    public void addSleeveCuff(ShirtSleeveCuffModel shirtSleeveCuffModel) {
        insertSelective(shirtSleeveCuffModel);
        List<SleeveCuffSewModel> sleeveCuffSewModels = shirtSleeveCuffModel.getSleeveCuffSewModelList();
        if(CollectionUtils.isEmpty(sleeveCuffSewModels)){
            throw new BaseException("后片缝制工艺数据不能为空");
        }
        sleeveCuffSewModels.forEach(sleeveCuffSewModel-> sleeveCuffSewModel.setMainId(shirtSleeveCuffModel.getId()));
        sleeveCuffSewService.addSleeveCuff(sleeveCuffSewModels);
    }

    @Override
    public void test() {
        ShirtSleeveCuffModel shirtSleeveCuffModel = new ShirtSleeveCuffModel();

        List<SleeveCuffSewModel> sleeveCuffSewModels = new ArrayList<>();
        SleeveCuffSewModel model1 = new SleeveCuffSewModel();
        model1.setPartName("袖克夫");
        model1.setProcessCode("");
        model1.setPictureInfo("");
        model1.setPictureList(Lists.newArrayList());
        model1.setProcessDescription("落样扣烫袖英面/里一边(24cm)*4");
        model1.setEquipmentType("小烫");
        model1.setProcessLevel(2);
        model1.setMaterialLevel(3);
        model1.setStandardTime(new BigDecimal("49.2"));
        model1.setOffLineProcess(1);
        SleeveCuffSewModel model2 = new SleeveCuffSewModel();
        model2.setPartName("袖克夫");
        model2.setProcessCode("");
        model2.setPictureInfo("");
        model2.setPictureList(Lists.newArrayList());
        model2.setProcessDescription("刀车切修面里袖英一边(24cm)*4");
        model2.setEquipmentType("刀车");
        model2.setProcessLevel(2);
        model2.setMaterialLevel(3);
        model2.setStandardTime(new BigDecimal("31.2"));
        model2.setOffLineProcess(1);
        SleeveCuffSewModel model3 = new SleeveCuffSewModel();
        model3.setPartName("袖克夫");
        model3.setProcessCode("");
        model3.setPictureInfo("");
        model3.setPictureList(Lists.newArrayList());
        model3.setProcessDescription("模板缉转袖英(方角/5+3+18+3+5cm)*2(一个模板上完成2个)");
        model3.setEquipmentType("电脑平车");
        model3.setProcessLevel(2);
        model3.setMaterialLevel(3);
        model3.setStandardTime(new BigDecimal("49.8"));
        model3.setOffLineProcess(1);
        SleeveCuffSewModel model4 = new SleeveCuffSewModel();
        model4.setPartName("袖克夫");
        model4.setProcessCode("");
        model4.setPictureInfo("");
        model4.setPictureList(Lists.newArrayList());
        model4.setProcessDescription("刀车切修袖英(5+3+18+3+5cm)*2");
        model4.setEquipmentType("刀车");
        model4.setProcessLevel(2);
        model4.setMaterialLevel(3);
        model4.setStandardTime(new BigDecimal("28.8"));
        model4.setOffLineProcess(1);
        SleeveCuffSewModel model5 = new SleeveCuffSewModel();
        model5.setPartName("袖克夫");
        model5.setProcessCode("");
        model5.setPictureInfo("");
        model5.setPictureList(Lists.newArrayList());
        model5.setProcessDescription("手工修剪袖英角位连翻角4个*2");
        model5.setEquipmentType("手工");
        model5.setProcessLevel(3);
        model5.setMaterialLevel(3);
        model5.setStandardTime(new BigDecimal("54"));
        model5.setOffLineProcess(0);
        SleeveCuffSewModel model6 = new SleeveCuffSewModel();
        model6.setPartName("袖克夫");
        model6.setProcessCode("");
        model6.setPictureInfo("");
        model6.setPictureList(Lists.newArrayList());
        model6.setProcessDescription("烫定袖英五边(未间线/5+3+18+3+5cm)*2");
        model6.setEquipmentType("小烫");
        model6.setProcessLevel(2);
        model6.setMaterialLevel(3);
        model6.setStandardTime(new BigDecimal("37.8"));
        model6.setOffLineProcess(1);
        SleeveCuffSewModel model7 = new SleeveCuffSewModel();
        model7.setPartName("袖克夫");
        model7.setProcessCode("");
        model7.setPictureInfo("");
        model7.setPictureList(Lists.newArrayList());
        model7.setProcessDescription("夹上袖英连折头连点位(有衩/24cm)*2（上袖英）");
        model7.setEquipmentType("电脑平车");
        model7.setProcessLevel(0);
        model7.setMaterialLevel(3);
        model7.setStandardTime(new BigDecimal("108"));
        model7.setOffLineProcess(1);
        SleeveCuffSewModel model8 = new SleeveCuffSewModel();
        model8.setPartName("袖克夫");
        model8.setProcessCode("");
        model8.setPictureInfo("");
        model8.setPictureList(Lists.newArrayList());
        model8.setProcessDescription("烫平袖英(24cm)*2");
        model8.setEquipmentType("小烫");
        model8.setProcessLevel(3);
        model8.setMaterialLevel(3);
        model8.setStandardTime(new BigDecimal("16.2"));
        model8.setOffLineProcess(1);
        sleeveCuffSewModels.add(model1);
        sleeveCuffSewModels.add(model2);
        sleeveCuffSewModels.add(model3);
        sleeveCuffSewModels.add(model4);
        sleeveCuffSewModels.add(model5);
        sleeveCuffSewModels.add(model6);
        sleeveCuffSewModels.add(model7);
        sleeveCuffSewModels.add(model8);
        shirtSleeveCuffModel.setWithCuffLinks(0);
        shirtSleeveCuffModel.setCuffLinksFeatures("横排两粒扣");
        shirtSleeveCuffModel.setCuffType("圆角");
        shirtSleeveCuffModel.setCuffSleeveSewInfo(JSONObject.toJSONString(sleeveCuffSewModels));
        shirtSleeveCuffModel.setSleeveCuffSewModelList(sleeveCuffSewModels);
        this.addSleeveCuff(shirtSleeveCuffModel);
    }
}
