package com.h2t.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDTO {
    private String id;

    private String account;

    private String password;

    //appKey和appSecret两个字段直接写死
    private String appKey = "";

    /**
     * 生成时间
     */
    private Long gmtCreateTime;

    /**
     * 过期时间
     */
    private Long expireTime;
}
