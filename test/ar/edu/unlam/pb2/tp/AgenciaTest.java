package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Agencia;
import ar.edu.unlam.pb2.dominio.Cazador;
import ar.edu.unlam.pb2.dominio.CazadorRural;
import ar.edu.unlam.pb2.dominio.CazadorUrbano;
import ar.edu.unlam.pb2.dominio.NoHayCapturadosException;
import ar.edu.unlam.pb2.dominio.Profugo;
import ar.edu.unlam.pb2.dominio.Zona;

public class AgenciaTest {

	private Cazador cazadorUrbano;
	private Cazador cazadorRural;
	private Zona zona1;
	private Zona zona2;
	private Profugo profugo1;
	private Profugo profugo2;
	private Profugo profugo3;
	private Agencia agencia;

	@Before
	public void setUp() {
		agencia = new Agencia();
		cazadorUrbano = new CazadorUrbano("Pedro", 100);
		cazadorRural = new CazadorRural("Pedro", 100);
		profugo1 = new Profugo("Luis", 10, 20, false);
		profugo2 = new Profugo("Pedro", 5, 30, false);
		zona1 = new Zona("Ciudad");
		zona2 = new Zona("Campo");
	}
	
	@Test
	public void queSePuedaRegistrarUnCazadorEnLaAgencia() {
		cazadorUrbano = new CazadorUrbano("Pedro", 40);

		agencia.agregarCazador(cazadorUrbano);

		assertTrue(agencia.getCazadores().contains(cazadorUrbano));
	}

	@Test
	public void queAlCrearLaAgenciaNoHayProfugosCapturados() {
		assertTrue(agencia.obtenerLosProfugosCapturados().isEmpty());
	}

	@Test
	public void quePuedoObtenerTodosLosProfugosCapturadosPorLosCazadores() {

		zona1.agregarProfugo(profugo1);
		zona1.agregarProfugo(profugo2);
		cazadorUrbano.capturar(zona1);

		agencia.agregarCazador(cazadorUrbano);

		Set<Profugo> capturados = agencia.obtenerLosProfugosCapturados();

		assertEquals(2, capturados.size());
		assertTrue(capturados.contains(profugo1));
		assertTrue(capturados.contains(profugo2));
	}

	@Test
	public void quePuedoObtenerElProfugoMasHabilDeLosCapturados() throws NoHayCapturadosException {
		
		zona1.agregarProfugo(profugo1);
		zona1.agregarProfugo(profugo2);
		cazadorUrbano.capturar(zona1);

		agencia.agregarCazador(cazadorUrbano);

		Profugo masHabil = agencia.obtenerProfugoMasHabilCapturado();
		assertEquals("Pedro", masHabil.getNombre());
	}

	@Test
	public void quePuedoObtenerElCazadorConMasCapturasRealizadas() throws NoHayCapturadosException {

		profugo1 = new Profugo("A", 10, 20, false);
		profugo2 = new Profugo("B", 5, 30, false);
		profugo3 = new Profugo("C", 8, 25, true);

		zona1.agregarProfugo(profugo1);
		zona1.agregarProfugo(profugo2);

		zona2.agregarProfugo(profugo3);

		cazadorUrbano.capturar(zona1);
		cazadorRural.capturar(zona2);

		agencia.agregarCazador(cazadorUrbano);
		agencia.agregarCazador(cazadorRural);

		Cazador cazadorconMasCapturas = agencia.obtenerCazadorConMasCapturas();

		assertEquals(cazadorUrbano, cazadorconMasCapturas);
	}
	
	@Test
	public void queNoSePuedaAgregarDosCazadoresConElMismoNombreALaAgencia() {
	    Agencia agencia = new Agencia();

	    Cazador cazador1 = new CazadorUrbano("Luis", 50);
	    Cazador cazador2 = new CazadorUrbano("Luis", 80);

	    agencia.agregarCazador(cazador1);
	    agencia.agregarCazador(cazador2);

	    assertEquals(1, agencia.getCazadores().size());
	}
	
	@Test
	public void quePuedoObtenerElNombreDeLaZona() {
		assertEquals("Ciudad", zona1.getNombre());
	}
	
	@Test(expected = NoHayCapturadosException.class)
	public void queNoSePuedaDevolverProfugoMasHabilSiNoHayCapturados() throws NoHayCapturadosException {
	    agencia.obtenerProfugoMasHabilCapturado();
	}

	@Test(expected = NoHayCapturadosException.class)
	public void queNoSePuedaDevolverCazadorConMasCapturasSiNoHayCazadores() throws NoHayCapturadosException {
	    agencia.obtenerCazadorConMasCapturas();
	}
	
	@Test(expected = NoHayCapturadosException.class)
	public void queNoSePuedaDevolverCazadorConMasCapturasSiNadieCapturo() throws NoHayCapturadosException {
		cazadorUrbano = new CazadorUrbano("Pedro", 10);
		cazadorRural = new CazadorRural("Pedro", 20);
	    agencia.agregarCazador(cazadorUrbano);
	    agencia.agregarCazador(cazadorRural);
	    
	    agencia.obtenerCazadorConMasCapturas();
	}
	
	@Test
	public void mensajeDeExcepcionCuandoNoHayProfugosCapturados() {

	    try {
	    	profugo1 = agencia.obtenerProfugoMasHabilCapturado();
	        fail("Se esperaba una NoHayCapturadosException y no se lanzó.");
	    } catch (NoHayCapturadosException mensaje) {
	        assertEquals("No hay prófugos capturados en la agencia.", mensaje.getMessage());
	    }
	}
}
