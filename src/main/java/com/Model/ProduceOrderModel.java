package com.Model;

import basic.model.base.BaseModel;
import basic.model.base.FileBaseModel;
import com.alibaba.fastjson.JSONObject;
import com.assembly.oauth.annotation.FuzzySearch;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "shirt_produce_order")
public class ProduceOrderModel extends BaseModel {

    private static final long serialVersionUID = 1893188238431016035L;

    /**
     * 生产订单号
     */
    private String produceOrderNo;

    @FuzzySearch
    private String styleNo;//款号
    @FuzzySearch
    private String styleName;//款式名称
    @FuzzySearch
    private String insideStyleNo;//内部款号
    private String styleParentCategory;//款式父类
    private String styleSubCategory;//款式子类
    private String sizeParentCategoryCode;//尺码父类的code
    private String sizeParentCategoryName;//尺码父类的name
    private String sizeSubCategory;//尺码子类
    private String details;//详情
    private String remark;//备注
    private String unit;//单位
    @FuzzySearch
    private String season;//季节
    private Integer status;//状态  0停用，1启用 默认 1

    private String picture1Info;//款式图片1
    private String picture2Info;//2
    private String picture3Info;//3

//    /**
//     * 款式资料数量
//     */
//    private Integer dataStatus;
//    /**
//     * 工序版本数量
//     */
//    private Integer craftVersionQty;
//    /**
//     * BOM版本数量
//     */
//    private Integer bomVersionQty;
//    /**
//     * 纸样版本数量
//     */
//    private Integer patternVersionQty;
//    /**
//     * 报价记录数量
//     */
//    private Integer quoteQty;

    @Transient
    private List<FileBaseModel> picture1List = new ArrayList<>();//款式图片1
    @Transient
    private List<FileBaseModel> picture2List = new ArrayList<>();//款式图片1
    @Transient
    private List<FileBaseModel> picture3List = new ArrayList<>();//款式图片1
    @Transient
    private List<String> sizeSubCategoryList = new ArrayList<>();//尺码组详细



    public void zip() {
        if (CollectionUtils.isNotEmpty(getPicture1List())) {
            setPicture1Info(JSONObject.toJSONString(getPicture1List()));
        }
        if (CollectionUtils.isNotEmpty(getPicture2List())) {
            setPicture2Info(JSONObject.toJSONString(getPicture2List()));
        }
        if (CollectionUtils.isNotEmpty(getPicture3List())) {
            setPicture3Info(JSONObject.toJSONString(getPicture3List()));
        }
        if (CollectionUtils.isNotEmpty(getSizeSubCategoryList())) {
            setSizeSubCategory(JSONObject.toJSONString(getSizeSubCategoryList()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getPicture1Info())) {
            List<FileBaseModel> picture1List = JSONObject.parseArray(getPicture1Info(), FileBaseModel.class);
            setPicture1List(picture1List);
        }
        if (StringUtils.isNotBlank(getPicture2Info())) {
            List<FileBaseModel> picture2List = JSONObject.parseArray(getPicture2Info(), FileBaseModel.class);
            setPicture2List(picture2List);
        }
        if (StringUtils.isNotBlank(getPicture3Info())) {
            List<FileBaseModel> picture3List = JSONObject.parseArray(getPicture3Info(), FileBaseModel.class);
            setPicture3List(picture3List);
        }
        if (StringUtils.isNotBlank(getSizeSubCategory())) {
            List<String> sizeSubCategoryList = JSONObject.parseArray(getSizeSubCategory(), String.class);
            setSizeSubCategoryList(sizeSubCategoryList);
        }

    }

}
