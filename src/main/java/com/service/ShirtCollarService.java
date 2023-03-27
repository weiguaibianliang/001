package com.service;

import com.Model.ShirtCollarModel;

import java.util.List;

public interface ShirtCollarService {


    void addCollar(ShirtCollarModel shirtCollarModel);

    List<String> getCollEnumName();

}
