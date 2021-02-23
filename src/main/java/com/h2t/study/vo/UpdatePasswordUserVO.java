package com.h2t.study.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * UpdatePasswordUserVO
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/02/23 15:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordUserVO {
    @NotBlank(message = "id不为空")
    private String id;

    @NotBlank(message = "密码不为空")
    private String password;
}
