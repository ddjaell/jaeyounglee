package com.example.jaeyounglee.common.exception;

import com.example.jaeyounglee.common.enums.ErrorCode;

public class CommonException extends RuntimeException{
    private ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        super(errorCode.getDetailErrorMessage());
        this.errorCode = errorCode;

    }

    public CommonException(ErrorCode errorCode, String detailErrorMessage) {
        super(detailErrorMessage);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
