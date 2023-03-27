package com.mapper;

import com.Model.ShirtSleeveCuffModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShirtSleeveCuffMapper extends MyMapper<ShirtSleeveCuffModel> {

}
