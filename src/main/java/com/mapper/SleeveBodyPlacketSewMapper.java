package com.mapper;

import com.Model.ShirtSleeveBodyPlacketModel;
import com.Model.SleeveBodyPlacketSewModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SleeveBodyPlacketSewMapper extends MyMapper<SleeveBodyPlacketSewModel> {

    void batchUpdate(List<SleeveBodyPlacketSewModel> list);

}
