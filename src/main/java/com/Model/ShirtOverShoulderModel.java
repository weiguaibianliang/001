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
@Table(name = "shirt_over_shoulder")
@ApiModel(description = "衬衫过肩表")
public class ShirtOverShoulderModel extends BaseModel {

    private static final long serialVersionUID = 815654610325270293L;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 过肩类型  0-正肩 1-落肩
     */
    private Integer overShoulderType;

//    /**
//     * 过肩特点
//     */
//    private Integer overShoulderName;

//    /**
//     * 是否明线暗线 0-明线 1-暗线
//     */
//    private Integer brightOrDarkLine;
//
//    /**
//     * 有无活褶 0-有活褶 1-无活褶
//     */
//    private Integer livePleats;

    /**
     * 过肩缝制工艺信息
     */
    private String overShoulderSewInfo;

    @Transient
    private List<OverShoulderSewModel> overShoulderSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getOverShoulderSewModelList())) {
            setOverShoulderSewInfo(JSONObject.toJSONString(getOverShoulderSewModelList()));
        } else {
            setOverShoulderSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getOverShoulderSewInfo())) {
            List<OverShoulderSewModel> overShoulderSewModels = JSONObject.parseArray(getOverShoulderSewInfo(), OverShoulderSewModel.class);
            setOverShoulderSewModelList(overShoulderSewModels);
        }else {
            setOverShoulderSewModelList(new ArrayList<>());
        }

    }

}
