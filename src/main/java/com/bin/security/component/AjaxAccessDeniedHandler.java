package com.bin.security.component;

import com.alibaba.fastjson.JSON;
import com.bin.common.Result;
import com.bin.common.enums.ResultEnum;
import com.bin.common.util.JwtTokenUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 无权访问的JSON返回
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) {
        JwtTokenUtil.renderString(JSON.toJSONString(Result.error(ResultEnum.USER_NO_PERMISSION)));
    }
}
