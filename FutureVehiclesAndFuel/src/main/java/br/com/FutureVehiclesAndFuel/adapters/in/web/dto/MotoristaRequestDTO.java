package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Dados de autenticação do motorista")
public record MotoristaRequestDTO(
        @NotBlank String nome,
        @NotBlank String cnh,
        @NotNull LocalDate validadeCnh,
        @NotNull Long empresaId
) {
}
