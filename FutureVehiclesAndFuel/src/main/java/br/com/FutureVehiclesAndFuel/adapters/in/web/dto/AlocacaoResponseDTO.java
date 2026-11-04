package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;

import java.time.LocalDateTime;

@Schema(description = "Retorno dos dados de autenticação com sucesso")
public record AlocacaoResponseDTO(
        Long id, Long veiculoId, Long motoristaId, LocalDateTime dataInicio,
        LocalDateTime dataFim, Integer kmInicial, Integer kmFinal, boolean ativa
) {
    public static AlocacaoResponseDTO fromDomain(Alocacao alocacao) {
        return new AlocacaoResponseDTO(alocacao.getId(), alocacao.getVeiculoId(), alocacao.getMotoristaId(),
                alocacao.getDataInicio(), alocacao.getDataFim(), alocacao.getKmInicial(),
                alocacao.getKmFinal(), alocacao.estaAtiva());
    }
}
