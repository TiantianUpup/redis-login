package com.h2t.study.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO {
    /**
     * 用户账号
     */
    @NotBlank(message = "用户名为空")
    private String username;

    /**
     * 账号密码
     */
    @NotBlank(message = "密码为空")
    private String password;
}
