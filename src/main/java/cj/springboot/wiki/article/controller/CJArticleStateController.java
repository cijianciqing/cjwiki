package cj.springboot.wiki.article.controller;

import cj.springboot.wiki.article.entity.CJArticleState;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wiki/article/state")
public class CJArticleStateController {
    @RequestMapping(value = "/list")
    public CJAjaxResult getAllState(){
        List<String> states = new ArrayList<>();
        CJArticleState[] cjArticleStates = CJArticleState.values();
        for (CJArticleState cjArticleState : cjArticleStates) {
            states.add(cjArticleState.getArticleState());
        }
        return CJAjaxResult.success("CJ 返回成功",states);
    }
}
