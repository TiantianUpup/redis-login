package com.h2t.study.service;

import com.h2t.study.vo.LoginUserVO;
import com.h2t.study.vo.UpdatePasswordUserVO;

/**
 * UserService
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/01/11 11:18
 */
public interface UserService {
    String login(LoginUserVO loginUserVO);

    boolean loginOut(String id);

    String updatePassword(UpdatePasswordUserVO updatePasswordUserVO);
}
