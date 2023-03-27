package com.service;

import com.Model.SleeveBodyPlacketSewModel;

import java.util.List;

public interface SleeveBodyPlacketSewService {
    void addSleeveBodyPlacketSew(List<SleeveBodyPlacketSewModel> sleeveBodyPlacketSewModels);

    void updateSleeveBodyPlacketSew(List<SleeveBodyPlacketSewModel> sleeveBodyPlacketSewModels);

    List<SleeveBodyPlacketSewModel> getListByMainId(Long mainId);

    List<SleeveBodyPlacketSewModel> getListByMainIdList(List<Long> mainIdList);

    void delete(List<SleeveBodyPlacketSewModel> list);

}
