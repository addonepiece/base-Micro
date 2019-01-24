package com.dly.auth.mapper;

import com.dly.auth.model.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryMapper {

    // 查询全部数据字典类型
    List<Dictionary> getAllDictionaryTypes(Dictionary dictionary);

    // auto generator
    int deleteByPrimaryKey(String uuid);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
}