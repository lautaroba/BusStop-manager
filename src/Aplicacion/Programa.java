package Aplicacion;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Dominio.Gestor;

@SuppressWarnings("serial")
public class Programa extends JFrame {

	public Programa(String s, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor g) {

		this.setTitle(s);
		this.setLayout(new GridBagLayout());
		this.setVisible(true);

		Mapa m = new Mapa(listaParadas, listaConexiones, g);
		this.add(m, new GBC(1, 0).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));

		Opciones op = new Opciones(m, listaParadas, listaConexiones, g);
		this.add(op, new GBC(0, 0).setWeight(0.1, 0.1).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));

		this.setResizable(false);
		this.setSize(1050, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
