package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.util.List;

import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.AlimentoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.AlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.AlimentoMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlimentoRepositoryImpl implements AlimentoRepository {

    AlimentoPanacheRepository alimentoPanacheRepository;

    AlimentoMapper alimentoMapper;

    @Override
    public void insertAlimento(Alimento alimento) {
        AlimentoEntity entity = alimentoMapper.toEntity(alimento);
        alimentoPanacheRepository.persist(entity);
    }

    @Override
    public void editAlimento(Alimento alimento, Integer codigoTipoAlimento, Integer codigoAlimento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editAlimento'");
    }

    @Override
    public void deleteAlimento(Alimento alimento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAlimento'");
    }

    @Override
    public Alimento getAlimentoById(int codigoTipoAlimento, int codigoAlimento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAlimentoById'");
    }

    @Override
    public List<Alimento> getAlimentosByCategory(int codigoTipoAlimento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAlimentosByCategory'");
    }

}
