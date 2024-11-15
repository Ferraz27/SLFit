package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;



@Entity
@PrimaryKeyJoinColumn(name = "fpk_id_gerente", referencedColumnName = "pk_id_pessoa")
@Table(name = "gerente")
public class Gerente extends Pessoa {
    
	@OneToOne(mappedBy = "gerente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Unidade unidade;
	
}

