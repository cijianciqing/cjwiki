package cj.springboot.wiki.security.rbac.service.impl;

import cj.springboot.wiki.security.rbac.entity.CjAuthUserEntity;
import cj.springboot.wiki.security.rbac.dao.CjAuthUserDao;
import cj.springboot.wiki.security.rbac.service.CjAuthUserService;
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
public class CjAuthUserServiceImpl extends ServiceImpl<CjAuthUserDao, CjAuthUserEntity> implements CjAuthUserService {

}
