package ar.edu.unlam.pb2.dominio;

public class EntrenamientoInvalidoException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public EntrenamientoInvalidoException(String mensaje) {
        super(mensaje);
    }
}