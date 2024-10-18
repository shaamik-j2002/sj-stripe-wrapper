package com.trimble.stripewrapper.dtos;

public class AccountCreateDTO {
    private String secretKey;
    private String email;
    private String type;
    private String country;

    public AccountCreateDTO() {
    }

    public AccountCreateDTO(String secretKey, String email, String type, String country) {
        this.secretKey = secretKey;
        this.email = email;
        this.type = type;
        this.country = country;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
