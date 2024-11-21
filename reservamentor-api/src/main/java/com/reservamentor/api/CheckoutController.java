package com.reservamentor.api;

import com.reservamentor.dto.PaymentCaptureResponse;
import com.reservamentor.dto.PaymentOrderResponse;
import com.reservamentor.dto.PurchaseItemCreateUpdateDTO;
import com.reservamentor.integration.payment.paypal.service.PayPalService;
import com.reservamentor.model.entity.Purchase;
import com.reservamentor.model.entity.PurchaseItem;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.service.CheckoutService;
import com.reservamentor.service.SesionMentoriaService;
import com.reservamentor.service.UsuarioService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
@PreAuthorize("hasRole('ESTUDIANTE')")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final UsuarioService usuarioService;
    private final SesionMentoriaService sesionMentoriaService;
    private final PayPalService payPalService;

    @PostMapping("/create")
    public ResponseEntity<PaymentOrderResponse> createPaymentOrder(
            @RequestParam Integer purchaseId,
            @RequestParam String returnUrl,
            @RequestParam String cancelUrl,
            @RequestParam(required = false, defaultValue = "paypal") String paymentProvider
    ) throws MessagingException {
        PaymentOrderResponse response = checkoutService.createPayment(purchaseId, returnUrl, cancelUrl);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/createID/{sesionMentoriaId}")
    public ResponseEntity<Integer> createPurchaseID(@PathVariable Integer sesionMentoriaId, @RequestHeader("Authorization") String bearerToken) {
        //Requiere de la sesion de mentoria
        //Authenticator
        Usuario usuario = usuarioService.getUsuario(bearerToken);
        SesionMentoria sesionMentoria = sesionMentoriaService.searchById(sesionMentoriaId);

        PurchaseItem purchaseItem = payPalService.createPurchaseItem(sesionMentoria);
        Purchase purchase = payPalService.createPurchase(purchaseItem,usuario);

        return new ResponseEntity<>(purchase.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/capture")
    public ResponseEntity<PaymentCaptureResponse> capturePaymentOrder(
            @RequestParam String orderId,
            @RequestParam(required = false, defaultValue = "paypal") String paymentProvider
    ) throws MessagingException {
        PaymentCaptureResponse response = checkoutService.capturePayment(orderId);

        if (response.isCompleted()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}