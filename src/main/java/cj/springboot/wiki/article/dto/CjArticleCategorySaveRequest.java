package cj.springboot.wiki.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CjArticleCategorySaveRequest {
    private String id;

    private String categoryDesc;

    private String name;

    private Integer sortNo;

    private String parentId;

    private String fontIcon;

}
