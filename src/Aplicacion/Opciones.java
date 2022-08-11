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
		
		IncidentesInterfaz i = new IncidentesInterfaz(Mapa, listaParadas, listaConexiones, g);
		this.add(i, "Incidentes");
		
	}
}
