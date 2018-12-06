package com.dly.mapper;

import com.dly.model.entity.Role;
import com.dly.model.filter.RoleFilter;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleFilter example);

    int deleteByExample(RoleFilter example);

    int deleteByPrimaryKey(String uuid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleFilter example);

    Role selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleFilter example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleFilter example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}