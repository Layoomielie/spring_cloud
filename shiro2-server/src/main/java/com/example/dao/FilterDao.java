package com.example.dao;

import com.example.entity.Filter;
import com.example.query.FilterExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterDao {
    long countByExample(FilterExample example);

    int deleteByExample(FilterExample example);

    int deleteByPrimaryKey(String id);

    int insert(Filter record);

    int insertSelective(Filter record);

    List<Filter> selectByExample(FilterExample example);

    Filter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Filter record, @Param("example") FilterExample example);

    int updateByExample(@Param("record") Filter record, @Param("example") FilterExample example);

    int updateByPrimaryKeySelective(Filter record);

    int updateByPrimaryKey(Filter record);
}