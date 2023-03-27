package com.mapper;

import com.Model.ShirtOverShoulderModel;
import com.Model.ShirtSleeveBodyPlacketModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShirtSleeveBodyPlacketMapper extends MyMapper<ShirtSleeveBodyPlacketModel> {

}
