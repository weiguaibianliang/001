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
@Table(name = "shirt_chest_pouch")
@ApiModel(description = "衬衫胸袋表")
public class ShirtChestPouchModel extends BaseModel {

    private static final long serialVersionUID = 2806334192675176391L;

//    /**
//     * 口袋数量
//     */
//    private Integer pocketsNumber;

    /**
     * 性别 0-男 1-女
     */
    private Integer sexName;

    /**
     * 尺码信息
     */
    private String sizeInfo;

    /**
     * 口袋类型
     */
    private Integer pocketType;

//    /**
//     * 口袋中明袋的形状
//     */
//    private String brightPocketShape;
//
//    /**
//     * 口袋对条格 0-是 1-否
//     */
//    private Integer pocketToBarGrid;
//
//    /**
//     * 线迹要求
//     */
//    private String stitchRequirement;
//
//    /**
//     * 装饰 0-有装饰 1-无装饰
//     */
//    private Integer isDecoration;

    /**
     * 口袋缝制工艺信息
     */
    private String chestPouchSewInfo;

    /**
     * 口袋缝制工艺
     */
    @Transient
    private List<ChestPouchSewModel> chestPouchSewModelList;

    public void zip() {
        if (CollectionUtils.isNotEmpty(getChestPouchSewModelList())) {
            setChestPouchSewInfo(JSONObject.toJSONString(getChestPouchSewModelList()));
        } else {
            setChestPouchSewInfo(JSONObject.toJSONString(new ArrayList<>()));
        }
    }

    public void unzip() {
        if (StringUtils.isNotBlank(getChestPouchSewInfo())) {
            List<ChestPouchSewModel> chestPouchSewModels = JSONObject.parseArray(getChestPouchSewInfo(), ChestPouchSewModel.class);
            setChestPouchSewModelList(chestPouchSewModels);
        }else {
            setChestPouchSewModelList(new ArrayList<>());
        }

    }
}
