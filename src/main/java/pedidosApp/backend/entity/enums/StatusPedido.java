package pedidosApp.backend.entity.enums;

public enum StatusPedido {
    ABERTO("Pedido em aberto"),
    ENCERRADO_REPROVADO("Pedido encerrado e reprovado"),
    ENCERRADO_APROVADO("Pedido encerrado e aprovado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
