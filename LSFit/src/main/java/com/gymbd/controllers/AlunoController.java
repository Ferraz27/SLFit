package com.gymbd.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymbd.model.Aluno;
import com.gymbd.model.Endereco;
import com.gymbd.model.Exercicio;
import com.gymbd.model.FichaDeExercicio;
import com.gymbd.model.Gerente;
import com.gymbd.model.Instrutor;
import com.gymbd.model.Maquina;
import com.gymbd.model.Pessoa;
import com.gymbd.model.Plano;
import com.gymbd.model.Unidade;
import com.gymbd.service.AlunoService;
import com.gymbd.service.EnderecoService;
import com.gymbd.service.ExercicioService;
import com.gymbd.service.FichaDeExercicioService;
import com.gymbd.service.GerenteService;
import com.gymbd.service.InstrutorService;
import com.gymbd.service.MaquinaService;
import com.gymbd.service.PessoaService;
import com.gymbd.service.PlanoService;
import com.gymbd.service.UnidadeService;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.InstrutorRepository;
import com.gymbd.repository.ExercicioRepository;

@Controller
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private InstrutorService instrutorService;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private UnidadeService unidadeService;
    @Autowired
    private MaquinaService maquinaService;
    @Autowired
    private GerenteService gerenteService;
    @Autowired
    private ExercicioService exercicioService;
    @Autowired
    private FichaDeExercicioService fichaDeExercicioService;
    @Autowired
    private PlanoService planoService;

    @GetMapping("/home")
    public String home() {
        return "index";  // O Spring Boot buscará 'index.html' automaticamente
    }
    
    @GetMapping("/pessoas")
    public String paginaPessoas(Model model) {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        model.addAttribute("pessoas", pessoas);  // Passa a lista de alunos para a view
        return "Read/ReadPessoa";
    }
    
    @PostMapping("pessoas/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        pessoaService.salvarPessoa(pessoa);
        return "redirect:/pessoas/form";
    }
    
    @GetMapping("pessoas/form")
    public String mostrarFormularioPessoa() {
        return "Create/CreatePessoa";
    }
    
    @GetMapping("/instrutores")
    public String paginaInstrutor(Model model) {
        List<Instrutor> instrutores = instrutorService.listarInstrutores();
        model.addAttribute("instrutores", instrutores); 
        return "Read/ReadInstrutor";
    }
    
    @GetMapping("/gerentes")
    public String paginaGerente(Model model) {
        List<Gerente> gerentes = gerenteService.listarGerentes();
        model.addAttribute("gerentes", gerentes); 
        return "Read/ReadGerente";
    }
    
    @PostMapping("instrutor/salvar")
    public String salvarInstrutor(@ModelAttribute Instrutor instrutor, @ModelAttribute Endereco endereco) {
        instrutorService.salvarInstrutor(instrutor);
        endereco.setFpkIdPessoa(instrutor.getPkIdPessoa());
        enderecoService.salvarEndereco(endereco);
        return "redirect:/instrutores";
    }
    
    @PostMapping("instrutor/atualizar/{id}")
    public String atualizarInstrutor(@PathVariable Integer id, @ModelAttribute Instrutor instrutor, @ModelAttribute Endereco endereco) {
        instrutor.setPkIdPessoa(id);
        instrutorService.atualizarInstrutor(instrutor);
        endereco.setFpkIdPessoa(instrutor.getPkIdPessoa());
        enderecoService.atualizarEndereco(endereco);        
        return "redirect:/instrutores";
    }
    
    @GetMapping("instrutor/form/{id}")
    public String mostrarFormularioAtualizacaoInstrutor(@PathVariable Integer id, Model model) {
        Instrutor instrutor = instrutorService.buscarInstrutorPorId(id);
        model.addAttribute("instrutor", instrutor);
        return "Update/UpdateInstrutor";
    }

    @GetMapping("instrutor/form")
    public String mostrarFormularioInstrutor() {
        return "Create/CreateInstrutor";
    }
    
    @GetMapping("/instrutor/deletar/{id}")
    public String deletarInstrutor(@PathVariable Integer id) {
        instrutorService.deletarInstrutor(id);
        return "redirect:/instrutores"; // Redireciona para a lista de instrutores
    }
    @GetMapping("/instrutores/salarioAcimaDaMedia")
    public String instrutoresComSalarioAcimaDaMedia(Model model) {
        List<Instrutor> instrutores = instrutorService.findInstrutoresWithSalaryAboveAverage();
        model.addAttribute("instrutores", instrutores);
        return "Read/ReadInstrutoresSalarioAcimaDaMedia";
    }
    
    @GetMapping("/alunos")
    public String paginaAluno(Model model) {
        List<Aluno> alunos = alunoService.listarAlunos();
        model.addAttribute("alunos", alunos); 
        return "Read/ReadAluno";
        }
        
        @PostMapping("aluno/salvar")
        public String salvarAluno(@ModelAttribute Aluno aluno, @ModelAttribute Endereco endereco, @ModelAttribute Plano plano, Integer unidadeId, Integer indicacaoId) {
        Unidade unidade = unidadeService.buscarUnidadePorId(unidadeId);
        if (unidade != null) {
            plano.setUnidade(unidade);
        }

        Aluno indicacao = alunoService.buscarAlunoPorId(indicacaoId);
        aluno.setIndicacao(indicacao);

        java.time.LocalDate dataAtual = java.time.LocalDate.now();
        
        alunoService.salvarAluno(aluno);
        endereco.setFpkIdPessoa(aluno.getPkIdPessoa());
        plano.setFpkIdPlano(aluno.getPkIdPessoa());
        plano.setDataDeMatricula(dataAtual);
        enderecoService.salvarEndereco(endereco);      
        planoService.salvarPlano(plano);
        
        return "redirect:/alunos";
        }

        @PostMapping("aluno/atualizar/{id}")
        public String atualizarAluno(@PathVariable Integer id, @ModelAttribute Aluno aluno, @ModelAttribute Endereco endereco, @ModelAttribute Plano plano, Integer unidadeId, Integer indicacaoId) {
        Unidade unidade = unidadeService.buscarUnidadePorId(unidadeId);
        if (unidade != null) {
            plano.setUnidade(unidade);
        }
        
        java.time.LocalDate dataAtual = java.time.LocalDate.now();

        Aluno indicacao = alunoService.buscarAlunoPorId(indicacaoId);
        aluno.setIndicacao(indicacao);
        
        aluno.setPkIdPessoa(id);
        alunoService.atualizarAluno(aluno);
        endereco.setFpkIdPessoa(aluno.getPkIdPessoa());
        plano.setFpkIdPlano(aluno.getPkIdPessoa());
        plano.setDataDeMatricula(dataAtual);
        enderecoService.atualizarEndereco(endereco);      
        planoService.atualizarPlano(plano);
        return "redirect:/alunos";
        }

        @GetMapping("aluno/form/{id}")
        public String mostrarFormularioAtualizacaoAluno(@PathVariable Integer id, Model model) {
        Aluno aluno = alunoService.buscarAlunoPorId(id);
        model.addAttribute("aluno", aluno);
        return "Update/UpdateAluno";
    }

    @GetMapping("aluno/form")
    public String mostrarFormularioAluno() {
        return "Create/CreateAluno";
    }
    
    @GetMapping("/aluno/deletar/{id}")
    public String deletarAluno(@PathVariable Integer id) {
        alunoService.deletarAluno(id);
        return "redirect:/alunos"; // Redireciona para a lista de alunos
    }
    
    @GetMapping("unidades")
    public String paginaUnidade(Model model) {
        List<Unidade> unidades = unidadeService.listarUnidades();
        model.addAttribute("unidades", unidades); 
        return "Read/ReadUnidade";
    }
    
    @PostMapping("unidade/salvar")
    public String salvarUnidade(@ModelAttribute Unidade unidade) {
        unidadeService.salvarUnidade(unidade);
        return "redirect:/unidades";
    }

    @PostMapping("unidade/atualizar/{id}")
    public String atualizarUnidade(@PathVariable Integer id, @ModelAttribute Unidade unidade) {
        unidade.setPkIdUnidade(id);
        unidadeService.atualizarUnidade(unidade);
        return "redirect:/unidades";
    }

    @GetMapping("unidade/form/{id}")
    public String mostrarFormularioAtualizacaoUnidade(@PathVariable Integer id, Model model) {
        Unidade unidade = unidadeService.buscarUnidadePorId(id);
        model.addAttribute("unidade", unidade);
        return "Update/UpdateUnidade";
    }

    @GetMapping("unidade/form")
    public String mostrarFormularioUnidade() {
        return "Create/CreateUnidade";
    }
    
    @GetMapping("/unidade/deletar/{id}")
    public String deletarUnidadeEApagarGerente(@PathVariable Integer id) {
        Unidade unidade = unidadeService.buscarUnidadePorId(id);
        if (unidade != null) {
            // Apaga o gerente associado
            Gerente gerente = unidade.getGerente();
            if (gerente != null) {
                gerenteService.deletarGerente(gerente.getPkIdPessoa());
            }
            unidadeService.deletarUnidade(id);
        }
        return "redirect:/unidades";
    }

    @GetMapping("/unidade/maisAlunos")
    public String unidadeComMaisAlunos(Model model) {
        Object object = unidadeService.findUnidadeWithMostAlunos();
        model.addAttribute("unidade", object);
        return "Read/ReadUnidadeMaisAlunos";
    }
    
    @PostMapping("maquina/salvar")
    public String salvarMaquina(@ModelAttribute Maquina maquina) {
        maquinaService.salvarMaquina(maquina);
        return "redirect:/home";
    }

    @PostMapping("maquina/atualizar/{id}")
    public String atualizarMaquina(@PathVariable Integer id, @ModelAttribute Maquina maquina) {
        maquina.setPkIdMaquina(id);
        maquinaService.atualizarMaquina(maquina);
        return "redirect:/maquinas";
    }

    @GetMapping("maquina/form/{id}")
    public String mostrarFormularioAtualizacaoMaquina(@PathVariable Integer id, Model model) {
        Maquina maquina = maquinaService.buscarMaquinaPorId(id);
        model.addAttribute("maquina", maquina);
        return "Update/UpdateMaquina";
    }

    @GetMapping("maquina/form")
    public String mostrarFormularioMaquina() {
        return "Create/CreateMaquina";
    }
    
    @GetMapping("/maquinas")
    public String listarMaquinasEExercicios(Model model) {
        List<Maquina> maquinas = maquinaService.listarMaquinasEExercicios();
        model.addAttribute("maquinas", maquinas);  // Passa a lista de máquinas e exercícios para a view
        return "Read/ReadMaquinaExercicio";  // Nome da view (o HTML que você passou)
    }
    
    @GetMapping("/maquina/deletar/{id}")
    public String deletarMaquina(@PathVariable Integer id) {
        maquinaService.deletarMaquina(id);  // Chama o serviço para deletar a máquina
        return "redirect:/maquinas";  // Redireciona para a lista de máquinas
    }

    @PostMapping("gerente/salvar")
    public String salvarGerente(@ModelAttribute Gerente gerente, @ModelAttribute Endereco endereco, Integer unidadeId) {
        // Verifica se a unidade existe
        Unidade unidade = unidadeService.buscarUnidadePorId(unidadeId);
        if (unidade != null) {
            // Associa o gerente à unidade
            gerente.setUnidade(unidade);
        }
        
        // Salva o gerente
        gerenteService.salvarGerente(gerente);

        // Salva o endereço do gerente
        endereco.setFpkIdPessoa(gerente.getPkIdPessoa());
        enderecoService.salvarEndereco(endereco);
        
        return "redirect:/instrutores"; // Redireciona para a página de instrutores
    }

    @PostMapping("gerente/atualizar/{id}")
    public String atualizarGerente(@PathVariable Integer id, @ModelAttribute Gerente gerente, @ModelAttribute Endereco endereco) {
        gerente.setPkIdPessoa(id);
        gerenteService.atualizarGerente(gerente);
        endereco.setFpkIdPessoa(gerente.getPkIdPessoa());
        enderecoService.atualizarEndereco(endereco);        
        return "redirect:/gerentes";
    }

    @GetMapping("gerente/form/{id}")
    public String mostrarFormularioAtualizacaoGerente(@PathVariable Integer id, Model model) {
        Gerente gerente = gerenteService.buscarPorId(id);
        model.addAttribute("gerente", gerente);
        return "Update/UpdateGerente";
    }
    
    @GetMapping("gerente/form")
    public String mostrarFormularioGerente() {
        return "Create/CreateGerente";
    }
    
    @GetMapping("/gerente/deletar/{id}")
    public String deletarGerenteSemUnidade(@PathVariable Integer id) {
        gerenteService.deletarGerente(id);
        return "redirect:/gerentes";
    }
    
    @GetMapping("/exercicios")
    public String listarExercicios(Model model) {
        List<Exercicio> exercicios = exercicioService.listarExerciciosComDetalhes();
        model.addAttribute("exercicios", exercicios);  // Passa a lista de exercícios para a view
        return "Read/ReadExercicio";  // Nome da view (o HTML para exibir os exercícios)
    }
    
    @GetMapping("exercicio/form")
    public String mostrarFormularioExercicio(Model model) {
        // Recupera a lista de máquinas para exibir no formulário
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        model.addAttribute("maquinas", maquinas);  // Passa a lista de máquinas para a view
        model.addAttribute("exercicio", new Exercicio());  // Passa um objeto Exercicio vazio para o form
        return "Create/CreateExercicio";  // O caminho do template do formulário
    }
    
    @PostMapping("exercicio/salvar")
    public String salvarExercicio(@ModelAttribute Exercicio exercicio, @RequestParam Integer maquinaId) {
        // Recupera a máquina pelo ID
        Maquina maquina = maquinaService.buscarMaquinaPorId(maquinaId);
        if (maquina != null) {
            exercicio.setMaquina(maquina);  // Associa a máquina ao exercício
            exercicioService.salvarExercicio(exercicio);  // Salva o exercício
        }
        return "redirect:/exercicios";  // Redireciona para a página de exercícios
    }

    @PostMapping("exercicio/atualizar/{name}")
    public String atualizarExercicio(@PathVariable String name, @ModelAttribute Exercicio exercicio, @RequestParam Integer maquinaId) {
        exercicio.setNomeExercicio(name);
        Maquina maquina = maquinaService.buscarMaquinaPorId(maquinaId);
        if (maquina != null) {
            exercicio.setMaquina(maquina);
            exercicioService.atualizarExercicio(exercicio);
        }
        return "redirect:/exercicios";
    }

    @GetMapping("exercicio/form/{name}")
    public String mostrarFormularioAtualizacaoExercicio(@PathVariable String name, Model model) {
        Exercicio exercicio = exercicioService.buscarExercicioPorNome(name);
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        model.addAttribute("exercicio", exercicio);
        model.addAttribute("maquinas", maquinas);
        return "Update/UpdateExercicio";
    }
    
    @GetMapping("/exercicio/deletar/{nomeExercicio}")
    public String deletarExercicio(@PathVariable String nomeExercicio) {
        exercicioService.deletarExercicio(nomeExercicio);  // Chama o serviço para deletar o exercício
        return "redirect:/exercicios";  // Redireciona para a lista de exercícios
    }

    @GetMapping("/fichas")
    public String listarFichasDeExercicio(Model model) {
        List<FichaDeExercicio> fichas = fichaDeExercicioService.listarFichasDeExercicio();
        model.addAttribute("fichas", fichas);
        return "Read/ReadFichas";  // Página que lista as fichas (nome da view)
    }

    @GetMapping("/ficha/{id}")
    public String detalhesFichaDeExercicio(@PathVariable Integer id, Model model) {
        FichaDeExercicio fichaDeExercicio = fichaDeExercicioService.buscarFichaDeExercicioDetalhada(id);
        if(fichaDeExercicio == null) {
            return "redirect:/fichas";
        }
        model.addAttribute("fichaDeExercicio", fichaDeExercicio);
        return "Read/ReadDetalhesFichaDeExercicio";  // Página que mostra os detalhes da ficha (nome da view)
    }
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired 
    InstrutorRepository instrutorRepository;
    @Autowired
    ExercicioRepository exercicioRepository;
    @PostMapping("fichaExercicio/atualizar/{id}")
    public String atualizarFichaDeExercicio(@PathVariable Integer id,
                                        @ModelAttribute FichaDeExercicio fichaDeExercicio,
                                        @RequestParam Integer alunoId,
                                        @RequestParam Integer instrutorId,
                                        @RequestParam List<String> exercicios) {
    fichaDeExercicio.setPkIdFichaDeTreino(id);
    Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Instrutor instrutor = instrutorRepository.findById(instrutorId).orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        // Buscar os novos exercícios com base na lista de nomes
        List<Exercicio> listaExercicios = exercicioRepository.findByNomeExercicioIn(exercicios);

        // Atualizar os dados da ficha de exercício
        fichaDeExercicio.setAluno(aluno);
        fichaDeExercicio.setInstrutor(instrutor);
        fichaDeExercicio.setExercicios(listaExercicios);
    fichaDeExercicioService.atualizarFichaDeExercicio(fichaDeExercicio);
    return "redirect:/fichas";
}


    @GetMapping("fichaExercicio/form/{id}")
    public String mostrarFormularioAtualizacaoFichaDeExercicio(@PathVariable Integer id, Model model) {
        FichaDeExercicio fichaDeExercicio = fichaDeExercicioService.getFichaDeExercicioById(id);
        model.addAttribute("fichaDeExercicio", fichaDeExercicio);
        model.addAttribute("alunos", alunoService.listarAlunos());
        model.addAttribute("instrutores", instrutorService.listarInstrutores());
        model.addAttribute("exercicios", exercicioService.listarExerciciosComDetalhes());
        return "Update/UpdateFichaDeExercicio";
    }

    @GetMapping("/fichaExercicio/form")
    public String mostrarFormularioFichaDeExercicio(Model model) {
        model.addAttribute("alunos", alunoService.listarAlunos());
        model.addAttribute("instrutores", instrutorService.listarInstrutores());
        model.addAttribute("exercicios", exercicioService.listarExerciciosComDetalhes());
        model.addAttribute("fichaDeExercicio", new FichaDeExercicio());
        return "Create/CreateFichaDeExercicio";
    }

    @PostMapping("/fichaExercicio/salvar")
    public String salvarFichaDeExercicio(@ModelAttribute FichaDeExercicio fichaDeExercicio,
                                          @RequestParam Integer alunoId,
                                          @RequestParam Integer instrutorId,
                                          @RequestParam List<String> exercicios) {
    	Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Instrutor instrutor = instrutorRepository.findById(instrutorId).orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        // Buscar os novos exercícios com base na lista de nomes
        List<Exercicio> listaExercicios = exercicioRepository.findByNomeExercicioIn(exercicios);

        // Atualizar os dados da ficha de exercício
        fichaDeExercicio.setAluno(aluno);
        fichaDeExercicio.setInstrutor(instrutor);
        fichaDeExercicio.setExercicios(listaExercicios);
        fichaDeExercicioService.salvarFichaDeExercicio(fichaDeExercicio);
        return "redirect:/home";
    }
}