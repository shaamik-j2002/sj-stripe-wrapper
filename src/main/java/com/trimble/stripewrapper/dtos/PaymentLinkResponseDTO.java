package com.trimble.stripewrapper.dtos;

public class PaymentLinkResponseDTO {
    private String paymentLink;
    private ErrorResponseDTO errorResponseDTO;

    public PaymentLinkResponseDTO(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public PaymentLinkResponseDTO(ErrorResponseDTO errorResponseDTO) {
        this.errorResponseDTO = errorResponseDTO;
    }

    public PaymentLinkResponseDTO(String paymentLink, ErrorResponseDTO errorResponseDTO) {
        this.paymentLink = paymentLink;
        this.errorResponseDTO = errorResponseDTO;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public ErrorResponseDTO getErrorResponseDTO() {
        return errorResponseDTO;
    }

    public void setErrorResponseDTO(ErrorResponseDTO errorResponseDTO) {
        this.errorResponseDTO = errorResponseDTO;
    }
}
