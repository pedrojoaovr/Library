package Library.Library.Repository;

import Library.Library.Entity.Livro;
import Library.Library.Entity.LogAlteracoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogTransacaoRepository extends JpaRepository<LogAlteracoes, Long> {
}
