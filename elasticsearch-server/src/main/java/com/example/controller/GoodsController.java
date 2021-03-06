package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dao.GoodsRepository;
import com.example.entity.GoodsInfo;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.template.GetTemplate;
import io.searchbox.indices.template.PutTemplate;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private JestClient jestClient;

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dates = dateFormat.format(date);


    @GetMapping("save")
    public String save() {
        GoodsInfo goodsInfo = new GoodsInfo(20, "商品" + System.currentTimeMillis(), 2, dates, "It is good goods");

        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping("saveIndex")
    public String saveAsIndex(String indexName) {
        List<IndexQuery> list = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            GoodsInfo goodsInfo = new GoodsInfo(i, "indexName:" + indexName + " 商品" + i, i, dates, "这是一个测试" + i + "商品");
            IndexQuery indexQuery = new IndexQueryBuilder().withId(goodsInfo.getId() + "").withObject(goodsInfo).withIndexName(indexName).withType("doc").build();
            list.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(list);
        elasticsearchTemplate.refresh(indexName);
        return "success";
    }

    @GetMapping("saveAll")
    public String saveAll() {
        List<GoodsInfo> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
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

    @GetMapping("search/all")
    public List<GoodsInfo> goodSearchAll(int page, int size) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.rangeQuery("id").lt(20));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        PageRequest pageRequest = PageRequest.of(page, size);
        FieldSortBuilder sort = SortBuilders.fieldSort("id");
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withSort(sort).withPageable(pageRequest).build();
        List<GoodsInfo> goodsInfos = elasticsearchTemplate.queryForList(nativeSearchQuery, GoodsInfo.class);
        System.out.println(goodsInfos.size());
       /* SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch("goods-a").setQuery(boolQueryBuilder);
        SearchResponse searchResponse = searchRequestBuilder.get();
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String index = hit.getIndex();
            System.out.println(index);
        }*/
        return goodsInfos;
    }

    @GetMapping("update/index")
    public int goodsUpdateQuery(String id) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("goods-a");
        updateRequest.type("doc");
        updateRequest.id(id);
        updateRequest.doc(XContentFactory.jsonBuilder().startObject().field("detail", "222222").endObject());
        UpdateResponse updateResponse = elasticsearchTemplate.getClient().update(updateRequest).actionGet();
        RestStatus status = updateResponse.status();
        return status.getStatus();
    }

    @GetMapping("template/add")
    public void templateAdd() throws IOException {
        String source;
        XContentBuilder mapBuilder = null;
        String A="{\n" +
                "    \"order\" : 0,\n" +
                "    \"version\" : 60001,\n" +
                "    \"index_patterns\" : [\n" +
                "      \"A\"\n" +
                "    ],\n" +
                "    \"settings\" : {\n" +
                "      \"index\" : {\n" +
                "        \"number_of_shards\" : \"5\",\n" +
                "        \"number_of_replicas\" : \"1\",\n" +
                "        \"refresh_interval\" : \"3s\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"mappings\" : {\n" +
                "      \"doc\" : {\n" +
                "        \"dynamic_templates\" : [\n" +
                "          {\n" +
                "            \"message_field\" : {\n" +
                "              \"path_match\" : \"message\",\n" +
                "              \"match_mapping_type\" : \"string\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"text\",\n" +
                "                \"norms\" : false\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"tbName_fields\" : {\n" +
                "              \"match\" : \"tbName\",\n" +
                "              \"match_mapping_type\" : \"string\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"keyword\",\n" +
                "                \"norms\" : false\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"timestamp_fields\" : {\n" +
                "              \"match\" : \"timestamp\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"date\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"time_fields\" : {\n" +
                "              \"match\" : \"time\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"date\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"createTime_fields\" : {\n" +
                "              \"match\" : \"createTime\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"date\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"datestamp_fields\" : {\n" +
                "              \"match\" : \"date\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"date\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"date_fields\" : {\n" +
                "              \"match\" : \"*Date\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"date\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"time_fields\" : {\n" +
                "              \"match\" : \"*Time\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"date\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"integer_fields\" : {\n" +
                "              \"match_mapping_type\" : \"long\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"long\",\n" +
                "                \"norms\" : false\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"string_fields\" : {\n" +
                "              \"match\" : \"*\",\n" +
                "              \"match_mapping_type\" : \"string\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"keyword\",\n" +
                "                \"ignore_above\" : 1000,\n" +
                "                \"norms\" : false\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        ],\n" +
                "        \"properties\" : {\n" +
                "          \"geoip\" : {\n" +
                "            \"dynamic\" : true,\n" +
                "            \"properties\" : {\n" +
                "              \"ip\" : {\n" +
                "                \"type\" : \"ip\"\n" +
                "              },\n" +
                "              \"location\" : {\n" +
                "                \"type\" : \"geo_point\"\n" +
                "              },\n" +
                "              \"latitude\" : {\n" +
                "                \"type\" : \"half_float\"\n" +
                "              },\n" +
                "              \"longitude\" : {\n" +
                "                \"type\" : \"half_float\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"aliases\" : { }\n" +
                "  }";
        try {
            mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder.startObject().field("template", "df_returnreport*").field("order", 1)//
                    .startObject("settings").field("number_of_shards", 5)//五个分片
                    .startObject("index").field("max_result_window", "1000000")//一次查询最大一百万
                    .endObject()//
                    .endObject()//
                    .startObject("mappings")//

                    .startObject("df_returnreport")//type名
                    .startObject("properties")//
                    .startObject("id").field("type", "long").endObject()//
                    .startObject("username").field("type", "keyword").endObject()//
                    .startObject("content").field("type", "text").field("analyzer", "ik_max_word").endObject()//
                    .startObject("returntime").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()//
                    .startObject("gateway").field("type", "integer").endObject()//
                    .endObject()//
                    .endObject()//

                    .endObject()//
                    .startObject("aliases").startObject("df_returnreport").endObject().endObject()//别名
                    .endObject();//
            source = mapBuilder.getOutputStream().toString();
            JestResult jestResult = jestClient.execute(new PutTemplate.Builder("my_returnreport", A).build());
            System.out.println("result:" + jestResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("template/get")
    public void getTemplate(String template) {
        try {
            JestResult jestResult = jestClient.execute(new GetTemplate.Builder(template).build());
            System.out.println("result:" + jestResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @GetMapping("jest/get")
    public void getJestSearch() {
        try {
            String A="{\n" +
                    "  \"query\": {\n" +
                    "    \"range\": {\n" +
                    "      \"id\": {\n" +
                    "        \"lte\": 10\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}'";
            Search build = new Search.Builder(A).addIndex("goods-a").build();
            SearchResult jestResult = jestClient.execute(build);
            System.out.println("result:" + jestResult.getJsonString());
            List<SearchResult.Hit<JSONObject, Void>> hits = jestResult.getHits(JSONObject.class);
            hits.forEach(hit->{
                System.out.println(hit.index);
                System.out.println(hit.source);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    @GetMapping("query/page")
    public List<GoodsInfo> goodQueryPage() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.rangeQuery("id").lt(20));
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        AggregatedPage<GoodsInfo> goodsInfos = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), GoodsInfo.class);
        goodsInfos.forEach(goodsInfo -> {
            System.out.println(goodsInfo.toString());
        });
        return null;
    }

    @GetMapping("search/after")
    public Object goodSearchAfter(String... sortValues) {
        long startTime = System.currentTimeMillis();
        SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch("goods");
        searchRequestBuilder = searchRequestBuilder.addSort(SortBuilders.fieldSort("price")).addSort(SortBuilders.fieldSort("id")).addSort(SortBuilders.fieldSort("_id").order(SortOrder.ASC));
        searchRequestBuilder.setSize(10);
        if (sortValues != null) {
            searchRequestBuilder.searchAfter(sortValues);
        }
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHit[] hits = response.getHits().getHits();
        Object[] sortValue = null;
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            System.out.println(sourceAsString);

            if (i == hits.length - 1) {
                sortValue = hits[i].getSortValues();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return sortValue;
    }

    @GetMapping("search/entity")
    public Object goodSearchEntity() {
        long startTime = System.currentTimeMillis();
        SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch("goods");
        searchRequestBuilder = searchRequestBuilder.addSort(SortBuilders.fieldSort("price")).addSort(SortBuilders.fieldSort("id")).addSort(SortBuilders.fieldSort("_id").order(SortOrder.ASC));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price")).withSort(SortBuilders.fieldSort("id")).withSort(SortBuilders.fieldSort("_id").order(SortOrder.ASC));
        PageRequest page = PageRequest.of(800, 10);
        nativeSearchQueryBuilder.withPageable(page);
        AggregatedPage<GoodsInfo> goodsInfos = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), GoodsInfo.class);
        goodsInfos.forEach(goodsInfo -> {
            System.out.println(goodsInfo);
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return null;
    }

    @GetMapping("search/scroll")
    public void goodSearchScroll() {
        Client client = elasticsearchTemplate.getClient();

        FieldSortBuilder sortBuilder = SortBuilders.fieldSort("type").order(SortOrder.DESC);

        SearchResponse response = client.prepareSearch("goods").setTypes("doc").addSort(SortBuilders.fieldSort("price")).addSort(SortBuilders.fieldSort("_doc")).addSort(SortBuilders.fieldSort("price"))
                .setSize(10).setScroll(TimeValue.timeValueMinutes(2)).execute()
                .actionGet();

        System.out.println("当前hits数量为：  " + response.getHits().getHits().length);
        int i = 1;
        System.out.println(response.getScrollId());
        System.out.println("---------------------------------");

        while (response.getHits().getHits().length > 0) {
            String scrollId = response.getScrollId();
            i++;
            if (i > 5) {
                return;
            }
            response = client.prepareSearchScroll(scrollId)
                    .setScroll(TimeValue.timeValueMinutes(2))//设置查询context的存活时间
                    .execute()
                    .actionGet();
            List<GoodsInfo> list = new ArrayList<>();
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits.getHits()) {
                String hitString = hit.getSourceAsString();
                GoodsInfo goodsInfo = JSON.parseObject(hitString, GoodsInfo.class);
                list.add(goodsInfo);
            }

            list.forEach(goodsInfo -> System.out.println(goodsInfo.toString()));
            System.out.println("---------------------------------");
        }

        //  list.forEach(goodsInfo -> System.out.println(goodsInfo.toString()));
//        ClearScrollRequest request = new ClearScrollRequest();
//        request.addScrollId(response.getScrollId());
//        client.clearScroll(request);
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

    @GetMapping("search/terms")
    public List<GoodsInfo> goodSearchTerms(String name) {

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
