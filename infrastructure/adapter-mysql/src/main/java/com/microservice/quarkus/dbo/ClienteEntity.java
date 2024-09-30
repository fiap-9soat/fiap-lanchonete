package dbo;

import com.microservice.quarkus.domain.model.Cliente;
import jakarta.persistence.*;

@Entity
@Table(name = "Clientes")
public class ClienteEntity extends Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigoCliente;

}
