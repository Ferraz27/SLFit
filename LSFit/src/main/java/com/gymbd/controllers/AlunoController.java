package com.gymbd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gymbd.model.Aluno;
import com.gymbd.service.AlunoService;
import com.gymbd.model.Instrutor;
import com.gymbd.model.Pessoa;
import com.gymbd.service.InstrutorService;
import com.gymbd.service.PessoaService;



@Controller
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private InstrutorService instrutorService;
	@Autowired
	private PessoaService pessoaService;
	
    @GetMapping("/home")
    public String home() {
        return "index";  // O Spring Boot buscará 'index.html' automaticamente
    }
    
    @GetMapping("/pagina-aluno")
    public String paginaAluno(Model model) {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        model.addAttribute("pessoas", pessoas);  // Passa a lista de alunos para a view
        return "pagina-alunos";
    }
    
    @GetMapping("/pagina-instrutores")
    public String paginaInstrutor(Model model) {
        List<Instrutor> instrutores = instrutorService.listarInstrutores();
        model.addAttribute("instrutores", instrutores); 
        return "pagina-instrutores";
    }
    
    
}
