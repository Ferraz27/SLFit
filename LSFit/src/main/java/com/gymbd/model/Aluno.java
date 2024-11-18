package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;



@Entity
@PrimaryKeyJoinColumn(name = "fpk_id_aluno", referencedColumnName = "pk_id_pessoa")
@Table(name = "aluno")
public class Aluno extends Pessoa {
    
	@OneToOne(mappedBy = "aluno",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Plano plano;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_que_indicou", referencedColumnName = "fpk_id_aluno", nullable = true)
    private Aluno indicacao;
	
	
	public Plano getPlano() {
		return plano;
	}

	public Aluno getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(Aluno indicacao) {
		this.indicacao = indicacao;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public FichaDeExercicio getFichaDeExercicio() {
		return fichaDeExercicio;
	}

	public void setFichaDeExercicio(FichaDeExercicio fichaDeExercicio) {
		this.fichaDeExercicio = fichaDeExercicio;
	}

	@OneToOne(mappedBy = "aluno",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FichaDeExercicio fichaDeExercicio;
}

