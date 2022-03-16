package cj.springboot.wiki.article.entity;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2022-03-16
 */
@TableName("cj_article_category")
public class CjArticleCategoryEntity extends CJBaseColmns {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("category_desc")
    private String categoryDesc;

    @TableField("`name`")
    private String name;

    @TableField("sortNo")
    private Integer sortNo;

    @TableField("parent_id")
    private Long parentId;

    @TableField("fontIcon")
    private String fontIcon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getFontIcon() {
        return fontIcon;
    }

    public void setFontIcon(String fontIcon) {
        this.fontIcon = fontIcon;
    }

    @Override
    public String toString() {
        return "CjArticleCategoryEntity{" +
            "id=" + id +
            ", categoryDesc=" + categoryDesc +
            ", name=" + name +
            ", sortNo=" + sortNo +
            ", parentId=" + parentId +
            ", fontIcon=" + fontIcon +
        "}";
    }
}
