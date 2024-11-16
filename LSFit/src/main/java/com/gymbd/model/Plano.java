package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;


@Entity
@Table(name = "plano")
public class Plano {
	
	@Id
	@Column(name="fpk_id_plano")
	private Integer fpkIdPlano;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fpk_id_plano")
    private Aluno aluno;
	
	@Column(name="preco")
	private double preco;
	
	public Integer getFpkIdPlano() {
		return fpkIdPlano;
	}

	public void setFpkIdPlano(Integer fpkIdPlano) {
		this.fpkIdPlano = fpkIdPlano;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getDuracaoEmMeses() {
		return duracaoEmMeses;
	}

	public void setDuracaoEmMeses(Integer duracaoEmMeses) {
		this.duracaoEmMeses = duracaoEmMeses;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Column(name="duracao_meses")
	private Integer duracaoEmMeses;
	
	

}
