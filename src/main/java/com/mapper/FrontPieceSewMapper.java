package com.mapper;

import com.Model.FrontPieceSewModel;
import com.Model.ShirtFrontPieceModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FrontPieceSewMapper extends MyMapper<FrontPieceSewModel> {

}
