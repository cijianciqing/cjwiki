package cj.springboot.wiki.mission.service.impl;

import cj.springboot.wiki.antvue.list.CJListResponse;
import cj.springboot.wiki.mission.dto.CJMissionSetpSaveRequest;
import cj.springboot.wiki.mission.entity.CjMissionInfoEntity;
import cj.springboot.wiki.mission.entity.CjMissionStepEntity;
import cj.springboot.wiki.mission.dao.CjMissionStepDao;
import cj.springboot.wiki.mission.service.CjMissionStepService;
import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-03-25
 */
@Service
public class CjMissionStepServiceImpl extends ServiceImpl<CjMissionStepDao, CjMissionStepEntity> implements CjMissionStepService {

    @Autowired
    CJDozerUtil cjDozerUtil;


    @Autowired
    CjMissionStepDao cjMissionStepDao;

    public List<CJListResponse> getMissionSteps(String missionId) {
        QueryWrapper<CjMissionStepEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id",missionId);
        List<String> columns = new ArrayList<>();
        columns.add("update_time");
        columns.add("create_time");
        queryWrapper.orderBy(true,false,columns);

        List<CjMissionStepEntity> cjMissionStepEntities = cjMissionStepDao.selectList(queryWrapper);

        return cjDozerUtil.convertor(cjMissionStepEntities,CJListResponse.class);

    }

    public void saveMissionStep(CJMissionSetpSaveRequest req) {
        CjMissionStepEntity convertor = cjDozerUtil.convertor(req, CjMissionStepEntity.class);
        saveOrUpdate(convertor);
    }
}
