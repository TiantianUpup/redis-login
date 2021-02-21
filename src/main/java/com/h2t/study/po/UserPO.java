package com.h2t.study.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * UserPO
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/02/21 14:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {
    private String id;

    private String username;

    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime gmtCreate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime gmtModified;
}
