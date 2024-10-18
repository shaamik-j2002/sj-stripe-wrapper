package com.trimble.stripewrapper.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseDTO {

    private String id;
    private String createdAt;
    private String url;
    private ErrorResponseDTO errorDTO;

    public AccountResponseDTO() {
    }

    public AccountResponseDTO(String id, String createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public AccountResponseDTO(String id, String createdAt, String url) {
        this.id = id;
        this.createdAt = createdAt;
        this.url = url;
    }

    public AccountResponseDTO(ErrorResponseDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ErrorResponseDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(ErrorResponseDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
