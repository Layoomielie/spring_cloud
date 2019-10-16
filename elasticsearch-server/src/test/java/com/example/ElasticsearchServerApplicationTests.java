package com.example;

import com.example.dao.AggregationDao;
import com.example.entity.GoodsInfo;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchServerApplicationTests {

    @Autowired
    AggregationDao aggregationDao;

    @Autowired
    JestClient jestClient;

    @Test
    public void index() {


        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setDescription("AAAAA");
        goodsInfo.setId(000001);
        goodsInfo.setName("AAAAA");
        goodsInfo.setPrice(20);
        Index build = new Index.Builder(goodsInfo).index("jest").type("doc").id(goodsInfo.getId()+"").build();
        try {
            jestClient.execute(build);
            System.out.println("数据索引成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void search(){
        //查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"name\" : \"AAAAA\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("jest").addType("doc").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
