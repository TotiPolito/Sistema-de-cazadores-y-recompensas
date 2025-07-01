package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public class Zona {

	private String nombre;
	private List<Profugo> profugos;

	public Zona(String nombre) {
		this.setNombre(nombre);
		this.profugos = new ArrayList<>();
	}

	public void agregarProfugo(Profugo profugo) {
		profugos.add(profugo);
	}

	public List<Profugo> getProfugos() {
		return profugos;
	}

	public void EliminarProfugo(Profugo profugo) {
		profugos.remove(profugo);
	}
	
	public Profugo buscarProfugoPorNombre(String nombre) {
		
		Profugo profugoBuscado = null;
		for (Profugo profugo : profugos) {
			if (profugo.getNombre().equals(nombre)) {
				profugoBuscado = profugo;
			}
		}
		return profugoBuscado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
