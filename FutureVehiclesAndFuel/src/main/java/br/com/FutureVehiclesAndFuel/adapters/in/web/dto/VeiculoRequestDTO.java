package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados de validação do veiculo")
public record VeiculoRequestDTO(
        @NotBlank String placa,
        @NotBlank String modelo,
        String marca,
        Integer quilometragemAtual,
        @NotNull Long empresaId,
        @NotNull TipoPropulsao tipoPropulsao,
        Double capacidadeBateriaKwh,
        Integer autonomiaEstimadaKm,
        Double nivelCargaAtualPercentual,
        Double capacidadeTanqueH2Kg,
        Double pressaoTanqueBar,
        Double capacidadeTanqueLitros
) {
}
