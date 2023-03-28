package com.mapper;

import com.Model.ShirtCollarModel;
import com.Model.ShirtPleatModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShirtPleatMapper extends MyMapper<ShirtPleatModel> {

}
