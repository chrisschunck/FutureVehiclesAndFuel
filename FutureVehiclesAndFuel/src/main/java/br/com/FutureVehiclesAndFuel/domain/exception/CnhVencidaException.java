package br.com.FutureVehiclesAndFuel.domain.exception;
import org.springframework.Schema;

@Schema(message="exceção para cnh vencida")
public class CnhVencidaException extends RuntimeException {
    public CnhVencidaException(String message) {
        super(message);
    }
}
