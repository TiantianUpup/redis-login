package com.h2t.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCodeEnum {
    //未知异常
    TNP1001000("TNP1001000", "未知异常"),
    //入参异常
    TNP1001001("TNP1001001", "用户不存在"),
    TNP1001002("TNP1001002", "用户密码错误"),
    TNP1001003("TNP1001003", "登出异常");


    private String errorCode;
    private String errorMsg;
}
