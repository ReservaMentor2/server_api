package com.reservamentor.service.impl;

import com.reservamentor.dto.PurchaseCreateUpdateDTO;
import com.reservamentor.dto.PurchaseDTO;
import com.reservamentor.dto.PurchaseReportDTO;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.mapper.PurchaseMapper;
import com.reservamentor.model.entity.Purchase;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.model.entity.enums.PaymentStatus;
import com.reservamentor.repository.SesionMentoriaRepository;
import com.reservamentor.repository.PurchaseRepository;
import com.reservamentor.repository.UsuarioRepository;
import com.reservamentor.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UsuarioRepository userRepository;
    private final SesionMentoriaRepository sesionMentoriaRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    @Transactional
    public PurchaseDTO createPurchase(PurchaseCreateUpdateDTO purchaseDTO) {
        // Convertir el DTO en una entidad Purchase
        Purchase purchase = purchaseMapper.toPurchaseEntity(purchaseDTO);

        // Verificar si el cliente existe en la base de datos
        /*User user = userRepository.findById(purchaseDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + purchaseDTO.getCustomerId()));*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = null;
        /*
        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            usuario = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }

         */


        purchase.setUser(usuario); // Asociar el cliente a la compra

        // Verificar si los libros existen en la base de datos antes de proceder
        purchase.getItems().forEach(item -> {
            SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(item.getSesionMentoriaid().getId())
                    .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + item.getSesionMentoriaid().getId()));
            item.setSesionMentoriaid(sesionMentoria); // Asociar el libro existente al PurchaseItem
            item.setPurchase(purchase); // Asociar el PurchaseItem a la compra actual
        });

        // Establecer la fecha de creación y estado de pago
        purchase.setCreatedAt(LocalDateTime.now());
        purchase.setPaymentStatus(PaymentStatus.PENDING);

        // Calcular el total basado en la cantidad de libros comprados
        Float total = purchase.getItems()
                .stream()
                .map(item -> item.getPrice() * item.getQuantity())
                .reduce(0f, Float::sum);

        purchase.setTotal(total);

        // Guardar la compra
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Retornar el DTO mapeado
        return purchaseMapper.toPurchaseDTO(savedPurchase);
    }


  /* @Override
    @Transactional(readOnly = true)
    public List<PurchaseDTO> getPurchaseHistoryByUserId(Integer userId) {
        return purchaseRepository.findByUserId(userId)
                .stream()
                .map(purchaseMapper::toPurchaseDTO)
                .collect(Collectors.toList());
    }*/


    @Override
    @Transactional(readOnly = true)
    public List<PurchaseDTO> getPurchaseHistoryByUserId() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = null;
/*
        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            usuario = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }
*/
        return purchaseRepository.findByUserId(usuario.getId()).stream()
                .map(purchaseMapper::toPurchaseDTO)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReportDTO> getPurchaseReportByDate() {
        List<Object[]> results = purchaseRepository.getPurchaseReportByDate();

        // Mapea cada Object[] a un PurchaseReportDTO
        return results.stream().map(result ->
                new PurchaseReportDTO(
                        ((Integer) result[0]).intValue(),  // Cast de la cantidad
                        (String) result[1]                // Cast de la fecha
                )
        ).collect(Collectors.toList());
    }

    /////

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .map(purchaseMapper::toPurchaseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO getPurchaseById(Integer id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));
        return purchaseMapper.toPurchaseDTO(purchase);  // Retornar el DTO en lugar de la entidad
    }


    @Override
    @Transactional
    public PurchaseDTO confirmPurchase(Integer purchaseId) {
        // Obtener la entidad Purchase directamente desde el repositorio
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));

        // Confirmar la compra y calcular el total
       /* Float total = purchase.getItems()
                .stream()
                .map(item -> item.getPrice() * item.getQuantity())
                .reduce(0f, Float::sum);

        purchase.setTotal(total);*/
        purchase.setPaymentStatus(PaymentStatus.PAID);

        // Guardar y retornar el DTO actualizado
        Purchase updatedPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.toPurchaseDTO(updatedPurchase);
    }

}