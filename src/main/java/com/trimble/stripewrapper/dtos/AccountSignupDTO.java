package com.trimble.stripewrapper.dtos;

public class AccountSignupDTO {
    private String secretKey;
    private String accountId;
    private String refreshUrl;
    private String returnUrl;

    public AccountSignupDTO() {
    }

    public AccountSignupDTO(String secretKey, String accountId) {
        this.secretKey = secretKey;
        this.accountId = accountId;
    }

    public AccountSignupDTO(String secretKey, String accountId, String refreshUrl, String returnUrl) {
        this.secretKey = secretKey;
        this.accountId = accountId;
        this.refreshUrl = refreshUrl;
        this.returnUrl = returnUrl;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRefreshUrl() {
        return refreshUrl;
    }

    public void setRefreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
