package com.mapper;

import com.Model.BackPieceSewModel;
import com.Model.ChestPouchSewModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ChestPouchSewMapper extends MyMapper<ChestPouchSewModel> {

}
