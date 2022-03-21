package cj.springboot.wiki.article.dao;

import cj.springboot.wiki.antvue.table.CJTableQueryReq;
import cj.springboot.wiki.article.entity.CjArticleEntity;
import cj.springboot.wiki.config.mybatis.CJMapper;
import cj.springboot.wiki.entity.EbookEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
public interface CjArticleDao extends CJMapper<CjArticleEntity> {

    List<CjArticleEntity>  getArticlesByRootId(String id);

    List<CjArticleEntity>  getArticlesByRequest(CJTableQueryReq cjTableQueryReq);


}
