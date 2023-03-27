package com.mapper;

import com.Model.CollarSewModel;
import com.Model.ShirtCollarModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CollarSewMapper extends MyMapper<CollarSewModel> {

}
