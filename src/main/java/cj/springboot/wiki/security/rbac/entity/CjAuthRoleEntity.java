package cj.springboot.wiki.security.rbac.entity;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2022-03-24
 */
@TableName("cj_auth_role")
public class CjAuthRoleEntity extends CJBaseColmns {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("cjuniversal_create_time")
    private LocalDateTime cjuniversalCreateTime;

    @TableField("cjuniversal_del_status")
    private Integer cjuniversalDelStatus;

    @TableField("cjuniversal_update_time")
    private LocalDateTime cjuniversalUpdateTime;

    @TableField("`name`")
    private String name;

    @TableField("roleDesc")
    private String roleDesc;

    @TableField("roleTag")
    private String roleTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getCjuniversalCreateTime() {
        return cjuniversalCreateTime;
    }

    public void setCjuniversalCreateTime(LocalDateTime cjuniversalCreateTime) {
        this.cjuniversalCreateTime = cjuniversalCreateTime;
    }
    public Integer getCjuniversalDelStatus() {
        return cjuniversalDelStatus;
    }

    public void setCjuniversalDelStatus(Integer cjuniversalDelStatus) {
        this.cjuniversalDelStatus = cjuniversalDelStatus;
    }
    public LocalDateTime getCjuniversalUpdateTime() {
        return cjuniversalUpdateTime;
    }

    public void setCjuniversalUpdateTime(LocalDateTime cjuniversalUpdateTime) {
        this.cjuniversalUpdateTime = cjuniversalUpdateTime;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    public String getRoleTag() {
        return roleTag;
    }

    public void setRoleTag(String roleTag) {
        this.roleTag = roleTag;
    }

    @Override
    public String toString() {
        return "CjAuthRoleEntity{" +
            "id=" + id +
            ", cjuniversalCreateTime=" + cjuniversalCreateTime +
            ", cjuniversalDelStatus=" + cjuniversalDelStatus +
            ", cjuniversalUpdateTime=" + cjuniversalUpdateTime +
            ", name=" + name +
            ", roleDesc=" + roleDesc +
            ", roleTag=" + roleTag +
        "}";
    }
}
