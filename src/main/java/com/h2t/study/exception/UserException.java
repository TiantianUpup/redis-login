package com.h2t.study.exception;

import com.h2t.study.enums.ErrorCodeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
public class UserException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum;

    public UserException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getErrorMsg());
        this.errorCodeEnum = errorCodeEnum;
    }
}
