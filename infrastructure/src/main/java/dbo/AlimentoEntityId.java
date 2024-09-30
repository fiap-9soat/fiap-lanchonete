package dbo;

import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class AlimentoEntityId implements Serializable {
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
}
