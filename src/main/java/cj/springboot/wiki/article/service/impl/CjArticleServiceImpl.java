package cj.springboot.wiki.article.service.impl;

import cj.springboot.wiki.antvue.table.CJTableQueryReq;
import cj.springboot.wiki.article.dto.CJArticleTransfer;
import cj.springboot.wiki.article.dto.CjArticleSaveRequest;
import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import cj.springboot.wiki.article.entity.CjArticleEntity;
import cj.springboot.wiki.article.dao.CjArticleDao;
import cj.springboot.wiki.article.service.CjArticleService;
import cj.springboot.wiki.dto.req.CJPageReq;
import cj.springboot.wiki.dto.req.EbookSaveReq;
import cj.springboot.wiki.entity.EbookEntity;
import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
@Slf4j
@Service
public class CjArticleServiceImpl extends ServiceImpl<CjArticleDao, CjArticleEntity> implements CjArticleService {

    @Autowired
    CJDozerUtil cjDozerUtil;

    @Autowired
    CjArticleDao cjArticleDao;

    @Override
    public List<CJArticleTransfer> getArticles() {
        List<CjArticleEntity> cjArticleEntities = cjArticleDao.selectList(null);

        return cjDozerUtil.convertor(cjArticleEntities, CJArticleTransfer.class);
    }

    @Override
    public List<CJArticleTransfer> getArticleTable(CJTableQueryReq tableQueryReq) {

        log.info("请求参数：{}", tableQueryReq.getCjPageReq());
        CJPageReq cjPageReq = tableQueryReq.getCjPageReq();
//        QueryWrapper<CjArticleEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(StringUtils.isNotBlank(tableQueryReq.getName()), "name", tableQueryReq.getName());


        PageHelper.startPage(cjPageReq.getPage(), cjPageReq.getSize());
        List<CjArticleEntity> cjArticleEntities = cjArticleDao.getArticlesByRequest(tableQueryReq);

        return cjDozerUtil.convertor(cjArticleEntities, CJArticleTransfer.class);
    }

    @Override
    public void delete(Long id) {
        cjArticleDao.deleteById(id);
    }

    @Override
    public void save(CjArticleSaveRequest req) {
        log.info("save请求：{}", req);
        CjArticleEntity convertor = cjDozerUtil.convertor(req, CjArticleEntity.class);
        saveOrUpdate(convertor);

    }
}
