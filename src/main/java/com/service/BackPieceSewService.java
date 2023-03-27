package com.service;

import com.Model.BackPieceSewModel;

import java.util.List;

public interface BackPieceSewService {
    void addBackPieceSew(List<BackPieceSewModel> backPieceSewModels);

    List<BackPieceSewModel> getListByMainId(Long id);

}
