package cj.springboot.wiki.wangeditor.service;

import cj.springboot.wiki.wangeditor.dto.CJWangEditorImageData;
import cj.springboot.wiki.wangeditor.entity.CjUploadedFileEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cj
 * @since 2022-03-23
 */
public interface CjUploadedFileService extends IService<CjUploadedFileEntity> {

    List<CJWangEditorImageData> saveFile(HttpServletRequest request);

    CjUploadedFileEntity getFile(String fileId);
}
