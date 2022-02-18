package cj.springboot.wiki.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/*
* 自定义Filter示例，暂时禁用
* */
//@Configuration
public class CJServletConfig {
    @Bean
    public FilterRegistrationBean timeFilter() {

        FilterRegistrationBean registrationan = new FilterRegistrationBean();

        CJTimeFilter timeFilter = new CJTimeFilter();

        registrationan.setFilter(timeFilter);


        List<String> urls = new ArrayList<>();

        urls.add("/*");

        registrationan.setUrlPatterns(urls);

        return registrationan;

    }
}
