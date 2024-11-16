package com.gymbd.model;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "unidade")
public class Unidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_id_unidade")
	private Integer pkIdUnidade;
	
	@Column(name = "rua", length = 100)
    private String rua;
	
	@Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "cep", length = 8)
    private String cep;
    
    @Column(name = "telefone1", length = 15)
    private String telefone1Unidade;

    @Column(name = "telefone2", length = 15)
    private String telefone2Unidade;
    
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_gerente")
	private Gerente gerente;

    public Integer getPkIdUnidade() {
		return pkIdUnidade;
	}

	public void setPkIdUnidade(Integer pkIdUnidade) {
		this.pkIdUnidade = pkIdUnidade;
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

	public String getTelefone1Unidade() {
		return telefone1Unidade;
	}

	public void setTelefone1Unidade(String telefone1Unidade) {
		this.telefone1Unidade = telefone1Unidade;
	}

	public String getTelefone2Unidade() {
		return telefone2Unidade;
	}

	public void setTelefone2Unidade(String telefone2Unidade) {
		this.telefone2Unidade = telefone2Unidade;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
	    this.gerente = gerente;
	}


	
	

}
