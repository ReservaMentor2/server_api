package com.reservamentor.integration.payment.paypal.service;

import com.reservamentor.integration.payment.paypal.dto.OrderCaptureResponse;
import com.reservamentor.integration.payment.paypal.dto.OrderResponse;

public interface PayPalService {

    public void init();
    public String getAccessToken();
    public OrderResponse createOrder(Integer purchaseId, String returnUrl, String cancelUrl);
    public OrderCaptureResponse captureOrder(String orderId);
}