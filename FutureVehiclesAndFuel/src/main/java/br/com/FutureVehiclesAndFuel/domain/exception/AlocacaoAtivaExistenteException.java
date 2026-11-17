package br.com.FutureVehiclesAndFuel.domain.exception;
import org.srpingframework.Schema;

@Schema(message="Exceção para alocação existente ativa")
public class AlocacaoAtivaExistenteException extends RuntimeException {
    public AlocacaoAtivaExistenteException(String message) {
        super(message);
    }
}
