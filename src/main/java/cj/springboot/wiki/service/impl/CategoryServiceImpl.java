package cj.springboot.wiki.service.impl;

import cj.springboot.wiki.dao.EbookDao;
import cj.springboot.wiki.dto.response.CategoryResponse;
import cj.springboot.wiki.entity.CategoryEntity;
import cj.springboot.wiki.dao.CategoryDao;
import cj.springboot.wiki.entity.EbookEntity;
import cj.springboot.wiki.service.CategoryService;
import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-02-21
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CJDozerUtil cjDozerUtil;

    public List<CategoryResponse> getCategoryTable() {
        //获取所有的category
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true,true, "sort");
        List<CategoryEntity> categoryEntities = categoryDao.selectList(queryWrapper);
        List<CategoryResponse> convertor = cjDozerUtil.convertor(categoryEntities, CategoryResponse.class);

//        List<CategoryResponse> children = new ArrayList<>();
        List<CategoryResponse> result = new ArrayList<>();
        /*
         * 遍历所有的category,如果parentId != 0, 则插入到对应的children中
         * 双循环
         * */
        for (CategoryResponse c : convertor) {
            if (c.getParent() != 0L) {
                for (CategoryResponse p : convertor) {

                    if (c.getParent().equals(p.getId())) {
                        //报错java.lang.NullPointerException: null
                        //而且要考虑到，没有parent id 不需要设置children
                        if(ObjectUtil.isNull(p.getChildren())){
                            p.setChildren(new ArrayList<>());
                        }
                        System.out.println(c.getParent()+"-->"+p.getId());
                        p.getChildren().add(c);
                    }
                }
            }
        }

        //获取parentId为0的category
        for (CategoryResponse c : convertor) {
            if (c.getParent() == 0L) {
                result.add(c);
            }

        }

        return result;
    }
}
