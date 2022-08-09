package Aplicacion;

import java.awt.Color;
import java.util.ArrayList;

import Dominio.Bus;
import Dominio.Economica;
import Dominio.Gestor;
import Dominio.Linea;
import Dominio.Nodo;
import Dominio.Superior;
import Dominio.TipoServicio;

public class App {

	public static void main(String[] args) {
		
		ArrayList<Punto> listaParadas = new ArrayList<Punto>();
		ArrayList<Flecha> listaConexiones = new ArrayList<Flecha>();
		Gestor g = new Gestor();
		// Declaración puntos precargados
		
		Punto p1 = new Punto(100, 200, "Piedras", 1);
		listaParadas.add(p1);
		Punto p2 = new Punto(300, 200, "Lavalle", 2);
		listaParadas.add(p2);
		Punto p3 = new Punto(600, 200, "Roberto Godoy", 3);
		listaParadas.add(p3);
		Punto p4 = new Punto(450, 300, "Obispo Boneo", 4);
		listaParadas.add(p4);
		Punto p5 = new Punto(230, 400, "Obispo Principe", 5);
		listaParadas.add(p5);
		Punto p6 = new Punto(130, 400, "Marcial Candiotti", 6);
		listaParadas.add(p6);
		Punto p7 = new Punto(100, 600, "General Paz", 7);
		listaParadas.add(p7);
		Punto p8 = new Punto(300, 600, "Boulevard Galves", 8);
		listaParadas.add(p8);
		Punto p9 = new Punto(450, 500, "Avenida Almirante Brown", 9);
		listaParadas.add(p9);
		Punto p10 = new Punto(600, 600, "Avenida Alejando Alem", 10);
		listaParadas.add(p10);
		Punto p11 = new Punto(600, 400, "General Risso", 11);
		listaParadas.add(p11);
		
		Nodo n1 = new Nodo(1, "Piedras");
		Nodo n2 = new Nodo(2, "Lavalle");
		Nodo n3 = new Nodo(3, "Roberto Godoy");
		Nodo n4 = new Nodo(4, "Obispo Boneo");
		Nodo n5 = new Nodo(5, "Obispo Principe");
		Nodo n6 = new Nodo(6, "Marcial Candioti");
		Nodo n7 = new Nodo(7, "General Paz");
		Nodo n8 = new Nodo(8, "Boulevard Galves");
		Nodo n9 = new Nodo(9, "Avenida Almirante Brown");
		Nodo n10 = new Nodo(10, "Avenida Alejandro Alem");
		Nodo n11 = new Nodo(11, "General Risso"); 
		
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
		
		Flecha f1 = new Flecha(p1, p2, 50); 
		listaConexiones.add(f1);
		Flecha f2 = new Flecha(p1, p6, 70);
		listaConexiones.add(f2);
		Flecha f3 = new Flecha(p1, p7, 40);
		listaConexiones.add(f3);
		Flecha f4 = new Flecha(p2, p3, 100);
		listaConexiones.add(f4);
		Flecha f5 = new Flecha(p2, p5, 200);
		listaConexiones.add(f5);
		Flecha f6 = new Flecha(p3, p4, 30);
		listaConexiones.add(f6);
		Flecha f7 = new Flecha(p4, p6, 30);
		listaConexiones.add(f7); 
		Flecha f8 = new Flecha(p4, p10, 50);
		listaConexiones.add(f8);
		Flecha f9 = new Flecha(p5, p11, 40);
		listaConexiones.add(f9);
		Flecha f10 = new Flecha(p6, p7, 50);
		listaConexiones.add(f10);
		Flecha f11 = new Flecha(p6, p9, 40);
		listaConexiones.add(f11);
		Flecha f12 = new Flecha(p7, p8, 40);
		listaConexiones.add(f12);
		Flecha f13 = new Flecha(p8, p1, 120);
		listaConexiones.add(f13);
		Flecha f14 = new Flecha(p8, p9, 50);
		listaConexiones.add(f14);
		Flecha f15 = new Flecha(p9, p11, 100);
		listaConexiones.add(f15);
		Flecha f16 = new Flecha(p9, p3, 250);
		listaConexiones.add(f16);
		Flecha f17 = new Flecha(p10, p9, 40);
		listaConexiones.add(f17);
		Flecha f18 = new Flecha(p11, p1, 350);
		listaConexiones.add(f18);
		
		g.agregarCamino(p1.getNodo(),p2.getNodo(),50);
		g.agregarCamino(p1.getNodo(),p6.getNodo(),70);
		g.agregarCamino(p1.getNodo(),p7.getNodo(),40);
		g.agregarCamino(p2.getNodo(),p3.getNodo(),100);
		g.agregarCamino(p2.getNodo(),p5.getNodo(),200);
		g.agregarCamino(p3.getNodo(),p4.getNodo(),30);
		g.agregarCamino(p4.getNodo(),p6.getNodo(),30);
		g.agregarCamino(p4.getNodo(),p10.getNodo(),50);
		g.agregarCamino(p5.getNodo(),p11.getNodo(),40);
		g.agregarCamino(p6.getNodo(),p7.getNodo(),50);
		g.agregarCamino(p6.getNodo(),p9.getNodo(),40);
		g.agregarCamino(p7.getNodo(),p8.getNodo(),40);
		g.agregarCamino(p8.getNodo(),p1.getNodo(),120);
		g.agregarCamino(p8.getNodo(),p9.getNodo(),50);
		g.agregarCamino(p9.getNodo(),p11.getNodo(),100);
		g.agregarCamino(p9.getNodo(),p3.getNodo(),250);
		g.agregarCamino(p10.getNodo(),p9.getNodo(),40);
		g.agregarCamino(p11.getNodo(),p1.getNodo(),350);
		
		Linea sup = new Superior("a", Color.RED, 50, TipoServicio.AirConditioner, 100);
		Linea eco = new Economica("b", Color.BLUE, 100, 0.3, 2);
	
		sup.setBuses(new Bus(p1.getNodo(), p5.getNodo(), 10));
		eco.setBuses(new Bus(p5.getNodo(), p11.getNodo(), 2));
		eco.setBuses(new Bus(p1.getNodo(), p11.getNodo(), 3));
		
		g.agregarLinea(sup);
		g.agregarLinea(eco);
		
		//for(Punto p : listaParadas) System.out.println("componente X: "+p.getX()+" componente Y: "+p.getY());
		new Programa("Trabajo Practico", listaParadas, listaConexiones, g);
		
		
	}

}
