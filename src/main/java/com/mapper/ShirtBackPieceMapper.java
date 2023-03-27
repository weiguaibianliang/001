package com.mapper;

import com.Model.ShirtBackPieceModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShirtBackPieceMapper extends MyMapper<ShirtBackPieceModel> {


}
