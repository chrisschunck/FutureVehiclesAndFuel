package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.MotoristaRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.MotoristaResponseDTO;
import br.com.FutureVehiclesAndFuel.application.port.in.CadastrarMotoristaUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarMotoristaCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/motoristas")
public class MotoristaController {

    private final CadastrarMotoristaUseCase cadastrarMotoristaUseCase;

    public MotoristaController(CadastrarMotoristaUseCase cadastrarMotoristaUseCase) {
        this.cadastrarMotoristaUseCase = cadastrarMotoristaUseCase;
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
    public ResponseEntity<MotoristaResponseDTO> cadastrar(@Valid @RequestBody MotoristaRequestDTO dto) {
        CadastrarMotoristaCommand command = new CadastrarMotoristaCommand(
                dto.nome(), dto.cnh(), dto.validadeCnh(), dto.empresaId());
        var motorista = cadastrarMotoristaUseCase.cadastrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(MotoristaResponseDTO.fromDomain(motorista));
    }
}
