package com.service.impl;

import basic.result.page.PageQuery;
import com.DTO.PartsLibraryDTO;
import com.DTO.ProcessChartDTO;
import com.Enum.*;
import com.Model.*;
import com.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProcessChartServiceImpl implements ProcessChartService {

    private static final Character[] CN_NUMERIC = {'一', '二', '三', '四', '五',
            '六', '七', '八', '九', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖',
            '十', '百', '千', '拾', '佰', '仟', '万', '亿', '○', 'Ｏ', '零', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '两'};

    @Autowired
    private ShirtFrontPieceService shirtFrontPieceService;

    @Autowired
    private ShirtBackPieceService shirtBackPieceService;

    @Autowired
    private ShirtLapelService shirtLapelService;

    @Autowired
    private ShirtSleeveCuffService shirtSleeveCuffService;

    @Autowired
    private ShirtCollarService shirtCollarService;

    @Autowired
    private ShirtOverShoulderService shirtOverShoulderService;

    @Autowired
    private ShirtSleeveBodyPlacketService shirtSleeveBodyPlacketService;

    @Autowired
    private ShirtChestPouchService shirtChestPouchService;

    @Autowired
    private ProduceOrderService produceOrderService;

    @Autowired
    private ShirtPleatService shirtPleatService;

    @Autowired
    private ShirtProvinceService shirtProvinceService;


    /**
     * 生产订单信息：
     * 款式描述信息
     * 款式尺寸详细
     */
    @Override
    public List<ProcessChartDTO> getBasicModuleProcess(String produceOrderNo) {
        //获取生产订单信息
        ProduceOrderModel produceOrderModel = produceOrderService.findByProduceOrderNo(produceOrderNo);
        if (produceOrderModel == null) {
            return new ArrayList<>();
        }
        //性别
        Integer sexName = 0;
        //尺码信息
        String sizeInfo = "170/88A";
        //前片特征
        Integer frontPieceFeatures = 0;
        //后片特征
        Integer backPleatShapeCharacter = 0;
        //门襟特征
        Integer lapelCharacter = 0;
        //如果生产订单信息不为空，那么可以得到基本款式模块的工序
        //基本模块工序：前片（啥都没）、后片（啥都没）、直下摆无开衩、门襟（明门襟）
        List<ProcessChartDTO> list = new ArrayList<>();
        ShirtFrontPieceModel shirtFrontPieceModel = shirtFrontPieceService.findByRemark(sexName, sizeInfo, frontPieceFeatures);
        ShirtBackPieceModel shirtBackPieceModel = shirtBackPieceService.findByRemark(sexName, sizeInfo, backPleatShapeCharacter);
        ShirtLapelModel shirtLapelModel = shirtLapelService.findByRemark(sexName, sizeInfo, lapelCharacter);

        shirtFrontPieceModel.getFrontPieceSewModelList().forEach(frontPieceSewModel -> {
            ProcessChartDTO processChartDTO = new ProcessChartDTO();
            BeanUtils.copyProperties(frontPieceSewModel, processChartDTO);
            list.add(processChartDTO);
        });

        shirtLapelModel.getLapelSewModelList().forEach(lapelSewModel -> {
            ProcessChartDTO processChartDTO = new ProcessChartDTO();
            BeanUtils.copyProperties(lapelSewModel, processChartDTO);
            list.add(processChartDTO);
        });

        shirtBackPieceModel.getBackPieceSewModelList().forEach(backPieceSewModel -> {
            ProcessChartDTO processChartDTO = new ProcessChartDTO();
            BeanUtils.copyProperties(backPieceSewModel, processChartDTO);
            list.add(processChartDTO);
        });

        return list;


    }

    @Override
    public List<Map<String, Integer>> getOverallShirtProcess(String produceNo) {
        //获取生产订单信息
        ProduceOrderModel produceOrderModel = produceOrderService.findByProduceOrderNo(produceNo);
        if (produceOrderModel == null) {
            return new ArrayList<>();
        }
        //性别
        Integer sexName = 0;
        //尺码信息
        String sizeInfo = "170/88A";
        //款式描述信息
        String remark = produceOrderModel.getRemark();
        //关于逗号/句号/顿号，先变成顿号，然后再以顿号拆分
        String[] remarkStr = remark.replace(",", "、").replace("。", "、").replace("，", "、").split("、");
        //基本模块
        Map<String, Integer> basicMap = new HashMap<>(16);
        //可选模块
        Map<String, Integer> optionalMap = new HashMap<>(16);
        //特殊模块
        Map<String, Integer> specialMap = new HashMap<>(16);
        //锁眼和钉扣个数
        Map<String, Integer> buttonMap = new HashMap<>(16);
        //省的个数
        Map<String, Integer> provinceMap = new HashMap<>(16);
        //褶的个数
        Map<String, Integer> pleatMap = new HashMap<>(16);
        //对数组中的每一个元素进行特征字段判断
        for (int i = 0; i < remarkStr.length; i++) {
            String str = remarkStr[i];
            //领的特征
            if (str.contains(PartsLibraryEnum.COLLAR.getName()) && !str.contains(PartsLibraryEnum.SLEEVE.getName())) {
                //TODO 首字会有重复的，逻辑需要修改
                PartsLibraryDTO partsLibraryDTO = getMapByCollar(remarkStr, i);
                optionalMap.putAll(partsLibraryDTO.getOptionalMap());
                buttonMap.putAll(partsLibraryDTO.getButtonMap());
            }
            //得到育克所有的近似名字(同义词匹配)
            List<String> strings = PartsLibraryEnum.getListByOverShoulder();
            boolean anyMatch = strings.stream().anyMatch(s -> str.contains(s) && !str.contains(PartsLibraryEnum.PLEAT.getName()) &&
                    !str.contains(PartsLibraryEnum.FOLD.getName()) && !str.contains(PartsLibraryEnum.PROVINCE.getName()));
            //育克特征
            if (anyMatch) {
                PartsLibraryDTO partsLibraryDTO = getMapByOverShoulder(remarkStr, i);
                optionalMap.putAll(partsLibraryDTO.getOptionalMap());
            }
            //门襟特征
            if (str.contains(PartsLibraryEnum.LAPEL.getName())) {
                PartsLibraryDTO partsLibraryDTO = getMapByLapel(remarkStr, i);
                basicMap.putAll(partsLibraryDTO.getBasicMap());
                buttonMap.putAll(partsLibraryDTO.getButtonMap());
            }
            //胸袋--(会有胸袋扣，如果是一个胸袋，一定会在左边)
            if (str.contains(PartsLibraryEnum.CHEST_POUCH.getName())) {
                PartsLibraryDTO partsLibraryDTO = getMapByChestPouch(remarkStr, i);
                optionalMap.putAll(partsLibraryDTO.getOptionalMap());
                buttonMap.putAll(partsLibraryDTO.getButtonMap());
            }
            //省道的特征
            if (str.contains(PartsLibraryEnum.PROVINCE.getName())) {
                PartsLibraryDTO partsLibraryDTO = getMapByProvince(remarkStr, i);
                specialMap.putAll(partsLibraryDTO.getSpecialMap());
                provinceMap.putAll(partsLibraryDTO.getProvinceMap());
            }
            //前身中的U形胸挡
            //TODO 这一块逻辑也需要修改
            if (str.contains(SpecialProcessEnum.U_CHEST_BLOCK.getName())) {
                //特殊模块里面插入U形胸挡
                specialMap.put("前片" + SpecialProcessEnum.U_CHEST_BLOCK.getName(), SpecialProcessEnum.U_CHEST_BLOCK.getType());
            }
            if (str.contains(SpecialProcessEnum.U_SHAPED_YUKON.getName())) {
                //特殊模块里面插入U形育克
                specialMap.put("前片" + SpecialProcessEnum.U_SHAPED_YUKON.getName(), SpecialProcessEnum.U_SHAPED_YUKON.getType());
            }
            if (str.contains(SpecialProcessEnum.DECORATIVE_TABS.getName())) {
                //特殊模块里面插入装饰袢
                Map<String,Integer> map = SpecialPartsEnum.getSpecialPartsMap();
                for (String s : map.keySet()) {
                    if(str.contains(s)){
                        specialMap.put(s+SpecialProcessEnum.DECORATIVE_TABS.getName(), SpecialProcessEnum.DECORATIVE_TABS.getType());
                    }
                }
            }
            if (str.contains(SpecialProcessEnum.DECORATION_DIVISION.getName())) {
                //特殊模块里面插入装饰分割
                Map<String,Integer> map = SpecialPartsEnum.getSpecialPartsMap();
                for (String s : map.keySet()) {
                    if(str.contains(s)){
                        specialMap.put(s+SpecialProcessEnum.DECORATION_DIVISION.getName(), SpecialProcessEnum.DECORATION_DIVISION.getType());
                    }
                }
            }
            if (str.contains(SpecialProcessEnum.SHOULDER_BADGE.getName())) {
                //特殊模块里面插入肩章
                specialMap.put(SpecialProcessEnum.SHOULDER_BADGE.getName(), SpecialProcessEnum.SHOULDER_BADGE.getType());
            }
            if (str.contains(SpecialProcessEnum.PRINTS.getName())) {
                //特殊模块里面插入印花
                specialMap.put(SpecialProcessEnum.PRINTS.getName(), SpecialProcessEnum.PRINTS.getType());
            }
            if (str.contains(SpecialProcessEnum.REVERSE_COLLAR.getName())) {
                //特殊模块里面插入反领
                specialMap.put(SpecialProcessEnum.REVERSE_COLLAR.getName(), SpecialProcessEnum.REVERSE_COLLAR.getType());
            }
            if (str.contains(SpecialProcessEnum.ELBOW_PATCH.getName())) {
                //特殊模块里面插入肘部补丁
                specialMap.put(SpecialProcessEnum.ELBOW_PATCH.getName(), SpecialProcessEnum.ELBOW_PATCH.getType());
            }
            if (str.contains(SpecialProcessEnum.LACE.getName())) {
                //特殊模块里面插入花边
                specialMap.put(SpecialProcessEnum.LACE.getName(), SpecialProcessEnum.LACE.getType());
            }
            if (str.contains(SpecialProcessEnum.EDGE_CURL.getName())) {
                //特殊模块里面插入卷边
                specialMap.put(SpecialProcessEnum.EDGE_CURL.getName(), SpecialProcessEnum.EDGE_CURL.getType());
            }
            if (str.contains(SpecialProcessEnum.EMBROIDERY.getName())) {
                //特殊模块里面插入刺绣
                specialMap.put(SpecialProcessEnum.EMBROIDERY.getName(), SpecialProcessEnum.EMBROIDERY.getType());
            }
            if (str.contains(SpecialProcessEnum.COLLAR_SLEEVE.getName())) {
                //特殊模块里面插入领口袖
                specialMap.put(SpecialProcessEnum.COLLAR_SLEEVE.getName(), SpecialProcessEnum.COLLAR_SLEEVE.getType());
            }

            //后片中的下摆
            if (str.contains(PartsLibraryEnum.HEM.getName())) {
                PartsLibraryDTO partsLibraryDTO = getMapByHem(remarkStr, i);
                basicMap.putAll(partsLibraryDTO.getBasicMap());
            }
            //褶裥的特征
            if (str.contains(PartsLibraryEnum.FOLD.getName()) || str.contains(PartsLibraryEnum.PLEAT.getName())) {
                PartsLibraryDTO partsLibraryDTO = getMapByFold(remarkStr, i);
                specialMap.putAll(partsLibraryDTO.getSpecialMap());
                pleatMap.putAll(partsLibraryDTO.getPleatMap());
            }
            //TODO 袖的逻辑也要进行修改
            if (str.contains(PartsLibraryEnum.SLEEVE.getName())) {
                //短袖的特征
                if (str.contains(PartsLibraryEnum.SHORT_SLEEVE.getName())) {
                    PartsLibraryDTO partsLibraryDTO = getMapByShortSleeve(remarkStr, i);
                    optionalMap.putAll(partsLibraryDTO.getOptionalMap());
                }
                //五分袖的特征
                if (str.contains(PartsLibraryEnum.FIVE_SLEEVE.getName())) {
                    PartsLibraryDTO partsLibraryDTO = getMapByFiveSleeve(remarkStr, i);
                    optionalMap.putAll(partsLibraryDTO.getOptionalMap());
                }
                //七分袖的特征
                if (str.contains(PartsLibraryEnum.SEVENTH_SLEEVE.getName())) {
                    PartsLibraryDTO partsLibraryDTO = getMapBySevenSleeve(remarkStr, i);
                    optionalMap.putAll(partsLibraryDTO.getOptionalMap());
                }
                if (!str.contains(PartsLibraryEnum.PLEAT.getName()) && !str.contains(PartsLibraryEnum.FOLD.getName())) {
                    //袖的共同特征
                    PartsLibraryDTO libraryDTO = getMapByCommonSleeve(remarkStr, i);
                    optionalMap.putAll(libraryDTO.getOptionalMap());
                }

                //长袖的特征
                PartsLibraryDTO partsLibraryDTO = getMapByLongSleeve(remarkStr, i);
                optionalMap.putAll(partsLibraryDTO.getOptionalMap());
                buttonMap.putAll(partsLibraryDTO.getButtonMap());

            }

        }
        List<ProcessChartDTO> basicList = getBasicPartProcess(basicMap, buttonMap);
        List<ProcessChartDTO> specialList = getSpecialModuleProcess(specialMap, provinceMap, pleatMap);
        List<ProcessChartDTO> optionalList = getOptionalModuleProcess(optionalMap, buttonMap);
        List<Map<String, Integer>> list = new ArrayList<>();
        list.add(basicMap);
        list.add(buttonMap);
        list.add(specialMap);
        list.add(provinceMap);
        list.add(pleatMap);
        list.add(optionalMap);

        return list;
    }

    @Override
    public List<ProcessChartDTO> getExportList(String produceNo) {
        return getBasicModuleProcess(produceNo);
    }

    private PartsLibraryDTO getMapByCommonSleeve(String[] remarkStr, int i) {
        String str = remarkStr[i];
        char[] chars = str.toCharArray();
        //得到首字特征map
        Map<String, Integer> map = SleeveEnum.getFirstCharMap();
        Map<String, Integer> optionalMap = new HashMap<>(16);
        for (char aChar : chars) {
            if (map.containsKey(String.valueOf(aChar))) {
                //可选模块中插入袖的类型
                optionalMap.put(SleeveEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setOptionalMap(optionalMap);
        return dto;

    }


    private PartsLibraryDTO getMapByOverShoulder(String[] remarkStr, int i) {
        String str = remarkStr[i];
        List<String> vitalList = OverShoulderEnum.getListByVital();
        Map<String, Integer> optionalMap = new HashMap<>(16);
        if (vitalList.stream().allMatch(str::contains)) {
            //可选模块中插入育克的类型
            optionalMap.put(OverShoulderEnum.FRONT_MERGE_BACK_SHOULDER.getName(), OverShoulderEnum.FRONT_MERGE_BACK_SHOULDER.getType());
        } else if (!str.contains("前") && str.contains("后") && str.contains("合并")) {
            //可选模块中插入育克的类型
            optionalMap.put(OverShoulderEnum.REAR_SHOULDER_YUKON.getName(), OverShoulderEnum.REAR_SHOULDER_YUKON.getType());
        } else if(str.contains("前") && str.contains("后")){
            //可选模块中插入育克的类型
            optionalMap.put(OverShoulderEnum.FRONT_REAR_SHOULDER_YUKON.getName(), OverShoulderEnum.FRONT_REAR_SHOULDER_YUKON.getType());
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setOptionalMap(optionalMap);
        return dto;
    }


    /*
    袖的逻辑（短袖、五分袖、七分袖、长袖）
    2、长袖
    （1）袖头与袖克夫和袖英是同义替换
    （2）袖衩条
    （3）开不开衩
    （4）袖扣有多少粒
    */
    private PartsLibraryDTO getMapByLongSleeve(String[] remarkStr, int i) {
        Map<String, Integer> optionalMap = new HashMap<>(16);
        Map<String, Integer> buttonMap = new HashMap<>(16);
        //如果是长袖，那就把长袖后面的字符串全部截取掉，然后再进行判断袖的特征。
        for (int j = i; j < remarkStr.length; j++) {
            String string = remarkStr[j];
            //把字符串拆成字符数组
            char[] chars = string.toCharArray();
            StringBuilder numberStr = new StringBuilder();
            if (string.contains("袖英") || string.contains("袖克夫") || string.contains("袖头")) {
                Map<String, Integer> map = LongSleeveEnum.getFirstCharCuffMap();
                for (char aChar : chars) {
                    if (map.containsKey(String.valueOf(aChar))) {
                        //可选模块中插入袖的类型
                        optionalMap.put(LongSleeveEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
                    }
                    //中文数字与阿拉伯数字的转换并判断
                    if (Arrays.asList(CN_NUMERIC).contains(aChar)) {
                        Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                        Integer integer = integerMap.get(aChar);
                        numberStr.append(integer);
                    }
                    if (!Objects.equals(String.valueOf(numberStr), "")) {
                        //袖克夫的纽扣个数
                        buttonMap.put("袖克夫", Integer.parseInt(String.valueOf(numberStr)));
                    }
                }
                if (j < remarkStr.length - 1) {
                    String s = remarkStr[j + 1];
                    char[] chars1 = s.toCharArray();
                    for (char bChar : chars1) {
                        //中文数字与阿拉伯数字的转换并判断
                        if (Arrays.asList(CN_NUMERIC).contains(bChar) && !s.contains("开衩")) {
                            Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                            Integer integer = integerMap.get(bChar);
                            numberStr.append(integer);
                        }
                        if (!Objects.equals(String.valueOf(numberStr), "")) {
                            //袖克夫的纽扣个数
                            buttonMap.put("袖克夫", Integer.parseInt(String.valueOf(numberStr)));
                        }
                    }
                }

            } else if (string.contains("开") && string.contains("衩")) {
                StringBuilder numberStr1 = new StringBuilder();
                for (char aChar : chars) {
                    if (aChar == '不') {
                        //可选模块中插入袖的类型
                        optionalMap.put(LongSleeveEnum.CUFF_WITHOUT_SLIT.getName(), LongSleeveEnum.CUFF_WITHOUT_SLIT.getType());
                    } else {
                        //可选模块中插入袖的类型
                        optionalMap.put(LongSleeveEnum.CUFF_WITH_SLIT.getName(), LongSleeveEnum.CUFF_WITH_SLIT.getType());
                        break;
                    }
                    //中文数字与阿拉伯数字的转换并判断
                    if (Arrays.asList(CN_NUMERIC).contains(aChar)) {
                        Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                        Integer integer = integerMap.get(aChar);
                        numberStr1.append(integer);
                    }
                    if (!Objects.equals(String.valueOf(numberStr1), "")) {
                        //袖衩的纽扣个数
                        buttonMap.put("袖衩", Integer.parseInt(String.valueOf(numberStr1)));
                    }
                }
                if (j < remarkStr.length - 1) {
                    String s = remarkStr[j + 1];
                    char[] chars1 = s.toCharArray();
                    for (char bChar : chars1) {
                        //中文数字与阿拉伯数字的转换并判断
                        if (Arrays.asList(CN_NUMERIC).contains(bChar) && !s.contains(PartsLibraryEnum.PLEAT.getName()) && !s.contains(PartsLibraryEnum.PROVINCE.getName())
                                && !s.contains(PartsLibraryEnum.CHEST_POUCH.getName()) && !s.contains(PartsLibraryEnum.COLLAR.getName())) {
                            Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                            Integer integer = integerMap.get(bChar);
                            numberStr1.append(integer);
                        }
                        if (!Objects.equals(String.valueOf(numberStr), "")) {
                            //袖衩的纽扣个数
                            buttonMap.put("袖衩", Integer.parseInt(String.valueOf(numberStr1)));
                        }
                    }
                }
            } else if (string.contains("袖衩条")) {
                Map<String, Integer> integerMap = LongSleeveEnum.getSpecialCharMap();
                for (char bChar : chars) {
                    if (integerMap.containsKey(String.valueOf(bChar))) {
                        //可选模块中插入袖的类型
                        optionalMap.put(LongSleeveEnum.getNameByType(integerMap.get(String.valueOf(bChar))), integerMap.get(String.valueOf(bChar)));
                    }
                }

            } else if (string.contains("袖口")) {
                Map<String, Integer> importantMap = LongSleeveEnum.getImportantMap();
                Set<String> stringSet = importantMap.keySet();
                for (String str : stringSet) {
                    if (string.contains(str)) {
                        //可选模块中插入袖的类型
                        optionalMap.put(LongSleeveEnum.getNameByType(importantMap.get(str)), importantMap.get(str));
                    }
                }
            } else if (string.contains("半袖套")) {
                //可选模块中插入袖的类型
                optionalMap.put(LongSleeveEnum.HALF_SLEEVE_COVERS.getName(), LongSleeveEnum.HALF_SLEEVE_COVERS.getType());
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setButtonMap(buttonMap);
        dto.setOptionalMap(optionalMap);
        return dto;
    }

    private PartsLibraryDTO getMapBySevenSleeve(String[] remarkStr, int i) {
        String str = remarkStr[i];
        char[] chars = str.toCharArray();
        //得到首字特征map
        Map<String, Integer> map = SevenSleeveEnum.getFirstCharMap();
        Map<String, Integer> optionalMap = new HashMap<>(16);
        for (char aChar : chars) {
            if (map.containsKey(String.valueOf(aChar))) {
                //可选模块中插入袖的类型
                optionalMap.put(SevenSleeveEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
            } else {
                //可选模块中插入七分袖的标准类型
                optionalMap.put(SevenSleeveEnum.STANDARD_SEVENTH_SLEEVE.getName(), SevenSleeveEnum.STANDARD_SEVENTH_SLEEVE.getType());
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setOptionalMap(optionalMap);
        return dto;
    }

    private PartsLibraryDTO getMapByFiveSleeve(String[] remarkStr, int i) {
        String str = remarkStr[i];
        char[] chars = str.toCharArray();
        //得到首字特征map
        Map<String, Integer> map = FiveSleeveEnum.getFirstCharMap();
        Map<String, Integer> optionalMap = new HashMap<>(16);
        for (char aChar : chars) {
            if (map.containsKey(String.valueOf(aChar))) {
                //可选模块中插入袖的类型
                optionalMap.put(FiveSleeveEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
            } else {
                //可选模块中插入五分袖的标准类型
                optionalMap.put(FiveSleeveEnum.STANDARD_FIVE_SLEEVE.getName(), FiveSleeveEnum.STANDARD_FIVE_SLEEVE.getType());
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setOptionalMap(optionalMap);
        return dto;
    }


    /*
    短袖/五分袖/七分袖,没有袖衩条与袖克夫等特征
    */
    private PartsLibraryDTO getMapByShortSleeve(String[] remarkStr, int i) {
        String str = remarkStr[i];
        //得到首字特征map
        Map<String, Integer> map = ShortSleeveEnum.getFirstCharMap();
        Map<String, Integer> optionalMap = new HashMap<>(16);
        for (String s : map.keySet()) {
            if (str.contains(s)) {
                //可选模块中插入袖的类型
                optionalMap.put(ShortSleeveEnum.getNameByType(map.get(s)), map.get(s));
            }
        }
        if(map.keySet().stream().noneMatch(str::contains)) {
            //可选模块中插入短袖的标准类型
            optionalMap.put(ShortSleeveEnum.STANDARD_SHORT_SLEEVE.getName(), ShortSleeveEnum.STANDARD_SHORT_SLEEVE.getType());
        }

        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setOptionalMap(optionalMap);
        return dto;
    }

    /*
    褶、裥和省差不多的逻辑，褶和裥是同义词，可以相互替换。
    1、判断袖上的褶（标准褶、多褶、碎褶）
    （1）袖口开小袖衩,收碎褶
    （2）袖口开小袖衩，袖口收碎褶
    2、判断前片上的褶（前过肩褶、前腰褶、胸褶、胁褶、肚褶）
    （1）左右前片收腋下省，有过肩褶（过肩还可以同义替换）
    （2）左右前片收腋下省，有前过肩褶
    3、判断后片上的褶（双肩褶、工字褶、腰褶）
    （1）后片收腰省，有后背双肩褶
    （2）后片收腰省，有双肩褶
    */
    private PartsLibraryDTO getMapByFold(String[] remarkStr, int i) {
        String str = remarkStr[i];
        char[] chars = str.toCharArray();
        Map<String, Integer> specialMap = new HashMap<>(16);
        Map<String, Integer> pleatMap = new HashMap<>(16);
        //重复首字关键字map
        Map<String, List<Integer>> repeatMap = PleatEnum.getMapByVitalRepeatChar();
        //中间单独关键字map
        Map<String, Integer> integerMap = PleatEnum.getMapByVitalChar();
        StringBuilder numberStr = new StringBuilder();
        for (char aChar : chars) {
            //中文数字与阿拉伯数字的转换并判断
            if (Arrays.asList(CN_NUMERIC).contains(aChar)) {
                Map<Character, Integer> integerMap1 = PartsLibraryEnum.getButtonMap();
                Integer integer = integerMap1.get(aChar);
                numberStr.append(integer);
            }
        }
        for (char aChar : chars) {
            if (repeatMap.containsKey(String.valueOf(aChar))) {
                //得到首字map对应的枚举类List
                List<Integer> list = repeatMap.get(String.valueOf(aChar));
                //重复关键字map,再进行一次筛选判断
                Map<String, Integer> map = PleatEnum.getMapByQueryVital(list);
                for (char bChar : chars) {
                    if (map.containsKey(String.valueOf(bChar))) {
                        //特殊模块中插入褶的类型
                        specialMap.put(PleatEnum.getNameByType(map.get(String.valueOf(bChar))), map.get(String.valueOf(bChar)));
                    } else {
                        //特殊模块中插入褶的类型
                        specialMap.put(PleatEnum.STANDARD_PLEAT_CUFF.getName(), PleatEnum.STANDARD_PLEAT_CUFF.getType());
                    }
                    if (!Objects.equals(String.valueOf(numberStr), "")) {
                        //得到褶裥的个数
                        pleatMap.put(PleatEnum.STANDARD_PLEAT_CUFF.getName(), Integer.parseInt(String.valueOf(numberStr)));
                    }
                }
            } else if (integerMap.containsKey(String.valueOf(aChar))) {
                //特殊模块中插入褶的类型
                specialMap.put(PleatEnum.getNameByType(integerMap.get(String.valueOf(aChar))), integerMap.get(String.valueOf(aChar)));
                if (!Objects.equals(String.valueOf(numberStr), "")) {
                    //得到褶裥的个数
                    pleatMap.put(PleatEnum.getNameByType(integerMap.get(String.valueOf(aChar))), Integer.parseInt(String.valueOf(numberStr)));
                }
            }
        }
        if (i > 1) {
            if (remarkStr[i - 1].contains("前") || remarkStr[i - 2].contains("前")) {
                List<Integer> integerList = repeatMap.get("前");
                //重复关键字map,再进行一次筛选判断
                Map<String, Integer> map = PleatEnum.getMapByQueryVital(integerList);
                for (char aChar : chars) {
                    if (map.containsKey(String.valueOf(aChar))) {
                        //特殊模块中插入褶的类型
                        specialMap.put(PleatEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
                    }
                    if (!Objects.equals(String.valueOf(numberStr), "")) {
                        //得到褶裥的个数
                        pleatMap.put(PleatEnum.getNameByType(map.get(String.valueOf(aChar))), Integer.parseInt(String.valueOf(numberStr)));
                    }
                }
            } else if (remarkStr[i - 1].contains("后") || remarkStr[i - 2].contains("后")) {
                List<Integer> integerList = repeatMap.get("后");
                //重复关键字map,再进行一次筛选判断
                Map<String, Integer> map = PleatEnum.getMapByQueryVital(integerList);
                for (char aChar : chars) {
                    if (map.containsKey(String.valueOf(aChar))) {
                        //特殊模块中插入褶的类型
                        specialMap.put(PleatEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
                    }
                    if (!Objects.equals(String.valueOf(numberStr), "")) {
                        //得到褶裥的个数
                        pleatMap.put(PleatEnum.getNameByType(map.get(String.valueOf(aChar))), Integer.parseInt(String.valueOf(numberStr)));
                    }
                }
            } else if (remarkStr[i - 1].contains("袖") || remarkStr[i - 2].contains("袖")) {
                List<Integer> integerList = repeatMap.get("袖");
                //重复关键字map,再进行一次筛选判断
                Map<String, Integer> map = PleatEnum.getMapByQueryVital(integerList);
                for (char aChar : chars) {
                    if (map.containsKey(String.valueOf(aChar))) {
                        //特殊模块中插入褶的类型
                        specialMap.put(PleatEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
                    }
                    if (!Objects.equals(String.valueOf(numberStr), "")) {
                        //得到褶裥的个数
                        pleatMap.put(PleatEnum.STANDARD_PLEAT_CUFF.getName(), Integer.parseInt(String.valueOf(numberStr)));
                    }
                }
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setSpecialMap(specialMap);
        dto.setPleatMap(pleatMap);
        return dto;
    }

    /*
     * 直下摆不开衩
     * 直下摆，不开衩
     */
    private PartsLibraryDTO getMapByHem(String[] remarkStr, int i) {
        String str = remarkStr[i];
        char[] chars = str.toCharArray();
        Map<String, List<Integer>> map = BackPieceEnum.getFirstCharMap();
        Map<String, Integer> basicMap = new HashMap<>(16);
        if (str.contains("衩")) {
            //对于后片中的下摆以数字分析法建立哈希函数（首字）
            for (char aChar : chars) {
                if (map.containsKey(String.valueOf(aChar))) {
                    if (map.get(String.valueOf(aChar)).size() == 1) {
                        //基本模块中插入下摆的类型
                        basicMap.put(BackPieceEnum.getNameByType(map.get(String.valueOf(aChar)).get(0)), map.get(String.valueOf(aChar)).get(0));
                    } else {
                        //得到首字相同的list
                        List<Integer> integers = map.get(String.valueOf(aChar));
                        Map<String, Integer> integerMap = BackPieceEnum.getSecondCharMap(integers);
                        for (char bChar : chars) {
                            if (integerMap.containsKey(String.valueOf(bChar))) {
                                //基本模块中插入下摆的类型
                                basicMap.put(BackPieceEnum.getNameByType(integerMap.get(String.valueOf(bChar))), integerMap.get(String.valueOf(bChar)));
                            } else {
                                //基本模块中插入下摆的类型
                                basicMap.put(BackPieceEnum.getNameByType(integerMap.get("hu")), integerMap.get("hu"));
                            }
                        }
                    }
                }
            }
        } else {
            //对于后片中的下摆以数字分析法建立哈希函数（首字）
            for (char aChar : chars) {
                if (map.containsKey(String.valueOf(aChar))) {
                    //得到首字相同的list
                    List<Integer> integers = map.get(String.valueOf(aChar));
                    Map<String, Integer> integerMap = BackPieceEnum.getSecondCharMap(integers);
                    if (i < remarkStr.length - 1) {
                        //判断下摆后一个字符串里面是否有第二个字的特征信息
                        boolean flag1 = !remarkStr[i + 1].contains(PartsLibraryEnum.SLEEVE.getName()) && integerMap.keySet().stream().anyMatch(s -> remarkStr[i + 1].contains(s));
                        boolean flag2 = Arrays.stream(PartsLibraryEnum.values()).anyMatch(p -> remarkStr[i + 1].contains(p.getName()));
                        if (flag1 || flag2) {
                            //基本模块中插入下摆的类型
                            basicMap.put(BackPieceEnum.getNameByType(integerMap.get("不")), integerMap.get("不"));
                        } else if (remarkStr[i + 1].contains("衩")) {
                            //基本模块中插入下摆的类型
                            basicMap.put(BackPieceEnum.getNameByType(integerMap.get("hu")), integerMap.get("hu"));
                        } else {
                            //基本模块中插入下摆的类型
                            basicMap.put(BackPieceEnum.getNameByType(integerMap.get("不")), integerMap.get("不"));
                        }
                    } else {
                        //基本模块中插入下摆的类型
                        basicMap.put(BackPieceEnum.getNameByType(integerMap.get("不")), integerMap.get("不"));
                    }
                }
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setBasicMap(basicMap);
        return dto;
    }


    /*
     省表示的各种情况
     1、左右前片收腋下省和腰省各一个，后片收腰省两个
     2、左右前片收腋下省一个，收腰省1个，后片收腰省两个
     3、左右前片收通省一个
    */
    private PartsLibraryDTO getMapByProvince(String[] remarkStr, int i) {
        String str = remarkStr[i];
        char[] chars = str.toCharArray();
        StringBuilder numberStr = new StringBuilder();
        Map<String, Integer> specialMap = new HashMap<>(16);
        Map<String, Integer> provinceMap = new HashMap<>(16);
        for (char aChar : chars) {
            //中文数字与阿拉伯数字的转换并判断
            if (Arrays.asList(CN_NUMERIC).contains(aChar)) {
                Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                Integer integer = integerMap.get(aChar);
                numberStr.append(integer);
            }
        }
        //对于省以数字分析法建立哈希函数（首字）
        Map<String, List<Integer>> map = ProvinceEnum.getFirsCharMap();
        for (char aChar : chars) {
            if (map.containsKey(String.valueOf(aChar))) {
                if (map.get(String.valueOf(aChar)).size() == 1) {
                    List<Integer> integerList = map.get(String.valueOf(aChar));
                    //特殊模块中插入省道的类型
                    specialMap.put(ProvinceEnum.getNameByType(integerList.get(0)), integerList.get(0));
                    //得到省道的个数
                    if (!Objects.equals(String.valueOf(numberStr), "")) {
                        provinceMap.put(ProvinceEnum.getNameByType(integerList.get(0)), Integer.parseInt(String.valueOf(numberStr)));
                    }
                } else {
                    //得到首字相同的list
                    List<Integer> integers = map.get(String.valueOf(aChar));
                    Map<String, Integer> integerMap = ProvinceEnum.getSecondCharMap(integers);
                    //判断第二个字符
                    for (char bChar : chars) {
                        if (integerMap.containsKey(String.valueOf(bChar))) {
                            //特殊模块中插入省的类型
                            specialMap.put(ProvinceEnum.getNameByType(integerMap.get(String.valueOf(bChar))), integerMap.get(String.valueOf(bChar)));
                            if (!Objects.equals(String.valueOf(numberStr), "")) {
                                //得到省道的个数
                                provinceMap.put(ProvinceEnum.getNameByType(integerMap.get(String.valueOf(bChar))), Integer.parseInt(String.valueOf(numberStr)));
                            }
                        }
                    }
                }
            }
        }
        if (i > 1) {
            //中间进行补充
            if (!str.contains("前") && !str.contains("后")) {
                if (remarkStr[i - 1].contains("前") || remarkStr[i - 2].contains("前")) {
                    List<Integer> integerList = map.get("前");
                    Map<String, Integer> integerMap = ProvinceEnum.getSecondCharMap(integerList);
                    for (char aChar : chars) {
                        if (integerMap.containsKey(String.valueOf(aChar))) {
                            //特殊模块中插入省的类型
                            specialMap.put(ProvinceEnum.getNameByType(integerMap.get(String.valueOf(aChar))), integerMap.get(String.valueOf(aChar)));
                            if (!Objects.equals(String.valueOf(numberStr), "")) {
                                //得到省道的个数
                                provinceMap.put(ProvinceEnum.getNameByType(integerMap.get(String.valueOf(aChar))), Integer.parseInt(String.valueOf(numberStr)));
                            }
                        }
                    }
                } else if (remarkStr[i - 1].contains("后") || remarkStr[i - 2].contains("后")) {
                    Map<String, Integer> integerMap = ProvinceEnum.getSecondCharMap(map.get("后"));
                    for (char aChar : chars) {
                        if (integerMap.containsKey(String.valueOf(aChar))) {
                            //特殊模块中插入省的类型
                            specialMap.put(ProvinceEnum.getNameByType(integerMap.get(String.valueOf(aChar))), integerMap.get(String.valueOf(aChar)));
                            if (!Objects.equals(String.valueOf(numberStr), "")) {
                                //得到省道的个数
                                provinceMap.put(ProvinceEnum.getNameByType(integerMap.get(String.valueOf(aChar))), Integer.parseInt(String.valueOf(numberStr)));
                            }
                        }
                    }
                }
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setSpecialMap(specialMap);
        dto.setProvinceMap(provinceMap);
        return dto;
    }


    /*
     胸袋的表示形式：
     左胸明贴袋一个，有一粒扣
    */
    private PartsLibraryDTO getMapByChestPouch(String[] remarkStr, int i) {
        String str = remarkStr[i];
        //对于胸袋以关键字段建立哈希函数
        Map<String, List<Integer>> map = ChestPouchEnum.getVitalMap();
        Map<String, Integer> optionalMap = new HashMap<>(16);
        Map<String, Integer> buttonMap = new HashMap<>(16);
        //通过entrySet()来遍历
        Set<Map.Entry<String, List<Integer>>> entries = map.entrySet();
        for (Map.Entry<String, List<Integer>> entry : entries) {
            if (str.contains(entry.getKey())) {
                List<Integer> value = entry.getValue();
                Map<String, Integer> integerMap = ChestPouchEnum.getSpecialMap(value);

                //把字符串分割成字符数组
                char[] chars = str.toCharArray();
                for (char aChar : chars) {
                    if (integerMap.containsKey(String.valueOf(aChar))) {
                        //可选模块
                        optionalMap.put(ChestPouchEnum.getNameByType(integerMap.get(String.valueOf(aChar))), integerMap.get(String.valueOf(aChar)));
                    }
                }
                if(!str.contains("右")){
                        optionalMap.put(ChestPouchEnum.getNameByType(integerMap.get("左")), integerMap.get("左"));
                }

            }
        }
        if (i < remarkStr.length - 1) {
            //判断下一个字符串
            char[] chars2 = remarkStr[i + 1].toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (char bChar : chars2) {
                //中文数字与阿拉伯数字的转换并判断
                if (Arrays.asList(CN_NUMERIC).contains(bChar) && !remarkStr[i + 1].contains(PartsLibraryEnum.LAPEL.getName())
                        && !remarkStr[i + 1].contains(PartsLibraryEnum.SLEEVE.getName())) {
                    Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                    Integer integer = integerMap.get(bChar);
                    stringBuilder.append(integer);
                }
                if (!Objects.equals(String.valueOf(stringBuilder), "")) {
                    //用于锁眼和钉扣
                    buttonMap.put(PartsLibraryEnum.CHEST_POUCH.getName(), Integer.parseInt(String.valueOf(stringBuilder)));
                }
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setOptionalMap(optionalMap);
        dto.setButtonMap(buttonMap);
        return dto;
    }


    /*
     门襟有三种表示类型：
     1、门襟有6个扣眼；
     2、门襟，右襟上有6个扣眼；
     3、门襟，开6个扣眼。
    */
    private PartsLibraryDTO getMapByLapel(String[] remarkStr, int i) {
        String str = remarkStr[i];
        //把字符串分割成字符数组
        char[] chars = str.toCharArray();
        StringBuilder numberStr = new StringBuilder();
        Map<String, Integer> basicMap = new HashMap<>(16);
        Map<String, Integer> buttonMap = new HashMap<>(16);
        //中文数字与阿拉伯数字的转换并判断
        for (char aChar : chars) {
            if (Arrays.asList(CN_NUMERIC).contains(aChar)) {
                Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                Integer integer = integerMap.get(aChar);
                numberStr.append(integer);
            }
        }
        //对于门襟中以数字分析法建立哈希函数（首字）
        Map<String, Integer> map = LapelEnum.getFirstCharMap();
        for (char aChar : chars) {
            if (map.containsKey(String.valueOf(aChar))) {
                //门襟是基本模块
                basicMap.put(LapelEnum.getNameByType(map.get(String.valueOf(aChar))), map.get(String.valueOf(aChar)));
                if (!Objects.equals(String.valueOf(numberStr), "")) {
                    //用于锁眼和钉扣
                    buttonMap.put(PartsLibraryEnum.LAPEL.getName(), Integer.parseInt(String.valueOf(numberStr)));
                }
            }
        }
        if (i < remarkStr.length - 1) {
            char[] chars1 = remarkStr[i + 1].toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (char bChar : chars1) {
                //中文数字与阿拉伯数字的转换并判断
                if (Arrays.asList(CN_NUMERIC).contains(bChar) && !remarkStr[i + 1].contains(PartsLibraryEnum.CHEST_POUCH.getName())
                        && !remarkStr[i + 1].contains(PartsLibraryEnum.SLEEVE.getName())) {
                    Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                    Integer integer = integerMap.get(bChar);
                    stringBuilder.append(integer);
                }
                if (!Objects.equals(String.valueOf(stringBuilder), "")) {
                    //用于锁眼和钉扣
                    buttonMap.put(PartsLibraryEnum.LAPEL.getName(), Integer.parseInt(String.valueOf(stringBuilder)));
                }
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setBasicMap(basicMap);
        dto.setButtonMap(buttonMap);
        return dto;
    }

    /*
     领
     1、纽扣领有纽扣
     2、其他领没有纽扣
    */
    private PartsLibraryDTO getMapByCollar(String[] remarkStr, int i) {
        String str = remarkStr[i];
        //对于领子中以数字分析法建立哈希函数（首字）
        char[] chars = str.toCharArray();
        Map<String, List<Integer>> map = CollarEnum.getFirsCharMap();
        Map<String, Integer> optionalMap = new HashMap<>(16);
        Map<String, Integer> buttonMap = new HashMap<>(16);
        for (char aChar : chars) {
            StringBuilder numberStr = new StringBuilder();
            if (map.containsKey(String.valueOf(aChar))) {
                if (map.get(String.valueOf(aChar)).size() == 1) {
                    //可选模块中插入领子的类型
                    optionalMap.put(CollarEnum.getNameByType(map.get(String.valueOf(aChar)).get(0)), map.get(String.valueOf(aChar)).get(0));
                } else {
                    //得到首字相同的list
                    List<Integer> integers = map.get(String.valueOf(aChar));
                    Map<String, Integer> integerMap = CollarEnum.getSecondCharMap(integers);
                    //判断第二个字符
                    char[] chars1 = str.toCharArray();
                    for (char bChar : chars1) {
                        if (integerMap.containsKey(String.valueOf(bChar))) {
                            //可选模块中插入领子的类型
                            optionalMap.put(CollarEnum.getNameByType(integerMap.get(String.valueOf(bChar))), integerMap.get(String.valueOf(bChar)));
                        }
                    }
                }
                //中文数字与阿拉伯数字的转换并判断
                if (Arrays.asList(CN_NUMERIC).contains(aChar)) {
                    Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                    Integer integer = integerMap.get(aChar);
                    numberStr.append(integer);
                }
                if (!Objects.equals(String.valueOf(numberStr), "")) {
                    //领的纽扣个数
                    buttonMap.put("领", Integer.parseInt(String.valueOf(numberStr)));
                }

            }
        }
        if (i < remarkStr.length - 1) {
            String str2 = remarkStr[i + 1];
            char[] chars1 = str2.toCharArray();
            for (char bChar : chars1) {
                StringBuilder numberStr = new StringBuilder();
                //中文数字与阿拉伯数字的转换并判断
                if (Arrays.asList(CN_NUMERIC).contains(bChar) && !str2.contains(PartsLibraryEnum.PLEAT.getName()) && !str2.contains(PartsLibraryEnum.PROVINCE.getName())
                        && !str2.contains(PartsLibraryEnum.CHEST_POUCH.getName()) && !str2.contains(PartsLibraryEnum.SLEEVE.getName())) {
                    Map<Character, Integer> integerMap = PartsLibraryEnum.getButtonMap();
                    Integer integer = integerMap.get(bChar);
                    numberStr.append(integer);
                }
                if (!Objects.equals(String.valueOf(numberStr), "")) {
                    //领的纽扣个数
                    buttonMap.put("领", Integer.parseInt(String.valueOf(numberStr)));
                }
            }
        }
        PartsLibraryDTO dto = new PartsLibraryDTO();
        dto.setOptionalMap(optionalMap);
        dto.setButtonMap(buttonMap);
        return dto;
    }


    /**
     * 基本款式工艺模块：前片、后片（包含下摆）、门襟、
     */
    public List<ProcessChartDTO> getBasicPartProcess(Map<String, Integer> basicMap, Map<String, Integer> buttonMap) {
        return new ArrayList<>();
    }


    /**
     * 获取可选模块工序:育克、袖、领、胸袋
     */
    public List<ProcessChartDTO> getOptionalModuleProcess(Map<String, Integer> optionalMap, Map<String, Integer> buttonMap) {
        return new ArrayList<>();
    }


    /**
     * 获取特殊工艺模块工序：省、褶裥等
     */
    public List<ProcessChartDTO> getSpecialModuleProcess(Map<String, Integer> specialMap, Map<String, Integer> provinceMap, Map<String, Integer> pleatMap) {
        return new ArrayList<>();
    }

    /**
     * 获取连接组合工序：合肩缝、上领、合摆缝、上袖、上袖头、订胸袋
     */
    public List<ProcessChartDTO> getConnectModuleProcess(String produceNo) {
        return new ArrayList<>();
    }

    //收尾模块
    public List<ProcessChartDTO> getCloseModuleProcess(String produceNo) {
        return new ArrayList<>();
    }
}


