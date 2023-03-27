package com.Model;

import basic.model.base.BaseModel;
import basic.model.base.FileBaseModel;
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
@Table(name = "shirt_lapel")
@ApiModel(description = "衬衫门襟表")
public class ShirtLapelModel extends BaseModel {

    private static final long serialVersionUID = -2046867796837440662L;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 门襟分类
     */
    private Integer lapelCharacter;

//    /**
//     * 门襟特征
//     */
//    private String lapelCharacter;

    /**
     * 门襟钉扣个数
     */
    private Integer placketButton;

    /**
     * 门襟缝制工艺信息
     */
    private String lapelSewInfo;

    /**
     * 门襟缝制工艺
     */
    @Transient
    private List<LapelSewModel> lapelSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getLapelSewModelList())) {
            setLapelSewInfo(JSONObject.toJSONString(getLapelSewModelList()));
        } else {
            setLapelSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getLapelSewInfo())) {
            List<LapelSewModel> lapelSewModels = JSONObject.parseArray(getLapelSewInfo(), LapelSewModel.class);
            setLapelSewModelList(lapelSewModels);
        }else {
            setLapelSewModelList(new ArrayList<>());
        }

    }

}
