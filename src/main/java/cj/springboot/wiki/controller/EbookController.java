package cj.springboot.wiki.controller;


import cj.springboot.wiki.dto.req.CJPageReq;
import cj.springboot.wiki.dto.req.EbookQueryReq;
import cj.springboot.wiki.dto.req.EbookSaveReq;
import cj.springboot.wiki.entity.EbookEntity;
import cj.springboot.wiki.service.EbookService;
import cj.springboot.wiki.service.impl.EbookServiceImpl;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 电子书 前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-02-10
 */
@Slf4j
@RestController
@RequestMapping("/wiki/ebook")
public class EbookController {

    @Autowired
    private EbookServiceImpl ebookService;

    @RequestMapping(value = "/list")
    public CJAjaxResult getEbookList(){
        List<EbookEntity> ebookList = ebookService.getEbookList();
        return CJAjaxResult.success(ebookList);
    }

    //查询 ebook - table
    @PostMapping(value = "/table")
    public CJAjaxResult getEbookList(@Validated @RequestBody EbookQueryReq ebookQueryReq){
        List<EbookEntity> ebookList = ebookService.getEbookTable(ebookQueryReq);
        PageInfo<EbookEntity> pageInfo = new PageInfo<>(ebookList);

        log.info("EbookSaveReq总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());
        return CJAjaxResult.success(pageInfo);
    }
    //添加 或者 更新ebook
    @PostMapping("/save")
    public CJAjaxResult save(@Valid @RequestBody EbookSaveReq req) {
        ebookService.cjSave(req);
        return CJAjaxResult.success("添加Ebook成功");
    }
    //删除特定ebook
    @DeleteMapping("/delete/{id}")
    public CJAjaxResult delete(@PathVariable Long id) {
        ebookService.delete(id);
        return CJAjaxResult.success("删除Ebook成功");
    }


}
