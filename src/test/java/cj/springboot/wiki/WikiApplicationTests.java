package cj.springboot.wiki;

import cj.springboot.wiki.antvue.table.CJTableQueryReq;
import cj.springboot.wiki.article.dao.CjArticleDao;
import cj.springboot.wiki.article.entity.CjArticleEntity;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

@SpringBootTest
class WikiApplicationTests {



    @Test
    void contextLoads() {
    }

    @Autowired
    CjArticleDao cjArticleDao;

    @Test
    public void test01() {
//        List<CjArticleEntity> articlesByRootId = cjArticleDao.getArticlesByRootId("21");
        CJTableQueryReq cjTableQueryReq = new CJTableQueryReq();
        cjTableQueryReq.setName("java");
        cjTableQueryReq.setCategoryId("21");
        List<CjArticleEntity> articlesByRootId = cjArticleDao.getArticlesByRequest(cjTableQueryReq);
        articlesByRootId.forEach(cjArticleEntity -> System.out.println(cjArticleEntity.getName()));
    }
}
