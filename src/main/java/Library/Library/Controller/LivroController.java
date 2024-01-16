package Library.Library.Controller;

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
    public ResponseEntity cadastrar(@RequestBody DadosCadastroLivro dados, UriComponentsBuilder uriBuilder) {
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
    @GetMapping("/disponiveis")
    public ResponseEntity<Page<DadosDetalhamentoLivro>> listarLivrosDisponiveis(@PageableDefault(size = 10) Pageable paginacao) {
        Page<DadosDetalhamentoLivro> livros = livroService.listarLivrosDisponiveis(paginacao);
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
    public ResponseEntity<DadosDetalhamentoLivro> alterarStatusLivro(@PathVariable Long id, @RequestParam Status novoStatus) {
        Livro livro = livroService.alterarStatusLivro(id, novoStatus);
        if (livro == null) {
            return ResponseEntity.notFound().build();
        }
        DadosDetalhamentoLivro livroAtualizado = new DadosDetalhamentoLivro(livro);
        return ResponseEntity.ok(livroAtualizado);
    }


}
