package com.h2t.study.interceptor;

import com.h2t.study.dto.UserTokenDTO;
import com.h2t.study.service.RedisService;
import com.h2t.study.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthenticateInterceptor implements HandlerInterceptor {
    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String authToken = request.getHeader("Authorization");
        String token = authToken.substring("Bearer".length() + 1).trim();
        UserTokenDTO userTokenDTO = JWTUtil.parseToken(token);
        //1.判断请求是否有效
        if (redisService.get(userTokenDTO.getId()) == null
                || !redisService.get(userTokenDTO.getId()).equals(token)) {
            return false;
        }

        //2.判断是否需要续期
        if (redisService.getExpireTime(userTokenDTO.getId()) < 1 * 60 * 30) {
            redisService.set(userTokenDTO.getId(), token);
            log.error("update token info, id is:{}, user info is:{}", userTokenDTO.getId(), token);
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
