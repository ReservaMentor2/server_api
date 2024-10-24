package com.reservamentor.service;

import com.reservamentor.dto.PaymentOrderResponse;
import com.reservamentor.dto.PaymentCaptureResponse;

public interface CheckoutService {
    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePayment(String orderId);
}
