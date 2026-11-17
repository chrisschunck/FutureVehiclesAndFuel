package br.com.FutureVehiclesAndFuel.domain.exception;
import org.springframework.Schema;

@Schema(message="Exceção para saida não encontrada")
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
