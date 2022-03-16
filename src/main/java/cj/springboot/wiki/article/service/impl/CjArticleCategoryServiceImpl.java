package cj.springboot.wiki.article.service.impl;

import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import cj.springboot.wiki.article.dao.CjArticleCategoryDao;
import cj.springboot.wiki.article.service.CjArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-03-16
 */
@Service
public class CjArticleCategoryServiceImpl extends ServiceImpl<CjArticleCategoryDao, CjArticleCategoryEntity> implements CjArticleCategoryService {

}
