package com.gymbd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @Column(name = "fpk_id_pessoa")
    private Integer fpkIdPessoa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fpk_id_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;

    @Column(name = "rua_endereco")
    private String rua;

    @Column(name = "bairro_endereco")
    private String bairro;

    @Column(name = "numero_endereco")
    private Integer numero;

    @Column(name = "cep_endereco")
    private String cep;


    public Integer getFpkIdPessoa() {
		return fpkIdPessoa;
	}

	public void setFpkIdPessoa(Integer fpkIdPessoa) {
		this.fpkIdPessoa = fpkIdPessoa;
	}

	public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
