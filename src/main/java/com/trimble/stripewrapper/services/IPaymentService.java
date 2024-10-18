package com.trimble.stripewrapper.services;

import com.trimble.stripewrapper.dtos.PaymentLinkInputDTO;
import com.trimble.stripewrapper.dtos.PaymentLinkResponseDTO;

import java.awt.image.BufferedImage;

public interface IPaymentService {
    PaymentLinkResponseDTO getPaymentLink(PaymentLinkInputDTO paymentLinkInputDTO);
    BufferedImage getPaymentQRCode(PaymentLinkInputDTO paymentLinkInputDTO);
}
