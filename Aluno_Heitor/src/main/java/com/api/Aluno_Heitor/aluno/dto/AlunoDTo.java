package com.api.Aluno_Heitor.aluno.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoDTo {
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, message = "O nome deve conter mais que dois caracteres")
    private String nome;
    @NotBlank(message = "O curso é obrigatório")
    @Size(min = 2, message = "O curso deve conter mais que dois caracteres")
    private String curso;

    private String telefone;
}
