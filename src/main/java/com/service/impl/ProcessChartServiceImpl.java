package com.service.impl;

import com.DTO.ProcessChartDTO;
import com.Enum.PartsLibraryEnum;
import com.Model.*;
import com.alibaba.druid.sql.visitor.functions.Char;
import com.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProcessChartServiceImpl implements ProcessChartService {

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
        String[] remarkStr = remark.split("、");
        for (String str : remarkStr) {
            if(str.contains(PartsLibraryEnum.COLLAR.getName())){
                //筛选领子中的特征信息

            }

        }

        return null;
    }


}
