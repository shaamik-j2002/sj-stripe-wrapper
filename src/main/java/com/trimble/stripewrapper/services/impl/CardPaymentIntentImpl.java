package com.trimble.stripewrapper.services.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.net.RequestOptions;
import com.trimble.stripewrapper.dtos.ErrorResponseDTO;
import com.trimble.stripewrapper.dtos.PaymentIntentInputDTO;
import com.trimble.stripewrapper.dtos.PaymentIntentResponseDTO;
import com.trimble.stripewrapper.services.IPaymentIntentService;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.trimble.stripewrapper.helper.PaymentParamsHelper.*;

@Service
public class CardPaymentIntentImpl implements IPaymentIntentService {

    @Override
    public PaymentIntentResponseDTO makePayment(PaymentIntentInputDTO paymentIntentInputDTO) {

        // do validation on paymentIntentInputDTO

        Stripe.apiKey = paymentIntentInputDTO.getPublicKey();

        RequestOptions requestOptions = RequestOptions.builder().setStripeAccount(paymentIntentInputDTO.getAccountId()).build();    // put it in a util class
        try {
            Map<String, Object> params = getCardDetails(paymentIntentInputDTO.getCardTokenDTO());
            PaymentMethod paymentMethod = PaymentMethod.create(params, requestOptions);

            Stripe.apiKey = paymentIntentInputDTO.getSecretKey();

            Customer customer = Customer.create(createCustomerParams(paymentIntentInputDTO), requestOptions);

            paymentMethod = paymentMethod.attach(getCustomerParams(customer.getId()), requestOptions);

            PaymentIntent paymentIntent = PaymentIntent.create(createPaymentIntentParams(paymentIntentInputDTO, customer, paymentMethod), requestOptions);
            return new PaymentIntentResponseDTO(paymentIntent.getStatus());
        } catch (StripeException e) {
            e.printStackTrace();
            return new PaymentIntentResponseDTO(new ErrorResponseDTO(e.getStatusCode().toString(), e.getMessage()));
        }
    }
}