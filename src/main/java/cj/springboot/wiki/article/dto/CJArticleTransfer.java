package cj.springboot.wiki.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJArticleTransfer {

    private String id;

    private String name;

    private String articleDesc;

    private String articleState;

    private String categoryId;

    //当点击显示文章内容时，需要此字段
    private String content;

}
