package com.dly.auth.mapper;

import com.dly.auth.model.entity.Dictionary;

public interface DictionaryMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
}