package cj.springboot.wiki.security.rbac.service.impl;

import cj.springboot.wiki.security.rbac.entity.CjAuthRoleAndResourceEntity;
import cj.springboot.wiki.security.rbac.dao.CjAuthRoleAndResourceDao;
import cj.springboot.wiki.security.rbac.service.CjAuthRoleAndResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-03-24
 */
@Service
public class CjAuthRoleAndResourceServiceImpl extends ServiceImpl<CjAuthRoleAndResourceDao, CjAuthRoleAndResourceEntity> implements CjAuthRoleAndResourceService {

}
