package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cazador;
import ar.edu.unlam.pb2.dominio.CazadorRural;
import ar.edu.unlam.pb2.dominio.CazadorSigiloso;
import ar.edu.unlam.pb2.dominio.Profugo;

public class ProfugoTest {
	
	@Test
	public void queUnProfugoPuedeEntrenarArtesMarcialesMixtasYSuNivelDeHabilidadSeDuplicaDe30a60() {

		Profugo profugo = new Profugo("John", 20, 30, false);
		profugo.aplicarArtesMarciales();
		
		assertTrue(profugo.getTieneArtesMarciales());
		assertEquals(profugo.getNivelDeHabilidad(), Integer.valueOf(60));
		
	}

	@Test
	public void queUnProfugoPuedeHacerEntrenamientoDeEliteYnoPuedeSerNerviosoMas() {
		Cazador cazador = new CazadorRural(10);
		Profugo profugo = new Profugo("John", 80, 30, false);
		profugo.aplicarEntrenamientoElite();
		cazador.intimidar(profugo);
		assertTrue(profugo.getTieneEntrenamientoElite());
		assertFalse(profugo.getEsNervioso());
		
	}
	@Test
	public void queUnProfugoPuedeTenerProteccionLegalYSuInocenciaNoDisminuyeDe40() {
		Cazador cazador = new CazadorSigiloso(10);
		//									nivel de inocencia 40
		Profugo profugo = new Profugo("John", 40, 40, false);
		// limite en 40
		profugo.aplicarProteccionLegal();
		assertTrue(profugo.getTieneProteccionLegal());
		// El cazador Sigiloso al intimidar baja el nivel de inocencia en 5
		cazador.intimidar(profugo);
		Integer obtenido = profugo.getNivelDeInocencia();
		//Se mantiene en 40 la inocencia
		assertEquals(Integer.valueOf(40), obtenido);
		
	}
	
	@Test
	public void queUnProfugoPuedeHacerTodosLosEntrenamientosYAcumularHabilidades() {

		Profugo profugo = new Profugo("John", 20, 20, false);
		profugo.aplicarArtesMarciales();
		profugo.aplicarEntrenamientoElite();
		profugo.aplicarProteccionLegal();
		// El sigiloso reduce en 5 la habilidad al intimidar
		Cazador cazador = new CazadorSigiloso(5);
		// El rural lo vuelve nervioso
		Cazador cazador2 = new CazadorRural(10);
		cazador.intimidar(profugo);
		cazador2.intimidar(profugo);
		
		// deberia tener 35 de nivel de habilidad a pesar de que lo intimidamos despues de duplicarla
		assertEquals(Integer.valueOf(35), profugo.getNivelDeHabilidad());
		assertFalse(profugo.getEsNervioso());		
	}
	
}
