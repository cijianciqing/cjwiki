package cj.springboot.wiki.controller;


import cj.springboot.wiki.dto.response.CategoryResponse;
import cj.springboot.wiki.entity.CategoryEntity;
import cj.springboot.wiki.service.impl.CategoryServiceImpl;
import cj.springboot.wiki.service.impl.CategoryServiceImpl;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 分类 前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-02-21
 */
@Slf4j
@RestController
@RequestMapping("/wiki/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    //查询 category - table
    @PostMapping(value = "/table")
    public CJAjaxResult getCategoryList(){
        List<CategoryResponse> categoryList = categoryService.getCategoryTable();
        return CJAjaxResult.success(categoryList);
    }
}
