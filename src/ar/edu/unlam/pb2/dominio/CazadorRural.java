package ar.edu.unlam.pb2.dominio;

public class CazadorRural extends Cazador {

	public CazadorRural(Integer nivelDeExperiencia) {
		super(nivelDeExperiencia);
	}

	@Override
	public Boolean puedeCapturar(Profugo profugo) {
		return getExperiencia() > profugo.getNivelDeInocencia() && profugo.getEsNervioso();
	}

	@Override
	public void intimidar(Profugo profugo) {
		profugo.reducirInocencia(2);
		profugo.setEsNervioso(true);
	}
}
