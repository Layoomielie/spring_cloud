package com.example.dao;

import com.example.entity.SecurityMatcher;
import com.example.query.SecurityMatcherExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SecurityMatcherDao {
    long countByExample(SecurityMatcherExample example);

    int deleteByExample(SecurityMatcherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SecurityMatcher record);

    int insertSelective(SecurityMatcher record);

    List<SecurityMatcher> selectByExample(SecurityMatcherExample example);

    SecurityMatcher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SecurityMatcher record, @Param("example") SecurityMatcherExample example);

    int updateByExample(@Param("record") SecurityMatcher record, @Param("example") SecurityMatcherExample example);

    int updateByPrimaryKeySelective(SecurityMatcher record);

    int updateByPrimaryKey(SecurityMatcher record);
}