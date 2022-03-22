package cj.springboot.wiki.wangeditor.controller;

import cj.springboot.wiki.wangeditor.dto.CJWangEditorImageReturn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wiki/wangeditor")
public class CJWangEditorController {
    @RequestMapping(value = "/uploadImg")
    public CJWangEditorImageReturn test(){

        return null;
    }
}
