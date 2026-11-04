package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoManutencao;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Dados de autenteicação de manutenção")
public record ManutencaoRequestDTO(
        @NotNull Long veiculoId,
        @NotNull TipoManutencao tipo,
        @NotNull Integer quilometragemRealizada,
        @NotNull LocalDate dataRealizada,
        Integer intervaloProximaKm,
        BigDecimal custo
) {
}
