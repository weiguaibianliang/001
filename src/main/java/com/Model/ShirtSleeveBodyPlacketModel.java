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
@Table(name = "shirt_body_sleeve")
@ApiModel(description = "衬衫袖身袖衩表")
public class ShirtSleeveBodyPlacketModel extends BaseModel {

    private static final long serialVersionUID = 3407668695374161354L;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 袖类型
     */
    private Integer sleeveBodyType;

//    /**
//     * 袖衩（有无开衩）0-有开衩 1-无开衩
//     */
//    private Integer withSlit;
//
//    /**
//     * 开衩形式
//     */
//    private String slitForm;

    /**
     * 袖衩扣个数
     */
    private Integer withCuffLink;

//    /**
//     * 线迹要求
//     */
//    private String stitchRequirement;

    /**
     * 袖身袖衩缝制工艺信息
     */
    private String sleeveBodyPlacketSewInfo;

    /**
     * 袖身袖衩缝制工艺
     */
    @Transient
    private List<SleeveBodyPlacketSewModel> sleeveBodyPlacketModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getSleeveBodyPlacketModelList())) {
            setSleeveBodyPlacketSewInfo(JSONObject.toJSONString(getSleeveBodyPlacketModelList()));
        } else {
            setSleeveBodyPlacketSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getSleeveBodyPlacketSewInfo())) {
            List<SleeveBodyPlacketSewModel> sleeveBodyPlacketSewModels = JSONObject.parseArray(getSleeveBodyPlacketSewInfo(), SleeveBodyPlacketSewModel.class);
            setSleeveBodyPlacketModelList(sleeveBodyPlacketSewModels);
        }else {
            setSleeveBodyPlacketModelList(new ArrayList<>());
        }

    }

}
