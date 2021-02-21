package com.h2t.study.service.impl;

import com.h2t.study.dao.UserMapper;
import com.h2t.study.dto.UserTokenDTO;
import com.h2t.study.enums.ErrorCodeEnum;
import com.h2t.study.exception.UserException;
import com.h2t.study.po.UserPO;
import com.h2t.study.service.RedisService;
import com.h2t.study.service.UserService;
import com.h2t.study.utils.JWTUtil;
import com.h2t.study.utils.PropertiesUtil;
import com.h2t.study.vo.LoginUserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserServiceImpl
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/01/11 11:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    @Override
    public String login(LoginUserVO loginUserVO) {
        //1.判断用户名密码是否正确
        UserPO userPO = userMapper.getByUsername(loginUserVO.getUsername());
        if (userPO == null) {
            throw new UserException(ErrorCodeEnum.TNP1001001);
        }
        if (!loginUserVO.getPassword().equals(userPO.getPassword())) {
            throw new UserException(ErrorCodeEnum.TNP1001002);
        }

        //2.用户名密码正确生成token
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        PropertiesUtil.copyProperties(userTokenDTO, loginUserVO);
        userTokenDTO.setId(userPO.getId());
        String token = JWTUtil.generateToken(userTokenDTO);

        //3.存入token至redis
        redisService.set(userPO.getId(), token);
        return token;
    }

    @Override
    public void loginOut(String id) {
        if (!redisService.delete(id)) {
            throw new UserException(ErrorCodeEnum.TNP1001003);
        }
    }
}
