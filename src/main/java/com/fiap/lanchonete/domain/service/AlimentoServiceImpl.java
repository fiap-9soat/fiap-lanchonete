package com.fiap.lanchonete.domain.service;

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
        alimentoRepository.insertAlimento(alimento);
    }

    @Override
    public void editarAlimento(Alimento alimento, Integer codigoTipoAlimento, Integer codigoAlimento) {
        alimentoRepository.editAlimento(alimento, codigoTipoAlimento, codigoAlimento);
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
