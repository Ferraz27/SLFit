package com.gymbd.model;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "unidade")
public class Unidade {
	
	@Id
	@Column(name="pk_id_unidade")
	private Integer pkIdUnidade;
	
	@Column(name = "rua", length = 100)
    private String rua;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "cep", length = 8)
    private String cep;
    
    @Column(name = "telefone1", length = 15)
    private String telefone1Unidade;

    @Column(name = "telefone2", length = 15)
    private String telefone2Unidade;
    
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_gerente", insertable = false, updatable = false)
	private Gerente gerente;
	

}
