package com.h2t.study.controller;

import com.h2t.study.service.UserService;
import com.h2t.study.vo.LoginUserVO;
import com.h2t.study.vo.UpdatePasswordUserVO;
import org.springframework.web.bind.annotation.*;

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
    public Object login(@Valid @RequestBody LoginUserVO loginUserVO) {
        return userService.login(loginUserVO);
    }

    @DeleteMapping("/login_out/{id}")
    public Object loginOut(@PathVariable("id")
                           @Valid @NotBlank(message = "id不能为空") String id) {
        return userService.loginOut(id);
    }

    @PutMapping("/user")
    public Object updatePassword(@Valid @RequestBody UpdatePasswordUserVO updatePasswordUserVO) {
        return userService.updatePassword(updatePasswordUserVO);
    }
}
