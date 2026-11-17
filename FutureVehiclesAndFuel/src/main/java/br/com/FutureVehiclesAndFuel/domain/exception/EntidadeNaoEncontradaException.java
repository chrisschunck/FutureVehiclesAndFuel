package br.com.FutureVehiclesAndFuel.domain.exception;
import org.springframework.Schema;

@Schema(message="Exceção para entidade não encontrada")
public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
