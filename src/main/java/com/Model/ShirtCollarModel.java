package com.Model;

import basic.model.base.BaseModel;
import basic.model.base.FileBaseModel;
import com.DTO.CollarSewDTO;
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
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "shirt_collar")
@ApiModel(description = "衬衫领子表")
public class ShirtCollarModel extends BaseModel {
    private static final long serialVersionUID = 1479295688602205809L;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 领形
     */
    private Integer collarShape;

//    /**
//     * 领面领座
//     */
//    private String collarSurfaceSeat;
//
//    /**
//     * 领角形状
//     */
//    private String collarAngle;
//
//    /**
//     * 领面线
//     */
//    private String collarFacingLine;
//
//    /**
//     * 领面特点
//     */
//    private String collarFeatures;

    /**
     * 领子缝制工艺信息
     */
    private String collarSewInfo;

    /**
     * 领子缝制工艺
     */
    @Transient
    List<CollarSewModel> collarSewDTOList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getCollarSewDTOList())) {
            setCollarSewInfo(JSONObject.toJSONString(getCollarSewDTOList()));
        } else {
            setCollarSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getCollarSewInfo())) {
            List<CollarSewModel> collarSewModels = JSONObject.parseArray(getCollarSewInfo(), CollarSewModel.class);
            setCollarSewDTOList(collarSewModels);
        }else {
            setCollarSewDTOList(new ArrayList<>());
        }

    }


}
