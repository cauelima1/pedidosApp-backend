package pedidosApp.backend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> items;
    private BigDecimal valorTotal;
    private String condicaoFrete;
    private String observacoes;
    private StatusPedido statusPedido;
    private Date data;

   //impostos e comissões
    private BigDecimal icms;
    private BigDecimal ipi;
    private BigDecimal st;
    private BigDecimal mc;
    private BigDecimal mc1;
    private BigDecimal frete;
    private BigDecimal stvd;


    public Pedido(){}

    public BigDecimal getIcms() {
        return icms;
    }

    public void setIcms(BigDecimal icms) {
        this.icms = icms;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getIpi() {
        return ipi;
    }

    public void setIpi(BigDecimal ipi) {
        this.ipi = ipi;
    }

    public BigDecimal getSt() {
        return st;
    }

    public void setSt(BigDecimal st) {
        this.st = st;
    }

    public BigDecimal getMc() {
        return mc;
    }

    public void setMc(BigDecimal mc) {
        this.mc = mc;
    }

    public BigDecimal getMc1() {
        return mc1;
    }

    public void setMc1(BigDecimal mc1) {
        this.mc1 = mc1;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public void setFrete(BigDecimal frete) {
        this.frete = frete;
    }


    public BigDecimal getStvd() {
        return stvd;
    }

    public void setStvd(BigDecimal stvd) {
        this.stvd = stvd;
    }

    public Date getData() {
        return data;
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


