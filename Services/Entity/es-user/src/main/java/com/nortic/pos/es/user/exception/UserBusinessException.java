package com.nortic.pos.es.user.exception;

import lombok.Getter;

@Getter
public class UserBusinessException extends RuntimeException{

    private final UserExceptionCode userExceptionCode;

    public UserBusinessException(UserExceptionCode userExceptionCode) {
        super(userExceptionCode.getMessage());
        this.userExceptionCode = userExceptionCode;
    }

}
