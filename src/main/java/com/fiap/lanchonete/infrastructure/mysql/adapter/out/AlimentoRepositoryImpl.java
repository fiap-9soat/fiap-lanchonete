package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.AlimentoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.AlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.AlimentoEntityId;
import com.fiap.lanchonete.infrastructure.mysql.mapper.AlimentoEntityMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AlimentoRepositoryImpl implements AlimentoRepository {

    AlimentoPanacheRepository alimentoPanacheRepository;

    AlimentoEntityMapper alimentoEntityMapper;

    @Override
    public void insertAlimento(Alimento alimento) {
        AlimentoEntity entity = alimentoEntityMapper.toEntity(alimento);
        alimentoPanacheRepository.persist(entity);
    }

    @Override
    public Short getLastCodigoAlimento(Short codigoTipoAlimento) {
        return alimentoPanacheRepository.getEntityManager()
            .createQuery("""
                SELECT IFNULL(MAX(codigoAlimento)+1, '1')
                FROM AlimentoEntity
                WHERE codigoTipoAlimento = :codigoTipoAlimento
                """, Short.class)
            .setParameter("codigoTipoAlimento", codigoTipoAlimento)
            .getSingleResult();
    }

    @Override
    public void updateAlimento(Alimento alimento) {
        AlimentoEntity entity = alimentoEntityMapper.toEntity(alimento);
        alimentoPanacheRepository.getEntityManager()
            .merge(entity);
    }

    @Override
    public void deleteAlimento(Short codigoAlimento, Short codigoTipoAlimento) {

        alimentoPanacheRepository.delete("""
                FROM AlimentoEntity ali
                WHERE codigoAlimento = ?1
                AND codigoTipoAlimento = ?2
                """,
            codigoAlimento,
            codigoTipoAlimento);
    }

    @Override
    public Alimento getAlimentoById(Short codigoAlimento, Short codigoTipoAlimento) {
        AlimentoEntityId entityId = new AlimentoEntityId();
        entityId.setCodigoAlimento(codigoAlimento);
        entityId.setCodigoTipoAlimento(codigoTipoAlimento);
        AlimentoEntity entityById = alimentoPanacheRepository.findById(entityId);
        return alimentoEntityMapper.toDomain(entityById);
    }

    @Override
    public List<Alimento> getAlimentosByTipo(Short codigoTipoAlimento) {
        return alimentoPanacheRepository
            .find("codigoTipoAlimento", codigoTipoAlimento)
            .stream()
            .map(alimentoEntityMapper::toDomain)
            .toList();
    }

}
