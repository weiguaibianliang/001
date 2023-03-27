package com.service;

import com.Model.FrontPieceSewModel;

import java.util.List;

public interface FrontPieceSewService {
    void addFrontPieceSew(List<FrontPieceSewModel> shirtFrontPieceModels);


    void deleteByMainId(Long mainId);

}
