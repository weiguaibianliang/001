package com.service.impl;

import com.DTO.ProcessChartDTO;
import com.Enum.*;
import com.Model.*;
import com.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ProcessChartServiceImpl implements ProcessChartService {

    private static final Character[] CN_NUMERIC = { '一', '二', '三', '四', '五',
            '六', '七', '八', '九', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖',
            '十', '百', '千', '拾', '佰', '仟', '万', '亿', '○', 'Ｏ', '零' };

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
     *
     */
    @Override
    public List<ProcessChartDTO> getBasicModuleProcess(String produceOrderNo) {
        //获取生产订单信息
        ProduceOrderModel produceOrderModel = produceOrderService.findByProduceOrderNo(produceOrderNo);
        if(produceOrderModel == null){
            return new ArrayList<>();
        }
        //性别
        Integer sexName = 0;
        //尺码信息
        String sizeInfo =  "170/88A";
        //前片特征
        Integer frontPieceFeatures = 0;
        //后片特征
        Integer backPleatShapeCharacter = 0;
        //门襟特征
        Integer lapelCharacter = 0;
        //如果生产订单信息不为空，那么可以得到基本款式模块的工序
        //基本模块工序：前片（啥都没）、后片（啥都没）、直下摆无开衩、门襟（明门襟）
        List<ProcessChartDTO> list = new ArrayList<>();
        ShirtFrontPieceModel shirtFrontPieceModel = shirtFrontPieceService.findByRemark(sexName,sizeInfo,frontPieceFeatures);
        ShirtBackPieceModel shirtBackPieceModel = shirtBackPieceService.findByRemark(sexName,sizeInfo,backPleatShapeCharacter);
        ShirtLapelModel shirtLapelModel = shirtLapelService.findByRemark(sexName,sizeInfo,lapelCharacter);

        shirtFrontPieceModel.getFrontPieceSewModelList().forEach(frontPieceSewModel -> {
            ProcessChartDTO processChartDTO = new ProcessChartDTO();
            BeanUtils.copyProperties(frontPieceSewModel,processChartDTO);
            list.add(processChartDTO);
        });

        shirtLapelModel.getLapelSewModelList().forEach(lapelSewModel -> {
            ProcessChartDTO processChartDTO = new ProcessChartDTO();
            BeanUtils.copyProperties(lapelSewModel,processChartDTO);
            list.add(processChartDTO);
        });

        shirtBackPieceModel.getBackPieceSewModelList().forEach(backPieceSewModel -> {
            ProcessChartDTO processChartDTO = new ProcessChartDTO();
            BeanUtils.copyProperties(backPieceSewModel,processChartDTO);
            list.add(processChartDTO);
        });

        return list;


    }

    @Override
    public List<ProcessChartDTO> getOptionalModuleProcess(String produceOrderNo) {
        return new ArrayList<>();
    }

    @Override
    public List<ProcessChartDTO> getSpecialModuleProcess(String produceNo) {
        return new ArrayList<>();
    }

    @Override
    public List<ProcessChartDTO> getConnectModuleProcess(String produceNo) {
        return new ArrayList<>();
    }

    @Override
    public List<ProcessChartDTO> getCloseModuleProcess(String produceNo) {
        return new ArrayList<>();
    }

    @Override
    public List<ProcessChartDTO> getOverallShirtProcess(String produceNo) {


        List<String> collarEnumName = shirtCollarService.getCollEnumName();
        List<String> chestPouchEnumName = shirtChestPouchService.getChestPouchEnumName();
        List<String> lapelEnumName = shirtLapelService.getLapelEnumName();
        List<String> overShoulderEnumName = shirtOverShoulderService.getOverShoulderEnumName();
        List<String> backPieceEnumName = shirtBackPieceService.getBackPieceEnumName();
        List<String> frontPieceEnumName = shirtFrontPieceService.getFrontPieceEnumName();
        List<String> longSleeveEnumName = shirtSleeveBodyPlacketService.getLongSleeveEnumName();
        List<String> sevenSleeveEnumName = shirtSleeveBodyPlacketService.getSevenEnumName();
        List<String> fiveSleeveEnumName = shirtSleeveBodyPlacketService.getFiveSleeveEnumName();
        List<String> shortSleeveEnumName = shirtSleeveBodyPlacketService.getShortSleeveEnumName();
        List<String> pleatEnumName  = shirtPleatService.getShortPleatEnumName();
        List<String> provinceEnumName = shirtProvinceService.getProvinceEnumName();


        //基本模块
        Map<String,Integer> basicMap = new HashMap<>(16);
        //可选模块
        Map<String,Integer> optionalMap = new HashMap<>(16);
        //特殊模块
        Map<String,Integer> specialMap = new HashMap<>(16);

        //锁眼和钉扣个数
        Map<String,Integer> buttonMap = new HashMap<>(16);

        //获取生产订单信息
        ProduceOrderModel produceOrderModel = produceOrderService.findByProduceOrderNo(produceNo);
        if(produceOrderModel == null){
            return new ArrayList<>();
        }
        //性别
        Integer sexName = 0;
        //尺码信息
        String sizeInfo =  "170/88A";
        //款式描述信息
        String remark = produceOrderModel.getRemark();
        //关于逗号/句号/顿号，先变成顿号，然后再以顿号拆分
        String[] remarkStr = remark.replace(",","、").replace("。","、").split("、");
        //对数组中的每一个元素进行特征字段判断
        for (int i = 0; i < remarkStr.length; i++){
             String str =  remarkStr[i];
            //领
            if(str.contains(PartsLibraryEnum.COLLAR.getName())){
                //对于领子中以数字分析法建立哈希函数（首字）
                char[] chars = str.toCharArray();
                Map<String,List<Integer>> map = CollarEnum.getFirsCharMap();
                for (char aChar : chars) {
                    if(map.containsKey(String.valueOf(aChar))){
                        if( map.get(String.valueOf(aChar)).size() == 1){
                            //可选模块中插入领子的类型
                            optionalMap.put(PartsLibraryEnum.COLLAR.getName(),map.get(String.valueOf(aChar)).get(0));
                        }else {
                            //得到首字相同的list
                            List<Integer> integers = map.get(String.valueOf(aChar));
                            Map<String,Integer> integerMap = CollarEnum.getSecondCharMap(integers);
                            //判断第二个字符
                            char[] chars1  = str.substring(2,chars.length).toCharArray();
                            for (char bChar : chars1) {
                                if(integerMap.containsKey(String.valueOf(bChar))){
                                    //可选模块中插入领子的类型
                                    optionalMap.put(PartsLibraryEnum.COLLAR.getName(),integerMap.get(String.valueOf(bChar)));
                                }
                            }
                        }
                    }
                }
            }

            //得到育克所有的近似名字(同义词匹配)
            List<String> strings = PartsLibraryEnum.getListByOverShoulder();
            boolean anyMatch = strings.stream().anyMatch(s -> str.contains(s) && !str.contains(PartsLibraryEnum.PLEAT.getName()) &&
                    !str.contains(PartsLibraryEnum.FOLD.getName()) && !str.contains(PartsLibraryEnum.PROVINCE.getName()));

            //育克
            if(anyMatch){
                //对于育克中以数字分析法建立哈希函数（首字）
                char[] chars = str.toCharArray();
                Map<String,Integer> map = OverShoulderEnum.getFirstCharMap();
                for (char aChar : chars) {
                    if(map.containsKey(String.valueOf(aChar))){
                        optionalMap.put(PartsLibraryEnum.OVER_SHOULDER.getName(),map.get(String.valueOf(aChar)));
                    }
                }
            }

            /*
            门襟有三种表示类型：
            1、门襟有6个扣眼；
            2、门襟，右襟上有6个扣眼；
            3、门襟，开6个扣眼。
             */
            if(str.contains(PartsLibraryEnum.LAPEL.getName())){
                //把字符串分割成字符数组
                char[] chars = str.toCharArray();
                StringBuilder numberStr = new StringBuilder();
                for (char aChar : chars) {
                    //中文数字与阿拉伯数字的转换并判断
                    if(Arrays.asList(CN_NUMERIC).contains(aChar)){
                        Map<Character,Integer> integerMap = PartsLibraryEnum.getButtonMap();
                        Integer integer = integerMap.get(aChar);
                        numberStr.append(integer);
                    }
                }
                //对于门襟中以数字分析法建立哈希函数（首字）
                Map<String,Integer> map = LapelEnum.getFirstCharMap();
                if(numberStr.toString().equals("")){
                    for (char aChar : chars) {
                        if(map.containsKey(String.valueOf(aChar))){
                            //门襟是基本模块
                            basicMap.put(PartsLibraryEnum.LAPEL.getName(),map.get(String.valueOf(aChar)));
                        }
                    }
                }else {
                    for (char aChar : chars) {
                        if(map.containsKey(String.valueOf(aChar))){
                            //门襟是基本模块
                            basicMap.put(PartsLibraryEnum.LAPEL.getName(),map.get(String.valueOf(aChar)));
                        }
                    }
                    if(!buttonMap.containsKey(PartsLibraryEnum.LAPEL.getName())){
                        //用于锁眼和钉扣
                        buttonMap.put(PartsLibraryEnum.LAPEL.getName(),Integer.parseInt(String.valueOf(numberStr)));
                    }
                }

                char[] chars1 = remarkStr[i+1].toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (char bChar : chars1) {
                    //中文数字与阿拉伯数字的转换并判断
                    if(Arrays.asList(CN_NUMERIC).contains(bChar)){
                        Map<Character,Integer> integerMap = PartsLibraryEnum.getButtonMap();
                        Integer integer = integerMap.get(bChar);
                        stringBuilder.append(integer);
                    }
                }
                if(remarkStr[i+1].contains(String.valueOf(stringBuilder)) && !remarkStr[i+1].contains(PartsLibraryEnum.CHEST_POUCH.getName())
                && !remarkStr[i+1].contains(PartsLibraryEnum.SLEEVE.getName())){
                    //用于锁眼和钉扣
                    buttonMap.put(PartsLibraryEnum.LAPEL.getName(),Integer.parseInt(String.valueOf(stringBuilder)));
                }
            }


            /*
            胸袋的表示形式：
            1、左胸明贴袋一个，有一粒扣
             */
            //胸袋--(会有胸袋扣，如果是一个胸袋，一定会在左边)
            if(str.contains(PartsLibraryEnum.CHEST_POUCH.getName())){
                //对于胸袋以关键字段建立哈希函数
                Map<String,List<Integer>> map = ChestPouchEnum.getVitalMap();
                //通过entrySet()来遍历
                Set<Map.Entry<String,List<Integer>>> entries = map.entrySet();
                for (Map.Entry<String,List<Integer>> entry : entries) {
                    if(str.contains(entry.getKey())){
                        List<Integer> value = entry.getValue();
                        Map<String,Integer> integerMap = ChestPouchEnum.getSpecialMap(value);
                        //把字符串分割成字符数组
                        char[] chars = str.toCharArray();
                        for (char aChar : chars) {
                            if(integerMap.containsKey(String.valueOf(aChar))){
                                //可选模块
                                optionalMap.put(PartsLibraryEnum.CHEST_POUCH.getName(),integerMap.get(String.valueOf(aChar)));
                            }else {
                                optionalMap.put(PartsLibraryEnum.CHEST_POUCH.getName(),integerMap.get(String.valueOf(aChar)));
                            }
                        }

                    }
                }
                //判断下一个字符串
                char[] chars2 = remarkStr[i+1].toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (char bChar : chars2) {
                    //中文数字与阿拉伯数字的转换并判断
                    if(Arrays.asList(CN_NUMERIC).contains(bChar)){
                        Map<Character,Integer> integerMap = PartsLibraryEnum.getButtonMap();
                        Integer integer = integerMap.get(bChar);
                        stringBuilder.append(integer);
                    }
                }
                if(remarkStr[i+1].contains(String.valueOf(stringBuilder)) && !remarkStr[i+1].contains(PartsLibraryEnum.LAPEL.getName())
                        && !remarkStr[i+1].contains(PartsLibraryEnum.SLEEVE.getName())){
                    //用于锁眼和钉扣
                    buttonMap.put(PartsLibraryEnum.CHEST_POUCH.getName(),Integer.parseInt(String.valueOf(stringBuilder)));
                }
            }

            //省


        }

        return null;
    }


}
