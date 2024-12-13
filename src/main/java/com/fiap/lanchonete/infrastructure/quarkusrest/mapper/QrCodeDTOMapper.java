package com.fiap.lanchonete.infrastructure.quarkusrest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fiap.lanchonete.domain.model.QrCodeDto;
import com.fiap.lanchonete.infrastructure.quarkusrest.dto.ExternalQrCodeDto;

@Mapper(componentModel = "jakarta")
public interface QrCodeDTOMapper {

    @Mapping(source = "qr_data", target = "qrCode")
    @Mapping(target = "codigoPedido", ignore = true)
    QrCodeDto toDomain(ExternalQrCodeDto dto);
}
