package com.trimble.stripewrapper.dtos;

public class PaymentIntentResponseDTO {

    private String status;
    private ErrorResponseDTO errorResponseDTO;

    public PaymentIntentResponseDTO(String status) {
        this.status = status;
    }

    public PaymentIntentResponseDTO(ErrorResponseDTO errorResponseDTO) {
        this.errorResponseDTO = errorResponseDTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponseDTO getErrorResponseDTO() {
        return errorResponseDTO;
    }

    public void setErrorResponseDTO(ErrorResponseDTO errorResponseDTO) {
        this.errorResponseDTO = errorResponseDTO;
    }
}
