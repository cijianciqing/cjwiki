package cj.springboot.wiki.service.impl;

import cj.springboot.wiki.entity.TestEntity;
import cj.springboot.wiki.dao.TestDao;
import cj.springboot.wiki.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试 服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-02-08
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestDao, TestEntity> implements TestService {

}
