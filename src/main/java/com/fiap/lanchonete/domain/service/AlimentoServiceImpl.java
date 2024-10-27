package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.mapper.AlimentoMapper;
import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.pojo.CreateAlimentoDto;
import com.fiap.lanchonete.domain.pojo.DeleteAlimentoDto;
import com.fiap.lanchonete.domain.pojo.EditAlimentoDto;
import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class AlimentoServiceImpl implements AlimentoService {

    private AlimentoRepository alimentoRepository;

    private AlimentoMapper alimentoMapper;

    @Override
    public void cadastrarAlimento(CreateAlimentoDto createAlimentoDto) {
        Short lastCodigoAlimento = alimentoRepository.getNextCodigoAlimento(createAlimentoDto.codigoTipoAlimento());
        Alimento alimento = alimentoMapper.toDomain(createAlimentoDto);
        alimento.setCodigoAlimento(lastCodigoAlimento);

        alimentoRepository.insertAlimento(alimento);
    }

    @Override
    public void editarAlimento(EditAlimentoDto editAlimentoDto) {
        Alimento alimentoById = alimentoRepository.getAlimentoById(editAlimentoDto.codigoAlimento(), editAlimentoDto.codigoTipoAlimento());
        if (Objects.isNull(alimentoById)) {
            throw new NotFoundException("Alimento não encontrado!");
        }

        Alimento alimento = alimentoMapper.toDomain(editAlimentoDto);
        alimentoRepository.updateAlimento(alimento);
    }

    @Override
    public void deletarAlimento(DeleteAlimentoDto deleteAlimentoDto) {
        Alimento alimentoById = alimentoRepository.getAlimentoById(deleteAlimentoDto.codigoAlimento(), deleteAlimentoDto.codigoTipoAlimento());
        if (Objects.isNull(alimentoById)) {
            throw new NotFoundException("Alimento não encontrado!");
        }
        alimentoRepository.deleteAlimento(deleteAlimentoDto.codigoAlimento(), deleteAlimentoDto.codigoTipoAlimento());
    }

    @Override
    public List<Alimento> listarAlimentosPorTipo(Short codigoTipoAlimento) {
        return alimentoRepository.getAlimentosByTipo(codigoTipoAlimento);
    }
}
