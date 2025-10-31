package pedidosApp.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_cliente")
@Entity
public class Cliente {

    @Id
    @Column(unique = true)
    private Long cnpj;
    @Column(unique = true)
    private String nome;
    private String cep;
    private String endereco;
    private String municipio;
    private String uf;
    private String obs;
    private Double df;

    @JsonManagedReference
    @OneToMany( mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedido = new ArrayList<>();

    public Cliente (){}

    public Double getDf(){
        return df;
    }

    public void setDf(Double df){
        this.df = df;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}

