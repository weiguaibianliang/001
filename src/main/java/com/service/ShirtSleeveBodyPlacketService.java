package com.service;

import com.Model.ShirtSleeveBodyPlacketModel;

import java.util.List;

public interface ShirtSleeveBodyPlacketService {
    void addSleeveBodyPlacket(ShirtSleeveBodyPlacketModel shirtSleeveBodyPlacketModel);


    void updateSleeveBodyPlacket(ShirtSleeveBodyPlacketModel shirtSleeveBodyPlacketModel);

    List<String> getLongSleeveEnumName();

    List<String> getSevenEnumName();

    List<String> getFiveSleeveEnumName();

    List<String> getShortSleeveEnumName();

}
