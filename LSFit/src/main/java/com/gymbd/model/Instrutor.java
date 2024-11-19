package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;


@Entity
@PrimaryKeyJoinColumn(name = "fpk_id_instrutor", referencedColumnName = "pk_id_pessoa")
@Table(name = "instrutor")
public class Instrutor extends Pessoa {
	
	@Column(name = "salario_instrutor", nullable = false)
	private Double salarioInstrutor;

	public Double getSalarioInstrutor() {
	    return salarioInstrutor;
	}

	public void setSalarioInstrutor(Double salarioInstrutor) {
	    this.salarioInstrutor = salarioInstrutor;
	}

	@OneToMany(mappedBy = "instrutor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FichaDeExercicio> fichasDeExercicios;


	public List<FichaDeExercicio> getFichasDeExercicios() {
		return fichasDeExercicios;
	}

	public void setFichasDeExercicios(List<FichaDeExercicio> fichasDeExercicios) {
		this.fichasDeExercicios = fichasDeExercicios;
	}
}

