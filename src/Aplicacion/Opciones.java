package Aplicacion;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import Dominio.Gestor;

@SuppressWarnings("serial")
public class Opciones extends JTabbedPane{
	
	public Opciones(Mapa Mapa, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor g) {
		
		this.setBackground(Color.decode("#FFFFFF"));
		this.setVisible(true);
		
		Parada p = new Parada(Mapa, listaParadas, listaConexiones, g);
		this.add(p, "Paradas");
		
		LineaInterfaz l = new LineaInterfaz(Mapa, listaParadas, listaConexiones, g);
		this.add(l, "Lineas");
		
		Incidentes i = new Incidentes(Mapa, listaParadas, listaConexiones);
		this.add(i, "Incidentes");
		
		Boletos b = new Boletos(Mapa, listaParadas, listaConexiones, g);
		this.add(b, "Boletos");
		
	}
	
	
}
