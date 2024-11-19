package com.gymbd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Endereco;
import com.gymbd.model.Plano;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.PlanoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    

    // Buscar plano por ID
    public Plano buscarPlanoPorId(Integer id) {
    	return planoRepository.findById(id).orElse(null);
    }

    // Salvar plano
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvarPlano(Plano plano) {
        String sql = "INSERT INTO plano (fpk_id_plano, duracao_meses, fk_id_unidade, data_matricula, preco) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, plano.getFpkIdPlano(), plano.getDuracaoEmMeses(), plano.getUnidade().getPkIdUnidade(), plano.getDataDeMatricula(), plano.getPreco());
    }

    public void atualizarPlano(Plano plano) {
        String sql = "UPDATE plano SET duracao_meses = ?, fk_id_unidade = ?, data_matricula = ?, preco = ? WHERE fpk_id_plano = ?";
        jdbcTemplate.update(sql, plano.getDuracaoEmMeses(), plano.getUnidade().getPkIdUnidade(), plano.getDataDeMatricula(), plano.getPreco(), plano.getFpkIdPlano());
    }

    
    
    
}
