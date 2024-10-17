package com.fiap.lanchonete.domain.ports.out;

import java.sql.SQLException;
import java.util.List;

import com.fiap.lanchonete.domain.model.Alimento;

public interface AlimentoRepository {
    public void insertAlimento(Alimento alimento);

    public void editAlimento(Alimento alimento);

    public void deleteAlimento(Alimento alimento);

    public Integer getLastCodigoAlimento(Alimento alimento);

    public Alimento getAlimentoById(int codigoTipoAlimento, int codigoAlimento);

    public List<Alimento> getAlimentosByCategory(int codigoTipoAlimento);
}
