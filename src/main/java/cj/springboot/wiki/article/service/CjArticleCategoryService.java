package cj.springboot.wiki.article.service;

import cj.springboot.wiki.antvue.dto.CJVueTreeNode;
import cj.springboot.wiki.antvue.treeSelect.CJVueTreeSelectNode;
import cj.springboot.wiki.article.dto.CjArticleCategorySaveRequest;
import cj.springboot.wiki.article.dto.CjArticleCategoryTransfer;
import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
public interface CjArticleCategoryService extends IService<CjArticleCategoryEntity> {

    List<CJVueTreeNode> getCategories();

    List<CJVueTreeSelectNode> getTreeSelect();


    CjArticleCategoryTransfer getTreeNode(String treeNodeId);

    void saveCategory(CjArticleCategorySaveRequest req);


    void deleteCategoryByRootId(String id);
}
