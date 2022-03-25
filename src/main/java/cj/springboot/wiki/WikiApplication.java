package cj.springboot.wiki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cj.springboot.wiki.dao",
        "cj.springboot.wiki.article.dao",
        "cj.springboot.wiki.wangeditor.dao",
        "cj.springboot.wiki.security.rbac.dao",
        "cj.springboot.wiki.mission.dao"
})
public class WikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikiApplication.class, args);
    }

}
