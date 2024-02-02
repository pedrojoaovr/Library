package Library.Library.Controller;

import Library.Library.DTO.DadosAtualizacaoLivro;
import Library.Library.DTO.DadosCadastroLivro;
import Library.Library.DTO.DadosDetalhamentoLivro;
import Library.Library.DTO.Status;
import Library.Library.Entity.Livro;
import Library.Library.Repository.LivroRepository;
import Library.Library.Service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroLivro dados, UriComponentsBuilder uriBuilder) {
        var livro = new Livro(dados);
        livroService.save(livro);
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoLivro(livro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoLivro>> listarTodosLivros(@PageableDefault(size = 10) Pageable paginacao) {
        Page<DadosDetalhamentoLivro> livros = livroService.listarTodosLivros(paginacao);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/porStatus")
    public ResponseEntity<Page<DadosDetalhamentoLivro>> listarLivrosPorStatus(@RequestParam Status status, @PageableDefault(size = 10) Pageable paginacao) {
        Page<DadosDetalhamentoLivro> livros = livroService.listarLivrosPorStatus(status, paginacao);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/porTitulo")
    public ResponseEntity<Page<DadosDetalhamentoLivro>> buscarPorNome(@RequestParam String palavraChave, @PageableDefault(size = 10) Pageable paginacao) {
        Page<DadosDetalhamentoLivro> livros = livroService.buscarLivrosPorPalavraChave(palavraChave, paginacao);
        return ResponseEntity.ok(livros);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        livroService.excluirLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/alterar-status")
    @Transactional
    public ResponseEntity<Livro> alterarStatusLivro(
            @PathVariable Long id,
            @RequestBody DadosAtualizacaoLivro dadosAtualizacaoLivro) {
        Livro livroAtualizado = livroService.alterarStatusLivro(id, dadosAtualizacaoLivro);
        return ResponseEntity.ok(livroAtualizado);
    }









}
