package com.service;

import com.Model.ShirtFrontPieceModel;

import java.util.List;

public interface ShirtFrontPieceService {
    void addFrontPiece(ShirtFrontPieceModel shirtFrontPieceModel);

    void deleteById(Long id);

    ShirtFrontPieceModel findByRemark(Integer sexName, String sizeInfo, Integer frontPieceFeatures);

    List<String> getFrontPieceEnumName();

}
