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
	@Column(name="pk_id_plano")
	private Integer pkIdPlano;
	
	@Column(name="preco")
	private double preco;
	
	@Column(name="duracao_meses")
	private Integer duracaoEmMeses;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fpk_id_aluno", insertable = false, updatable = false)
    private Aluno aluno;

}
