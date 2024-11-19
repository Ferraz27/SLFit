package com.gymbd.service;

import com.gymbd.model.Endereco;
import com.gymbd.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Método para salvar um Endereco
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvarEndereco(Endereco endereco) {
        String sql = "INSERT INTO enderecos (fpk_id_pessoa, rua_endereco, bairro_endereco, numero_endereco, cep_endereco) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, endereco.getFpkIdPessoa(), endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getCep());
    }

    public void atualizarEndereco(Endereco endereco) {
        String sql = "UPDATE enderecos SET rua_endereco = ?, bairro_endereco = ?, numero_endereco = ?, cep_endereco = ? WHERE fpk_id_pessoa = ?";
        jdbcTemplate.update(sql, endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getCep(), endereco.getFpkIdPessoa());
    }
    // Método para buscar um Endereco por id
    public Endereco buscarEnderecoPorId(Integer id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    // Adicione outros métodos conforme necessário
}
