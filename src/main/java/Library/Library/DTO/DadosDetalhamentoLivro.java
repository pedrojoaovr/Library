package Library.Library.DTO;

import Library.Library.Entity.Livro;


public record DadosDetalhamentoLivro(Long id, String titulo, String autor, int ano_publicacao, String genero, String isbn, Status status) {
    public DadosDetalhamentoLivro(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno_publicacao(), livro.getGenero() ,livro.getIsbn(), livro.getStatus());
    }
}
