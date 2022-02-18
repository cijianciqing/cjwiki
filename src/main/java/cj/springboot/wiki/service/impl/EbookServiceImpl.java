package cj.springboot.wiki.service.impl;

import cj.springboot.wiki.dto.req.CJPageReq;
import cj.springboot.wiki.dto.req.EbookQueryReq;
import cj.springboot.wiki.dto.req.EbookSaveReq;
import cj.springboot.wiki.entity.EbookEntity;
import cj.springboot.wiki.dao.EbookDao;
import cj.springboot.wiki.service.EbookService;
import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 电子书 服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-02-10
 */
@Slf4j
@Service
public class EbookServiceImpl extends ServiceImpl<EbookDao, EbookEntity> implements EbookService {

    @Autowired
    private EbookDao ebookDao;

    @Autowired
    private CJDozerUtil cjDozerUtil;

    public List<EbookEntity> getEbookList() {
        return ebookDao.selectList(null);
    }

    public List<EbookEntity> getEbookTable(EbookQueryReq ebookQueryReq) {

        log.info("总行数：{}", ebookQueryReq.getCjPageReq());
        CJPageReq cjPageReq = ebookQueryReq.getCjPageReq();
        QueryWrapper<EbookEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(ebookQueryReq.getName()), "name", ebookQueryReq.getName());


        PageHelper.startPage(cjPageReq.getPage(), cjPageReq.getSize());
        List<EbookEntity> ebookEntities = ebookDao.selectList(queryWrapper);


        return ebookEntities;
    }

    public void cjSave(EbookSaveReq req) {
        EbookEntity convertor = cjDozerUtil.convertor(req, EbookEntity.class);
        saveOrUpdate(convertor);
    }

    public void delete(Long id) {
        ebookDao.deleteById(id);
    }
}
