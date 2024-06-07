package br.com.marco.springApi.medico;

import br.com.marco.springApi.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicos(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
