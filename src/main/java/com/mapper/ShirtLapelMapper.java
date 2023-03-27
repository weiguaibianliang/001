package com.mapper;

import com.Model.ShirtFrontPieceModel;
import com.Model.ShirtLapelModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShirtLapelMapper extends MyMapper<ShirtLapelModel> {

}
