package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Set;

public class Zona {

	private String nombre;
	private Set<Profugo> profugos;

	public Zona(String nombre) {
		this.setNombre(nombre);
		this.profugos = new HashSet<>();
	}

	public void agregarProfugo(Profugo profugo) {
		profugos.add(profugo);
	}

	public Set<Profugo> getProfugos() {
		return profugos;
	}

	public void eliminarProfugo(Profugo profugo) {
		profugos.remove(profugo);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	

}
