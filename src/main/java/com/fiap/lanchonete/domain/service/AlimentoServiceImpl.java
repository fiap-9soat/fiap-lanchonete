package com.fiap.lanchonete.domain.service;

import java.sql.SQLException;
import java.util.List;

import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlimentoServiceImpl implements AlimentoService {

    private AlimentoRepository alimentoRepository;

    @Override
    public void cadastrarAlimento(Alimento alimento) {
        Integer ultimoAlimento = alimentoRepository.getLastCodigoAlimento(alimento);
        alimento.setCodigoAlimento(ultimoAlimento);
        alimentoRepository.insertAlimento(alimento);
    }

    @Override
    public void editarAlimento(Alimento alimento) {
        alimentoRepository.editAlimento(alimento);
    }

    @Override
    public void deletarAlimento(Alimento alimento) {
        alimentoRepository.deleteAlimento(alimento);
    }

    @Override
    public List<Alimento> listarAlimentosPorCategoria(Integer codigoTipoAlimento) {
        return alimentoRepository.getAlimentosByCategory(codigoTipoAlimento);
    }
}
