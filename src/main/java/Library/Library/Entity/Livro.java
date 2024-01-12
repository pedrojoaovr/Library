package Library.Library.Entity;

import Library.Library.DTO.Status;
import Library.Library.DTO.DadosCadastroLivro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "livros")
@Entity(name = "Livro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private int ano_publicacao;
    private String genero;
    private String isbn;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Livro(DadosCadastroLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor();
        this.ano_publicacao = dados.ano_pubicacao();
        this.genero=dados.genero();
        this.isbn= dados.isbn();
        this.status= dados.status();
    }


}
