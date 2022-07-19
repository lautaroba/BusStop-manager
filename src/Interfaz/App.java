package Interfaz;

import java.awt.*;
import javax.swing.*;

public class App {
	
	public static void main(String[] args) {
		
		JFrame Programa = new JFrame("Trabajo Practico DIED2022");
		Programa.setLayout(new GridBagLayout());
		Programa.setVisible(true);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		JPanel Mapa = new JPanel();
		Mapa.setBackground(Color.decode("#7EFF8C"));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.7;
		gbc.weighty = 0.7;
		gbc.fill = GridBagConstraints.BOTH;
		Programa.add(Mapa, gbc);
		JLabel Etiqueta1 = new JLabel("Ciudad de Santa Fe");
		gbc.anchor = GridBagConstraints.NORTHEAST;
		Mapa.add(Etiqueta1, gbc);
		
		
		JPanel Opciones = new JPanel();
		Opciones.setLayout(new GridBagLayout());
		Opciones.setBackground(Color.decode("#4B4B4B"));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.3;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.BOTH;
		Programa.add(Opciones, gbc);
		
		
		JPanel Paradas = new JPanel();
		Paradas.setLayout(new GridBagLayout());
		Paradas.setBackground(Color.decode("#DEDEDE"));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.3;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.BOTH;
		Opciones.add(Paradas, gbc);
		JLabel Etiqueta2 = new JLabel("Paradas");
		gbc.weightx=0.1;
		gbc.weighty=0.1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.fill = GridBagConstraints.NONE;
		Paradas.add(Etiqueta2, gbc);
		JButton Boton1 = new JButton("Nuevo");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		Paradas.add(Boton1, gbc);
		
		
		JPanel Lineas = new JPanel();
		Lineas.setBackground(Color.decode("#DEDEDE"));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.3;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.BOTH;
		Opciones.add(Lineas, gbc);
		JLabel Etiqueta3 = new JLabel("Lineas");
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		Lineas.add(Etiqueta3, gbc);
		
		
		JPanel Incidentes = new JPanel();
		Incidentes.setBackground(Color.decode("#DEDEDE"));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.3;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.BOTH;
		Opciones.add(Incidentes, gbc);
		JLabel Etiqueta4 = new JLabel("Incidentes");
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		Incidentes.add(Etiqueta4, gbc);
		
		Programa.pack();
		Programa.setSize(800, 500);
		Programa.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	
	
	
}
