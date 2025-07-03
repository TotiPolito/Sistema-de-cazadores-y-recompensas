package ar.edu.unlam.pb2.dominio;

public class ArtesMarciales implements Entrenable {
	public void aplicarA(Profugo profugo) throws HabilidadExcedidaException {
		profugo.aplicarArtesMarciales();
	}
}
