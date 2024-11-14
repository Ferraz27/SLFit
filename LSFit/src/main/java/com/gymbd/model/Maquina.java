package com.gymbd.model;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "maquina")
public class Maquina {

	@Id
	@Column(name = "pk_id_maquina", nullable = false)
	private Integer pkIdMaquina;
	
	@Column(name = "data_manutencao", nullable = false)
	private Date dataManutencao;
	
	@Column(name="nome_maquina", length = 100, nullable = false)
	private String nomeMaquina;
	
	@OneToOne(mappedBy = "maquina",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Exercicio exercicio;
}
