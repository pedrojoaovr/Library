package Library.Library.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroLivro(
        @NotBlank
        String titulo,
        @NotBlank
        String autor,

        @NotNull
        int ano_publicacao,
        @NotBlank
        String genero,
        @NotBlank
        @Pattern(regexp = "\\d{13}")
        String isbn,
        @NotNull
        Status status
) {


}
