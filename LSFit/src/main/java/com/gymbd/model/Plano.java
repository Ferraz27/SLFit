package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;


@Entity
@Table(name = "plano")
public class Plano {
	
	@Column(name="duracao_meses")
	private Integer duracaoEmMeses;
	
	@Id
	@Column(name="fpk_id_plano")
	private Integer fpkIdPlano;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fpk_id_plano")
    private Aluno aluno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_unidade", nullable = false)
	private Unidade unidade;
	
	@Column(name = "data_matricula")
    private LocalDate dataDeMatricula;
    
    public LocalDate getDataDeMatricula() {
        return dataDeMatricula;
    }

    public void setDataDeMatricula(LocalDate dataDeMatricula) {
        this.dataDeMatricula = dataDeMatricula;
    }

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

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

	
	
	

}
