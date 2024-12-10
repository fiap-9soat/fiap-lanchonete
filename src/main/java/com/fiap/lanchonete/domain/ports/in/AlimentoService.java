package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.pojo.CreateAlimentoDto;
import com.fiap.lanchonete.domain.pojo.DeleteAlimentoDto;
import com.fiap.lanchonete.domain.pojo.EditAlimentoDto;

import java.util.List;

public interface AlimentoService {

    Alimento buscarAlimentoPorId(Short codigoAlimento, Short codigoTipoAlimento);

    void cadastrarAlimento(CreateAlimentoDto createAlimentoDto);

    void editarAlimento(EditAlimentoDto editAlimentoDto);

    void deletarAlimento(DeleteAlimentoDto deleteAlimentoDto);

    List<Alimento> listarAlimentosPorTipo(Short codigoTipoAlimento);
}
