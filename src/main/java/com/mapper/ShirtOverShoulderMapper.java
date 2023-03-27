package com.mapper;

import com.Model.OverShoulderSewModel;
import com.Model.ShirtOverShoulderModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShirtOverShoulderMapper extends MyMapper<ShirtOverShoulderModel> {

}
