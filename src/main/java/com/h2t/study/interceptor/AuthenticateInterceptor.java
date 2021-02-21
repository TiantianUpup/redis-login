package com.h2t.study.interceptor;

import com.h2t.study.dto.UserTokenDTO;
import com.h2t.study.enums.ErrorCodeEnum;
import com.h2t.study.exception.UserException;
import com.h2t.study.service.RedisService;
import com.h2t.study.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权拦截器
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/02/21 20:15
 */
public class AuthenticateInterceptor implements HandlerInterceptor {
    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String authToken = request.getHeader("Authorization");
        String token = authToken.substring("Bearer".length() + 1).trim();
        UserTokenDTO userTokenDTO = JWTUtil.parseToken(token);
        if (redisService.get(userTokenDTO.getId()) == null) {
            throw new UserException(ErrorCodeEnum.TNP1001004);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
