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
@Table(name = "shirt_front_piece")
@ApiModel(description = "衬衫前片表")
public class ShirtFrontPieceModel extends BaseModel {

    private static final long serialVersionUID = -1428979473086675503L;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 前片特征
     */
    private Integer frontPieceFeatures;

//    /**
//     * 省道
//     */
//    private String provincialRoad;

    /**
     * 前片缝制工艺信息
     */
    private String frontPieceSewInfo;

    /**
     * 前片缝制工艺
     */
    @Transient
    private List<FrontPieceSewModel> frontPieceSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getFrontPieceSewModelList())) {
            setFrontPieceSewInfo(JSONObject.toJSONString(getFrontPieceSewModelList()));
        } else {
            setFrontPieceSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getFrontPieceSewInfo())) {
            List<FrontPieceSewModel> frontPieceSewModels = JSONObject.parseArray(getFrontPieceSewInfo(), FrontPieceSewModel.class);
            setFrontPieceSewModelList(frontPieceSewModels);
        }else {
            setFrontPieceSewModelList(new ArrayList<>());
        }

    }



}
