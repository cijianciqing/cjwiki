package cj.springboot.wiki.security.rbac.service.impl;

import cj.springboot.wiki.dto.response.CategoryResponse;
import cj.springboot.wiki.security.rbac.dto.CjAuthResourceTransfer;
import cj.springboot.wiki.security.rbac.entity.CjAuthResourceEntity;
import cj.springboot.wiki.security.rbac.dao.CjAuthResourceDao;
import cj.springboot.wiki.security.rbac.service.CjAuthResourceService;
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
 * @since 2022-03-24
 */
@Service
public class CjAuthResourceServiceImpl extends ServiceImpl<CjAuthResourceDao, CjAuthResourceEntity> implements CjAuthResourceService {

    @Autowired
    private CJDozerUtil cjDozerUtil;

    @Autowired
    private CjAuthResourceDao cjAuthResourceDao;


    /*
    * 获取resource中所有的menu,
    * */
    public List<CjAuthResourceTransfer> getAllMenus() {
        QueryWrapper<CjAuthResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type","MENU");
        queryWrapper.orderBy(true,true,"sort");
        List<CjAuthResourceEntity> cjAuthResourceEntities = cjAuthResourceDao.selectList(queryWrapper);
        List<CjAuthResourceTransfer> convertors = cjDozerUtil.convertor(cjAuthResourceEntities, CjAuthResourceTransfer.class);

        /*
         * 遍历所有的category,如果parentId != 0, 则插入到对应的children中
         * 双循环
         * */
        for (CjAuthResourceTransfer c : convertors) {
            if (! c.getParentId().equalsIgnoreCase("0")) {
                for (CjAuthResourceTransfer p : convertors) {

                    if (c.getParentId().equals(p.getId())) {
                        //报错java.lang.NullPointerException: null
                        //而且要考虑到，没有parent id 不需要设置children
                        if(ObjectUtil.isNull(p.getChildren())){
                            p.setChildren(new ArrayList<>());
                        }
                        p.getChildren().add(c);
                    }
                }
            }
        }

        List<CjAuthResourceTransfer> result = new ArrayList<>();

        //获取parent_id为1[root]的resource
        for (CjAuthResourceTransfer c : convertors) {
            if (c.getParentId().equalsIgnoreCase("1")) {
                result.add(c);
            }

        }
        return result;
    }

}
