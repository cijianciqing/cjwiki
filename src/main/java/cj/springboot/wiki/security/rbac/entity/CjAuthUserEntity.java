package cj.springboot.wiki.security.rbac.entity;

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
 * @since 2022-03-24
 */
@TableName("cj_auth_user")
public class CjAuthUserEntity extends CJBaseColmns {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("email")
    private String email;

    @TableField("isAccountNonExpired")
    private Boolean isAccountNonExpired;

    @TableField("isAccountNonLocked")
    private Boolean isAccountNonLocked;

    @TableField("isCredentialsNonExpired")
    private Boolean isCredentialsNonExpired;

    @TableField("isEnabled")
    private Boolean isEnabled;

    @TableField("`password`")
    private String password;

    @TableField("showname")
    private String showname;

    @TableField("telephoneNo")
    private String telephoneNo;

    @TableField("username")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }
    public Boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }
    public Boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }
    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CjAuthUserEntity{" +
            "id=" + id +
            ", email=" + email +
            ", isAccountNonExpired=" + isAccountNonExpired +
            ", isAccountNonLocked=" + isAccountNonLocked +
            ", isCredentialsNonExpired=" + isCredentialsNonExpired +
            ", isEnabled=" + isEnabled +
            ", password=" + password +
            ", showname=" + showname +
            ", telephoneNo=" + telephoneNo +
            ", username=" + username +
        "}";
    }
}
