package com.service;

import com.Model.ShirtLapelModel;
import com.assembly.oauth.web.BaseService;

import java.util.List;

public interface ShirtLapelService  {
    void addLapel(ShirtLapelModel shirtLapelModel);

    ShirtLapelModel findByRemark(Integer sexName, String sizeInfo, Integer lapelCharacter);

    List<String> getLapelEnumName();

}
