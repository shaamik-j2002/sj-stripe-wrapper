package com.trimble.stripewrapper.helper;

import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.param.*;
import com.trimble.stripewrapper.dtos.CardTokenDTO;
import com.trimble.stripewrapper.dtos.PaymentIntentInputDTO;
import com.trimble.stripewrapper.dtos.PaymentLinkInputDTO;

import java.util.HashMap;
import java.util.Map;

public class PaymentParamsHelper {

    public static ProductCreateParams createProductParams(PaymentLinkInputDTO paymentLinkInputDTO) {
        return ProductCreateParams.builder()
                .setName(paymentLinkInputDTO.getProductName())
                .setDescription(paymentLinkInputDTO.getProductDescription())
                .setType(ProductCreateParams.Type.GOOD)
                .build();
    }

    public static PriceCreateParams createPriceParams(PaymentLinkInputDTO paymentLinkInputDTO, String productId) {
        return PriceCreateParams.builder()
                .setCurrency(paymentLinkInputDTO.getCurrency())
                .setUnitAmount(Long.parseLong(paymentLinkInputDTO.getAmount()))
                .setProduct(productId)
                .build();
    }

    public static PaymentLinkCreateParams createPaymentLinkParams(PaymentLinkInputDTO paymentLinkInputDTO, String priceId) {
        return PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(priceId)
                                .setQuantity(1L)
                                .build()
                )
                .build();
    }

    public static Map<String, Object> createCustomerParams(PaymentIntentInputDTO paymentIntentInputDTO) {
        Map<String, Object> params = new HashMap() {
            {
//                put("description", paymentIntentInputDTO.get());
                put("email", paymentIntentInputDTO.getCustomerEmail());
                put("name", paymentIntentInputDTO.getCustomerName());
            }
        };
        return params;
    }

    public static PaymentIntentCreateParams createPaymentIntentParams(PaymentIntentInputDTO paymentIntentInputDTO, Customer customer, PaymentMethod paymentMethod) {
        return PaymentIntentCreateParams.builder()
                .setAmount(paymentIntentInputDTO.getAmount())
                .setCurrency(paymentIntentInputDTO.getCurrency())
                .setCustomer(customer.getId())
                .setPaymentMethod(paymentMethod.getId())
                .setReturnUrl(paymentIntentInputDTO.getReturnUrl())
                .setConfirm(true)
                .setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
                .build();
    }

    public static Map<String, Object> getCardDetails(CardTokenDTO cardTokenDTO) {
        Map<String, Object> card = new HashMap() {
            {
                put("number", cardTokenDTO.getCardNumber());
                put("exp_month", cardTokenDTO.getExpiryMonth());
                put("exp_year", cardTokenDTO.getExpiryYear());
                put("cvc", cardTokenDTO.getCvv());
            }
        };

        Map<String, Object> params = new HashMap() {{
            put("type", "card");
            put("card", card);
        }};
        return params;
    }

    public static Map<String, Object> getCustomerParams(String customerId) {
        return new HashMap() {{
            put("customer", customerId);
        }};
    }
}
