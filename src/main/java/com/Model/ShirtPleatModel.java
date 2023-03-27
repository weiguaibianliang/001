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
@Table(name = "shirt_pleat")
@ApiModel(description = "衬衫褶裥表")
public class ShirtPleatModel extends BaseModel {

    private static final long serialVersionUID = 3016591299729224038L;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 褶裥特征
     */
    private Integer pleatFeatures;


    /**
     * 褶裥缝制工艺信息
     */
    private String pleatSewInfo;

    /**
     * 前片缝制工艺
     */
    @Transient
    private List<PleatSewModel> pleatSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getPleatSewModelList())) {
            setPleatSewInfo(JSONObject.toJSONString(getPleatSewModelList()));
        } else {
            setPleatSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getPleatSewInfo())) {
            List<PleatSewModel> pleatSewModels = JSONObject.parseArray(getPleatSewInfo(), PleatSewModel.class);
            setPleatSewModelList(pleatSewModels);
        }else {
            setPleatSewModelList(new ArrayList<>());
        }

    }
}
