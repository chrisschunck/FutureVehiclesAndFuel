package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.MotoristaRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.MotoristaResponseDTO;
import br.com.FutureVehiclesAndFuel.application.port.in.CadastrarMotoristaUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarMotoristaCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        @ApiResponse(responseCode="200", description="Evento com sucesso", name="Response sucesso, "ok"")
        @ApiResponse(responseCode="201", description="Evento válidado", name="Response validado")
        @ApiResponse(responseCode="400", description="Response com data invalidada", name="Requisição ruim")
        @ApiResponse(responseCode="401", description="Evento inválido ou token inacessivel", name="Response não autorizado")
        @ApiResponse(responseCode="404", description="Evento não encontrado", name="Response não encontrato")
        @ApiResponse(responseCode="500", description="Evento sem servidor no ar", name="Response com erro no servidor")
    })
    @PostMapping
    public ResponseEntity<MotoristaResponseDTO> cadastrar(@Valid @RequestBody MotoristaRequestDTO dto) {
        CadastrarMotoristaCommand command = new CadastrarMotoristaCommand(
                dto.nome(), dto.cnh(), dto.validadeCnh(), dto.empresaId());
        var motorista = cadastrarMotoristaUseCase.cadastrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(MotoristaResponseDTO.fromDomain(motorista));
    }
}
