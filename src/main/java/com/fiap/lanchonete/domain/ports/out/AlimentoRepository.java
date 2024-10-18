package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.Alimento;

import java.util.List;

public interface AlimentoRepository {
    public void insertAlimento(Alimento alimento);

    public void updateAlimento(Alimento alimento);

    public void deleteAlimento(Short codigoAlimento, Short codigoTipoAlimento);

    public Short getLastCodigoAlimento(Short codigoTipoAlimento);

    public Alimento getAlimentoById(Short codigoAlimento, Short codigoTipoAlimento);

    public List<Alimento> getAlimentosByTipo(Short codigoTipoAlimento);
}
