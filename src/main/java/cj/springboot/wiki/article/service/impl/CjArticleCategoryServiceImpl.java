package cj.springboot.wiki.article.service.impl;

import cj.springboot.wiki.antvue.dto.CJVueTreeNode;
import cj.springboot.wiki.antvue.treeSelect.CJVueTreeSelectNode;
import cj.springboot.wiki.article.dto.CjArticleCategorySaveRequest;
import cj.springboot.wiki.article.dto.CjArticleCategoryTransfer;
import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import cj.springboot.wiki.article.dao.CjArticleCategoryDao;
import cj.springboot.wiki.article.service.CjArticleCategoryService;
import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
@Service
public class CjArticleCategoryServiceImpl extends ServiceImpl<CjArticleCategoryDao, CjArticleCategoryEntity> implements CjArticleCategoryService {


    @Autowired
    CJDozerUtil cjDozerUtil;

    @Autowired
    CjArticleCategoryDao cjArticleCategoryDao;

    public  List<CjArticleCategoryTransfer> getAllCategory(){
        QueryWrapper<CjArticleCategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true,true,"sort_no");
        List<CjArticleCategoryEntity> cjArticleCategoryEntities = cjArticleCategoryDao.selectList(queryWrapper);
        List<CjArticleCategoryTransfer> convertors = cjDozerUtil.convertor(cjArticleCategoryEntities, CjArticleCategoryTransfer.class);
        CjArticleCategoryTransfer root = new CjArticleCategoryTransfer();
        List<CjArticleCategoryTransfer> list = new ArrayList<>();
        //将所有的categories组合起来
        for(int i=0; i<convertors.size();i++){
            CjArticleCategoryTransfer articleCategoryTransfer = convertors.get(i);
            if (articleCategoryTransfer.getParentId().equalsIgnoreCase("0")){
                root = articleCategoryTransfer;
            }
            for(int j=0; j<convertors.size(); j++){
                if(convertors.get(j).getParentId().equals(articleCategoryTransfer.getId())){

                    if (CollectionUtils.isEmpty(articleCategoryTransfer.getChildren())) {
                        articleCategoryTransfer.setChildren(new ArrayList<>());
                    }
                    articleCategoryTransfer.getChildren().add(convertors.get(j));
                }
            }
        }
        list.add(root);
        return list;
    }

    @Override
    public List<CJVueTreeNode> getCategories() {
        return cjDozerUtil.convertor(getAllCategory(), CJVueTreeNode.class);
    }

    @Override
    public List<CJVueTreeSelectNode> getTreeSelect() {
        return cjDozerUtil.convertor(getAllCategory(), CJVueTreeSelectNode.class);
    }

    @Override
    public CjArticleCategoryTransfer getTreeNode(String treeNodeId) {
        CjArticleCategoryEntity byId = getById(treeNodeId);
        return cjDozerUtil.convertor(byId, CjArticleCategoryTransfer.class);
    }

    @Override
    public void saveCategory(CjArticleCategorySaveRequest req) {
        CjArticleCategoryEntity convertor = cjDozerUtil.convertor(req, CjArticleCategoryEntity.class);
        saveOrUpdate(convertor);
    }

    @Override
    public void deleteCategoryByRootId(String id) {
        cjArticleCategoryDao.deleteCategoryByRootId(id);
    }
}
