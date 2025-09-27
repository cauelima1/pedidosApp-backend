package pedidosApp.backend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Table(name = "db_item")
@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    private String fabricante;

    private String nome;
    private String tipo;
    private String obs;
    private BigDecimal frete;
    private BigDecimal mc;
    private BigDecimal mc1;
    private BigDecimal quantidade;
    private BigDecimal custoTotal;
    private BigDecimal custo;
    private BigDecimal ipi;
    private BigDecimal st;
    private BigDecimal custoUnitario;
    private BigDecimal vtot;
    private BigDecimal stvd;
    private BigDecimal prcf;
    private BigDecimal vtotf;


    public BigDecimal getVtotf() {
        return vtotf;
    }

    public void setVtotf(BigDecimal vtotf) {
        this.vtotf = vtotf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getPrcf() {
        return prcf;
    }

    public void setPrcf(BigDecimal prcf) {
        this.prcf = prcf;
    }

    public BigDecimal getSt() {
        return st;
    }

    public void setSt(BigDecimal st) {
        this.st = st;
    }

    public BigDecimal getIpi() {
        return ipi;
    }

    public void setIpi(BigDecimal ipi) {
        this.ipi = ipi;
    }

    public BigDecimal getStvd() {
        return stvd;
    }

    public void setStvd(BigDecimal stvd) {
        this.stvd = stvd;
    }

    public BigDecimal getVtot() {
        return vtot;
    }

    public void setVtot(BigDecimal vtot) {
        this.vtot = vtot;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public void setFrete(BigDecimal frete) {
        this.frete = frete;
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


    public BigDecimal getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(BigDecimal custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    public Item() {
    }



    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }


    public BigDecimal getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(BigDecimal custoTotal) {
        this.custoTotal = custoTotal;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }
}
