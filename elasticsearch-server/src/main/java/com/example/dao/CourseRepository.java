package com.example.dao;

import com.example.entity.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhanghongjian
 * @Date 2019/5/5 20:02
 * @Description
 */
public interface CourseRepository  extends ElasticsearchRepository<Course,String> {

}
