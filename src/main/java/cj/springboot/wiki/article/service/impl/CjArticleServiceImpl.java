package cj.springboot.wiki.article.service.impl;

import cj.springboot.wiki.article.entity.CjArticleEntity;
import cj.springboot.wiki.article.dao.CjArticleDao;
import cj.springboot.wiki.article.service.CjArticleService;
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
public class CjArticleServiceImpl extends ServiceImpl<CjArticleDao, CjArticleEntity> implements CjArticleService {

}
