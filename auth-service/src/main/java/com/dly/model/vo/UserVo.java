package com.dly.model.vo;

import lombok.Data;

/**
 * 
 * 用户表
 *
 */
@Data
public class UserVo
{

	protected String uuid;

	// 账号
	protected String account;

	// 用户名
	protected String userName;

	// 密码
	protected String password;

	// 新密码
	protected String newPassword;

	// 昵称
	private String nickname;

	// 电话
	protected String phone;

	// 电子邮箱
	protected String email;

	// 终端类型
	protected String term;

	// 状态
	protected String status;

	// 上一次登录的ip地址
	protected String lastLoginIp;

	// 上一次登录时间
	protected String lastLoginTime;

	// 操作人员
	protected String operator;

	// 创建时间
	protected String createTime;

	// 备注
	protected String remarks;

}
