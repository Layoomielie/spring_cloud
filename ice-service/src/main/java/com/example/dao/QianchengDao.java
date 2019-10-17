package com.example.dao;

import com.example.entity.Qiancheng;
import com.example.entity.QianchengExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QianchengDao {
    long countByExample(QianchengExample example);

    int deleteByExample(QianchengExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Qiancheng record);

    int insertSelective(Qiancheng record);

    List<Qiancheng> selectByExample(QianchengExample example);

    Qiancheng selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Qiancheng record, @Param("example") QianchengExample example);

    int updateByExample(@Param("record") Qiancheng record, @Param("example") QianchengExample example);

    int updateByPrimaryKeySelective(Qiancheng record);

    int updateByPrimaryKey(Qiancheng record);
}