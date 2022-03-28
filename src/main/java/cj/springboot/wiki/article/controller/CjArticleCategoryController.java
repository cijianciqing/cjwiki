package cj.springboot.wiki.article.controller;


import cj.springboot.wiki.antvue.dto.CJVueTreeNode;
import cj.springboot.wiki.antvue.treeSelect.CJVueTreeSelectNode;
import cj.springboot.wiki.article.dto.CjArticleCategorySaveRequest;
import cj.springboot.wiki.article.dto.CjArticleCategoryTransfer;
import cj.springboot.wiki.article.dto.CjArticleSaveRequest;
import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import cj.springboot.wiki.article.service.CjArticleCategoryService;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        List<CJVueTreeNode> cjVueTreeNode = cjArticleCategoryService.getCategories();
        return  CJAjaxResult.success("成功返回", cjVueTreeNode);
    }

    /*
    * 获取所有的category
    * */
    @RequestMapping(value = "/treeSelect")
    public CJAjaxResult getTreeSelect(){
        List<CJVueTreeSelectNode> cjVueTreeSelectNode = cjArticleCategoryService.getTreeSelect();
        return  CJAjaxResult.success("成功返回", cjVueTreeSelectNode);
    }

    /*
    * 获取指定的category
    * */
    @RequestMapping(value = "/{treeNodeId}")
    public CJAjaxResult getTreeNode(@PathVariable(value ="treeNodeId") String treeNodeId){
        CjArticleCategoryTransfer cjArticleCategoryEntity = cjArticleCategoryService.getTreeNode(treeNodeId);
        return  CJAjaxResult.success("成功返回", cjArticleCategoryEntity);
    }

    //添加 或者 更新 category
    @PostMapping("/save")
    public CJAjaxResult save(@Valid @RequestBody CjArticleCategorySaveRequest req) {
        cjArticleCategoryService.saveCategory(req);
        return CJAjaxResult.success("添加Caegory成功");
    }

    //删除特定article
    @DeleteMapping("/delete/{id}")
    public CJAjaxResult delete(@PathVariable String id) {
        cjArticleCategoryService.deleteCategoryByRootId(id);
        return CJAjaxResult.success("删除Category成功");
    }

}
