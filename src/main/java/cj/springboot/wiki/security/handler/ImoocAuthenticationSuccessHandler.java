/**
 * 
 */
package cj.springboot.wiki.security.handler;


import cj.springboot.wiki.security.dto.CJUserResponse;
import cj.springboot.wiki.security.entity.CJUser;
import cj.springboot.wiki.util.jwt.CJJWTUtil;
import cj.springboot.wiki.util.redis.CJRedisCache;
import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * @author zhailiang
 *
 * 认证成功处理器
 */
@Slf4j
@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Value("${spring.application.name}")
	private String cjGlobalAppName;

	@Autowired
	private CJDozerUtil cjDozerUtil;

	//spring自动注册的
	//将authentication转换为json
	@Autowired
	private ObjectMapper objectMapper;


	@Autowired
	CJRedisCache cjRedisCache;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

		log.info("com.ns.cjcq.security.authentication.ImoocAuthenticationSuccessHandler.onAuthenticationSuccess 登录成功");

		/*
		 * 保存token到redis
		 * */
		CJUser loginUser = (CJUser) authentication.getPrincipal();
		CJUserResponse convertor = cjDozerUtil.convertor(loginUser, CJUserResponse.class);
		String userId = loginUser.getId().toString();
		String jwt = CJJWTUtil.createJWT(userId);
		convertor.setToken(jwt);
		String redisKey = "login" + cjGlobalAppName+ ":" + userId;
		//authenticate存入redis--保留1小时
//		cjRedisCache.setCacheObject(redisKey,loginUser,1, TimeUnit.HOURS	);
		cjRedisCache.setCacheObject(redisKey,loginUser);
		//把token响应给前端
//		HashMap<String,Object> map = new HashMap<>();
//		map.put("token",jwt);
//		map.put("cjUser",convertor);


//		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {//loginType 默认设置JSON
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString( CJAjaxResult.success("cj authentication success",convertor)));

//		} else {
//			super.onAuthenticationSuccess(request, response, authentication);
//		}


	}

}
