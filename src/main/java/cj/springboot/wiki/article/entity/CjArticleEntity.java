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
 * @since 2022-03-17
 */
@TableName("cj_article")
public class CjArticleEntity extends CJBaseColmns {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("articleDesc")
    private String articleDesc;

    @TableField("content")
    private String content;

    @TableField("`name`")
    private String name;

    @TableField("category_id")
    private String categoryId;

    @TableField("article_state")
    private String articleState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getArticleState() {
        return articleState;
    }

    public void setArticleState(String articleState) {
        this.articleState = articleState;
    }

    @Override
    public String toString() {
        return "CjArticleEntity{" +
            "id=" + id +
            ", articleDesc=" + articleDesc +
            ", content=" + content +
            ", name=" + name +
            ", categoryId=" + categoryId +
            ", articleState=" + articleState +
        "}";
    }
}
