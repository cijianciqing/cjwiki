package cj.springboot.wiki.security.rbac.service.impl;

import cj.springboot.wiki.security.rbac.entity.CjAuthRoleEntity;
import cj.springboot.wiki.security.rbac.dao.CjAuthRoleDao;
import cj.springboot.wiki.security.rbac.service.CjAuthRoleService;
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
public class CjAuthRoleServiceImpl extends ServiceImpl<CjAuthRoleDao, CjAuthRoleEntity> implements CjAuthRoleService {

}
