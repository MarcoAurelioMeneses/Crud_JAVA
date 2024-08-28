package br.com.marco.springApi.controller;


import br.com.marco.springApi.domain.medico.*;
import br.com.marco.springApi.domain.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


// No pradão mvc o C significa Controllers que basicamente é quem direciona o que cada URL e protocolo HTTP vai fazer
// RestController = Anotação que define a classe como Controller
// ResquestMapping = Anotação que define a URL base dos protrocolos HTTP
// Autowired = Anotação que indica onde a injeção automática de dependencia deve ser aplicada.
// PostMapping = Indica qual o protocolo HTTP será usado na função, neste caso é o POST
// GETMapping = Indica qual o protocolo HTTP será usado na função, neste caso é o GET
// PUTMapping = Indica qual o protocolo HTTP será usado na função, neste caso é o PUT
// DELETEMapping = Indica qual o protocolo HTTP será usado na função, neste caso é o DELETE
// Cadastrar = Aciona o protocolo POST que espera receber os dados que vem da Record DadosCadastroMedicos, depois aciona o repositório e cria um
// novo objeto Medico com esses atributos.
// ListarMedicos = Aciona o protocolo GET que pega as informações dos objetos Medico exitentes e retorna em formato de lista, com no maximo 10 objetos por página,
// busca esses objetos pelo ID e filtra se a entidade está com o status de ATIVO.
// AtualizarDados = Aciona o protocolo PUT que busca o objeto pelo ID e utilizar a função dentro do repositório para realizar a atualização.
// Excluir = Aciona o protocolo DELETE que também faz a busca pelo ID e exclui o Objeto.

@RestController
@RequestMapping("/medicos")
public class MedicosController {


    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDados(@RequestBody @Valid DadosAtualizacaoMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return  ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
