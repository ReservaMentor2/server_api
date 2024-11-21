package com.reservamentor.integration.payment.paypal.service.Impl;


import com.reservamentor.dto.PurchaseItemCreateUpdateDTO;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.integration.payment.paypal.dto.*;
import com.reservamentor.integration.payment.paypal.service.PayPalService;
import com.reservamentor.model.entity.Purchase;
import com.reservamentor.model.entity.PurchaseItem;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.model.entity.enums.PaymentStatus;
import com.reservamentor.repository.*;
import com.reservamentor.service.SesionMentoriaService;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PayPalServiceImpl implements PayPalService {

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Value("${paypal.api-base}")
    private String apiBase;

    @NonNull
    private PurchaseRepository purchaseRepository;
    private RestClient paypalClient;

    @NonNull
    private PurchaseItemRepository purchaseItemRepository;

    @PostConstruct
    public void init(){
        paypalClient = RestClient
                .builder()
                .baseUrl(apiBase)
                .build();
    }

    public String getAccessToken(){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        //Realización de Solicitudes:enviando una solicitud POST a la API de PayPal para obtener un token de acceso:
        return Objects.requireNonNull(
                        paypalClient.post()
                                .uri("/v1/oauth2/token")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder()
                                        .encodeToString((clientId + ":" + clientSecret).getBytes()))
                                .body(body)
                                .retrieve()
                                .toEntity(TokenResponse.class)
                                .getBody())
                .getAccessToken();
    }

    public OrderResponse createOrder(Integer purchaseId, String returnUrl, String cancelUrl){
        Purchase purchase= purchaseRepository.findById(purchaseId)
                .orElseThrow(ResourceNotFoundException::new);

        //Construcción de la solicitud de Pedido de Pago
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIntent("CAPTURE");

        Amount amount = new Amount();
        amount.setCurrencyCode("USD");
        amount.setValue(purchase.getTotal().toString());

        PurchaseUnit purchaseUnit = new PurchaseUnit();
        purchaseUnit.setReferenceId(purchase.getId().toString());
        purchaseUnit.setAmount(amount);

        orderRequest.setPurchaseUnits(Collections.singletonList(purchaseUnit));

        ApplicationContext applicationContext = ApplicationContext.builder()
                .brandName("ReservaMentor")
                .returnURL(returnUrl)
                .cancelURL(cancelUrl)
                .build();

        orderRequest.setApplicationContext(applicationContext);

        return paypalClient.post()
                .uri("/v2/checkout/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .body(orderRequest)
                .retrieve()
                .toEntity(OrderResponse.class)
                .getBody();
    }


    public OrderCaptureResponse captureOrder(String orderId){
        return paypalClient.post()
                .uri("/v2/checkout/orders/{order_id}/capture", orderId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .retrieve()
                .toEntity(OrderCaptureResponse.class)
                .getBody();
    }

    public PurchaseItem createPurchaseItem(SesionMentoria sesionMentoria) {
        PurchaseItem purchaseItem = new PurchaseItem();
        float price = (float) sesionMentoria.getPrecio();

        purchaseItem.setPrice(price);
        purchaseItem.setQuantity(1);
        purchaseItem.setSesionMentoriaid(sesionMentoria);

        PurchaseItem purchasedItem = purchaseItemRepository.save(purchaseItem);
        return purchasedItem;
    }

    public Purchase createPurchase(PurchaseItem purchaseItem, Usuario usuario) {
        Purchase purchase = new Purchase();

        purchase.setTotal(purchaseItem.getPrice());
        purchase.setUser(usuario);
        purchase.setCreatedAt(LocalDateTime.now());
        purchase.setPaymentStatus(PaymentStatus.PENDING);
        purchase.setItems(Collections.singletonList(purchaseItem));

        Purchase purchased = purchaseRepository.save(purchase);
        return purchased;
    }
}
