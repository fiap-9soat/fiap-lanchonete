package dbo;

import com.microservice.quarkus.domain.model.Pedido;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "Pedidos")
public class PedidoEntity extends Pedido {
    @Id
    @GeneratedValue
    private Integer codigoPedido;

    @CreationTimestamp
    private Instant tsPedido;

    @ManyToOne()
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo_cliente")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_alimento", referencedColumnName = "codigo_alimento")
    @JoinColumn(name = "codigo_tipo_alimento", referencedColumnName = "codigo_tipo_alimento")
    private AlimentoEntity alimento;
}
