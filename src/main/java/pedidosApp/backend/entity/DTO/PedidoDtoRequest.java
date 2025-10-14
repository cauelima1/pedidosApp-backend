package pedidosApp.backend.entity.DTO;


public record PedidoDtoRequest(Long idCliente,String condicaoFrete,
                               String observacoes,
                               String ipi, String st, String mc,
                               String mc1, String frete,
                               String stvd, String icms,
                               String pis, String cofins) {
}
