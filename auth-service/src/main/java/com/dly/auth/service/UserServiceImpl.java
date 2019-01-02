package com.dly.auth.service;

import com.dly.auth.mapper.UserMapper;
import com.dly.auth.model.entity.User;
import com.dly.auth.model.filter.UserFilter;
import com.dly.auth.model.vo.UserVo;
import com.dly.RESTful.ReqObject;
import com.dly.RESTful.ReqQuery;
import com.dly.RESTful.ResList;
import com.dly.exception.code.BasicErrCodes;
import com.dly.id.IDGenerator;
import com.dly.util.DateUtil;
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

	// 新增用户
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public User create(ReqObject<User> data) {
		User user = data.getObject();
		String uuid = user.getUuid();
		if (uuid == null || uuid.isEmpty()) {
			user.setUuid(IDGenerator.UUID.generate());
			user.setCreateTime(DateUtil.longDateTime());
		}
		// 验证必填项
		user.validRequireItem();
		try {
			userMapper.create(user);
		} catch (Exception e) {
			String cause = e.getCause().toString();
			// 重复条目
			if (cause.contains("Duplicate entry")) {
				throw BasicErrCodes.DAO_INSERT_DUPLICATE.exception(e);
			} else {
				throw BasicErrCodes.DAO_INSERT_ERROR.exception(e);
			}
		}

		return user;
	}

	// 查询用户表
	@Override
	public ResList<UserVo> retrieve(ReqObject<ReqQuery<UserFilter>> filter) {
		ReqQuery<UserFilter> query = filter.getObject();

		UserFilter qry = query.getObject();

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

}
