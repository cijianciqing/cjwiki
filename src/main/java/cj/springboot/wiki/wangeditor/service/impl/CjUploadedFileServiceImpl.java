package cj.springboot.wiki.wangeditor.service.impl;

import cj.springboot.wiki.wangeditor.dto.CJWangEditorImageData;
import cj.springboot.wiki.wangeditor.entity.CjUploadedFileEntity;
import cj.springboot.wiki.wangeditor.dao.CjUploadedFileDao;
import cj.springboot.wiki.wangeditor.service.CjUploadedFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2022-03-23
 */
@Service
public class CjUploadedFileServiceImpl extends ServiceImpl<CjUploadedFileDao, CjUploadedFileEntity> implements CjUploadedFileService {

    @Autowired
    CjUploadedFileDao cjUploadedFileDao;

    @Value("${cj.upload.image.path}")
    private String WangEditor_Image_Upload_PATH;

    @Value("${cj.download.image.path}")
    private String WangEditor_Image_Download_PATH;;


    @Override
    public List<CJWangEditorImageData> saveFile(HttpServletRequest request) {
        List<CJWangEditorImageData> cjWangEditorImageDataList =  new ArrayList<>();
        MultiValueMap<String, MultipartFile> multiFileMap = ((MultipartHttpServletRequest) request).getMultiFileMap();
        if(MapUtils.isNotEmpty(multiFileMap)){
            for(Map.Entry<String, List<MultipartFile>> entry : multiFileMap.entrySet()){
//                System.out.println(entry.getKey()+"--->");
                List<MultipartFile> multipartFileList = entry.getValue();
                for (MultipartFile multipartFile : multipartFileList) {
//                    System.out.println(multipartFile.getName()+"-->"+multipartFile.getOriginalFilename());
                    CjUploadedFileEntity cjUploadedFileEntity = new CjUploadedFileEntity();
                    cjUploadedFileEntity.setFileName(multipartFile.getOriginalFilename());

                    save(cjUploadedFileEntity);
                    String id = cjUploadedFileEntity.getId();

                    cjUploadedFileEntity.setFilePath( WangEditor_Image_Upload_PATH+id);

                    try {
                        multipartFile.transferTo(new File(WangEditor_Image_Upload_PATH+id));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    saveOrUpdate(cjUploadedFileEntity);


                    CJWangEditorImageData cjWangEditorImageData = new CJWangEditorImageData();

                    //后端数据库不保存路径、路径即是 WangEditor_Image_Download_PATH+id
                    //cjWangEditorImageData.setUrl(WangEditor_Image_Download_PATH+id);

                    cjWangEditorImageDataList.add(cjWangEditorImageData);
                }
            }
        }
        return cjWangEditorImageDataList;
    }

    @Override
    public CjUploadedFileEntity getFile(String fileId) {
        CjUploadedFileEntity cjUploadedFile = getById(fileId);
        String filePath = cjUploadedFile.getFilePath();
        LocalDateTime createTime = cjUploadedFile.getCreateTime();

        /*
        * 2022-4-12 8:37:00之前的路径不一样
        * */
        boolean after = createTime.isAfter(LocalDateTime.of(2022, 4, 12, 8, 37, 0));
        if(after){
            cjUploadedFile.setFilePath(WangEditor_Image_Upload_PATH+fileId);
        }else{
            cjUploadedFile.setFilePath(WangEditor_Image_Upload_PATH+filePath);
        }
        return cjUploadedFile;
    }
}
