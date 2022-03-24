package cj.springboot.wiki.article.service;

import cj.springboot.wiki.antvue.table.CJTableQueryReq;
import cj.springboot.wiki.antvue.table.CJTableResponse;
import cj.springboot.wiki.article.dto.CJArticleTransfer;
import cj.springboot.wiki.article.dto.CjArticleSaveRequest;
import cj.springboot.wiki.article.entity.CjArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
public interface CjArticleService extends IService<CjArticleEntity> {

    List<CJArticleTransfer> getArticles();

    CJTableResponse<CJArticleTransfer> getArticleTable(CJTableQueryReq tableQueryReq);

    void delete(String id);

    void save(CjArticleSaveRequest req);
}
