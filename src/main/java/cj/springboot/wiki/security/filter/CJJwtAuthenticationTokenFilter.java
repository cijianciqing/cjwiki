package cj.springboot.wiki.security.filter;


import cj.springboot.wiki.security.rbac.entity.CjAuthUserEntity;
import cj.springboot.wiki.util.jwt.CJJWTUtil;
import cj.springboot.wiki.util.redis.CJRedisCache;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CJJwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private CJRedisCache redisCache;

    @Value("${spring.application.name}")
    private String cjGlobalAppName;

    @Value("${cj.web.security.user.redis.timetout}")
    private int cjRedisUserTimeOut;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("CJJwtAuthenticationTokenFilter........");

        /*
        * 如果没有token就过
        * 如果有token
        *    1.token 在redis中存在，将Authentication设置为已认证
        *    2.token 在redis中不存在，抛出异常
        * */
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        //log.info("CJJwtAuthenticationTokenFilter....解析token....");
        String userid;
        try {
            Claims claims = CJJWTUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login" + cjGlobalAppName+ ":" + userid;
        CjAuthUserEntity loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            log.info("CJJwtAuthenticationTokenFilter....用户未登录....");
            throw new RuntimeException("CJ用户未登录");
        }
        //更新过期时间
        redisCache.expire(redisKey, cjRedisUserTimeOut, TimeUnit.HOURS);
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}