package cj.springboot.wiki.controller;


import cj.springboot.wiki.entity.EbookEntity;
import cj.springboot.wiki.service.EbookService;
import cj.springboot.wiki.service.impl.EbookServiceImpl;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 电子书 前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-02-10
 */
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

}
