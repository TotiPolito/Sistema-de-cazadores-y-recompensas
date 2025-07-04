package ar.edu.unlam.pb2.dominio;

public class CazadorSigiloso extends Cazador {

	public CazadorSigiloso(String nombre, Integer nivelDeExperiencia) {
		super(nombre, nivelDeExperiencia);
	}

	@Override
	public Boolean puedeCapturar(Profugo profugo) {
		return profugo.getNivelDeHabilidad() < 50;
	}

	@Override
	public void intimidar(Profugo profugo) {
		profugo.reducirInocencia(2);
		profugo.reducirHabilidad(5);
	}
}
