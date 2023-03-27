package com.DTO;

import basic.model.base.FileBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProcessChartDTO {
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
}
