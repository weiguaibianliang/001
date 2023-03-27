package com.Model;


import basic.model.base.BaseModel;
import com.alibaba.fastjson.JSONObject;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;
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
@Table(name = "shirt_back_piece")
@ApiModel(description = "衬衫后片表")
public class ShirtBackPieceModel extends BaseModel {

    private static final long serialVersionUID = -1463153759183836570L;

//    /**
//     * 省道
//     */
//    private String provincialRoad;
    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 后背特点
     */
    private Integer backPleatShapeCharacter;

//    /**
//     * 后背褶形类型 0-无褶 1-中褶 2-双侧褶
//     */
//    private Integer backPleatShapeType;
//
//    /**
//     * 下摆类型 0-平下摆 1-曲下摆
//     */
//    private Integer hemType;
//
//    /**
//     * 下摆是否开衩 0-开衩 1-不开衩
//     */
//    private Integer isSlit;

    /**
     * 后片缝制工艺信息
     */
    private String backPieceSewInfo;


    /**
     * 后片缝制工艺
     */
    @Transient
    List<BackPieceSewModel> backPieceSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getBackPieceSewModelList())) {
            setBackPieceSewInfo(JSONObject.toJSONString(getBackPieceSewModelList()));
        } else {
            setBackPieceSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getBackPieceSewInfo())) {
            List<BackPieceSewModel> backPieceSewModels = JSONObject.parseArray(getBackPieceSewInfo(), BackPieceSewModel.class);
            setBackPieceSewModelList(backPieceSewModels);
        }else {
            setBackPieceSewModelList(new ArrayList<>());
        }

    }

}
