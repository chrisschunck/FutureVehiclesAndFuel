package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoBEV;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoFCEV;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoHibrido;

@Schema(description = "Dados de retorno de autenticação do veículo")
public record VeiculoResponseDTO(
        Long id,
        String placa,
        String modelo,
        String marca,
        Integer quilometragemAtual,
        StatusVeiculo status,
        TipoPropulsao tipoPropulsao,
        Double capacidadeBateriaKwh,
        Integer autonomiaEstimadaKm,
        Double nivelCargaAtualPercentual,
        Double capacidadeTanqueH2Kg,
        Double pressaoTanqueBar,
        Double capacidadeTanqueLitros
) {
    public static VeiculoResponseDTO fromDomain(Veiculo veiculo) {
        Double bateriaKwh = null;
        Integer autonomia = null;
        Double nivelCarga = null;
        Double tanqueH2 = null;
        Double pressao = null;
        Double tanqueLitros = null;

        if (veiculo instanceof VeiculoBEV bev) {
            bateriaKwh = bev.getCapacidadeBateriaKwh();
            autonomia = bev.getAutonomiaEstimadaKm();
            nivelCarga = bev.getNivelCargaAtualPercentual();
        } else if (veiculo instanceof VeiculoFCEV fcev) {
            tanqueH2 = fcev.getCapacidadeTanqueH2Kg();
            pressao = fcev.getPressaoTanqueBar();
        } else if (veiculo instanceof VeiculoHibrido hibrido) {
            tanqueLitros = hibrido.getCapacidadeTanqueLitros();
            bateriaKwh = hibrido.getCapacidadeBateriaKwh();
        }

        return new VeiculoResponseDTO(
                veiculo.getId(), veiculo.getPlaca(), veiculo.getModelo(), veiculo.getMarca(),
                veiculo.getQuilometragemAtual(), veiculo.getStatus(), veiculo.getTipoPropulsao(),
                bateriaKwh, autonomia, nivelCarga, tanqueH2, pressao, tanqueLitros);
    }
}
