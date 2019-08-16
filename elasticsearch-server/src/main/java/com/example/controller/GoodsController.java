package com.example.controller;

import com.example.dao.GoodsRepository;
import com.example.entity.GoodsInfo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhanghongjian
 * @Date 2019/4/25 14:08
 * @Description
 */
@RestController
@RequestMapping("/goods/")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dates = dateFormat.format(date);

    @GetMapping("save")
    public String save() {
        GoodsInfo goodsInfo = new GoodsInfo(20, "商品" + System.currentTimeMillis(), 2, dates, "It is good goods");

        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping("saveOne")
    public String saveOne(GoodsInfo goodsInfo) {
        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping("saveAll")
    public String saveAll() {
        List<GoodsInfo> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            GoodsInfo goodsInfo = new GoodsInfo(i, "商品" + i, i, dates, "这是一个测试" + i + "商品");
            list.add(goodsInfo);
        }
        goodsRepository.saveAll(list);
        return "success";
    }

    @GetMapping("delete")
    public String delete(int id) {
        goodsRepository.deleteById(id);
        return "success";
    }

    @GetMapping("update")
    public String update(int id, String name, String description, Integer price) {
        GoodsInfo goodsInfo = new GoodsInfo(id, name, price, dates, description);
        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping("getOne")
    public Optional getOne(int id) {
        Optional<GoodsInfo> info = goodsRepository.findById(id);
        return info;
    }

    @GetMapping("getList")
    public List getList() {
        Iterable<GoodsInfo> goodsInfos = goodsRepository.findAll();
        List<GoodsInfo> list = new ArrayList<>();
        goodsInfos.forEach(goodsInfo -> list.add(goodsInfo));
        return list;
    }

    /**
     * 全文检索  根据整个类实体属性
     */
    @GetMapping("search")
    public List<GoodsInfo> goodSearch(String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<GoodsInfo> infos = goodsRepository.search(builder);
        List<GoodsInfo> list = new ArrayList<>();
        infos.forEach(goodsInfo -> list.add(goodsInfo));
        return list;
    }

    @GetMapping("search/page")
    public List<GoodsInfo> goodSearchPage(Integer page, Integer size, String q) {
        size = 1;
        PageRequest pageable = new PageRequest(page, size);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);

        Page<GoodsInfo> infos = goodsRepository.search(builder, pageable);
        Iterator<GoodsInfo> iterator = infos.iterator();
        List<GoodsInfo> list = new ArrayList<>();
        while (iterator.hasNext()) {
            GoodsInfo goodsInfo = iterator.next();
            list.add(goodsInfo);
        }
        return list;
    }

    //排序
    @GetMapping("search/sort")
    public List<GoodsInfo> goodSearchSort(Boolean boo) {
        Iterable<GoodsInfo> infos;
        if (boo) {
            infos = goodsRepository.findAll(Sort.by("price").ascending());
        } else {

            infos = goodsRepository.findAll(Sort.by("price").descending());
        }
        List<GoodsInfo> list = new ArrayList<>();
        infos.forEach(goodsInfo -> list.add(goodsInfo));
        return list;
    }

    /*
     *   term字段查询
     * */
    @GetMapping("search/term")
    public List<GoodsInfo> goodSearchTerm(String name) {

        //自定义查询条件器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        builder.withQuery(QueryBuilders.termQuery("name", name));

        // 查询结果自动分页
        Page<GoodsInfo> infos = goodsRepository.search(builder.build());
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        infos.forEach(goodsInfo -> goodsInfos.add(goodsInfo));
        return goodsInfos;
    }

    /*
     * @date: 2019/4/28
     * @Desc: match匹配查询  match会对关键词进行分词 通过分词去查询 and关系
     */
    @GetMapping("search/match")
    public List<GoodsInfo> goodSearchMatch(String name) {

        //自定义查询条件器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.matchQuery("name", name));

        QueryBuilders.matchQuery("name", "商路");
        QueryBuilders.termQuery("name", "商路");
        QueryBuilders.termsQuery("name", "商", "路");
        // 关于这三个查询器  做一下说明 有一点类似
        // match查询会把关键词分开查询 然后用and关系并联起来  例如查name字段的 商路  他会查 "商" 匹配那些数据 再查“路" 匹配那些数据 最后求并集返回
        // term查询属于比较精准的查询   他会查询包含关键词的数据   例如  商路  他只会查出name包含商路的数据
        // terms查询属于多值查询     例如 “商”，“路”  他会把name包含“商”或者包含“路”的数据查询出来  最后求并集返回
        // 就这里 查询效果来说 第一个和第三个查询出来的结果是一样的


        QueryBuilders.multiMatchQuery(name, "name", "price");
        QueryBuilders.boolQuery();
        QueryBuilders.matchAllQuery();

        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.must();   // and 关系
        queryBuilder.mustNot();   //  条件为非
        queryBuilder.should();     // or关系
        queryBuilder.filter();    //添加过滤器

        //构建分页查询
        builder.withPageable(PageRequest.of(0, 3));

        Page<GoodsInfo> infos = goodsRepository.search(builder.build());
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        infos.forEach(goodsInfo -> goodsInfos.add(goodsInfo));
        return goodsInfos;
    }

    @GetMapping("search/id")
    public void goodSearchById(String id) {

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
    }

    /**
     * 模糊查询
     */
    @GetMapping("search/wild")
    public List<GoodsInfo> goodSearchWild(String name) {
        //自定义查询条件器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.wildcardQuery("name", name + "*"));
        // 高亮
        builder.withHighlightFields(new HighlightBuilder.Field("price"));


        //   builder.withFilter(builder.withFields())
        Page<GoodsInfo> infos = goodsRepository.search(builder.build());
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        infos.forEach(goodsInfo -> goodsInfos.add(goodsInfo));
        return goodsInfos;
    }

    /*
     * @date: 2019/4/28
     * @Desc: 排序查询
     */
    @GetMapping("search/order")
    public List<GoodsInfo> goodSearchOrder() {
        //自定义查询条件器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        FieldSortBuilder price = SortBuilders.fieldSort("price").order(SortOrder.DESC).unmappedType("");

        builder.withSort(price);


        Page<GoodsInfo> infos = goodsRepository.search(builder.build());
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        infos.forEach(goodsInfo -> goodsInfos.add(goodsInfo));
        return goodsInfos;
    }

    /*
     * @date: 2019/4/28
     * @Desc: 多字段匹配一个值
     */
    public List<GoodsInfo> goodSearchMulti(String name) {
        //自定义查询条件器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        //多字段匹配
        QueryBuilders.multiMatchQuery(name, "name", "price");
        Page<GoodsInfo> infos = goodsRepository.search(builder.build());
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        infos.forEach(goodsInfo -> goodsInfos.add(goodsInfo));
        return goodsInfos;
    }


    @GetMapping("search/filter")
    public List goodSearchFilter() {
        PageRequest pageable = new PageRequest(0, 9999);
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        List<QueryBuilder> should = boolQueryBuilder.should();

        should.add(QueryBuilders.termQuery("name", "商品"));
        builder.withPageable(pageable);
        builder.withFilter(boolQueryBuilder);
        long startTime = System.currentTimeMillis();
        Page<GoodsInfo> infos = goodsRepository.search(builder.build());
        long endTime = System.currentTimeMillis();
        System.out.println("filter时间为   ： " + (endTime - startTime));
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        infos.forEach(goodsInfo -> goodsInfos.add(goodsInfo));
        return goodsInfos;
    }

    @GetMapping("search/filter2")
    public List goodSearchTerm() {

        PageRequest pageable = new PageRequest(0, 9999);

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        List<QueryBuilder> should = boolQueryBuilder.should();

        should.add(QueryBuilders.termQuery("name", "商品"));
        builder.withPageable(pageable);
        builder.withQuery(boolQueryBuilder);
        long startTime = System.currentTimeMillis();
        Page<GoodsInfo> infos = goodsRepository.search(builder.build());
        long endTime = System.currentTimeMillis();
        System.out.println("filter2时间为   ： " + (endTime - startTime));
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        infos.forEach(goodsInfo -> goodsInfos.add(goodsInfo));


        return goodsInfos;
    }
}
