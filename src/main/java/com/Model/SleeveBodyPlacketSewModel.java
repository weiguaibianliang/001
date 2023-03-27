package com.Model;

import basic.model.base.FileBaseModel;
import basic.model.base.IdModel;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "sleeve_body_placket_sew")
@ApiModel(description = "衬衫门襟工艺表")
public class SleeveBodyPlacketSewModel extends IdModel {

    private static final long serialVersionUID = 269528629650483278L;

    /**
     * 主表id
     */
    private Long mainId;

    /**
     * 部件要求 0-标准 ，1- 特殊要求1 ，2-特殊要求2 ，3-特殊要求3
     */
    private Integer componentRequireType;

    /**
     * 部件名称
     */
    private String partName;

    /**
     * 工序编码
     */
    private String processCode;

    /**
     * 工序图片
     */
    private String pictureInfo;


    @Transient
    private List<FileBaseModel> pictureList = new ArrayList<>();

    /**
     * 工序描述
     */
    private String processDescription;

    /**
     * 设备类型
     */
    private String equipmentType;

    /**
     * 工序等级 0-A 1-B 2-C 3-D
     */
    private Integer processLevel;

    /**
     * 面料等级 0-A 1-B 2-C 3-D
     */
    private Integer materialLevel;

    /**
     * 标准工时
     */
    private BigDecimal standardTime;

    /**
     * 是否线外 0-线外 1-不是线外
     */
    private Integer offLineProcess;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getPictureList())) {
            setPictureInfo(JSONObject.toJSONString(getPictureList()));
        } else {
            setPictureInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getPictureInfo())) {
            List<FileBaseModel> picture1List = JSONObject.parseArray(getPictureInfo(), FileBaseModel.class);
            setPictureList(picture1List);
        }else {
            setPictureList(new ArrayList<>());
        }

    }
}
