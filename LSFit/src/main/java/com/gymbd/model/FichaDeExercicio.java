package com.gymbd.model;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name="ficha_de_treino")
public class FichaDeExercicio {
	
	@Id
	@Column(name="pk_id_ficha_de_treino")
	private Integer pkIdFichaDeTreino;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_nome_exercicio", insertable = false, updatable = false)
    private Exercicio exercicio;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_instrutor", insertable = false, updatable = false)
    private Instrutor instrutor;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_aluno", insertable = false, updatable = false)
    private Aluno aluno;
}
