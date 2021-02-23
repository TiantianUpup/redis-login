package com.h2t.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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

    /**
     * 生成时间
     */
    private Long gmtCreate;
}
