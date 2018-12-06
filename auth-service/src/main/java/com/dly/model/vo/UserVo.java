package com.dly.model.vo;


import dly.exception.code.BasicErrCodes;
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


	protected String account;


	protected String userName;


	protected String password;


	protected String encryption;


	protected String phone;
	

	protected String email;
	

	protected String term;


	protected String status;


	protected String lastLoginIp;


	protected String lastLoginTime;


	protected String operator;


	protected String createTime;


	protected String remarks;
   
	public void validFilter()
	{
		if( account == null || account.isEmpty() ){
			throw BasicErrCodes.FILTER_IS_EMPTY.exception( "account" );
		}

		if( userName == null || userName.isEmpty() ){
			throw BasicErrCodes.FILTER_IS_EMPTY.exception( "userName" );
		}

		if( password == null || password.isEmpty() ){
			throw BasicErrCodes.FILTER_IS_EMPTY.exception( "password" );
		}

		if( status == null || status.isEmpty() ){
			throw BasicErrCodes.FILTER_IS_EMPTY.exception( "status" );
		}

		if( createTime == null || createTime.isEmpty() ){
			throw BasicErrCodes.FILTER_IS_EMPTY.exception( "createTime" );
		}
	}

}
