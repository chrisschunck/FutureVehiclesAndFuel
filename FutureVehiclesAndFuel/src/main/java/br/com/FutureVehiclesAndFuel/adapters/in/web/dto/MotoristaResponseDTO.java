package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.model.Motorista;

import java.time.LocalDate;

@Schema(description = "Dados de retorno da autenteicação do motorista")
public record MotoristaResponseDTO(
        Long id, String nome, String cnh, LocalDate validadeCnh, Long empresaId, boolean cnhValida
) {
    public static MotoristaResponseDTO fromDomain(Motorista motorista) {
        return new MotoristaResponseDTO(motorista.getId(), motorista.getNome(), motorista.getCnh(),
                motorista.getValidadeCnh(), motorista.getEmpresaId(), motorista.cnhValida());
    }
}
