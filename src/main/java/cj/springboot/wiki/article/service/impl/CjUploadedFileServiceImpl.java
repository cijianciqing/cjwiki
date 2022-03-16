package cj.springboot.wiki.article.service.impl;

import cj.springboot.wiki.article.entity.CjUploadedFileEntity;
import cj.springboot.wiki.article.dao.CjUploadedFileDao;
import cj.springboot.wiki.article.service.CjUploadedFileService;
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
public class CjUploadedFileServiceImpl extends ServiceImpl<CjUploadedFileDao, CjUploadedFileEntity> implements CjUploadedFileService {

}
