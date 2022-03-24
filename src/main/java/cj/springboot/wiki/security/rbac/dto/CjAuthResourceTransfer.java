package cj.springboot.wiki.security.rbac.dto;

import cj.springboot.wiki.article.dto.CjArticleCategoryTransfer;
import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-03-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CjAuthResourceTransfer{

    private static final long serialVersionUID = 1L;

    private String id;

    private String resourceType;

    private String fontIcon;

    private String name;

    private String resourceDesc;

    private Integer sort;

    private String url;

    private String parentId;

    private String urlType;

    private String routerTo;

    private List<CjAuthResourceTransfer> children;
}
