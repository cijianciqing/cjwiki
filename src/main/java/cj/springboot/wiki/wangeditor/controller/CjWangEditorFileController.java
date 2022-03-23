package cj.springboot.wiki.wangeditor.controller;


import cj.springboot.wiki.wangeditor.dto.CJWangEditorImageData;
import cj.springboot.wiki.wangeditor.dto.CJWangEditorImageReturn;
import cj.springboot.wiki.wangeditor.entity.CjUploadedFileEntity;
import cj.springboot.wiki.wangeditor.service.CjUploadedFileService;
import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2022-03-23
 */
@Slf4j
@RestController
@RequestMapping("/wiki/article/wangeditor")
public class CjWangEditorFileController {
    @Autowired
    CjUploadedFileService cjUploadedFileService;



    @PostMapping("/upload/file")
    public CJWangEditorImageReturn handleFileUpload(HttpServletRequest request){
        List<CJWangEditorImageData> cjWangEditorImageDataList = cjUploadedFileService.saveFile(request);

        return new CJWangEditorImageReturn(0,cjWangEditorImageDataList);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPic(@PathVariable(value = "id") String fileId) throws Exception {
        File file = new File(cjUploadedFileService.getFile(fileId).getFilePath());
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;

    }

}
