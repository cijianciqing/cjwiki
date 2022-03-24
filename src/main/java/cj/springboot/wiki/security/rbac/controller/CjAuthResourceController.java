package cj.springboot.wiki.security.rbac.controller;


import cj.springboot.wiki.security.rbac.dto.CjAuthResourceTransfer;
import cj.springboot.wiki.security.rbac.service.CjAuthResourceService;
import cj.springboot.wiki.security.rbac.service.impl.CjAuthResourceServiceImpl;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/authority/resource")
public class CjAuthResourceController {

    @Autowired
    private CjAuthResourceServiceImpl cjAuthResourceServiceImpl;

    @GetMapping(value = "/menus")
    public CJAjaxResult getBackMenus(){
        List<CjAuthResourceTransfer> list =  cjAuthResourceServiceImpl.getAllMenus();
        return CJAjaxResult.success(list);
    }

}
