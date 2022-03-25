package cj.springboot.wiki.mission.controller;


import cj.springboot.wiki.article.dto.CjArticleSaveRequest;
import cj.springboot.wiki.mission.dto.CJMissionResponse;
import cj.springboot.wiki.mission.dto.CJMissionSaveRequest;
import cj.springboot.wiki.mission.service.impl.CjMissionInfoServiceImpl;
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
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/wiki/mission/info")
public class CjMissionInfoController {

    @Autowired
    private CjMissionInfoServiceImpl cjMissionInfoService;

    //查询 category - table
    @PostMapping(value = "/table")
    public CJAjaxResult getCategoryList(){
        List<CJMissionResponse> categoryList = cjMissionInfoService.getAllMission();
        return CJAjaxResult.success(categoryList);
    }


    //查询 category - table
    @PostMapping(value = "/save")
    public CJAjaxResult addMission(@Valid @RequestBody CJMissionSaveRequest req){
        cjMissionInfoService.saveMission(req);
        return CJAjaxResult.success("添加MissionInfo成功");
    }
    //删除特定article
    @DeleteMapping("/delete/{id}")
    public CJAjaxResult delete(@PathVariable String id) {
        cjMissionInfoService.deleteMission(id);
        return CJAjaxResult.success("删除MissionInfo成功");
    }

}
