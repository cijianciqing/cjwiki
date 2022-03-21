package cj.springboot.wiki.article.dto;

import cj.springboot.wiki.article.entity.CjArticleCategoryEntity;
import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.*;
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
public class CjArticleCategoryTransfer {
    private String id;

    private String categoryDesc;

    private String name;

    private Integer sortNo;

    private String parentId;

    private List<CjArticleCategoryTransfer> children;

    private String fontIcon;

}
