package cj.springboot.wiki.security;


import cj.springboot.wiki.security.filter.CJJwtAuthenticationTokenFilter;
import cj.springboot.wiki.security.handler.CJAccessDeniedHandler;
import cj.springboot.wiki.security.handler.CJAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Slf4j
@Configuration
@EnableWebSecurity
public class CJSpringSecurityConfig extends WebSecurityConfigurerAdapter {


    //认证成功处理器
    @Autowired
    AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    //认证处理器
    @Autowired
    AuthenticationFailureHandler imoocAuthenctiationFailureHandler;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    CJJwtAuthenticationTokenFilter cjJwtAuthenticationTokenFilter;

    @Autowired
    CJAuthenticationEntryPoint cjAuthenticationEntryPoint;

    @Autowired
    CJAccessDeniedHandler cjAccessDeniedHandler;

    //用于登录用户密码加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

  /*   http    //先配置那些httpq请求需要此httpsecurity
                .requestMatchers()
                .antMatchers("/cjsec/**")
                .and()
                .authorizeRequests()
                .antMatchers("/cjsec/test1").anonymous()
                .anyRequest().access("@rbacService.hasPermission(request, authentication)");*/

        http    //先配置那些httpq请求需要此httpsecurity
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/cjRequireLogin").anonymous()
//                .anyRequest().authenticated()//不能在anyRequest后配置anyRequest
                .anyRequest().permitAll()
//                .anyRequest().access("@rbacService.hasPermission(request, authentication)")
        ;

        //把token校验过滤器添加到过滤器链中
//        http.addFilterBefore(cjJwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);



        http.formLogin()
                //设置登录页面参数名称
                .usernameParameter("user").passwordParameter("pwd")
                /*
                * 设置认证Filter需要登录校验（账号密码）时的URL
                * */
                .loginProcessingUrl("/cjauth")
                /*
                * 当未认证用户需要认证登录时
                * */
                .loginPage("/cjRequireLogin")
                //设置认证成功处理
                .successHandler(imoocAuthenticationSuccessHandler)
                //设置认证失败处理
                .failureHandler(imoocAuthenctiationFailureHandler)
        ;



        http
                .csrf().disable();

        //session配置
        http.sessionManagement()
                //不通过session获取SecurityContext
                //cj--不使用session管理
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /*
        * 并未成功 2022.2.23
        * */
        http.logout()
                //配置用户退出的URL
                .logoutUrl("/logout")
                //删除当前用户session,否则会产生invalidSession错误
//                .deleteCookies("JSESSIONID")
                //注销成功后的处理
                .logoutSuccessHandler(logoutSuccessHandler);

        /*
        * 认证失败处理
        * */
        http.exceptionHandling()
                .accessDeniedHandler(cjAccessDeniedHandler)// 该类用来统一处理 AccessDeniedException 异常
                .authenticationEntryPoint(cjAuthenticationEntryPoint);// 该类用来统一处理 AuthenticationException 异常
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
//        配置css.js.等静态资源
        web.ignoring().antMatchers("/cjThirdStatic/**");
        web.ignoring().antMatchers("/node_modules/**");
        web.ignoring().antMatchers("/sb2Static/**");
        web.ignoring().antMatchers("/icon/**");
        web.ignoring().antMatchers("/cjStatic/**");
        web.ignoring().antMatchers("/wiki/article/wangeditor/image/**");

    }
}
