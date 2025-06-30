package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cazador;

public class CazadorTest {

	@Test
	public void quePuedoCrearUnCazador() {
		Cazador cazador = new Cazador();
		assertNotNull(cazador);
	}

}
