package Aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Dominio.Gestor;
import Dominio.Nodo;

@SuppressWarnings("serial")
public class Parada extends JPanel {
	
	Gestor g;
	public Mapa mapa;
	JButton b1,b2,b3,b4;
	static int valorX = 100;
	static int valorY = 0;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	
	public Parada(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gestor) {
		
		this.mapa = m;
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.g = gestor;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.setBackground(Color.decode("#DEDEDE"));
		
		b1 = new JButton("Nuevo");
		this.add(b1);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b2 = new JButton("Buscar");
		b2.setEnabled(false);
		this.add(b2);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b3 = new JButton("Eliminar");
		b3.setEnabled(false);
		this.add(b3);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b4 = new JButton("Modificar");
		b4.setEnabled(false);
		this.add(b4);
		this.add(Box.createRigidArea(new Dimension(0,5)));
	
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Nuevo(b1);
                Buscar(b2);
                Eliminar(b3);
                Modificar(b4);
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
								|| numParada.getText().equals(Integer.toString(p.getNumeroParada()))) {
								
								p.registrarParada(true);
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
						if(nomParada.getText().equalsIgnoreCase(p.getNombreParada()) || numParada.getText().equals(Integer.toString(p.getNumeroParada()))) {
							p.registrarParada(false);
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
						if((nomParada.getText().equalsIgnoreCase(p.getNombreParada()) || numParada.getText().equals(Integer.toString(p.getNumeroParada())))
								&& p.getEstadoParada() == true) {
							JFrame temp1 = new JFrame("Datos de la parada");
							temp1.setVisible(true);
							temp1.setLayout(new GridLayout(3,2,10,10));
							temp1.setSize(300,100);
							temp1.setResizable(false);
							temp1.add(new JLabel ("Nombre parada: "+p.getNombreParada()));
							temp1.add(new JLabel ("Numero parada: "+p.getNumeroParada()));
						//	temp1.dispose();
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
	
}
