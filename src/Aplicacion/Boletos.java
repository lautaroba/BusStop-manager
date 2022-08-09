package Aplicacion;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Dominio.Economica;
import Dominio.Gestor;
import Dominio.Linea;
import Dominio.Superior;
import Dominio.TipoServicio;

@SuppressWarnings("serial")

public class Boletos extends JPanel{
	
	Gestor g;
	public Mapa mapa;
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	private Linea seleccionada;
	
	public Boletos(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gestor) {
		
		this.mapa = m;
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.g = gestor;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.setBackground(Color.decode("#DEDEDE"));
		
		b1 = new JButton("Ruta mas corta");
		this.add(b1, new GBC(0,0).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b2 = new JButton("Ruta mas rapida");
		b2.setEnabled(true);
		this.add(b2, new GBC(0,1).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b3 = new JButton("Ruta mas barata");
		b3.setEnabled(false);
		this.add(b3, new GBC(0,2).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b4 = new JButton("Comprar");
		b4.setEnabled(false);
		this.add(b4, new GBC(0,3).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		
		SwingUtilities.invokeLater(new Runnable() {
	           public void run() {
//	               rutaMasCorta(b1);
//	               rutaMasRapida(b2);
//	               rutaMasBarata(b3);
//	               comprar(b4);
	           }
	       });
	}
	
	
//	private void Nuevo(JButton b) {
//		
//		b.addActionListener(e -> {
//			
//			
//				
//		}
//	}
//	
	
	
	
	
	
	
	
	
	
}
