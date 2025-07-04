package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Cazador {

	protected String nombre;
	private Integer nivelDeExperiencia;
	private Set<Profugo> capturados = new HashSet<>();

	public Cazador(String nombre, Integer nivelDeExperiencia) {
		this.nombre = nombre;
		this.nivelDeExperiencia = nivelDeExperiencia;
	}

	public abstract Boolean puedeCapturar(Profugo profugo);

	public abstract void intimidar(Profugo profugo);

	public void capturar(Zona zona) {

		Set<Profugo> listaProfugos = zona.getProfugos();
		Set<Profugo> intimidados = new HashSet<>();
		Set<Profugo> capturadosZona = new HashSet<>();

		intentoCapturaProfugos(listaProfugos, intimidados, capturadosZona);

		eliminarProfugosCapturados(zona, capturadosZona);

		sumarExperiencia(intimidados, capturadosZona);
	}

	private void sumarExperiencia(Set<Profugo> intimidados, Set<Profugo> capturadosZona) {
		int minHabilidad = Integer.MAX_VALUE;

		for (Profugo profugo : capturadosZona) {
			int habilidad = profugo.getNivelDeHabilidad();
			if (habilidad < minHabilidad) {
				minHabilidad = habilidad;
			}
		}
		if (intimidados.size() == 0) {
			minHabilidad = 0;
		}

		nivelDeExperiencia += minHabilidad + (2 * capturadosZona.size());
	}


	private void eliminarProfugosCapturados(Zona zona, Set<Profugo> capturadosZona) {
		for (Profugo profugo : capturadosZona) {
			zona.eliminarProfugo(profugo);
		}

	}

	private void intentoCapturaProfugos(Set<Profugo> listaProfugos, Set<Profugo> intimidados,
			Set<Profugo> capturadosZona) {

		for (Profugo profugo : listaProfugos) {
			if (this.nivelDeExperiencia > profugo.getNivelDeInocencia() && puedeCapturar(profugo)) {
				capturados.add(profugo);
				capturadosZona.add(profugo);
			} else {
				intimidar(profugo);
				intimidados.add(profugo);
			}
		}

	}

	public Integer getExperiencia() {
		return nivelDeExperiencia;
	}

	public Set<Profugo> getCapturados() {
		return capturados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cazador other = (Cazador) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
