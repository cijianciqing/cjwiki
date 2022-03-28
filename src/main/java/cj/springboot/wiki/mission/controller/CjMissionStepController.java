package cj.springboot.wiki.mission.controller;


import cj.springboot.wiki.antvue.list.CJListResponse;
import cj.springboot.wiki.mission.dto.CJMissionSaveRequest;
import cj.springboot.wiki.mission.dto.CJMissionSetpSaveRequest;
import cj.springboot.wiki.mission.service.impl.CjMissionStepServiceImpl;
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
@RequestMapping("/wiki/mission/step")
public class CjMissionStepController {

    @Autowired
    private CjMissionStepServiceImpl cjMissionStepService;

    @GetMapping(value = "/{missionId}")
    public CJAjaxResult getMissionSteps(@PathVariable(value = "missionId",required = true) String missionId){
        List<CJListResponse> list = cjMissionStepService.getMissionSteps(missionId);
        return  CJAjaxResult.success("成功返回", list);
    }


    //保存 mission info
    @PostMapping(value = "/save")
    public CJAjaxResult addMission(@Valid @RequestBody CJMissionSetpSaveRequest req){
        cjMissionStepService.saveMissionStep(req);
        return CJAjaxResult.success("添加MissionInfo成功");
    }

    //删除特定 mission-step
    @DeleteMapping("/delete/{id}")
    public CJAjaxResult delete(@PathVariable String id) {
        cjMissionStepService.deleteMissionStep(id);
        return CJAjaxResult.success("删除MissionStep成功");
    }
}