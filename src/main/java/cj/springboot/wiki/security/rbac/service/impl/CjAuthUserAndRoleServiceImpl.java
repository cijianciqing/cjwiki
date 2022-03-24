package cj.springboot.wiki.security.rbac.service.impl;

import cj.springboot.wiki.security.rbac.entity.CjAuthUserAndRoleEntity;
import cj.springboot.wiki.security.rbac.dao.CjAuthUserAndRoleDao;
import cj.springboot.wiki.security.rbac.service.CjAuthUserAndRoleService;
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
public class CjAuthUserAndRoleServiceImpl extends ServiceImpl<CjAuthUserAndRoleDao, CjAuthUserAndRoleEntity> implements CjAuthUserAndRoleService {

}
