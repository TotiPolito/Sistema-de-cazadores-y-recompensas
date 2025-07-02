package ar.edu.unlam.pb2.tp;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Agencia;
import ar.edu.unlam.pb2.dominio.Cazador;
import ar.edu.unlam.pb2.dominio.CazadorRural;
import ar.edu.unlam.pb2.dominio.CazadorUrbano;
import ar.edu.unlam.pb2.dominio.Profugo;
import ar.edu.unlam.pb2.dominio.Zona;

public class AgenciaTest {

	@Test
	public void queSePuedaRegistrarUnCazadorEnLaAgencia() {
		Agencia agencia = new Agencia();
		Cazador cazador = new CazadorUrbano(40);
		
		agencia.registrarCazador(cazador);
		
		assertTrue(agencia.getCazadores().contains(cazador));
	}
	
    @Test
    public void queAlCrearLaAgenciaNoHayProfugosCapturados() {
        Agencia agencia = new Agencia();
        assertTrue(agencia.obtenerLosProfugosCapturados().isEmpty());
    }
    
    @Test
    public void quePuedoObtenerTodosLosProfugosCapturadosPorLosCazadores() {
    	
    	//Preparacion
    	Agencia agencia = new Agencia();
        Cazador cazador = new CazadorUrbano(100); // creado con suficiente experiencia para capturar
        
        Profugo profugo1 = new Profugo("Luis", 10, 20, false);
        Profugo profugo2 = new Profugo("Pedro", 5, 30, false);
        Zona zona = new Zona("Ciudad");
        zona.agregarProfugo(profugo1);
        zona.agregarProfugo(profugo2);
        cazador.capturar(zona);

        agencia.registrarCazador(cazador);

        Set<Profugo> capturados = agencia.obtenerLosProfugosCapturados();

        assertEquals(2, capturados.size());
        assertTrue(capturados.contains(profugo1));
        assertTrue(capturados.contains(profugo2));
    }

    @Test
    public void quePuedoObtenerElProfugoMasHabilDeLosCapturados() {
    	
    	//Preparacion
    	Agencia agencia = new Agencia();
        Cazador cazador = new CazadorUrbano(100);
        
        Profugo profugo1 = new Profugo("Luis", 10, 20, false);
        Profugo profugo2 = new Profugo("Pedro", 5, 30, false); // mas Habil seria pedro
        Zona zona = new Zona("Ciudad");
        zona.agregarProfugo(profugo1);
        zona.agregarProfugo(profugo2);
        cazador.capturar(zona);

        agencia.registrarCazador(cazador);

        Profugo masHabil = agencia.obtenerProfugoMasHabilCapturado();
        assertEquals("Pedro", masHabil.getNombre());
    }
    
    @Test
    public void quePuedoObtenerElCazadorConMasCapturasRealizadas() {
        
    	Agencia agencia = new Agencia();
        Cazador cazador1 = new CazadorUrbano(100);
        Cazador cazador2 = new CazadorRural(100); // suponemos que captura solo 1
        Profugo p1 = new Profugo("A", 10, 20, false); // capturable por urbano
        Profugo p2 = new Profugo("B", 5, 30, false);
        Profugo p3 = new Profugo("C", 8, 25, true); // capturable por rural
        
        Zona zona1 = new Zona("Ciudad");
        zona1.agregarProfugo(p1);
        zona1.agregarProfugo(p2);
        
        Zona zona2 = new Zona("Campo");
        zona2.agregarProfugo(p3);

        cazador1.capturar(zona1); // captura 2
        cazador2.capturar(zona2); // captura 1

        agencia.registrarCazador(cazador1);
        agencia.registrarCazador(cazador2);

        Cazador cazadorconMasCapturas = agencia.obtenerCazadorConMasCapturas();

        assertEquals(cazador1, cazadorconMasCapturas);
    }
    
}
