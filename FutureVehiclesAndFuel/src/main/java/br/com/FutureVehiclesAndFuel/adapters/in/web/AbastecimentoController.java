package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AbastecimentoRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AbastecimentoResponseDTO;
import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarAbastecimentoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarAbastecimentoCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

    private final RegistrarAbastecimentoUseCase registrarAbastecimentoUseCase;

    public AbastecimentoController(RegistrarAbastecimentoUseCase registrarAbastecimentoUseCase) {
        this.registrarAbastecimentoUseCase = registrarAbastecimentoUseCase;
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
    public ResponseEntity<AbastecimentoResponseDTO> registrar(@Valid @RequestBody AbastecimentoRequestDTO dto) {
        RegistrarAbastecimentoCommand command = new RegistrarAbastecimentoCommand(
                dto.veiculoId(), dto.quantidade(), dto.custo(), dto.localEletroposto());
        var abastecimento = registrarAbastecimentoUseCase.registrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(AbastecimentoResponseDTO.fromDomain(abastecimento));
    }
}
