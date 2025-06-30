package ar.edu.unlam.pb2.dominio;

public class CazadorSigiloso extends Cazador {

	public CazadorSigiloso(Integer nivelDeExperiencia) {
		super(nivelDeExperiencia);
	}

	@Override
	public Boolean puedeCapturar(Profugo profugo) {
		return getExperiencia() > profugo.getNivelDeInocencia() && profugo.getNivelDeHabilidad() < 50;
	}

	@Override
	public void intimidar(Profugo profugo) {
		profugo.reducirInocencia(2);
		profugo.reducirHabilidad(5);
	}
}
