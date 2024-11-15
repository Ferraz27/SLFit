package com.gymbd.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "maquina")
public class Maquina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_id_maquina", nullable = false)
	private Integer pkIdMaquina;
	
	@Column(name = "data_manutencao", nullable = false)
	private Date dataManutencao;
	
	@Column(name="nome_maquina", length = 100, nullable = false)
	private String nomeMaquina;
	
	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Exercicio> exercicios;


	public Integer getPkIdMaquina() {
		return pkIdMaquina;
	}

	public void setPkIdMaquina(Integer pkIdMaquina) {
		this.pkIdMaquina = pkIdMaquina;
	}

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public String getNomeMaquina() {
		return nomeMaquina;
	}

	public void setNomeMaquina(String nomeMaquina) {
		this.nomeMaquina = nomeMaquina;
	}

	public List<Exercicio> getExercicios() {
	    return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
	    this.exercicios = exercicios;
	}

}
