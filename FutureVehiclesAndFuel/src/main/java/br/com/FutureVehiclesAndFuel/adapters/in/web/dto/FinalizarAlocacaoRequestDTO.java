package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

@Schema(description = "Dados de autenticação de fim de alocação")
public record FinalizarAlocacaoRequestDTO(
        Integer kmFinal
) {
}
