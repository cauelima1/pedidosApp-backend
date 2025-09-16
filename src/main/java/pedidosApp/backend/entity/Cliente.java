package pedidosApp.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(unique = true)
    private Long cnpj;
    private String cep;
    private String endereco;
    private String municipio;
    private String uf;
    private BigDecimal df;
    private boolean isContribuinteICMS;

    public Cliente(Long id, String nome, Long cnpj, String cep, String endereco, String municipio, String uf, BigDecimal df, boolean isContribuinteICMS) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cep = cep;
        this.endereco = endereco;
        this.municipio = municipio;
        this.uf = uf;
        this.df = df;
        this.isContribuinteICMS = isContribuinteICMS;
    }

    public Cliente () {   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getDf() {
        return df;
    }

    public void setDf(BigDecimal df) {
        this.df = df;
    }

    public boolean isContribuinteICMS() {
        return isContribuinteICMS;
    }

    public void setContribuinteICMS(boolean contribuinteICMS) {
        isContribuinteICMS = contribuinteICMS;
    }
}

