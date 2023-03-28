package com.mapper;

import com.Model.ShirtPleatModel;
import com.Model.ShirtProvinceModel;
import com.assembly.oauth.web.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShirtProvinceMapper extends MyMapper<ShirtProvinceModel> {

}
