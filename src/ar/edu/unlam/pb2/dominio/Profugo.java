package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

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
		if (this.tieneProteccionLegal) {
			return Math.max(nivelDeInocencia, 40);
		}
		return nivelDeInocencia;
	}

	public Integer getNivelDeHabilidad() {
		if (this.tieneArtesMarciales) {
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

	public boolean getTieneArtesMarciales() {
		return tieneArtesMarciales;
	}

	public boolean getTieneEntrenamientoElite() {
		return tieneEntrenamientoElite;
	}

	public boolean getTieneProteccionLegal() {
		return tieneProteccionLegal;
	}

	public void aplicarArtesMarciales() throws HabilidadExcedidaException {
		if (nivelDeHabilidad * 2 > 100) {
			throw new HabilidadExcedidaException(
					"No se puede aplicar artes marciales porque Habilidad superaria los 100");
		}
		tieneArtesMarciales = true;
	}

	public void aplicarEntrenamientoElite() {
		tieneEntrenamientoElite = true;
		esNervioso = false;
	}

	public void aplicarProteccionLegal() throws EntrenamientoInvalidoException {
		if (this.nivelDeInocencia < 40) {
			throw new EntrenamientoInvalidoException(
					"Para hacer este entrenamiento el nivel de inocencia debe estar sobre 40");
		}
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
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Profugo other = (Profugo) obj;
        return Objects.equals(nombre, other.nombre);
    }
	@Override
	public int hashCode() {
	    return Objects.hash(nombre);
	}
}
