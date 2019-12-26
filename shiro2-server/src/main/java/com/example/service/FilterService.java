package com.example.service;

import com.example.dao.FilterDao;
import com.example.entity.Filter;
import com.example.query.FilterExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/26 11:00
 * @desc：
 **/
@Service
public class FilterService {

    @Autowired
    private FilterDao filterDao;

    public Optional<List<Filter>> getFilterListByStatus(List<Integer> list) {
        FilterExample filterExample = new FilterExample();
        FilterExample.Criteria criteria = filterExample.createCriteria();
        criteria.andStatusIn(list);
        List<Filter> filters = filterDao.selectByExample(filterExample);
        return Optional.of(filters);
    }
}
