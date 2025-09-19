package pedidosApp.backend.entity;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @Column(unique = true)
    private long cnpj;
    @Column(unique = true)
    private String nome;
    private String obs;
    private String cep;
    private String endereco;
    private String municipio;
    private String uf;
    private double df;
    private boolean isICMS;


    public Cliente (){}

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public double getDf() {
        return df;
    }

    public void setDf(double df) {
        this.df = df;
    }

    public boolean isICMS() {
        return isICMS;
    }

    public void setICMS(boolean ICMS) {
        isICMS = ICMS;
    }
}

