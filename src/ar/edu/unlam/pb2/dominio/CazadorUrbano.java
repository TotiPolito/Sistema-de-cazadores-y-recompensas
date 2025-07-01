package ar.edu.unlam.pb2.dominio;

public class CazadorUrbano extends Cazador {

	public CazadorUrbano(Integer nivelDeExperiencia) {
		super(nivelDeExperiencia);
	}

	@Override
	public Boolean puedeCapturar(Profugo profugo) {
		return !profugo.getEsNervioso();
	}

	@Override
	public void intimidar(Profugo profugo) {
		profugo.reducirInocencia(2);
		profugo.setEsNervioso(false);
	}
}
