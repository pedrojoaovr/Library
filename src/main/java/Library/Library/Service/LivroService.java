package Library.Library.Service;

import Library.Library.DTO.DadosCadastroLivro;
import Library.Library.DTO.DadosDetalhamentoLivro;
import Library.Library.DTO.Status;
import Library.Library.Entity.Livro;
import Library.Library.Repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService   {


    @Autowired
    LivroRepository repository;

    @Transactional
    public Livro save(Livro livro) {
        if (livro.getStatus() == null) {
            throw new IllegalArgumentException("Por favor, selecione um status para o livro.");
        }
        return repository.save(livro);
    }

    public Page<DadosDetalhamentoLivro> listarTodosLivros(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhamentoLivro::new);
    }

    public Page<DadosDetalhamentoLivro> listarLivrosDisponiveis(Pageable paginacao) {
        return repository.findAllByStatusAndDisponivelTrue(Status.DISPONIVEL, paginacao);
    }


}
