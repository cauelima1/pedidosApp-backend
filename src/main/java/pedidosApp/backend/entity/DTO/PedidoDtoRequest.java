package pedidosApp.backend.entity.DTO;


public record PedidoDtoRequest(Long idCliente,String condicaoFrete,
                               String observacoes, String statusPedido,
                               String ipi, String st, String mc,
                               String mc1, String frete, String vdot,
                               String stvd ) {
}
