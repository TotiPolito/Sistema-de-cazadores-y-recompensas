package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Set;

public class Agencia {

	private Set<Cazador> cazadores;

	public Agencia() {
		this.cazadores = new HashSet<>();
	}

	public void agregarCazador(Cazador cazador) {
		cazadores.add(cazador);
	}

	public Set<Profugo> obtenerLosProfugosCapturados() {

		Set<Profugo> capturados = new HashSet<>();

		for (Cazador cazador : cazadores) {
			for (Profugo profugo : cazador.getCapturados()) {
				capturados.add(profugo);
			}
		}
		return capturados;
	}

	public Profugo obtenerProfugoMasHabilCapturado() throws NoHayCapturadosException {
	    Profugo masHabil = null;

	    for (Cazador cazador : cazadores) {
	        for (Profugo profugo : cazador.getCapturados()) {
	            if (masHabil == null || profugo.getNivelDeHabilidad() > masHabil.getNivelDeHabilidad()) {
	                masHabil = profugo;
	            }
	        }
	    }

	    if (masHabil == null) {
	        throw new NoHayCapturadosException("No hay prófugos capturados en la agencia.");
	    }
	    return masHabil;
	}

	public Cazador obtenerCazadorConMasCapturas() throws NoHayCapturadosException {
	    Cazador conMasCapturas = null;
	    int maxCapturas = 0;

	    for (Cazador cazador : cazadores) {
	        Integer cantidad = cazador.getCapturados().size();
	        if (cantidad > maxCapturas) {
	            conMasCapturas = cazador;
	            maxCapturas = cantidad;
	        }
	    }

	    if (conMasCapturas == null) {
	        throw new NoHayCapturadosException("Ningún cazador tiene capturas.");
	    }

	    return conMasCapturas;
	}
	
	public Set<Cazador> getCazadores(){
		return this.cazadores;
		
	}

}
