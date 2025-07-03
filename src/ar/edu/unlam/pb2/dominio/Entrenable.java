package ar.edu.unlam.pb2.dominio;

public interface Entrenable {
	void aplicarA(Profugo p) throws EntrenamientoInvalidoException, HabilidadExcedidaException;
}
