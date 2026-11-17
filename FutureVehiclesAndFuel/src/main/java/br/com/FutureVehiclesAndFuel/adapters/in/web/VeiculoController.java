package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.VeiculoRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.VeiculoResponseDTO;
import br.com.FutureVehiclesAndFuel.application.port.in.CadastrarVeiculoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarVeiculosUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarVeiculoCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final CadastrarVeiculoUseCase cadastrarVeiculoUseCase;
    private final ConsultarVeiculosUseCase consultarVeiculosUseCase;

    public VeiculoController(CadastrarVeiculoUseCase cadastrarVeiculoUseCase,
                              ConsultarVeiculosUseCase consultarVeiculosUseCase) {
        this.cadastrarVeiculoUseCase = cadastrarVeiculoUseCase;
        this.consultarVeiculosUseCase = consultarVeiculosUseCase;
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
    public ResponseEntity<VeiculoResponseDTO> cadastrar(@Valid @RequestBody VeiculoRequestDTO dto) {
        CadastrarVeiculoCommand command = new CadastrarVeiculoCommand(
                dto.placa(), dto.modelo(), dto.marca(), dto.quilometragemAtual(), dto.empresaId(),
                dto.tipoPropulsao(), dto.capacidadeBateriaKwh(), dto.autonomiaEstimadaKm(),
                dto.nivelCargaAtualPercentual(), dto.capacidadeTanqueH2Kg(), dto.pressaoTanqueBar(),
                dto.capacidadeTanqueLitros());
        var veiculo = cadastrarVeiculoUseCase.cadastrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(VeiculoResponseDTO.fromDomain(veiculo));
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
    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {
        var veiculos = consultarVeiculosUseCase.listarTodos().stream()
                .map(VeiculoResponseDTO::fromDomain).toList();
        return ResponseEntity.ok(veiculos).HttpStatus.OK;
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
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorId(@PathVariable Long id) {
        var veiculo = consultarVeiculosUseCase.buscarPorId(id);
        return ResponseEntity.ok(VeiculoResponseDTO.fromDomain(veiculo), HttpStatus.OK);
    }
}
