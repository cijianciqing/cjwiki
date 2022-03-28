package cj.springboot.wiki.mission.service.impl;


import cj.springboot.wiki.mission.dto.CJMissionResponse;
import cj.springboot.wiki.mission.dto.CJMissionSaveRequest;
import cj.springboot.wiki.mission.entity.CjMissionInfoEntity;
import cj.springboot.wiki.mission.dao.CjMissionInfoDao;
import cj.springboot.wiki.mission.service.CjMissionInfoService;
import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import cn.hutool.core.util.ObjectUtil;
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
public class CjMissionInfoServiceImpl extends ServiceImpl<CjMissionInfoDao, CjMissionInfoEntity> implements CjMissionInfoService {

    @Autowired
    private CJDozerUtil cjDozerUtil;

    @Autowired
    private CjMissionInfoDao cjMissionInfoDao;

    public List<CJMissionResponse> getAllMission() {
        //获取所有的cjMission
        QueryWrapper<CjMissionInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true,true, "sort_no");
//        queryWrapper.eq(true,"finish_status","进行中");
        List<CjMissionInfoEntity> cjMissionEntities = cjMissionInfoDao.selectList(queryWrapper);
        List<CJMissionResponse> convertor = cjDozerUtil.convertor(cjMissionEntities, CJMissionResponse.class);

        List<CJMissionResponse> result = new ArrayList<>();
        /*
         * 遍历所有的cjMission,如果parentId != 0, 则插入到对应的children中
         * 双循环
         * */
        for (CJMissionResponse c : convertor) {
//            if (c.getParent() != 0L) {
                for (CJMissionResponse p : convertor) {

                    if (c.getParentId().equals(p.getId())) {
                        //报错java.lang.NullPointerException: null
                        //而且要考虑到，没有parent id 不需要设置children
                        if(ObjectUtil.isNull(p.getChildren())){
                            p.setChildren(new ArrayList<>());
                        }
//                        System.out.println(c.getParent()+"-->"+p.getId());
                        p.getChildren().add(c);
                    }
                }
//            }
        }

        //获取parentId为0的cjMission
        for (CJMissionResponse c : convertor) {
            if (c.getParentId().equals("0")) {
                result.add(c);
            }

        }

        return result;
    }

    public void saveMission(CJMissionSaveRequest req) {
        CjMissionInfoEntity convertor = cjDozerUtil.convertor(req, CjMissionInfoEntity.class);
        saveOrUpdate(convertor);
    }

    public void deleteMission(String id) {

        cjMissionInfoDao.deleteByRootId(id);
//        removeById(id);
    }
}
