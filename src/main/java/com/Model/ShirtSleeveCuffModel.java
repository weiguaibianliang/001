package com.Model;

import basic.model.base.BaseModel;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "shirt_sleeve_cuff")
@ApiModel(description = "衬衫袖克夫表")
public class ShirtSleeveCuffModel extends BaseModel {

    private static final long serialVersionUID = 3407668695374161354L;

    /**
     * 袖身mainId
     */
    private Long mainId;

    /**
     * 有无袖口扣 0-有袖口扣 1-无袖口扣
     */
    private Integer withCuffLinks;

    /**
     * 袖扣特征
     */
    private String cuffLinksFeatures;

    /**
     * 袖口类型
     */
    private String cuffType;

    /**
     * 袖克夫缝制工艺信息
     */
    private String cuffSleeveSewInfo;

    /**
     * 袖克夫缝制工艺
     */
    @Transient
    private List<SleeveCuffSewModel> sleeveCuffSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getSleeveCuffSewModelList())) {
            setCuffSleeveSewInfo(JSONObject.toJSONString(getSleeveCuffSewModelList()));
        } else {
            setCuffSleeveSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getCuffSleeveSewInfo())) {
            List<SleeveCuffSewModel> sleeveCuffSewModels = JSONObject.parseArray(getCuffSleeveSewInfo(), SleeveCuffSewModel.class);
            setSleeveCuffSewModelList(sleeveCuffSewModels);
        }else {
            setSleeveCuffSewModelList(new ArrayList<>());
        }

    }

}
