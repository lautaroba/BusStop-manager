package Aplicacion;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Dominio.Gestor;

@SuppressWarnings("serial")
public class Mapa extends JPanel {

	private Grafo g;

	public Mapa(ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gr) {

		this.setBackground(Color.decode("#DEDEDE"));
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
		g = new Grafo(getGraphics(), listaParadas, listaConexiones);

		Runnable r = () -> {
			try {
				Thread.sleep(200);
				g.dibujarParadas(getGraphics(), listaParadas, listaConexiones);
			} catch (InterruptedException e1) {
				System.out.println("No se pudo cargar el mapa");
			}
		};

		Thread t1 = new Thread(r);
		t1.start();
		g.validate();
		this.add(g, new GBC(0, 2).setWeight(0.9, 0.9).setFill(GBC.BOTH));
	}

	public Grafo getGrafo() {
		return g;
	}

	public void dibujarGrafo(ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones) {
		g.dibujarParadas(getGraphics(), listaParadas, listaConexiones);
	}

}
