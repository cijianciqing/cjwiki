package cj.springboot.wiki.article.controller;


import cj.springboot.wiki.antvue.dto.CJVueTreeNode;
import cj.springboot.wiki.antvue.treeSelect.CJVueTreeSelectNode;
import cj.springboot.wiki.article.service.CjArticleCategoryService;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/wiki/article/category")
public class CjArticleCategoryController {
    @Autowired
    CjArticleCategoryService cjArticleCategoryService;

    @RequestMapping(value = "/list")
    public CJAjaxResult getCategories(){
        CJVueTreeNode cjVueTreeNode = cjArticleCategoryService.getCategories();
        return  CJAjaxResult.success("成功返回", cjVueTreeNode);
    }

    @RequestMapping(value = "/treeSelect")
    public CJAjaxResult getTreeSelect(){
        CJVueTreeSelectNode cjVueTreeSelectNode = cjArticleCategoryService.getTreeSelect();
        return  CJAjaxResult.success("成功返回", cjVueTreeSelectNode);
    }

}
