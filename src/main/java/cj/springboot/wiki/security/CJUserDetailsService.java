package cj.springboot.wiki.security;

import cj.springboot.wiki.security.entity.CJUser;
import cj.springboot.wiki.security.rbac.entity.CjAuthUserEntity;
import cj.springboot.wiki.security.rbac.service.CjAuthUserService;
import cj.springboot.wiki.security.rbac.service.impl.CjAuthUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/*
* 用于登录用户认证、授权
* */
@Slf4j
@Component("myUserDetailsService")
public class CJUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private CjAuthUserServiceImpl cjAuthUserService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("cj.springboot.security.template.security.CJUserDetailsService.loadUserByUsername 登录用户名："+username);
        CjAuthUserEntity cjAuthUserEntity = cjAuthUserService.getByUserName(username);
//        CJUser cjUser = new CJUser();
//        cjUser.setId(666L);
//        cjUser.setUsername("u1");
//        cjUser.setShowname("测试User");
//        String encode = passwordEncoder.encode("123");
//        cjUser.setPassword(passwordEncoder.encode("123"));
        return  cjAuthUserService.getByUserName(username);

    }
}
