package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AbastecimentoRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.AbastecimentoResponseDTO;
import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarAbastecimentoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarAbastecimentoCommand;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<AbastecimentoResponseDTO> registrar(@Valid @RequestBody AbastecimentoRequestDTO dto) {
        RegistrarAbastecimentoCommand command = new RegistrarAbastecimentoCommand(
                dto.veiculoId(), dto.quantidade(), dto.custo(), dto.localEletroposto());
        var abastecimento = registrarAbastecimentoUseCase.registrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(AbastecimentoResponseDTO.fromDomain(abastecimento));
    }
}
