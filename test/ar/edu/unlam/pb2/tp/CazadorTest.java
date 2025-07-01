package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cazador;
import ar.edu.unlam.pb2.dominio.CazadorRural;
import ar.edu.unlam.pb2.dominio.CazadorSigiloso;
import ar.edu.unlam.pb2.dominio.CazadorUrbano;
import ar.edu.unlam.pb2.dominio.Profugo;
import ar.edu.unlam.pb2.dominio.Zona;

public class CazadorTest {

	private Cazador cazador;
	private Profugo profugo;

	@Test
	public void queUnCazadorNoCaptureAUnProfugoConMayorInocencia() {
		Cazador cazador = new CazadorUrbano(50);
		Profugo profugo = new Profugo("John", 80, 30, false);
		Zona zona = new Zona("Zona A");
		zona.agregarProfugo(profugo);

		cazador.capturar(zona);

		assertFalse(cazador.getCapturados().contains(profugo));
		assertTrue(zona.getProfugos().contains(profugo)); // no fue removido
	}

	@Test
	public void queUnCazadorSigilosoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorSigiloso(100);
		profugo = new Profugo("John",10, 40, false);
		assertTrue(cazador.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorSigilosoNoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorSigiloso(100);
		profugo = new Profugo("John",10, 60, false);
		assertFalse(cazador.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorUrbanoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorUrbano(100);
		profugo = new Profugo("John",10, 60, false);
		assertTrue(cazador.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorUrbanoNoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorUrbano(100);
		profugo = new Profugo("John",10, 60, true);
		assertFalse(cazador.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorRuralCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorRural(100);
		profugo = new Profugo("John",10, 60, true);
		assertTrue(cazador.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorRuralNoCaptureAUnProfugoQuePuedaCapturar() {
		cazador = new CazadorRural(100);
		profugo = new Profugo("John",10, 60, false);
		assertFalse(cazador.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorIntimideYDisminuyaElNivelDeInocenciaDelProfugo() {
		Cazador cazador1 = new CazadorSigiloso(90);
		Cazador cazador2 = new CazadorUrbano(90);
		Cazador cazador3 = new CazadorRural(90);

		Profugo profugo1 = new Profugo("John", 12, 60, false);

		cazador1.intimidar(profugo1);
		assertEquals(Integer.valueOf(10), profugo1.getNivelDeInocencia());

		cazador2.intimidar(profugo1);
		assertEquals(Integer.valueOf(8), profugo1.getNivelDeInocencia());

		cazador3.intimidar(profugo1);
		assertEquals(Integer.valueOf(6), profugo1.getNivelDeInocencia());

	}

	@Test
	public void queUnCazadorSigilosoIntimideYLaHabilidadDelProfugoDisminuyaEn5Unidades() {
		cazador = new CazadorSigiloso(100);
		profugo = new Profugo("John", 10, 60, false);
		cazador.intimidar(profugo);
		Integer obtenido = profugo.getNivelDeHabilidad();
		assertEquals(Integer.valueOf(55), obtenido);
	}

	@Test
	public void queUnCazadorUrbanoIntimideYElProfugoDejeDeSerNervioso() {
		cazador = new CazadorUrbano(100);
		profugo = new Profugo("John", 10, 60, true);
		cazador.intimidar(profugo);
		assertFalse(profugo.getEsNervioso());
	}

	@Test
	public void queUnCazadorRuralIntimideYElProfugoSeVuelvaNervioso() {
		cazador = new CazadorRural(100);
		profugo = new Profugo("John", 10, 60, true);
		cazador.intimidar(profugo);
		assertTrue(profugo.getEsNervioso());

	}
	
	@Test
	public void queUnCazadorCapturaATodosLosProfugosEnUnaZonaConExito() {
		Zona zona = new Zona("Ciudad");
		
		Profugo profugo1 = new Profugo("John",10, 60, true);
		Profugo profugo2 = new Profugo("Michael",20, 60, false);
		Profugo profugo3 = new Profugo("Trevor",30, 60, true);
		
		zona.agregarProfugo(profugo1);
		zona.agregarProfugo(profugo2);
		zona.agregarProfugo(profugo3);

		cazador = new CazadorUrbano(100);
		cazador.capturar(zona);
		
		// experiencia += (Mínimo valor de habilidad entre todos los intimidados) + (2 * prófugos capturados)
		// 100 += (     ) + ( 2 x 3 )
		// 100 +=  X + 6
		assertEquals(cazador.getExperiencia(), Integer.valueOf(162));
		

	}
	
	@Test
	public void queAlSerCapturadoUnProfugoNoEsteMasEnLaZona() {
		Zona zona = new Zona("Ciudad");
		Profugo profugo1 = new Profugo("John", 10, 60, true);
		zona.agregarProfugo(profugo1);
		cazador = new CazadorUrbano(100);
		cazador.capturar(zona);

		assertTrue(zona.buscarProfugoPorNombre("John").equals(profugo1));
		
		
	}
	
}
