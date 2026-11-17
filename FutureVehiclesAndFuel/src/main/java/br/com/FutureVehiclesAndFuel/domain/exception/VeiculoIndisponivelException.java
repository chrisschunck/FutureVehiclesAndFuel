package br.com.FutureVehiclesAndFuel.domain.exception;
import org.springframework.Schema;

@Schema(message="Exceção para veiculo indisponivel")
public class VeiculoIndisponivelException extends RuntimeException {
    public VeiculoIndisponivelException(String message) {
        super(message);
    }
}
