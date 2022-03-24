package cj.springboot.wiki.security.rbac.entity;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *
 * </p>
 *
 * @author cj
 * @since 2022-03-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)//出现这种问题的情况是由于JSON里面包含了实体没有的字段导致反序列化失败
@TableName("cj_auth_user")
public class CjAuthUserEntity extends CJBaseColmns implements UserDetails {

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


    //初始化 块
    {
        // 表示帐号是否未过期
        isAccountNonExpired = Boolean.TRUE;
        //表示帐号是否未锁定
        isAccountNonLocked = Boolean.TRUE;
        //表示登录凭据是否未过期
        isCredentialsNonExpired = Boolean.TRUE;
        //表示账户是否被启用
        isEnabled = Boolean.TRUE;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
