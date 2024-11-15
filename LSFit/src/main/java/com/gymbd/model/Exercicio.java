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
@Table(name="exercicio")
public class Exercicio {
	
	
	@Id
	@Column(name="pk_nome_exercicio", length = 50, nullable = false)
	private String nomeExercicio;
	
	@Column(name="series")
	private int series;
	
	@Column(name="repeticoes")
	private int numeroDeRepeticoes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fpk_id_maquina", nullable = false) // Chave estrangeira na tabela 'exercicio'
	private Maquina maquina;


	
	
	

}
