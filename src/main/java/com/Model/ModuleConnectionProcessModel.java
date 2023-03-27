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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "shirt_module_connection_process")
@ApiModel(description = "衬衫模块连接工序表")
public class ModuleConnectionProcessModel extends IdModel {

    private static final long serialVersionUID = -3338884013466190519L;

    /**
     * 特别步骤 检验/锁钮眼/点纽/钉纽扣/修剪线头/整烫/成品检验/放吊牌装袋
     */

    /**
     * 模块类型 0-基本模块 1-领 2-袖身袖衩 3-胸袋 4-袖克夫 5-最后收尾模块
     */
    private Integer moduleType;

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
