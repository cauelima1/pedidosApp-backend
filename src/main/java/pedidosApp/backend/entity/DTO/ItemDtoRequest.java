package pedidosApp.backend.entity.DTO;

public record
ItemDtoRequest (Long idPedido, String nome, String fabricante, String obs,
                String quantidade, String tipo,
                String custo){
}
