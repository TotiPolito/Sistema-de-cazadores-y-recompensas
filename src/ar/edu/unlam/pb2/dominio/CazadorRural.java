package ar.edu.unlam.pb2.dominio;

public class CazadorRural extends Cazador {

	public CazadorRural(String nombre, Integer nivelDeExperiencia) {
		super(nombre, nivelDeExperiencia);
	}

	@Override
	public Boolean puedeCapturar(Profugo profugo) {
		return profugo.getEsNervioso();
	}

	@Override
	public void intimidar(Profugo profugo) {
		profugo.reducirInocencia(2);
		profugo.setEsNervioso(true);
	}
}
