package ar.edu.unlam.pb2.dominio;

public class NoHayCapturadosException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoHayCapturadosException(String mensaje) {
        super(mensaje);
    }
}
