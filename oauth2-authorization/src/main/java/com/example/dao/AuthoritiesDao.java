package com.example.dao;

import com.example.entity.Authorities;
import com.example.query.AuthoritiesExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesDao {
    long countByExample(AuthoritiesExample example);

    int deleteByExample(AuthoritiesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Authorities record);

    int insertSelective(Authorities record);

    List<Authorities> selectByExample(AuthoritiesExample example);

    Authorities selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Authorities record, @Param("example") AuthoritiesExample example);

    int updateByExample(@Param("record") Authorities record, @Param("example") AuthoritiesExample example);

    int updateByPrimaryKeySelective(Authorities record);

    int updateByPrimaryKey(Authorities record);
}