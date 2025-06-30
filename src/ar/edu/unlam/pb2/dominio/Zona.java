package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public class Zona {

	private String nombre;
	private List<Profugo> profugos;

	public Zona(String nombre) {
		this.nombre = nombre;
		this.profugos = new ArrayList<>();
	}

	public void agregarProfugo(Profugo profugo) {
		profugos.add(profugo);
	}

	public List<Profugo> getProfugos() {
		return profugos;
	}

	public Boolean ExisteProfugoConNombre(Profugo profugo1) {

		for (Profugo profugo : profugos) {
			if (profugo.getNombre().equals(profugo1.getNombre())) {
				return true;
			}
		}
		return false;
	}

}
