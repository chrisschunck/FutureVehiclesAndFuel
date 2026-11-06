package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AlocacaoRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AlocacaoResponseDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.FinalizarAlocacaoRequestDTO;
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
        @ApiResponse(responseCode="200", description="Evento com sucesso", name="Response sucesso, "ok"")
        @ApiResponse(responseCode="201", description="Evento válidado", name="Response validado")
        @ApiResponse(responseCode="400", description="Response com data invalidada", name="Requisição ruim")
        @ApiResponse(responseCode="401", description="Evento inválido ou token inacessivel", name="Response não autorizado")
        @ApiResponse(responseCode="404", description="Evento não encontrado", name="Response não encontrato")
        @ApiResponse(responseCode="500", description="Evento sem servidor no ar", name="Response com erro no servidor")
    })
    @PostMapping
    public ResponseEntity<AlocacaoResponseDTO> alocar(@Valid @RequestBody AlocacaoRequestDTO dto) {
        AlocarVeiculoCommand command = new AlocarVeiculoCommand(dto.veiculoId(), dto.motoristaId(), dto.kmInicial());
        var alocacao = alocarVeiculoUseCase.alocar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(AlocacaoResponseDTO.fromDomain(alocacao));
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description="Evento com sucesso", name="Response sucesso, "ok"")
        @ApiResponse(responseCode="201", description="Evento válidado", name="Response validado")
        @ApiResponse(responseCode="400", description="Response com data invalidada", name="Requisição ruim")
        @ApiResponse(responseCode="401", description="Evento inválido ou token inacessivel", name="Response não autorizado")
        @ApiResponse(responseCode="404", description="Evento não encontrado", name="Response não encontrato")
        @ApiResponse(responseCode="500", description="Evento sem servidor no ar", name="Response com erro no servidor")
    })
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<AlocacaoResponseDTO> finalizar(@PathVariable Long id,
                                                          @RequestBody FinalizarAlocacaoRequestDTO dto) {
        var alocacao = finalizarAlocacaoUseCase.finalizar(id, dto.kmFinal());
        return ResponseEntity.ok(AlocacaoResponseDTO.fromDomain(alocacao));
    }
}
