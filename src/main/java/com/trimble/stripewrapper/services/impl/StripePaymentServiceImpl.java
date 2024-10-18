package com.trimble.stripewrapper.services.impl;

import static com.trimble.stripewrapper.helper.PaymentParamsHelper.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.net.RequestOptions;
import com.trimble.stripewrapper.dtos.ErrorResponseDTO;
import com.trimble.stripewrapper.dtos.PaymentLinkInputDTO;
import com.trimble.stripewrapper.dtos.PaymentLinkResponseDTO;
import com.trimble.stripewrapper.services.IPaymentService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class StripePaymentServiceImpl implements IPaymentService {
    public PaymentLinkResponseDTO getPaymentLink(PaymentLinkInputDTO paymentLinkInputDTO) {
        Stripe.apiKey = paymentLinkInputDTO.getSecretKey();

        RequestOptions requestOptions = RequestOptions.builder().setStripeAccount(paymentLinkInputDTO.getAccountId()).build();
        try {
            Product product = Product.create(createProductParams(paymentLinkInputDTO), requestOptions);

            Price price = Price.create(createPriceParams(paymentLinkInputDTO, product.getId()), requestOptions);

            PaymentLink paymentLink = PaymentLink.create(createPaymentLinkParams(paymentLinkInputDTO, price.getId()), requestOptions);

            return new PaymentLinkResponseDTO(paymentLink.getUrl());
        } catch (StripeException e) {
            e.printStackTrace();
            return new PaymentLinkResponseDTO(new ErrorResponseDTO(e.getStatusCode().toString(), e.getMessage()));
        }
    }

    public BufferedImage getPaymentQRCode(PaymentLinkInputDTO paymentLinkInputDTO) {
        PaymentLinkResponseDTO paymentLinkResponseDTO = getPaymentLink(paymentLinkInputDTO);
        if (paymentLinkResponseDTO.getErrorResponseDTO() == null) {
            try {
                return generateQRCodeImage(paymentLinkResponseDTO.getPaymentLink(), 500, 500);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
