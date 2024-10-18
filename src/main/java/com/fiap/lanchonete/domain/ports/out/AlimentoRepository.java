package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.Alimento;

import java.util.List;

public interface AlimentoRepository {
    public void insertAlimento(Alimento alimento);

    public void updateAlimento(Alimento alimento);

    public void deleteAlimento(Integer codigoAlimento, Integer codigoTipoAlimento);

    public Integer getLastCodigoAlimento(Alimento alimento);

    public Alimento getAlimentoById(int codigoAlimento, int codigoTipoAlimento);

    public List<Alimento> getAlimentosByTipo(int codigoTipoAlimento);
}
