package com.service;

import com.Model.ShirtBackPieceModel;

import java.util.List;

public interface ShirtBackPieceService {

    void addBackPiece(ShirtBackPieceModel shirtBackPieceModel);

    void updateById(Long id);

    ShirtBackPieceModel findByRemark(Integer sexName, String sizeInfo, Integer backPleatShapeCharacter);

    List<String> getBackPieceEnumName();

}
