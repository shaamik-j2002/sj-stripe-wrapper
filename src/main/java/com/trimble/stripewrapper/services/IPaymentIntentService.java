package com.trimble.stripewrapper.services;

import com.trimble.stripewrapper.dtos.PaymentIntentInputDTO;
import com.trimble.stripewrapper.dtos.PaymentIntentResponseDTO;

public interface IPaymentIntentService {
    PaymentIntentResponseDTO makePayment(PaymentIntentInputDTO paymentIntentInputDTO);
}
