package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;


@Entity
@Table(name="exercicio")
public class Exercicio {
	
	
	@Id
	@Column(name="pk_nome_exercicio", length = 50, nullable = false)
	private String nomeExercicio;
	
	@Column(name="series")
	private int series;
	
	@Column(name="repeticoes")
	private int numeroDeRepeticoes;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_maquina", insertable = false, updatable = false)
    private Maquina maquina;
	
	@OneToOne(mappedBy = "exercicio",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FichaDeExercicio fichaDeExercicio;

	
	
	

}
