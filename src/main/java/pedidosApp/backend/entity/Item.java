package pedidosApp.backend.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Table(name = "db_item")
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
    private String fabricante;
    private String nome;
    private String tipo;
    private String obs;
    private int quantidade;
    private BigDecimal precoLiqTotalComICMS;
    private BigDecimal precoUniComICMS;
    private BigDecimal IPI;
    private BigDecimal ST;
    private BigDecimal custoTotal;
    private BigDecimal custoUnitario;
    private BigDecimal mc;
    private BigDecimal mc1;
    private BigDecimal frete;
    private BigDecimal vdot;
    private BigDecimal stvd;
    private BigDecimal precoFinal;
    private BigDecimal valorTotalFinal;

    public Item (){}

    public BigDecimal getVdot() {
        return vdot;
    }

    public void setVdot(BigDecimal vdot) {
        this.vdot = vdot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoLiqTotalComICMS() {
        return precoLiqTotalComICMS;
    }

    public void setPrecoLiqTotalComICMS(BigDecimal precoLiqTotalComICMS) {
        this.precoLiqTotalComICMS = precoLiqTotalComICMS;
    }

    public BigDecimal getPrecoUniComICMS() {
        return precoUniComICMS;
    }

    public void setPrecoUniComICMS(BigDecimal precoUniComICMS) {
        this.precoUniComICMS = precoUniComICMS;
    }

    public BigDecimal getIPI() {
        return IPI;
    }

    public void setIPI(BigDecimal IPI) {
        this.IPI = IPI;
    }

    public BigDecimal getST() {
        return ST;
    }

    public void setST(BigDecimal ST) {
        this.ST = ST;
    }

    public BigDecimal getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(BigDecimal custoTotal) {
        this.custoTotal = custoTotal;
    }

    public BigDecimal getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(BigDecimal custoUnitario) {
        this.custoUnitario = custoUnitario;
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

    public BigDecimal getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(BigDecimal precoFinal) {
        this.precoFinal = precoFinal;
    }

    public BigDecimal getValorTotalFinal() {
        return valorTotalFinal;
    }

    public void setValorTotalFinal(BigDecimal valorTotalFinal) {
        this.valorTotalFinal = valorTotalFinal;
    }
}
