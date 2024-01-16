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

    @Transactional
    public void excluirLivro(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Livro alterarStatusLivro(Long id, Status novoStatus) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado: " + id));

        if (livro.getStatus() != novoStatus) {
            livro.setStatus(novoStatus);
            return repository.save(livro);
        } else {
            throw new IllegalStateException("O livro já está no status desejado.");
        }
    }



}
