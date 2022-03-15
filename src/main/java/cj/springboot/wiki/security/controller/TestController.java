package cj.springboot.wiki.security.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.validation.Valid;

//import javax.validation.Valid;

@Slf4j
@RestController
public class TestController {

    @RequestMapping(value ="/test")
    public void testController(){
        System.out.println("cj.springboot.security.template.controller.test.TestController.testController....");
    }

    @RequestMapping(value ="/cjsec/test1")
    public void testController01(){
        System.out.println("cj.springboot.security.template.controller.test.TestController.testController01....");
    }
    @RequestMapping(value = "/cjsec/test2")
    public void testController02(){
        System.out.println("cj.springboot.security.template.controller.test.TestController.testController02....");
    }


    @RequestMapping(value = "/cjsec/test3")
    public String testController03(){
        System.out.println("cj.springboot.security.template.controller.test.TestController.testController03....");
        return "CJLogin3";
    }



}
