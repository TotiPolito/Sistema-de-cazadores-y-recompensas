package ar.edu.unlam.pb2.dominio;

public abstract class Cazador {

	private Integer nivelDeExperiencia;
	
	public Cazador (Integer nivelDeExperiencia) {
		this.nivelDeExperiencia = nivelDeExperiencia;
	}
	
	public abstract Boolean puedeCapturar(Profugo profugo);
	public abstract void intimidar(Profugo profugo);

	public Integer getExperiencia() {
		return nivelDeExperiencia;
	}
	
	public void sumarExperiencia(Integer capturas, Integer minHabilidadIntimidados) {
		nivelDeExperiencia += minHabilidadIntimidados + (2 * capturas);
	}
}
