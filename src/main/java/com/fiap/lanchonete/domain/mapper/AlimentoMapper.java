package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.pojo.CreateAlimentoDto;
import com.fiap.lanchonete.domain.pojo.EditAlimentoDto;

public interface AlimentoMapper {
    Alimento toDomain(CreateAlimentoDto createAlimentoDto);

    Alimento toDomain(EditAlimentoDto editAlimentoDto);
}
