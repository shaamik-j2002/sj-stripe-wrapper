package com.trimble.stripewrapper.dtos;

public class ErrorResponseDTO {
    private String errorCode;
    private String errorMessage;

    @Override
    public String toString() {
        return "ErrorResponseDTO{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public ErrorResponseDTO(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
