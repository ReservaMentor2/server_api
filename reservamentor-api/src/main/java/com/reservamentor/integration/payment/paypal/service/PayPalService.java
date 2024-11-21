package com.reservamentor.integration.payment.paypal.service;

import com.reservamentor.integration.payment.paypal.dto.OrderCaptureResponse;
import com.reservamentor.integration.payment.paypal.dto.OrderResponse;
import com.reservamentor.model.entity.Purchase;
import com.reservamentor.model.entity.PurchaseItem;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.model.entity.Usuario;

public interface PayPalService {

    public void init();
    public String getAccessToken();
    public OrderResponse createOrder(Integer purchaseId, String returnUrl, String cancelUrl);
    public OrderCaptureResponse captureOrder(String orderId);

    public PurchaseItem createPurchaseItem(SesionMentoria sesionMentoria);
    public Purchase createPurchase(PurchaseItem purchaseItem, Usuario usuario);
}