package cj.springboot.wiki.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author cj
 * @since 2022-02-21
 */
@TableName("category")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 父id
     */
    @TableField("parent")
    private Long parent;

    /**
     * 名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 顺序
     */
    @TableField("sort")
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
            "id=" + id +
            ", parent=" + parent +
            ", name=" + name +
            ", sort=" + sort +
        "}";
    }
}
