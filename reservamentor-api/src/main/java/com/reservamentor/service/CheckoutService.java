package com.reservamentor.service;

import com.reservamentor.dto.PaymentOrderResponse;
import com.reservamentor.dto.PaymentCaptureResponse;
import jakarta.mail.MessagingException;

public interface CheckoutService {
    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl) throws MessagingException;

    PaymentCaptureResponse capturePayment(String orderId) throws MessagingException;
}
