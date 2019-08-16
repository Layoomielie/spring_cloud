package com.example.controller;

import com.example.dao.CourseRepository;
import com.example.entity.Course;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghongjian
 * @Date 2019/5/5 20:04
 * @Description
 */
@RestController
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("search")
    public List<Course> goodSearch(String q){
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<Course> infos = courseRepository.search(builder);
        List<Course> list = new ArrayList<>();
        infos.forEach(Course -> list.add(Course));
        return list;
    }

    @GetMapping("search/term")
    public List<Course> goodSearchTerm(String name){

        //自定义查询条件器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        builder.withQuery(QueryBuilders.termQuery("c_name", name));

        // 查询结果自动分页
        Page<Course> infos = courseRepository.search(builder.build());
        List<Course> goodsInfos = new ArrayList<>();
        infos.forEach(Course -> goodsInfos.add(Course));
        return goodsInfos;
    }
}
