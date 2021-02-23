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
import com.h2t.study.vo.UpdatePasswordUserVO;
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
        userTokenDTO.setGmtCreate(System.currentTimeMillis());
        String token = JWTUtil.generateToken(userTokenDTO);

        //3.存入token至redis
        redisService.set(userPO.getId(), token);
        return token;
    }

    @Override
    public boolean loginOut(String id) {
        boolean result = redisService.delete(id);
        if (!redisService.delete(id)) {
            throw new UserException(ErrorCodeEnum.TNP1001003);
        }

        return result;
    }

    @Override
    public String updatePassword(UpdatePasswordUserVO updatePasswordUserVO) {
        //1.修改密码
        UserPO userPO = UserPO.builder().password(updatePasswordUserVO.getPassword())
                .id(updatePasswordUserVO.getId())
                .build();
        UserPO user = userMapper.getById(updatePasswordUserVO.getId());
        if (user == null) {
            throw new UserException(ErrorCodeEnum.TNP1001001);
        }

        if (userMapper.updatePassword(userPO) != 1) {
            throw new UserException(ErrorCodeEnum.TNP1001005);
        }
        //2.生成新的token
        UserTokenDTO userTokenDTO = UserTokenDTO.builder()
                .id(updatePasswordUserVO.getId())
                .username(user.getUsername())
                .gmtCreate(System.currentTimeMillis()).build();
        String token = JWTUtil.generateToken(userTokenDTO);
        //3.更新token
        redisService.set(user.getId(), token);
        return token;
    }
}
