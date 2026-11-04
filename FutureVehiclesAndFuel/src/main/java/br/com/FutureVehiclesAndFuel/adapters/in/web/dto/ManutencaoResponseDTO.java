package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoManutencao;
import br.com.FutureVehiclesAndFuel.domain.model.Manutencao;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Retorno dos dados autenticados")
public record ManutencaoResponseDTO(
        Long id, Long veiculoId, TipoManutencao tipo, Integer quilometragemRealizada,
        LocalDate dataRealizada, Integer intervaloProximaKm, BigDecimal custo
) {
    public static ManutencaoResponseDTO fromDomain(Manutencao manutencao) {
        return new ManutencaoResponseDTO(manutencao.getId(), manutencao.getVeiculoId(), manutencao.getTipo(),
                manutencao.getQuilometragemRealizada(), manutencao.getDataRealizada(),
                manutencao.getIntervaloProximaKm(), manutencao.getCusto());
    }
}
