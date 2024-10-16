package com.fiap.lanchonete.domain.ports.in;

import java.util.List;

import com.fiap.lanchonete.domain.model.Alimento;

public interface AlimentoService {
    public void cadastrarAlimento(Alimento alimento);

    public void editarAlimento(Alimento alimento, Integer codigoTipoAlimento, Integer codigoAlimento);

    public void deletarAlimento(Alimento alimento);

    public List<Alimento> listarAlimentosPorCategoria(Integer codigoTipoAlimento);
}
