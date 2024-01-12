package Library.Library.Controller;

import Library.Library.DTO.DadosCadastroLivro;
import Library.Library.DTO.DadosDetalhamentoLivro;
import Library.Library.Entity.Livro;
import Library.Library.Repository.LivroRepository;
import Library.Library.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
