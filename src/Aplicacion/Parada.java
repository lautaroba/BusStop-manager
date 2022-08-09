package Aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Dominio.Gestor;
import Dominio.Linea;
import Dominio.Nodo;
import Dominio.Ruta;

@SuppressWarnings("serial")
public class Parada extends JPanel {
	
	Gestor g;
	public Mapa mapa;
	JButton b1,b2,b3,b4,b5,b6,b7,b8;
	static int valorX = 100;
	static int valorY = 0;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	private Punto paradaInicial;
	private Punto paradaFinal;
	
	public Parada(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gestor) {
		
		this.mapa = m;
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.g = gestor;
		this.paradaFinal=null;
		this.paradaInicial=null;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.setBackground(Color.decode("#DEDEDE"));
		
		b1 = new JButton("Nuevo");
		this.add(b1, new GBC(0,0).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b2 = new JButton("Buscar");
		b2.setEnabled(true);
		this.add(b2, new GBC(0,1).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b3 = new JButton("Eliminar");
		b3.setEnabled(false);
		this.add(b3, new GBC(0,2).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b4 = new JButton("Modificar");
		b4.setEnabled(false);
		this.add(b4, new GBC(0,3).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b5 = new JButton("Ruta mas corta");
		b5.setEnabled(false);
		this.add(b5, new GBC(0,4).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b6 = new JButton("Ruta mas rapida");
		b6.setEnabled(false);
		this.add(b6, new GBC(0,5).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b7 = new JButton("Ruta mas barata");
		b7.setEnabled(false);
		this.add(b7, new GBC(0,6).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b8 = new JButton("Comprar pasaje");
		b8.setEnabled(false);
		this.add(b8, new GBC(0,7).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));

		
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Nuevo(b1);
                Buscar(b2);
                Eliminar(b3);
                Modificar(b4);
	            rutaMasCorta(b5);
	            rutaMasRapida(b6);
	            rutaMasBarata(b7);
            }
        });
	}

	private void Nuevo(JButton b) {
		
		b.addActionListener(e -> {
			
				JFrame Temporal = new JFrame("Registrar nueva parada");
				Temporal.setVisible(true);
				Temporal.setLayout(new GridLayout(3,2, 10, 10));
				Temporal.setSize(400,150);
				Temporal.setResizable(false);
				
				Temporal.add(new JLabel("Ingrese nombre de la parada: "));
				JTextField nomParada = new JTextField();
				Temporal.add(nomParada);
				Temporal.add(new JLabel("Ingrese numero de la parada: "));
				JTextField numParada = new JTextField();
				Temporal.add(numParada);
				JButton Cancelar = new JButton("Cancelar");
				Temporal.add(Cancelar);
				JButton Aceptar = new JButton("Aceptar");
				Temporal.add(Aceptar);
				
				Aceptar.addActionListener(i -> {
					
					if(Aceptar.isEnabled()) {
						
						for(Punto p : listaParadas) {
							if(nomParada.getText().equalsIgnoreCase(p.getNombreParada()) 
								|| Integer.parseInt(numParada.getText()) == p.getNumeroParada()) {
								p.setColor(Color.BLACK);
								p.getNodo().setParada(true);
								b2.setEnabled(true);
								b3.setEnabled(true);
								mapa.dibujarGrafo(listaParadas, listaConexiones);
							}
							Temporal.dispose();
						}
					}
				});
				
				Cancelar.addActionListener(i -> {
					if(Cancelar.isEnabled()) {
						// modificar parada
						Temporal.dispose();
					}
				});
			});
	}
	
	private void Modificar(JButton b) {
		
		b.addActionListener(e -> {
			JFrame Temporal = new JFrame("Modificar Parada");
			Temporal.setVisible(true);
			Temporal.setLayout(new GridLayout(3,2, 10, 10));
			Temporal.setSize(400,150);
			Temporal.setResizable(false);
			
			Temporal.add(new JLabel("Ingrese un nombre nuevo: "));
			Temporal.add(new JTextField());
			Temporal.add(new JLabel("Ingrese un número nuevo: "));
			Temporal.add(new JTextField());
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			// FALTA CAPTURAR EL TEXTO
			Aceptar.addActionListener(i -> {
				if(Aceptar.isEnabled()) {
					
					// hacer modificar, que cambie numero o nombre de la calle
					
					b.setEnabled(false);
					b3.setEnabled(false);
					Temporal.dispose();
				}
			});
			
			Cancelar.addActionListener(i -> {
				if(Cancelar.isEnabled()) {
					Temporal.dispose();
				}
			});
			
		});
	}
	
	private void Eliminar(JButton b) {
		
		b.addActionListener(e -> {
			JFrame Temporal = new JFrame("Eliminar parada");
			Temporal.setVisible(true);
			Temporal.setLayout(new GridLayout(3,2, 10, 10));
			Temporal.setSize(400,150);
			Temporal.setResizable(false);
			
			Temporal.add(new JLabel("Ingrese nombre de la parada: "));
			JTextField nomParada = new JTextField();
			Temporal.add(nomParada);
			Temporal.add(new JLabel("Ingrese numero de la parada: "));
			JTextField numParada = new JTextField();
			Temporal.add(numParada);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			
			
			Aceptar.addActionListener(i -> {
				if(Aceptar.isEnabled()) {
					for(Punto p : listaParadas) {
						if(nomParada.getText().equalsIgnoreCase(p.getNombreParada()) || 
							Integer.parseInt(numParada.getText()) == p.getNumeroParada()) {
							p.setColor(Color.GRAY);
							p.getNodo().setParada(false);
							mapa.dibujarGrafo(listaParadas, listaConexiones);
						}
					}
					Temporal.dispose();
				}
			});
			
			Cancelar.addActionListener(i -> {
				if(Cancelar.isEnabled()) {
					// modificar parada
					Temporal.dispose();
				}
			});
		});
	}
	
	private void Buscar(JButton b) {
		
		b.addActionListener(e -> {
			JFrame Temporal = new JFrame("Buscar parada");
			Temporal.setVisible(true);
			Temporal.setLayout(new GridLayout(3,2, 10, 10));
			Temporal.setSize(400,150);
			Temporal.setResizable(false);
			

			Temporal.add(new JLabel("Ingrese nombre de la parada: "));
			JTextField nomParada = new JTextField();
			Temporal.add(nomParada);
			Temporal.add(new JLabel("Ingrese numero de la parada: "));
			JTextField numParada = new JTextField();
			Temporal.add(numParada);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			
			
			Aceptar.addActionListener(i -> {
				if(Aceptar.isEnabled()) {
					for(Punto p : listaParadas) {
						if( nomParada.getText().equalsIgnoreCase(p.getNombreParada()) || 
							Integer.parseInt(numParada.getText()) == p.getNumeroParada() && 
							p.getNodo().esParada()) {
							if(paradaInicial == null) { 
								paradaInicial = p;
								p.setColor(Color.YELLOW);
								b4.setEnabled(true);
							}
							else if(paradaFinal == null) {
								paradaFinal = p;
								p.setColor(Color.YELLOW);
								b4.setEnabled(false);
								b5.setEnabled(true);
								b6.setEnabled(true);
								b7.setEnabled(true);
							}
							mapa.dibujarGrafo(listaParadas, listaConexiones);
						}
					}
					Temporal.dispose();
				}
			});
			
			Cancelar.addActionListener(i -> {
				if(Cancelar.isEnabled()) {
					// modificar parada
					Temporal.dispose();
				}
			});		});
	}
	
	private void rutaMasRapida(JButton b) {
		
		b.addActionListener(e -> {
			
			ArrayList<Ruta> a;
			a = g.rutaMasRapida(paradaInicial.getNodo(), paradaFinal.getNodo());
			
			for(Ruta r : a) {
				ArrayList<Nodo> p = r.getParadas();
				if(!p.isEmpty()) {
		            Nodo Actual = p.get(0);
		            for(Nodo i : p) {
		                if(!i.equals(Actual)) {
		                	Linea l = g.getLinea(r.getBus());
		                	for(Flecha f : listaConexiones) {
		                		if(f.getPuntoInicio().getNodo().equals(Actual) && f.getPuntoFinal().getNodo().equals(i))
		                			f.setColor(l.getColor());
		                	}
		                Actual = i;
		            	}
		            }
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
		});
	}
	
	private void rutaMasCorta(JButton b) {
		
		b.addActionListener(e -> {
			
			ArrayList<Ruta> a;
			a = g.rutaMasCorta(paradaInicial.getNodo(), paradaFinal.getNodo());
			
			for(Ruta r : a) {
				ArrayList<Nodo> p = r.getParadas();
				if(!p.isEmpty()) {
		            Nodo Actual = p.get(0);
		            for(Nodo i : p) {
		                if(!i.equals(Actual)) {
		                	Linea l = g.getLinea(r.getBus());
		                	for(Flecha f : listaConexiones) {
		                		if(f.getPuntoInicio().getNodo().equals(Actual) && f.getPuntoFinal().getNodo().equals(i))
		                			f.setColor(l.getColor());
		                	}
		                Actual = i;
		            	}
		            }
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
		});
	}
	
	private void rutaMasBarata(JButton b) {
		
		b.addActionListener(e -> {
			
			ArrayList<Ruta> a;
			a = g.rutaMasBarata(paradaInicial.getNodo(), paradaFinal.getNodo());
			
			for(Ruta r : a) {
				ArrayList<Nodo> p = r.getParadas();
				if(!p.isEmpty()) {
		            Nodo Actual = p.get(0);
		            for(Nodo i : p) {
		                if(!i.equals(Actual)) {
		                	Linea l = g.getLinea(r.getBus());
		                	for(Flecha f : listaConexiones) {
		                		if(f.getPuntoInicio().getNodo().equals(Actual) && f.getPuntoFinal().getNodo().equals(i))
		                			f.setColor(l.getColor());
		                	}
		                Actual = i;
		            	}
		            }
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
		});
	}
	
}
