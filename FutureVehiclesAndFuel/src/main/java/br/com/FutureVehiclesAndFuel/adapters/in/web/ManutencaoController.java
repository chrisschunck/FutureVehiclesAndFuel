package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.ManutencaoRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.ManutencaoResponseDTO;
import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarManutencaoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarManutencaoCommand;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manutencoes")
public class ManutencaoController {

    private final RegistrarManutencaoUseCase registrarManutencaoUseCase;

    public ManutencaoController(RegistrarManutencaoUseCase registrarManutencaoUseCase) {
        this.registrarManutencaoUseCase = registrarManutencaoUseCase;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description="Evento com sucesso", name="Response sucesso, "ok"")
        @ApiResponse(responseCode="201", description="Evento válidado", name="Response validado")
        @ApiResponse(responseCode="400", description="Response com data invalidada", name="Requisição ruim")
        @ApiResponse(responseCode="401", description="Evento inválido ou token inacessivel", name="Response não autorizado")
        @ApiResponse(responseCode="404", description="Evento não encontrado", name="Response não encontrato")
        @ApiResponse(responseCode="500", description="Evento sem servidor no ar", name="Response com erro no servidor")
    })
    @PostMapping
    public ResponseEntity<ManutencaoResponseDTO> registrar(@Valid @RequestBody ManutencaoRequestDTO dto) {
        RegistrarManutencaoCommand command = new RegistrarManutencaoCommand(
                dto.veiculoId(), dto.tipo(), dto.quilometragemRealizada(), dto.dataRealizada(),
                dto.intervaloProximaKm(), dto.custo());
        var manutencao = registrarManutencaoUseCase.registrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(ManutencaoResponseDTO.fromDomain(manutencao));
    }
}
