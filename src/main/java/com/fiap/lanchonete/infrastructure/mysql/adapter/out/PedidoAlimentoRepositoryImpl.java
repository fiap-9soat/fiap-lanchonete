package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.util.ArrayList;
import java.util.List;

import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoAlimentoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoAlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoAlimentoEntityId;
import com.fiap.lanchonete.infrastructure.mysql.mapper.PedidoAlimentoEntityMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoAlimentoRepositoryImpl implements PedidoAlimentoRepository {

    PedidoAlimentoPanacheRepository pedidoAlimentoPanacheRepository;

    PedidoAlimentoEntityMapper pedidoAlimentoEntityMapper;

    @Override
    public void inserir(PedidoAlimento pedidoAlimento) {
        PedidoAlimentoEntity entity = pedidoAlimentoEntityMapper.toEntity(pedidoAlimento);
        pedidoAlimentoPanacheRepository.persist(entity);
    }

    @Override
    public void checarSeTipoAlimentoJaExiste(PedidoAlimento pedidoAlimento) throws Exception {
        PedidoAlimentoEntity query = pedidoAlimentoPanacheRepository.findById(new PedidoAlimentoEntityId(
                pedidoAlimento.getCodigoPedido(),
                pedidoAlimento.getCodigoTipoAlimento(),
                pedidoAlimento.getCodigoAlimento()));

        if (query != null) {
            throw new Exception("Esse tipo de alimento já foi incluido no sistema. Remova ou edite seu pedido.");
        }
    }

    @Override
    public void remover(PedidoAlimento pedidoAlimento) {
        pedidoAlimentoPanacheRepository.delete("""
                FROM PedidoAlimentoEntity pae
                WHERE codigoPedido = ?1
                AND codigoTipoAlimento = ?2
                AND codigoAlimento = ?3
                """,
                pedidoAlimento.getCodigoPedido(),
                pedidoAlimento.getCodigoTipoAlimento(),
                pedidoAlimento.getCodigoAlimento());
    }

    @Override
    public void editar(PedidoAlimento pedidoAlimento) {
        pedidoAlimentoPanacheRepository.update("""
                UPDATE PedidoAlimentoEntity
                SET quantidadeAlimento = ?1
                WHERE codigoPedido = ?2
                AND codigoTipoAlimento = ?3
                AND codigoAlimento = ?4
                """,
                pedidoAlimento.getQuantidadeAlimento(),
                pedidoAlimento.getCodigoPedido(),
                pedidoAlimento.getCodigoTipoAlimento(),
                pedidoAlimento.getCodigoAlimento());
    }

    @Override
    public List<PedidoAlimento> listar() {
        List<PedidoAlimento> listaPedidoAlimento = new ArrayList<>();
        List<PedidoAlimentoEntity> listaPedidoAlimentoEntity = pedidoAlimentoPanacheRepository.find("""
                SELECT pa
                FROM PedidoAlimentoEntity pa
                """).list();
        listaPedidoAlimentoEntity.stream()
                .forEach(entity -> listaPedidoAlimento.add(pedidoAlimentoEntityMapper.toDomain(entity)));
        return listaPedidoAlimento;
    }

    @Override
    public List<PedidoAlimento> listarPorCodigoPedido(Integer codigoPedido) {
        return pedidoAlimentoPanacheRepository
                .find("codigoPedido", codigoPedido)
                .stream()
                .map(pedidoAlimentoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void removerPorCodigoPedido(Integer codigoPedido) {
        pedidoAlimentoPanacheRepository.delete("""
                FROM PedidoAlimentoEntity pae
                WHERE codigoPedido = ?1
                """,
                codigoPedido);
    }
}
