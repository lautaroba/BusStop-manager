package Dominio;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class JTest {

	Gestor g = new Gestor();

	Nodo p1 = new Nodo(1, "Piedras");
	Nodo p2 = new Nodo(2, "Lavalle");
	Nodo p3 = new Nodo(3, "Roberto Godoy");
	Nodo p4 = new Nodo(4, "Obispo Boneo");
	Nodo p5 = new Nodo(5, "Obispo Principe");
	Nodo p6 = new Nodo(6, "Marcial Candioti");
	Nodo p7 = new Nodo(7, "General Paz");
	Nodo p8 = new Nodo(8, "Boulevard Galves");
	Nodo p9 = new Nodo(9, "Avenida Almirante Brown");
	Nodo p10 = new Nodo(10, "Avenida Alejandro Alem");
	Nodo p11 = new Nodo(11, "General Risso");

	Linea sup = new Superior("Linea A", Color.RED, 50, TipoServicio.AirConditioner, 10);
	Linea eco = new Economica("Linea B", Color.BLUE, 100, 0.3, 2);

	Bus b1 = new Bus(p1, p11, 10);
	Bus b2 = new Bus(p3, p8, 20);
	Bus b3 = new Bus(p11, p1, 20);

	Bus b4 = new Bus(p1, p4, 2);
	Bus b5 = new Bus(p4, p8, 3);
	Bus b6 = new Bus(p8, p2, 4);
	Bus b7 = new Bus(p2, p5, 5);
	Bus b8 = new Bus(p5, p6, 5);

	@BeforeClass
	public void init() {

		g.agregarParada(p1);
		g.agregarParada(p2);
		g.agregarParada(p3);
		g.agregarParada(p4);
		g.agregarParada(p5);
		g.agregarParada(p6);
		g.agregarParada(p7);
		g.agregarParada(p8);
		g.agregarParada(p9);
		g.agregarParada(p10);
		g.agregarParada(p11);

		g.agregarLinea(sup);
		g.agregarLinea(eco);

		g.agregarCamino(p1, p2, 5);
		g.agregarCamino(p1, p6, 7);
		g.agregarCamino(p1, p7, 4);
		g.agregarCamino(p2, p3, 10);
		g.agregarCamino(p2, p5, 20);
		g.agregarCamino(p3, p4, 3);
		g.agregarCamino(p4, p6, 3);
		g.agregarCamino(p4, p10, 5);
		g.agregarCamino(p5, p11, 4);
		g.agregarCamino(p6, p7, 5);
		g.agregarCamino(p6, p9, 4);
		g.agregarCamino(p7, p8, 4);
		g.agregarCamino(p8, p1, 12);
		g.agregarCamino(p8, p9, 5);
		g.agregarCamino(p9, p11, 10);
		g.agregarCamino(p9, p3, 25);
		g.agregarCamino(p10, p9, 4);
		g.agregarCamino(p11, p1, 35);

		sup.setBuses(b1);
		sup.setBuses(b2);
		sup.setBuses(b3);
		eco.setBuses(b4);
		eco.setBuses(b5);
		eco.setBuses(b6);
		eco.setBuses(b7);
		eco.setBuses(b8);
	}

	@Test
	void testTodosLosCaminosEntre2Paradas() {

		ArrayList<Ruta> a = g.buscarTodasLosCaminos(p1, p11);
		// assertEquals(a.size() , 7);

		for (Ruta r : a)
			r.Imprimir();

		System.out.println("----------- Termina testTodosLosCaminosEntre2Paradas ---------------");
	}

	@Test
	void testBuscarTodasLasRutasDeUnBus() {

		ArrayList<Ruta> a = g.buscarTodasLasRutasDeUnBus(p1, p11, b1);

		for (Ruta r : a)
			r.Imprimir();

		System.out.println("----------- Termina testBuscarTodasLasRutasDeUnBus ---------------");
	}

	@Test
	void testtrayectoTotalDeUnaLinea() {

		ArrayList<Ruta> a = g.trayectoTotalDeUnaLinea(eco);

		for (Ruta r : a)
			r.Imprimir();

		System.out.println("----------- Termina testtrayectoTotalDeUnaLinea ---------------");
	}

	@Test
	void testtodasLasRutasEntreDosParadas() {

		ArrayList<List<Ruta>> a = g.todasLasRutasEntreDosParadas(p1, p11);

		for (List<Ruta> rut : a) {
			System.out.println("- - - Nueva Ruta - - -");
			for (Ruta r : rut)
				r.Imprimir();
		}

		System.out.println("----------- Termina testtodasLasRutasEntreDosParadas ---------------");
	}

	@Test
	void testrutaMasCorta() {

		ArrayList<Ruta> a = g.rutaMasCorta(p1, p11);

		for (Ruta r : a)
			r.Imprimir();

		System.out.println("----------- Termina testrutaMasCorta ---------------");
	}

	@Test
	void testrutaMasRapida() {

		ArrayList<Ruta> a = g.rutaMasCorta(p1, p11);

		for (Ruta r : a)
			r.Imprimir();

		System.out.println("----------- Termina testrutaMasCorta ---------------");
	}

	@Test
	void testrutaMasBarata() {

		ArrayList<Ruta> a = g.rutaMasBarata(p1, p11);

		for (Ruta r : a)
			r.Imprimir();

		System.out.println("----------- Termina rutaMasBarata ---------------");
	}

}
