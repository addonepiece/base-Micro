package com.dly.service;

import com.dly.mapper.UserMapper;
import com.dly.model.entity.User;
import com.dly.model.filter.UserFilter;
import com.dly.model.vo.UserVo;
import dly.RESTful.ReqObject;
import dly.RESTful.ReqQuery;
import dly.RESTful.ResList;
import dly.exception.code.BasicErrCodes;
import dly.id.IDGenerator;
import dly.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 
 * 用户表
 *
 */
@Service
public class UserServiceImpl implements UserService {
	protected static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	@Autowired
	UserMapper userMapper;

	// 增加用户表
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	public User create(ReqObject<User> data) {
		User user = data.getObject();
		String uuid = user.getUuid();
		if (uuid == null || uuid.isEmpty()) {
			user.setUuid(IDGenerator.UUID.generate());
		}

		try {
			userMapper.create(user);
		} catch (Exception e) {
			String cause = e.getCause().toString();
			if (cause.contains("Duplicate entry")) {
				throw BasicErrCodes.DAO_INSERT_DUPLICATE.exception(e);
			} else {
				throw BasicErrCodes.DAO_INSERT_ERROR.exception(e);
			}
		}

		return user;
	}

	// 修改用户表
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	public UserFilter update(ReqObject<UserFilter> data) {
		UserFilter filter = data.getObject();

		userMapper.update(filter);
		return filter;
	}

	// 删除用户表
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	public Integer remove(ReqObject<User> data) {
		User user = data.getObject();
		return userMapper.remove(user);
	}

	// 查询用户表
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	public ResList<UserVo> retrieve(ReqObject<ReqQuery<UserFilter>> filter) {
		ReqQuery<UserFilter> query = filter.getObject();

		UserFilter qry = query.getObject();
		if (qry.getPassword() != null && !qry.getPassword().isEmpty()) {
			qry.setPassword(MD5.getMessageDigest(qry.getPassword()));
		}

		ResList<UserVo> resList;

		int startPage = query.getStartPage();
		int pageRow = query.getPageRow();
		if (startPage == 0 && pageRow == 0) {
			List<UserVo> list = userMapper.retrieve(qry);
			resList = new ResList<>(list);
			if (list.size() > ReqQuery.totalCount) {
				throw BasicErrCodes.DATASET_TOO_LARGE.exception(String.valueOf(list.size()),
						String.valueOf(ReqQuery.totalCount));
			}
		} else {
			PageHelper.startPage(startPage, pageRow);
			List<UserVo> list = userMapper.retrieve(qry);
			PageInfo<UserVo> page = new PageInfo<>(list);

			resList = new ResList<>(list);
			resList.setStartPage(startPage);
			resList.setPageRow(pageRow);
			resList.setTotalRow(page.getTotal());
		}

		return resList;
	}

}
