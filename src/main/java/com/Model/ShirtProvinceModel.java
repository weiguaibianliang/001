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
@Table(name = "shirt_province")
@ApiModel(description = "衬衫省道表")
public class ShirtProvinceModel extends BaseModel {

    private static final long serialVersionUID = 5840922480231318297L;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 省道特征
     */
    private Integer provinceFeatures;


    /**
     * 省道缝制工艺信息
     */
    private String provinceSewInfo;

    /**
     * 前片缝制工艺
     */
    @Transient
    private List<ProvinceSewModel> provinceSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getProvinceSewModelList())) {
            setProvinceSewInfo(JSONObject.toJSONString(getProvinceSewModelList()));
        } else {
            setProvinceSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getProvinceSewInfo())) {
            List<ProvinceSewModel> provinceSewModels = JSONObject.parseArray(getProvinceSewInfo(), ProvinceSewModel.class);
            setProvinceSewModelList(provinceSewModels);
        }else {
            setProvinceSewModelList(new ArrayList<>());
        }

    }

}
