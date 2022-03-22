package cj.springboot.wiki.wangeditor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJWangEditorImageData {
    private  String url;//必填
    private String alt;
    private String href;

}
