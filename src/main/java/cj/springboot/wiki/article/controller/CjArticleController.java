package cj.springboot.wiki.article.controller;


import cj.springboot.wiki.antvue.radio.CJVueRadio;
import cj.springboot.wiki.antvue.table.CJTableQueryReq;
import cj.springboot.wiki.antvue.table.CJTableResponse;
import cj.springboot.wiki.article.dto.CJArticleTransfer;
import cj.springboot.wiki.article.dto.CjArticleSaveRequest;
import cj.springboot.wiki.article.entity.CJArticleState;
import cj.springboot.wiki.article.service.CjArticleService;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
@Slf4j
@RestController
@RequestMapping("/wiki/article/article")
public class CjArticleController {

    @Autowired
    CjArticleService cjArticleService;

    @GetMapping(value = "/sate")
    public CJAjaxResult getState(){
        List<CJVueRadio> states = new ArrayList<>();
        for (CJArticleState sate : CJArticleState.values()) {
            CJVueRadio cjVueRadio = new CJVueRadio(sate.getStateDesc(),sate.getArticleState());
            states.add(cjVueRadio);
        }

        return CJAjaxResult.success(states);
    }

    @RequestMapping(value = "/list")
    public CJAjaxResult getCategories(){
        List<CJArticleTransfer> list = cjArticleService.getArticles();
        return  CJAjaxResult.success("成功返回", list);
    }

    //查询 article - table
    @PostMapping(value = "/table")
    public CJAjaxResult getEbookList(@Valid @RequestBody CJTableQueryReq tableQueryReq){
        CJTableResponse<CJArticleTransfer> pageInfo = cjArticleService.getArticleTable(tableQueryReq);
//        PageInfo<CJArticleTransfer> pageInfo = new PageInfo<>(artcleList);

        log.info("ArtcieTable总行数：{}", pageInfo.getTotal());

        return CJAjaxResult.success(pageInfo);
    }

    //添加 或者 更新article
    @PostMapping("/save")
    public CJAjaxResult save(@Valid @RequestBody CjArticleSaveRequest req) {
        cjArticleService.save(req);
        return CJAjaxResult.success("添加Ebook成功");
    }

    //删除特定article
    @DeleteMapping("/delete/{id}")
    public CJAjaxResult delete(@PathVariable String id) {
        cjArticleService.delete(id);
        return CJAjaxResult.success("删除Ebook成功");
    }
}
