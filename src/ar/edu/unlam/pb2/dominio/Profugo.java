package ar.edu.unlam.pb2.dominio;

public class Profugo {

	private Integer nivelDeInocencia;
	private Integer nivelDeHabilidad;
	private Boolean esNervioso;
	
	public Profugo (Integer nivelDeInocencia, Integer nivelDeHabilidad, Boolean esNervioso) {
		this.nivelDeInocencia = nivelDeInocencia;
		this.nivelDeHabilidad = nivelDeHabilidad;
		this.esNervioso = esNervioso;
	}

	public Integer getNivelDeInocencia() {
		return nivelDeInocencia;
	}

	public Integer getNivelDeHabilidad() {
		return nivelDeHabilidad;
	}

	public Boolean getEsNervioso() {
		return esNervioso;
	}

	public void setEsNervioso(Boolean esNervioso) {
		this.esNervioso = esNervioso;
	}
	
	public void reducirInocencia(Integer valor) {
		nivelDeInocencia = Math.max(0, nivelDeInocencia - valor);
	}
	
	public void reducirHabilidad(Integer valor) {
		nivelDeHabilidad = Math.max(0, nivelDeHabilidad - valor);
	}
	
}
