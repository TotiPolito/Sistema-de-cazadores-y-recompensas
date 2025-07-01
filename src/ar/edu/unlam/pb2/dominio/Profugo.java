package ar.edu.unlam.pb2.dominio;

public class Profugo {

	private String nombre;
	private Integer nivelDeInocencia;
	private Integer nivelDeHabilidad;
	private Boolean esNervioso;
	private boolean tieneArtesMarciales = false;
	private boolean tieneEntrenamientoElite = false;
	private boolean tieneProteccionLegal = false;

	public Profugo(String nombre, Integer nivelDeInocencia, Integer nivelDeHabilidad, Boolean esNervioso) {
		this.nombre = nombre;
		this.nivelDeInocencia = nivelDeInocencia;
		this.nivelDeHabilidad = nivelDeHabilidad;
		this.esNervioso = esNervioso;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getNivelDeInocencia() {
		if (tieneProteccionLegal) {
			return Math.max(nivelDeInocencia, 40);
		}
		return nivelDeInocencia;
	}

	public Integer getNivelDeHabilidad() {
		if (tieneArtesMarciales) {
			return Math.min(nivelDeHabilidad * 2, 100);
		}
		return nivelDeHabilidad;
	}

	public Boolean getEsNervioso() {
		if (tieneEntrenamientoElite) {
			return false;
		}
		return esNervioso;
	}

	public void aplicarArtesMarciales() {
		tieneArtesMarciales = true;
	}

	public void aplicarEntrenamientoElite() {
		tieneEntrenamientoElite = true;
	}

	public void aplicarProteccionLegal() {
		tieneProteccionLegal = true;
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
