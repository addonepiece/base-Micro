package com.dly.service;


import com.dly.model.entity.User;
import com.dly.model.filter.UserFilter;
import com.dly.model.vo.UserVo;
import dly.RESTful.ReqObject;
import dly.RESTful.ReqQuery;
import dly.RESTful.ResList;

/**
 * 
 * 用户表
 *
 */
public interface UserService {
	// 增加用户表
	User create(ReqObject<User> data);

	// 修改用户表
	UserFilter update(ReqObject<UserFilter> data);

	// 删除用户表
	Integer remove(ReqObject<User> data);

	// 查询用户表
	ResList<UserVo> retrieve(ReqObject<ReqQuery<UserFilter>> dta);
	

}
