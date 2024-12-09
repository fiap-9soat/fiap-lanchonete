package com.fiap.lanchonete.infrastructure.quarkusrest.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fiap.lanchonete.domain.model.QrCodeDto;

@Mapper(componentModel = "jakarta")
public interface QrCodeDTOMapper {

    @Mapping(source = "in_store_order_id", target = "idPedidoLoja")
    @Mapping(source = "qr_data", target = "qrCode")
    QrCodeDto toDomain(ExternalQrCodeDto dto);

    @Mapping(source = "idPedidoLoja", target = "in_store_order_id")
    @Mapping(source = "qrCode", target = "qr_data")
    ExternalQrCodeDto fromDomain(QrCodeDto dto);
}
