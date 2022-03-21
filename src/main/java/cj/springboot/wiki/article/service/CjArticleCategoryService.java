package cj.springboot.wiki.article.service;

import cj.springboot.wiki.antvue.dto.CJVueTreeNode;
import cj.springboot.wiki.antvue.treeSelect.CJVueTreeSelectNode;
import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
public interface CjArticleCategoryService extends IService<CjArticleCategoryEntity> {

    CJVueTreeNode getCategories();

    CJVueTreeSelectNode getTreeSelect();
}
