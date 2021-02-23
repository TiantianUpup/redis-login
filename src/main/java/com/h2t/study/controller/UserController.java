package com.h2t.study.controller;

import com.h2t.study.service.UserService;
import com.h2t.study.vo.LoginUserVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * LoginController
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/01/11 10:35
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Object login(@Valid LoginUserVO loginUserVO) {
        return userService.login(loginUserVO);
    }

    @DeleteMapping("/login_out/{id}")
    public Object loginOut(@PathVariable("id")
                           @Valid @NotBlank(message = "id不能为空") String id) {
        return userService.loginOut(id);
    }

//    @PutMapping
//    public Object updatePassword() {
//
//    }
}
