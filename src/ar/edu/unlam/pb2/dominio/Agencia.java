package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Set;

public class Agencia {

	private Set<Cazador> cazadores = new HashSet<>();
	
	public void registrarCazador(Cazador cazador) {
		cazadores.add(cazador);
	}
	
	public Set<Cazador> getCazadores(){
		return this.cazadores;
		
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
	
	public Profugo obtenerProfugoMasHabilCapturado() {
		Profugo masHabil = null;
		
		for (Cazador cazador : cazadores) {
			for (Profugo profugo : cazador.getCapturados()) {
				if (masHabil == null || profugo.getNivelDeHabilidad() > masHabil.getNivelDeHabilidad()) {
					masHabil = profugo;
				}
			}
		}
		return masHabil;
		
	}
	
	public Cazador obtenerCazadorConMasCapturas() {
	    Cazador conMasCapturas = null;
	    int maxCapturas = 0;

	    for (Cazador cazador : cazadores) {
	        Integer cantidad = cazador.getCapturados().size();
	        if (conMasCapturas == null || cantidad > maxCapturas) {
	            conMasCapturas = cazador;
	            maxCapturas = cantidad;
	        }
	    }

	    return conMasCapturas;
	}
	
}
