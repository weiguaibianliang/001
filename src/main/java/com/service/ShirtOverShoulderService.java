package com.service;

import com.Model.ShirtOverShoulderModel;

import java.util.List;

public interface ShirtOverShoulderService {
    void addOverShoulder(ShirtOverShoulderModel shirtOverShoulderModel);

    List<String> getOverShoulderEnumName();

}
