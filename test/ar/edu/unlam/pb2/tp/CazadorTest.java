package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cazador;
import ar.edu.unlam.pb2.dominio.CazadorRural;
import ar.edu.unlam.pb2.dominio.CazadorSigiloso;
import ar.edu.unlam.pb2.dominio.CazadorUrbano;
import ar.edu.unlam.pb2.dominio.Profugo;

public class CazadorTest {

	private Cazador cazador;
	private Profugo profugo;
	
	@Test
	public void queUnCazadorNoCaptureAUnProfugoConNivelDeInocenciaConMayorNivelDeExperienciaQueElCazador() {
		Cazador cazador1 = new CazadorSigiloso(90);
		Cazador cazador2 = new CazadorUrbano(90);
		Cazador cazador3 = new CazadorRural(90);
		profugo = new Profugo (100, 60, false);
		assertFalse(cazador1.puedeCapturar(profugo));
		assertFalse(cazador2.puedeCapturar(profugo));
		assertFalse(cazador3.puedeCapturar(profugo));
	}
	
	@Test
	public void queUnCazadorSigilosoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorSigiloso(100);
		profugo = new Profugo (10, 40, false);
		assertTrue(cazador.puedeCapturar(profugo));
	}
	
	@Test
	public void queUnCazadorSigilosoNoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorSigiloso(100);
		profugo = new Profugo (10, 60, false);
		assertFalse(cazador.puedeCapturar(profugo));
	}
	
	@Test
	public void queUnCazadorUrbanoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorUrbano(100);
		profugo = new Profugo (10, 60, false);
		assertTrue(cazador.puedeCapturar(profugo));
	}
	
	@Test
	public void queUnCazadorUrbanoNoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorSigiloso(100);
		profugo = new Profugo (10, 60, true);
		assertFalse(cazador.puedeCapturar(profugo));
	}
	
	@Test
	public void queUnCazadorRuralCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorRural(100);
		profugo = new Profugo (10, 60, true);
		assertTrue(cazador.puedeCapturar(profugo));
	}
	
	@Test
	public void queUnCazadorRuralNoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorRural(100);
		profugo = new Profugo (10, 60, false);
		assertFalse(cazador.puedeCapturar(profugo));
	}
	
}
