package com.h2t.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDTO {
    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     * */
    private String username;
}
