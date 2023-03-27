package com.mapper;

import com.Model.BackPieceSewModel;
import com.Model.FrontPieceSewModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BackPieceSewMapper extends MyMapper<BackPieceSewModel> {

}
