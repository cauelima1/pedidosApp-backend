package pedidosApp.backend.entity;
import jakarta.persistence.*;
import pedidosApp.backend.entity.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name="tb_pedido")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> items;
    private BigDecimal valorTotal;
    private BigDecimal ICMS;
    private BigDecimal DIFAL;
    private BigDecimal IPI;
    private BigDecimal PIS;
    private BigDecimal COFINS;
    private String validade;
    private String condicaoFrete;
    private String observacoes;
    private StatusPedido statusPedido;
    private Date data;


    public Pedido(){}


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getICMS() {
        return ICMS;
    }

    public void setICMS(BigDecimal ICMS) {
        this.ICMS = ICMS;
    }

    public BigDecimal getDIFAL() {
        return DIFAL;
    }

    public void setDIFAL(BigDecimal DIFAL) {
        this.DIFAL = DIFAL;
    }

    public BigDecimal getIPI() {
        return IPI;
    }

    public void setIPI(BigDecimal IPI) {
        this.IPI = IPI;
    }

    public BigDecimal getPIS() {
        return PIS;
    }

    public void setPIS(BigDecimal PIS) {
        this.PIS = PIS;
    }

    public BigDecimal getCOFINS() {
        return COFINS;
    }

    public void setCOFINS(BigDecimal COFINS) {
        this.COFINS = COFINS;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCondicaoFrete() {
        return condicaoFrete;
    }

    public void setCondicaoFrete(String condicaoFrete) {
        this.condicaoFrete = condicaoFrete;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}


