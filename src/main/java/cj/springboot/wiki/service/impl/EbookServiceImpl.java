package cj.springboot.wiki.service.impl;

import cj.springboot.wiki.entity.EbookEntity;
import cj.springboot.wiki.dao.EbookDao;
import cj.springboot.wiki.service.EbookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 电子书 服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-02-10
 */
@Service
public class EbookServiceImpl extends ServiceImpl<EbookDao, EbookEntity> implements EbookService {

    @Autowired
    private EbookDao ebookDao;

    public List<EbookEntity> getEbookList(){
        return ebookDao.selectList(null);
    }
}
