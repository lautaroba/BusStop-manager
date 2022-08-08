package Aplicacion;

import java.util.ArrayList;

import Dominio.Gestor;

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
		
		g.agregarParada(p1.n);
		g.agregarParada(p2.n);
		g.agregarParada(p3.n);
		g.agregarParada(p4.n);
		g.agregarParada(p5.n);
		g.agregarParada(p6.n);
		g.agregarParada(p7.n);
		g.agregarParada(p8.n);
		g.agregarParada(p9.n);
		g.agregarParada(p10.n);
		g.agregarParada(p11.n);
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
		Flecha f9 = new Flecha(p4, p10, 50);
		listaConexiones.add(f9);
		Flecha f10 = new Flecha(p5, p11, 40);
		listaConexiones.add(f10);
		Flecha f11 = new Flecha(p6, p7, 50);
		listaConexiones.add(f11);
		Flecha f12 = new Flecha(p6, p9, 40);
		listaConexiones.add(f12);
		Flecha f13 = new Flecha(p7, p8, 40);
		listaConexiones.add(f13);
		Flecha f14 = new Flecha(p8, p1, 120);
		listaConexiones.add(f14);
		Flecha f15 = new Flecha(p8, p9, 50);
		listaConexiones.add(f15);
		Flecha f16 = new Flecha(p9, p11, 100);
		listaConexiones.add(f16);
		Flecha f17 = new Flecha(p9, p3, 250);
		listaConexiones.add(f17);
		Flecha f18 = new Flecha(p10, p9, 40);
		listaConexiones.add(f18);
		Flecha f19 = new Flecha(p11, p1, 350);
		listaConexiones.add(f19);
		
		//for(Punto p : listaParadas) System.out.println("componente X: "+p.getX()+" componente Y: "+p.getY());
		new Programa("Trabajo Practico", listaParadas, listaConexiones, g);
		
		
	}

}
