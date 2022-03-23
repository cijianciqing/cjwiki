package cj.springboot.wiki.security.handler;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class CJAccessDeniedHandler  implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info(accessDeniedException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write(objectMapper.writeValueAsString( CJAjaxResult.error("CJ权限不足", "Forbidden11:" + accessDeniedException.getMessage())));
    }
}
