package pedidosApp.backend.entity.DTO;

import java.math.BigDecimal;

public record PedidoDtoRequest(Long cnpj, String ICMS, String DIFAL,
                               String IPI, String COFINS, String validade,
                               String condicoesFrete, String observacoes) {
}
