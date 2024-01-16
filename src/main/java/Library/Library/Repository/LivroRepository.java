package Library.Library.Repository;

import Library.Library.DTO.DadosDetalhamentoLivro;
import Library.Library.DTO.Status;
import Library.Library.Entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.status = :status")
    Page<DadosDetalhamentoLivro> findAllByStatusAndDisponivelTrue(@Param("status") Status status, Pageable paginacao);

}
