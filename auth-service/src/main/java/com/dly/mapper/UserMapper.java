package com.dly.mapper;

import com.dly.model.entity.User;
import com.dly.model.filter.UserFilter;
import com.dly.model.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 增加用户表
    int create(User user);

    // 修改用户表
    int update(UserFilter filter);

    // 修改密码
    int changePassword(UserFilter filter);

    // 通过邮箱修改密码
    int resetPasswordByEmail(UserFilter filter);

    // 通过邮箱修改密码
    int resetPasswordByPhone(UserFilter filter);

    // 删除用户表
    int remove(User user);

    // 查询用户表
    List<UserVo> retrieve(UserFilter filter);

    // 激活邮箱注册用户
    int activeEmailUser(String email);

}