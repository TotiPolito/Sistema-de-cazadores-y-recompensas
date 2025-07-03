package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Cazador {

	private Integer nivelDeExperiencia;
	private List<Profugo> capturados = new ArrayList<>();

	public Cazador(Integer nivelDeExperiencia) {
		this.nivelDeExperiencia = nivelDeExperiencia;
	}

	public abstract Boolean puedeCapturar(Profugo profugo);

	public abstract void intimidar(Profugo profugo);

	public void capturar(Zona zona) {

		List<Profugo> listaProfugos = zona.getProfugos();
		List<Profugo> intimidados = new ArrayList<>();
		List<Profugo> capturadosZona = new ArrayList<>();

		intentoCapturaProfugos(listaProfugos, intimidados, capturadosZona);

		eliminarProfugosCapturados(zona, capturadosZona);

		sumarExperiencia(intimidados, capturadosZona);
	}

	private void sumarExperiencia(List<Profugo> intimidados, List<Profugo> capturadosZona) {
		int minHabilidad = Integer.MAX_VALUE;

		for (int i = 0; i < intimidados.size(); i++) {
			int habilidad = intimidados.get(i).getNivelDeHabilidad();

			if (habilidad < minHabilidad) {
				minHabilidad = habilidad;
			}
		}
		if (intimidados.size() == 0) {
			minHabilidad = 0;
		}

		nivelDeExperiencia += minHabilidad + (2 * capturadosZona.size());
	}

	private void eliminarProfugosCapturados(Zona zona, List<Profugo> capturadosZona) {
		for (int i = 0; i < capturadosZona.size(); i++) {
			zona.eliminarProfugo(capturadosZona.get(i));
		}
	}

	private void intentoCapturaProfugos(List<Profugo> listaProfugos, List<Profugo> intimidados,
			List<Profugo> capturadosZona) {

		for (int i = 0; i < listaProfugos.size(); i++) {
			Profugo profugo = listaProfugos.get(i);

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

	public List<Profugo> getCapturados() {
		return capturados;
	}

}
