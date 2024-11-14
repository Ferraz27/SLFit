package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;



@Entity
@PrimaryKeyJoinColumn(name = "fpk_id_aluno", referencedColumnName = "pk_id_pessoa")
@Table(name = "aluno")
public class Aluno extends Pessoa {
    // Sem colunas adicionais, pois Aluno só herda de Pessoa

    // Getters e Setters (se necessário)
}

