package com.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class PartsLibraryDTO {

    //基本模块
    private  Map<String, Integer> basicMap;

    //可选模块
    private  Map<String, Integer> optionalMap;

    //特殊模块
    private  Map<String, Integer> specialMap;

    //锁眼和钉扣个数
    private  Map<String, Integer> buttonMap;

    //省的个数
    private  Map<String, Integer> provinceMap;

    //褶裥的个数
    private Map<String,Integer> pleatMap;


}
