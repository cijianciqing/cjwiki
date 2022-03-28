package cj.springboot.wiki.article.dao;

import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import cj.springboot.wiki.config.mybatis.CJMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
public interface CjArticleCategoryDao extends CJMapper<CjArticleCategoryEntity> {

    void deleteCategoryByRootId(String id);

}
