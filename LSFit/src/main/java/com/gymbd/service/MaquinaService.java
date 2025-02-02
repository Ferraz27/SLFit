package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Maquina;
import com.gymbd.model.Pessoa;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.MaquinaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Salvar maquina
    public void salvarMaquina(Maquina maquina) {
        String sql = "INSERT INTO maquina (data_manutencao, nome_maquina) VALUES (?, ?)";
        jdbcTemplate.update(sql, maquina.getDataManutencao(), maquina.getNomeMaquina());
    }

    // Atualizar maquina
    public void atualizarMaquina(Maquina maquina) {
        String sql = "UPDATE maquina SET data_manutencao = ?, nome_maquina = ? WHERE pk_id_maquina = ?";
        jdbcTemplate.update(sql, maquina.getDataManutencao(), maquina.getNomeMaquina(), maquina.getPkIdMaquina());
    }
    
    public  List<Maquina> listarMaquinas() {
        return maquinaRepository.listarTodasMaquinas(); // Busca todos os Instrutors do banco
    }
    
    public Maquina buscarMaquinaPorId(Integer id) {
        return maquinaRepository.buscarMaquinaPorId(id);  // Chama o método do Repository
    }
    
    public List<Maquina> listarMaquinasEExercicios() {
        return maquinaRepository.buscarMaquinasEExercicios();  // Chama o repositório para fazer a consulta
    }
    
    public void deletarMaquina(Integer id) {
        maquinaRepository.deletarMaquinaPorId(id);
    
    }
}

