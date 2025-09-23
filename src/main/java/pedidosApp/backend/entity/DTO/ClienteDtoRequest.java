package pedidosApp.backend.entity.DTO;

import java.math.BigDecimal;

public record ClienteDtoRequest (String nome, long cnpj, String obs, String cep,
                                 String endereco, String municipio, String uf,
                                 double df){
}
