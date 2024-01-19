package Library.Library.Service;

import Library.Library.DTO.DadosCadastroLivro;
import Library.Library.DTO.DadosDetalhamentoLivro;
import Library.Library.DTO.Status;
import Library.Library.Entity.Livro;
import Library.Library.Entity.LogAlteracoes;
import Library.Library.Repository.LivroRepository;
import Library.Library.Repository.LogTransacaoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Service
public class LivroService   {


    @Autowired
    LivroRepository repository;

    @Autowired
    LogTransacaoRepository logAlteracoesRepository;



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

    public Page<DadosDetalhamentoLivro> listarLivrosPorStatus(Status status, Pageable paginacao) {
        if (status != null ) {
            return repository.findAllByStatus(status, paginacao);
        } else {
            return repository.findAll(paginacao).map(DadosDetalhamentoLivro::new);
        }
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
            Status statusAnterior = livro.getStatus();
            livro.setStatus(novoStatus);
            Livro livroAtualizado = repository.save(livro);
            inserirLogTransacaoLivro(id, statusAnterior, novoStatus);
            return livroAtualizado;
        } else {
            throw new IllegalStateException("O livro já está no status desejado.");
        }

    }

    private void inserirLogTransacaoLivro(Long livroId, Status statusAnterior, Status statusAtual) {
        LogAlteracoes logTransacao = new LogAlteracoes();
        logTransacao.setLivroId(livroId);
        logTransacao.setStatusAnterior(statusAnterior);
        logTransacao.setStatusAtual(statusAtual);
        logTransacao.setDataTransacao(LocalDateTime.now());
        logAlteracoesRepository.save(logTransacao);
    }

    public Page<DadosDetalhamentoLivro> buscarLivrosPorPalavraChave(String titulo, Pageable paginacao) {
        Specification<Livro> specification = (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(titulo)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
            }
            return null;
        };

        return repository.findAll(specification, paginacao).map(DadosDetalhamentoLivro::new);
    }




}
