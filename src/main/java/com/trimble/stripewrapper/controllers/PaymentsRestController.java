package com.trimble.stripewrapper.controllers;

import com.trimble.stripewrapper.dtos.PaymentIntentInputDTO;
import com.trimble.stripewrapper.dtos.PaymentIntentResponseDTO;
import com.trimble.stripewrapper.dtos.PaymentLinkInputDTO;
import com.trimble.stripewrapper.dtos.PaymentLinkResponseDTO;
import com.trimble.stripewrapper.services.impl.CardPaymentIntentImpl;
import com.trimble.stripewrapper.services.impl.StripePaymentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/v1")
public class PaymentsRestController {

    private final StripePaymentServiceImpl paymentService;
    private final CardPaymentIntentImpl cardPaymentIntent;

    public PaymentsRestController(StripePaymentServiceImpl paymentService, CardPaymentIntentImpl cardPaymentIntent) {
        this.paymentService = paymentService;
        this.cardPaymentIntent = cardPaymentIntent;
    }

    @GetMapping("/payment-link")
    public ResponseEntity<PaymentLinkResponseDTO> createPaymentLink(@RequestBody PaymentLinkInputDTO paymentLinkInputDTO) throws Exception {
        PaymentLinkResponseDTO paymentLinkResponseDTO = paymentService.getPaymentLink(paymentLinkInputDTO);

        if (paymentLinkResponseDTO.getErrorResponseDTO() != null)
            return ResponseEntity.badRequest().body(paymentLinkResponseDTO);

        return ResponseEntity.ok().body(paymentLinkResponseDTO);
    }

    @GetMapping("/payment-qrcode")
    public ResponseEntity<byte[]> createPaymentQRCode(@RequestBody PaymentLinkInputDTO paymentLinkInputDTO) throws Exception {
        BufferedImage bufferedImage = paymentService.getPaymentQRCode(paymentLinkInputDTO);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // Return image as byte array in response
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(imageBytes);
    }

    @GetMapping("/payment-creditcard")
    public ResponseEntity<PaymentIntentResponseDTO> makeCreditCardPayment(@RequestBody PaymentIntentInputDTO paymentIntentInputDTO) {
        PaymentIntentResponseDTO paymentIntentResponseDTO = cardPaymentIntent.makePayment(paymentIntentInputDTO);

        if (paymentIntentResponseDTO.getErrorResponseDTO() != null) {
            return ResponseEntity.badRequest().body(paymentIntentResponseDTO);
        } else {
            return ResponseEntity.ok().body(paymentIntentResponseDTO);
        }
    }
}
