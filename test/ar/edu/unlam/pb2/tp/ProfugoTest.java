package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.ArtesMarciales;
import ar.edu.unlam.pb2.dominio.Cazador;
import ar.edu.unlam.pb2.dominio.CazadorRural;
import ar.edu.unlam.pb2.dominio.CazadorSigiloso;
import ar.edu.unlam.pb2.dominio.Entrenable;
import ar.edu.unlam.pb2.dominio.EntrenamientoElite;
import ar.edu.unlam.pb2.dominio.EntrenamientoInvalidoException;
import ar.edu.unlam.pb2.dominio.HabilidadExcedidaException;
import ar.edu.unlam.pb2.dominio.Profugo;
import ar.edu.unlam.pb2.dominio.ProteccionLegal;

public class ProfugoTest {

	private Cazador cazador;
	private Profugo profugo;
	private Entrenable artesMarciales;
	private Entrenable entrenamientoElite;
	private Entrenable proteccionLegal;

	@Before
	public void setUp() {
		artesMarciales = new ArtesMarciales();
		entrenamientoElite = new EntrenamientoElite();
		proteccionLegal = new ProteccionLegal();
	}
	
	@Test
	public void queUnProfugoPuedeEntrenarArtesMarcialesMixtasYSuNivelDeHabilidadSeDuplicaDe30a60()
			throws HabilidadExcedidaException, EntrenamientoInvalidoException {

		profugo = new Profugo("John", 20, 30, false);
		artesMarciales.aplicarA(profugo);

		assertTrue(profugo.getTieneArtesMarciales());
		assertEquals(Integer.valueOf(60), profugo.getNivelDeHabilidad());

	}

	@Test
	public void queUnProfugoPuedeHacerEntrenamientoDeEliteYnoPuedeSerNerviosoMas() {
		cazador = new CazadorRural(10);
		profugo = new Profugo("John", 80, 30, false);
		profugo.aplicarEntrenamientoElite();
		cazador.intimidar(profugo);
		assertTrue(profugo.getTieneEntrenamientoElite());
		assertFalse(profugo.getEsNervioso());

	}

	@Test
	public void queUnProfugoPuedeTenerProteccionLegalYSuInocenciaNoDisminuyeDe40()
			throws EntrenamientoInvalidoException {
		cazador = new CazadorSigiloso(10);

		profugo = new Profugo("John", 40, 40, false);

		profugo.aplicarProteccionLegal();
		assertTrue(profugo.getTieneProteccionLegal());

		cazador.intimidar(profugo);
		Integer obtenido = profugo.getNivelDeInocencia();

		assertEquals(Integer.valueOf(40), obtenido);

	}

	@Test
	public void queUnProfugoPuedeHacerTodosLosEntrenamientosYAcumularHabilidades()
			throws HabilidadExcedidaException, EntrenamientoInvalidoException {

		profugo = new Profugo("John", 40, 40, false);

		artesMarciales.aplicarA(profugo);
		entrenamientoElite.aplicarA(profugo);
		proteccionLegal.aplicarA(profugo);

		cazador = new CazadorSigiloso(5);

		Cazador cazador2 = new CazadorRural(10);
		cazador.intimidar(profugo);
		cazador2.intimidar(profugo);

		assertEquals(Integer.valueOf(70), profugo.getNivelDeHabilidad());
		assertFalse(profugo.getEsNervioso());
	}

	@Test(expected = HabilidadExcedidaException.class)
	public void queNoSePuedaAplicarArtesMarcialesSiSuperaHabilidadMaxima() throws HabilidadExcedidaException {
		profugo = new Profugo("Luis", 10, 60, true);
		profugo.aplicarArtesMarciales(); // 60 * 2 = 120 → excepción

	}

	@Test(expected = EntrenamientoInvalidoException.class)
	public void queNoSePuedaAplicarProteccionLegalSiLaInocenciaEsMenorA40()
			throws HabilidadExcedidaException, EntrenamientoInvalidoException {
		profugo = new Profugo("Luis", 10, 30, true);
		profugo.aplicarProteccionLegal();

	}

}
