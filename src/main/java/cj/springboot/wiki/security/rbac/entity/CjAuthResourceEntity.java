package cj.springboot.wiki.security.rbac.entity;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2022-03-24
 */
@TableName("cj_auth_resource")
public class CjAuthResourceEntity extends CJBaseColmns {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;

    @TableField("resource_type")
    private String resourceType;

    @TableField("font_icon")
    private String fontIcon;

    @TableField("`name`")
    private String name;

    @TableField("resource_desc")
    private String resourceDesc;

    @TableField("sort")
    private Integer sort;

    @TableField("url")
    private String url;

    @TableField("parent_id")
    private String parentId;

    @TableField("url_type")
    private String urlType;

    @TableField("router_to")
    private String routerTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public String getFontIcon() {
        return fontIcon;
    }

    public void setFontIcon(String fontIcon) {
        this.fontIcon = fontIcon;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }
    public String getRouterTo() {
        return routerTo;
    }

    public void setRouterTo(String routerTo) {
        this.routerTo = routerTo;
    }

    @Override
    public String toString() {
        return "CjAuthResourceEntity{" +
            "id=" + id +
            ", resourceType=" + resourceType +
            ", fontIcon=" + fontIcon +
            ", name=" + name +
            ", resourceDesc=" + resourceDesc +
            ", sort=" + sort +
            ", url=" + url +
            ", parentId=" + parentId +
            ", urlType=" + urlType +
            ", routerTo=" + routerTo +
        "}";
    }
}
