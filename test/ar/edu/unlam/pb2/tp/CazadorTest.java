package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cazador;
import ar.edu.unlam.pb2.dominio.CazadorRural;
import ar.edu.unlam.pb2.dominio.CazadorSigiloso;
import ar.edu.unlam.pb2.dominio.CazadorUrbano;
import ar.edu.unlam.pb2.dominio.Profugo;
import ar.edu.unlam.pb2.dominio.Zona;

public class CazadorTest {

	private Cazador cazadorUrbano;
	private Cazador cazadorSigiloso;
	private Cazador cazadorRural;
	private Profugo profugo;
	private Profugo profugo1;
	private Profugo profugo2;
	private Profugo profugo3;
	private Zona zona;

	@Before
	public void setUp() {
		zona = new Zona("Ciudad");
		cazadorUrbano = new CazadorUrbano("Pedro", 100);
		cazadorSigiloso = new CazadorSigiloso("Pedro", 100);
		cazadorRural = new CazadorRural("Pedro", 100);
		profugo = new Profugo("John", 10, 60, false);
		profugo1 = new Profugo("Brian", 10, 60, true);
		profugo2 = new Profugo("Michael", 20, 60, false);
		profugo3 = new Profugo("Trevor", 30, 60, false);
	}

	@Test
	public void queUnCazadorNoCaptureAUnProfugoConMayorInocencia() {
		cazadorUrbano = new CazadorUrbano("Pedro", 50);
		profugo = new Profugo("John", 80, 30, false);
		zona = new Zona("Zona A");
		zona.agregarProfugo(profugo);

		cazadorUrbano.capturar(zona);

		assertFalse(cazadorUrbano.getCapturados().contains(profugo));
		assertTrue(zona.getProfugos().contains(profugo)); // no fue removido
	}

	@Test
	public void queUnCazadorSigilosoCaptureAUnProfugoQuePuedaCapturar() {
		profugo = new Profugo("John", 10, 40, false);
		assertTrue(cazadorSigiloso.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorSigilosoNoCaptureAUnProfugoQuePuedaCapturar() {
		assertFalse(cazadorSigiloso.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorUrbanoCaptureAUnProfugoQuePuedaCapturar() {
		assertTrue(cazadorUrbano.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorUrbanoNoCaptureAUnProfugoQuePuedaCapturar() {
		assertFalse(cazadorUrbano.puedeCapturar(profugo1));
	}

	@Test
	public void queUnCazadorRuralCaptureAUnProfugoQuePuedaCapturar() {
		assertTrue(cazadorRural.puedeCapturar(profugo1));
	}

	@Test
	public void queUnCazadorRuralNoCaptureAUnProfugoQuePuedaCapturar() {
		assertFalse(cazadorRural.puedeCapturar(profugo));
	}

	@Test
	public void queUnCazadorIntimideYDisminuyaElNivelDeInocenciaDelProfugo() {
		cazadorSigiloso = new CazadorSigiloso("Pedro", 90);
		cazadorUrbano = new CazadorUrbano("Pedro", 90);
		cazadorRural = new CazadorRural("Pedro", 90);

		profugo = new Profugo("John", 12, 60, false);

		cazadorSigiloso.intimidar(profugo);
		assertEquals(Integer.valueOf(10), profugo.getNivelDeInocencia());

		cazadorUrbano.intimidar(profugo); 
		assertEquals(Integer.valueOf(8), profugo.getNivelDeInocencia());

		cazadorRural.intimidar(profugo);
		assertEquals(Integer.valueOf(6), profugo.getNivelDeInocencia());

	}

	@Test
	public void queUnCazadorSigilosoIntimideYLaHabilidadDelProfugoDisminuyaEn5Unidades() {
		cazadorSigiloso = new CazadorSigiloso("Pedro", 100);
		cazadorSigiloso.intimidar(profugo);
		Integer obtenido = profugo.getNivelDeHabilidad();
		assertEquals(Integer.valueOf(55), obtenido);
	}

	@Test
	public void queUnCazadorUrbanoIntimideYElProfugoDejeDeSerNervioso() {
		cazadorUrbano.intimidar(profugo1);
		assertFalse(profugo.getEsNervioso());
	}

	@Test
	public void queUnCazadorRuralIntimideYElProfugoSeVuelvaNervioso() {
		cazadorRural.intimidar(profugo);
		assertTrue(profugo.getEsNervioso());

	}

	@Test
	public void queUnCazadorCapturaATodosLosProfugosEnUnaZonaConExito() {
		zona.agregarProfugo(profugo);
		zona.agregarProfugo(profugo2);
		zona.agregarProfugo(profugo3);

		cazadorUrbano.capturar(zona);

		assertEquals(Integer.valueOf(106), cazadorUrbano.getExperiencia());
		assertTrue(cazadorUrbano.getCapturados().contains(profugo));
		assertTrue(cazadorUrbano.getCapturados().contains(profugo2));
		assertTrue(cazadorUrbano.getCapturados().contains(profugo3));
		assertTrue(zona.getProfugos().isEmpty());

	}

	@Test
	public void queUnCazadorCapturaALosProfugosQuePuedeEnUnaZonaConExitoYAlRestoLoIntimide() {
		zona.agregarProfugo(profugo1);
		zona.agregarProfugo(profugo2);
		zona.agregarProfugo(profugo3);

		cazadorUrbano.capturar(zona);

		assertEquals(Integer.valueOf(164), cazadorUrbano.getExperiencia());
		assertTrue(cazadorUrbano.getCapturados().contains(profugo2));
		assertTrue(cazadorUrbano.getCapturados().contains(profugo3));
	}
	
	@Test
	public void queDosCazadoresConElMismoNombreSeanConsideradosIguales() {
	    Cazador c1 = new CazadorUrbano("Pedro", 100);
	    Cazador c2 = new CazadorUrbano("Pedro", 10); 

	    assertTrue(c1.equals(c2)); 
	    assertEquals(c1, c2);
	}
	
	@Test
	public void queAlSerCapturadoUnProfugoNoEsteMasEnLaZona() {
		Profugo profugoEliminado = new Profugo("Richard", 10, 20, false);
		zona.agregarProfugo(profugoEliminado);
		cazadorUrbano.capturar(zona);

		assertFalse(zona.getProfugos().contains(profugoEliminado));

	}

}
