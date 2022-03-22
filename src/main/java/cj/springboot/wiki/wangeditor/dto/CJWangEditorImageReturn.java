package cj.springboot.wiki.wangeditor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJWangEditorImageReturn {
    private  Integer errno;
    private List<CJWangEditorImageData> data;

}
