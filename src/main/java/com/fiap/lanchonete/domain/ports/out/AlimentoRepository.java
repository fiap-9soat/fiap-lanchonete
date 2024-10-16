package com.fiap.lanchonete.domain.ports.out;

import java.util.List;

import com.fiap.lanchonete.domain.model.Alimento;

public interface AlimentoRepository {
    public void insertAlimento(Alimento alimento);

    public void editAlimento(Alimento alimento, Integer codigoTipoAlimento, Integer codigoAlimento);

    public void deleteAlimento(Alimento alimento);

    public Alimento getAlimentoById(int codigoTipoAlimento, int codigoAlimento);

    public List<Alimento> getAlimentosByCategory(int codigoTipoAlimento);
}
