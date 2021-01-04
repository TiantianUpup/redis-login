package com.h2t.study.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO {
    private String id;

    private String account;

    private String password;

    private String captcha;

    //appKey和appSecret两个字段直接写死
    private String appKey;

    private String appSecret;
}
