package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para autenticação de alocação")
public record AlocacaoRequestDTO(
        @NotNull Long veiculoId,
        @NotNull Long motoristaId,
        Integer kmInicial
) {
}
