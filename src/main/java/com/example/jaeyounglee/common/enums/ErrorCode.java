package com.example.jaeyounglee.common.enums;

public enum ErrorCode {
    NOT_FOUND_PRODUCT("해당 상품이 없습니다."),
    ALREADY_EXIST_PRODUCT("이미 존재하는 상품 ID입니다."),
    NOT_FOUND_LAYOUT("해당 ID의 레이아웃이 존재하지 않습니다."),
    ALREADY_EXIST_LAYOUT("이미 존재하는 레이아웃입니다."),
    UNKNOWN_ERROR("알 수 없는 에러가 발생했습니다. 관리자에게 연락해주세요.")
    ;

    private final String detailErrorMessage;

    private ErrorCode(String detailErrorMessage) {
        this.detailErrorMessage = detailErrorMessage;
    }

    public String getDetailErrorMessage() {
        return detailErrorMessage;
    }
}
