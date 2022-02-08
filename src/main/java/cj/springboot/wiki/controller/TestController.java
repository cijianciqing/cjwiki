package cj.springboot.wiki.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试 前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-02-08
 */
@RestController
public class TestController {

    @RequestMapping(value = "/wiki/tttt")
    public String test(){
        return "this is test in cj.springboot.wiki.controller.TestController....";
    }
}
