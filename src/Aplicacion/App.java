package Aplicacion;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import Base_de_datos.BD;
import Dominio.Bus;
import Dominio.Economica;
import Dominio.Gestor;
import Dominio.Linea;
import Dominio.Superior;
import Dominio.TipoServicio;

public class App {

	public static Punto p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
	public static Flecha f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18;

	public static void main(String[] args) {

		ArrayList<Punto> listaParadas = new ArrayList<Punto>();
		ArrayList<Flecha> listaConexiones = new ArrayList<Flecha>();
		Gestor g = new Gestor();

//		Connection conn = BD.connect();
//		BD.crearTablas(conn);
//		BD.inicializarTablas(conn);

		cargarDatos(listaParadas, listaConexiones, g);

//		masRapidoCortoBaratoDistintos(g);
//		incidenteEnParada6(g);
//		muchosColectivos(g);
		
		new Programa("Trabajo Practico", listaParadas, listaConexiones, g);

	}

	public static void cargarDatos(ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor g) {
		p1 = new Punto(100, 200, "Piedras", 1);
		listaParadas.add(p1);
		p2 = new Punto(300, 200, "Lavalle", 2);
		listaParadas.add(p2);
		p3 = new Punto(600, 200, "Roberto Godoy", 3);
		listaParadas.add(p3);
		p4 = new Punto(450, 300, "Obispo Boneo", 4);
		listaParadas.add(p4);
		p5 = new Punto(230, 400, "Obispo Principe", 5);
		listaParadas.add(p5);
		p6 = new Punto(130, 400, "Marcial Candiotti", 6);
		listaParadas.add(p6);
		p7 = new Punto(100, 600, "General Paz", 7);
		listaParadas.add(p7);
		p8 = new Punto(300, 600, "Boulevard Galves", 8);
		listaParadas.add(p8);
		p9 = new Punto(450, 500, "Almirante Brown", 9);
		listaParadas.add(p9);
		p10 = new Punto(600, 600, "Alem", 10);
		listaParadas.add(p10);
		p11 = new Punto(600, 400, "Risso", 11);
		listaParadas.add(p11);

		g.agregarParada(p1.getNodo());
		g.agregarParada(p2.getNodo());
		g.agregarParada(p3.getNodo());
		g.agregarParada(p4.getNodo());
		g.agregarParada(p5.getNodo());
		g.agregarParada(p6.getNodo());
		g.agregarParada(p7.getNodo());
		g.agregarParada(p8.getNodo());
		g.agregarParada(p9.getNodo());
		g.agregarParada(p10.getNodo());
		g.agregarParada(p11.getNodo());
		// Declaración líneas precargadas

		f1 = new Flecha(p1, p2, 50);
		listaConexiones.add(f1);
		f2 = new Flecha(p1, p6, 70);
		listaConexiones.add(f2);
		f3 = new Flecha(p1, p7, 40);
		listaConexiones.add(f3);
		f4 = new Flecha(p2, p3, 100);
		listaConexiones.add(f4);
		f5 = new Flecha(p2, p5, 200);
		listaConexiones.add(f5);
		f6 = new Flecha(p3, p4, 30);
		listaConexiones.add(f6);
		f7 = new Flecha(p4, p6, 30);
		listaConexiones.add(f7);
		f8 = new Flecha(p4, p10, 50);
		listaConexiones.add(f8);
		f9 = new Flecha(p5, p11, 40);
		listaConexiones.add(f9);
		f10 = new Flecha(p6, p7, 50);
		listaConexiones.add(f10);
		f11 = new Flecha(p6, p9, 40);
		listaConexiones.add(f11);
		f12 = new Flecha(p7, p8, 40);
		listaConexiones.add(f12);
		f13 = new Flecha(p8, p1, 120);
		listaConexiones.add(f13);
		f14 = new Flecha(p8, p9, 50);
		listaConexiones.add(f14);
		f15 = new Flecha(p9, p11, 100);
		listaConexiones.add(f15);
		f16 = new Flecha(p9, p3, 250);
		listaConexiones.add(f16);
		f17 = new Flecha(p10, p9, 40);
		listaConexiones.add(f17);
		f18 = new Flecha(p11, p1, 350);
		listaConexiones.add(f18);

		g.agregarCamino(p1.getNodo(), p2.getNodo(), 50);
		g.agregarCamino(p1.getNodo(), p6.getNodo(), 70);
		g.agregarCamino(p1.getNodo(), p7.getNodo(), 40);
		g.agregarCamino(p2.getNodo(), p3.getNodo(), 100);
		g.agregarCamino(p2.getNodo(), p5.getNodo(), 200);
		g.agregarCamino(p3.getNodo(), p4.getNodo(), 30);
		g.agregarCamino(p4.getNodo(), p6.getNodo(), 30);
		g.agregarCamino(p4.getNodo(), p10.getNodo(), 50);
		g.agregarCamino(p5.getNodo(), p11.getNodo(), 40);
		g.agregarCamino(p6.getNodo(), p7.getNodo(), 50);
		g.agregarCamino(p6.getNodo(), p9.getNodo(), 40);
		g.agregarCamino(p7.getNodo(), p8.getNodo(), 40);
		g.agregarCamino(p8.getNodo(), p1.getNodo(), 120);
		g.agregarCamino(p8.getNodo(), p9.getNodo(), 50);
		g.agregarCamino(p9.getNodo(), p11.getNodo(), 100);
		g.agregarCamino(p9.getNodo(), p3.getNodo(), 250);
		g.agregarCamino(p10.getNodo(), p9.getNodo(), 40);
		g.agregarCamino(p11.getNodo(), p1.getNodo(), 350);

	}

	public static void muchosColectivos(Gestor g) {

		Linea sup = new Superior("a", Color.RED, 50, TipoServicio.AirConditioner, 10);
		Linea eco = new Economica("b", Color.BLUE, 100, 0.3, 2);

		sup.setBuses(new Bus(p1.getNodo(), p11.getNodo(), 1));
		sup.setBuses(new Bus(p11.getNodo(), p1.getNodo(), 2));
		sup.setBuses(new Bus(p1.getNodo(), p9.getNodo(), 3));
		sup.setBuses(new Bus(p9.getNodo(), p1.getNodo(), 4));

		eco.setBuses(new Bus(p1.getNodo(), p2.getNodo(), 5));
		eco.setBuses(new Bus(p2.getNodo(), p5.getNodo(), 6));
		eco.setBuses(new Bus(p5.getNodo(), p11.getNodo(), 7));
		eco.setBuses(new Bus(p11.getNodo(), p1.getNodo(), 8));
		eco.setBuses(new Bus(p1.getNodo(), p7.getNodo(), 9));
		eco.setBuses(new Bus(p7.getNodo(), p8.getNodo(), 10));
		eco.setBuses(new Bus(p8.getNodo(), p9.getNodo(), 11));
		eco.setBuses(new Bus(p9.getNodo(), p3.getNodo(), 12));
		eco.setBuses(new Bus(p3.getNodo(), p4.getNodo(), 13));
		eco.setBuses(new Bus(p4.getNodo(), p6.getNodo(), 9));

		g.agregarLinea(sup);
		g.agregarLinea(eco);
	}

	public static void incidenteEnParada6(Gestor g) {

		Linea sup = new Superior("a", Color.RED, 50, TipoServicio.AirConditioner, 10);

		sup.setBuses(new Bus(p11.getNodo(), p6.getNodo(), 1));

		g.agregarLinea(sup);
	}
	
	public static void masRapidoCortoBaratoDistintos(Gestor g) {
		
		Linea sup = new Superior("a", Color.RED, 50, TipoServicio.AirConditioner, 100);
		Linea eco = new Economica("b", Color.BLUE, 100, 0.3, 2);
		Linea sup2 = new Superior("c", Color.GREEN, 50, TipoServicio.AirConditioner, 1);

		sup.setBuses(new Bus(p1.getNodo(), p8.getNodo(), 1));
		sup.setBuses(new Bus(p8.getNodo(), p11.getNodo(), 2));
		
		sup2.setBuses(new Bus(p1.getNodo(), p11.getNodo(), 3));
		eco.setBuses(new Bus(p1.getNodo(), p11.getNodo(), 4));
		
		
		g.agregarLinea(sup);
		g.agregarLinea(sup2);
		g.agregarLinea(eco);
	}
	
}
