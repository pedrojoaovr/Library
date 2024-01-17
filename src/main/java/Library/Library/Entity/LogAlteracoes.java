package Library.Library.Entity;

import Library.Library.DTO.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "log_transacoes_livros")
@Entity(name = "LogAlteracoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class LogAlteracoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "livro_id")
    private Long livroId;

    @Column(name = "data_transacao")
    private LocalDateTime dataTransacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_anterior")
    private Status statusAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_atual")
    private Status statusAtual;

    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Livro livro;


}
