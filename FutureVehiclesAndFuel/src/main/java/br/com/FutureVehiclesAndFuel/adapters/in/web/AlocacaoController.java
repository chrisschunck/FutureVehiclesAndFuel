package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AlocacaoRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AlocacaoResponseDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.FinalizarAlocacaoRequestDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import br.com.FutureVehiclesAndFuel.application.port.in.AlocarVeiculoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.FinalizarAlocacaoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.AlocarVeiculoCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alocacoes")
public class AlocacaoController {

    private final AlocarVeiculoUseCase alocarVeiculoUseCase;
    private final FinalizarAlocacaoUseCase finalizarAlocacaoUseCase;

    public AlocacaoController(AlocarVeiculoUseCase alocarVeiculoUseCase,
                               FinalizarAlocacaoUseCase finalizarAlocacaoUseCase) {
        this.alocarVeiculoUseCase = alocarVeiculoUseCase;
        this.finalizarAlocacaoUseCase = finalizarAlocacaoUseCase;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description="OK")
        @ApiResponse(responseCode="201", description="CREATED")
        @ApiResponse(responseCode="204", description="NO CONTENT")
        @ApiResponse(responseCode="400", description="BAD REQUEST")
        @ApiResponse(responseCode="401", description="Unauthorized")
        @ApiResponse(responseCode="403", description="Forbidden")
        @ApiResponse(responseCode="404", description="Not Found")
        @ApiResponse(responseCode="500", description="Internal Error")
    })
    @PostMapping
    public ResponseEntity<AlocacaoResponseDTO> alocar(@Valid @RequestBody AlocacaoRequestDTO dto) {
        AlocarVeiculoCommand command = new AlocarVeiculoCommand(dto.veiculoId(), dto.motoristaId(), dto.kmInicial());
        var alocacao = alocarVeiculoUseCase.alocar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(AlocacaoResponseDTO.fromDomain(alocacao));
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description="OK")
        @ApiResponse(responseCode="201", description="CREATED")
        @ApiResponse(responseCode="204", description="NO CONTENT")
        @ApiResponse(responseCode="400", description="BAD REQUEST")
        @ApiResponse(responseCode="401", description="Unauthorized")
        @ApiResponse(responseCode="403", description="Forbidden")
        @ApiResponse(responseCode="404", description="Not Found")
        @ApiResponse(responseCode="500", description="Internal Error")
    })
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<AlocacaoResponseDTO> finalizar(@PathVariable Long id,
                                                          @RequestBody FinalizarAlocacaoRequestDTO dto) {
        var alocacao = finalizarAlocacaoUseCase.finalizar(id, dto.kmFinal());
        return ResponseEntity.ok(AlocacaoResponseDTO.fromDomain(alocacao), HttpStatus.OK);
    }
}
