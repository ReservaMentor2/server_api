package com.reservamentor.mapper;


import com.reservamentor.dto.PurchaseCreateUpdateDTO;
import com.reservamentor.dto.PurchaseDTO;
import com.reservamentor.dto.PurchaseItemCreateUpdateDTO;
import com.reservamentor.dto.PurchaseItemDTO;
import com.reservamentor.model.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    private final ModelMapper modelMapper;

    public PurchaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    //Convertir PurchaseCreateUpdateDTO a Purchase (Crear una compra)
    public Purchase toPurchaseEntity(PurchaseCreateUpdateDTO purchaseDTO) {
        Purchase purchase = modelMapper.map(purchaseDTO, Purchase.class);

        Usuario usuario = new Usuario();
        usuario.setId(purchaseDTO.getCustomerId());
        purchase.setUser(usuario);

        //Mapear manualmente los items de la compra
        purchase.setItems(purchaseDTO.getItems().stream()
                .map(this::toPurchaseItemEntity)
                .toList());

        return purchase;
    }

    //Convertir Purchase a PurchaseDTO (Mostrar una compra)
    public PurchaseDTO toPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = modelMapper.map(purchase, PurchaseDTO.class);

        purchaseDTO.setItems(purchase.getItems().stream()
                .map(this::toPurchaseItemDTO)
                .toList());
        return purchaseDTO;
    }

    private PurchaseItem toPurchaseItemEntity(PurchaseItemCreateUpdateDTO itemDTO) {
        PurchaseItem item = modelMapper.map(itemDTO, PurchaseItem.class);
        SesionMentoria sesionMentoria = new SesionMentoria();
        sesionMentoria.setId(itemDTO.getSesionMentoriaId());
        item.setSesionMentoriaid(sesionMentoria);
        return item;
    }

    private PurchaseItemDTO toPurchaseItemDTO(PurchaseItem item) {
        PurchaseItemDTO itemDTO = modelMapper.map(item, PurchaseItemDTO.class);
        itemDTO.setSesionMentoriaTitle(item.getSesionMentoriaid().getTitulo() + "USER");
        return itemDTO;
    }

}