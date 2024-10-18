package com.trimble.stripewrapper.dtos;

public class PaymentLinkInputDTO {

    private String secretKey;
    private String accountId;
    private String productName;
    private String productDescription;
    private String amount;
    private String currency;
    private String type;

    public PaymentLinkInputDTO(String secretKey, String accountId, String productName, String productDescription, String amount, String currency, String type) {
        this.secretKey = secretKey;
        this.accountId = accountId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.amount = amount;
        this.currency = currency;
        this.type = type;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PaymentLinkInputDTO{" +
                "secretKey='" + secretKey + '\'' +
                ", accountId='" + accountId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
