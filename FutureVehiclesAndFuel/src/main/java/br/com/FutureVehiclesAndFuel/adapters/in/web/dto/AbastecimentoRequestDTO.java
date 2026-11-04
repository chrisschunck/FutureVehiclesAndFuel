package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Dados para autenticação de abastacimento do veiculo")
public record AbastecimentoRequestDTO(
        @NotNull Long veiculoId,
        @NotNull Double quantidade,
        BigDecimal custo,
        String localEletroposto
) {
}
