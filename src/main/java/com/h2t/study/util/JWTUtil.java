package com.h2t.study.util;

import com.h2t.study.vo.LoginUserVO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    //过期时间默认为7天
    private static final long EXPIRE_TIME = 1 * 24 * 60 * 60 * 1000;
    //private static final long EXPIRE_TIME = 1 * 60 * 1000;
    //私钥
    private static final String TOKEN_SECRET = "privateKey";

    /**
     * 生成token，自定义过期时间 毫秒
     *
     * @param **username**
     * @param **password**
     * @return
     */
    public static String generateToken(LoginUserVO loginUserVO) {
        System.out.println("util: " + loginUserVO);
        try {
            // 设置过期时间
            Long currentTime = System.currentTimeMillis();
            Date date = new Date(currentTime + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            //校验字段 useid  usename 和appkey
            UserTokenDTO userTokenDTO = new UserTokenDTO();
            PropertiesUtil.copyProperties(userTokenDTO, loginUserVO);
            userTokenDTO.setExpireTime(currentTime + EXPIRE_TIME);
            System.out.println("DTO:  " + userTokenDTO);
            userTokenDTO.setGmtCreateTime(currentTime);
            //userTokenDTO.setId();
            return JWT.create()
                    .withHeader(header)
                    .withClaim("token", JSONObject.toJSONString(userTokenDTO))
                    //.withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param **token**
     * @return
     */
    public static UserTokenDTO parseToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String tokenInfo = jwt.getClaim("token").asString();
        System.out.println(tokenInfo);
        return JSON.parseObject(tokenInfo, UserTokenDTO.class);
    }
}
