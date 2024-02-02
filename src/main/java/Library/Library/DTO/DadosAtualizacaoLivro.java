package Library.Library.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLivro(
        @NotNull
        Status status) {}
