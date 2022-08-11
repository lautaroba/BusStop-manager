package Aplicacion;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Dominio.Bus;
import Dominio.Gestor;
import Dominio.Linea;
import Dominio.Nodo;
import Dominio.Ruta;

@SuppressWarnings("serial")
public class Parada extends JPanel {

	Gestor g;
	public Mapa mapa;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
	static int valorX = 100;
	static int valorY = 0;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	private Punto paradaInicial;
	private Punto paradaFinal;
	private ArrayList<Ruta> viajeSeleccionado;
	
	public Parada(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gestor) {

		this.mapa = m;
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.g = gestor;
		this.paradaFinal = null;
		this.paradaInicial = null;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setBackground(Color.decode("#DEDEDE"));

		b1 = new JButton("Nuevo");
		this.add(b1, new GBC(0, 0).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b2 = new JButton("Buscar");
		b2.setEnabled(true);
		this.add(b2, new GBC(0, 1).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b3 = new JButton("Eliminar");
		b3.setEnabled(false);
		this.add(b3, new GBC(0, 2).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b4 = new JButton("Modificar");
		b4.setEnabled(false);
		this.add(b4, new GBC(0, 3).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b5 = new JButton("Ruta mas corta");
		b5.setEnabled(false);
		this.add(b5, new GBC(0, 4).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b6 = new JButton("Ruta mas rapida");
		b6.setEnabled(false);
		this.add(b6, new GBC(0, 5).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b7 = new JButton("Ruta mas barata");
		b7.setEnabled(false);
		this.add(b7, new GBC(0, 6).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b8 = new JButton("Comprar pasaje");
		b8.setEnabled(false);
		this.add(b8, new GBC(0, 7).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b9 = new JButton("Limpiar");
		this.add(b9, new GBC(0, 8).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Nuevo(b1);
				Buscar(b2);
				Eliminar(b3);
				Modificar(b4);
				rutaMasCorta(b5);
				rutaMasRapida(b6);
				rutaMasBarata(b7);
				comprarPasaje(b8);
				limpiarPrograma(b9);
			}
		});
	}

	private void Nuevo(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Agregar parada");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(3, 2, 10, 10));

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
				boolean bandera = true;
				if (Aceptar.isEnabled()) {
					for (Punto p : listaParadas) {
						if (nomParada.getText().equalsIgnoreCase(p.getNombreParada())
								|| Integer.parseInt(numParada.getText()) == p.getNumeroParada()) {
							p.setColor(Color.BLACK);
							p.getNodo().setParada(true);
							b2.setEnabled(true);
							b3.setEnabled(true);
							mapa.dibujarGrafo(listaParadas, listaConexiones);
							bandera = false;
						}
					}
					if (bandera)
						JOptionPane.showMessageDialog(null, "No se pudo agregar la parada");
					Temp.dispose();
				}
			});

			Cancelar.addActionListener(i -> {
				if (Cancelar.isEnabled())
					Temp.dispose();
			});
			Temp.pack();
		});
	}

	private void Buscar(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Buscar parada");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(3, 2, 10, 10));

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
				if (Aceptar.isEnabled()) {
					int bandera = 0;
					for (Punto p : listaParadas) {
						if (nomParada.getText().equalsIgnoreCase(p.getNombreParada())
								|| Integer.parseInt(numParada.getText()) == p.getNumeroParada()
										&& p.getNodo().esParada() && p.getNodo().getEstado()) {
							if (paradaInicial == null) {
								paradaInicial = p;
								p.setColor(Color.YELLOW);
								b4.setEnabled(true);
								bandera = 1;
							} 
							else if (paradaFinal == null) {
								if(paradaInicial.equals(p)) {
									bandera = 2;
								}
								else {
								paradaFinal = p;
								p.setColor(Color.YELLOW);
								b4.setEnabled(false);
								b5.setEnabled(true);
								b6.setEnabled(true);
								b7.setEnabled(true);
								bandera = 1;
								}
							}
							mapa.dibujarGrafo(listaParadas, listaConexiones);
						}
					}
					if(bandera == 0) JOptionPane.showMessageDialog(null, "No se ha encontrado la parada");
					else if (bandera == 2) JOptionPane.showMessageDialog(null, "Debe seleccionar una parada distinta a la primera");
					Temp.dispose();
				}
			});

			Cancelar.addActionListener(i -> {
				if (Cancelar.isEnabled()) {
					Temp.dispose();
				}
			});
			Temp.pack();
		});
	}

	private void Eliminar(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Eliminar parada");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(3, 2, 10, 10));

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
				if (Aceptar.isEnabled()) {
					int bandera = 0;
					for (Punto p : listaParadas) {
						if (nomParada.getText().equalsIgnoreCase(p.getNombreParada())
								|| Integer.parseInt(numParada.getText()) == p.getNumeroParada()) {
							p.setColor(Color.GRAY);
							p.getNodo().setParada(false);
							bandera = 1;
							mapa.dibujarGrafo(listaParadas, listaConexiones);
						}
					}
					if(bandera == 0) JOptionPane.showMessageDialog(null, "No se ha podido eliminar la parada, ya que no se ha encontrado");
					else if(bandera == 1) JOptionPane.showMessageDialog(null, "Parada eliminada con éxito");
					Temp.dispose();
				}
			});

			Cancelar.addActionListener(i -> {
				if (Cancelar.isEnabled()) {
					Temp.dispose();
				}
			});
			Temp.pack();
		});
	}

	private void Modificar(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Modificar parada");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(3, 2, 10, 10));

			Temporal.add(new JLabel("Ingrese un nombre nuevo: "));
			JTextField nomParada = new JTextField();
			Temporal.add(nomParada);
			Temporal.add(new JLabel("Ingrese un número nuevo: "));
			JTextField numParada = new JTextField();
			Temporal.add(numParada);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					paradaInicial.getNodo().setCalle(nomParada.getText());
					b3.setEnabled(false);
					b4.setEnabled(false);
					paradaInicial = null;
					Temp.dispose();
				}
			});

			Cancelar.addActionListener(i -> {
				if (Cancelar.isEnabled())
					Temp.dispose();
			});
			Temp.pack();
		});
	}

	private void rutaMasRapida(JButton b) {

		b.addActionListener(e -> {

			ArrayList<Ruta> a;
			a = g.rutaMasRapida(paradaInicial.getNodo(), paradaFinal.getNodo());
			viajeSeleccionado = a;
			for (Ruta r : a) {
				ArrayList<Nodo> p = r.getParadas();
				if (!p.isEmpty()) {
					Nodo Actual = p.get(0);
					for (Nodo i : p) {
						if (!i.equals(Actual)) {
							Linea l = g.getLinea(r.getBus());
							for (Flecha f : listaConexiones) {
								if (f.getPuntoInicio().getNodo().equals(Actual)
										&& f.getPuntoFinal().getNodo().equals(i))
									f.setColor(l.getColor());
							}
							Actual = i;
						}
					}
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
			if(viajeSeleccionado != null)
				b8.setEnabled(true);
			else
				b8.setEnabled(false);
		});
	}

	private void rutaMasCorta(JButton b) {

		b.addActionListener(e -> {
			ArrayList<Ruta> a;
			a = g.rutaMasCorta(paradaInicial.getNodo(), paradaFinal.getNodo());
			viajeSeleccionado = a;
			for (Ruta r : a) {
				ArrayList<Nodo> p = r.getParadas();
				if (!p.isEmpty()) {
					Nodo Actual = p.get(0);
					for (Nodo i : p) {
						if (!i.equals(Actual)) {
							Linea l = g.getLinea(r.getBus());
							for (Flecha f : listaConexiones) {
								if (f.getPuntoInicio().getNodo().equals(Actual)
										&& f.getPuntoFinal().getNodo().equals(i))
									f.setColor(l.getColor());
							}
							Actual = i;
						}
					}
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
			if(viajeSeleccionado != null)
				b8.setEnabled(true);
			else
				b8.setEnabled(false);
		});
	}

	private void rutaMasBarata(JButton b) {

		b.addActionListener(e -> {
			ArrayList<Ruta> a;
			a = g.rutaMasBarata(paradaInicial.getNodo(), paradaFinal.getNodo());
			viajeSeleccionado = a;
			for (Ruta r : a) {
				ArrayList<Nodo> p = r.getParadas();
				if (!p.isEmpty()) {
					Nodo Actual = p.get(0);
					for (Nodo i : p) {
						if (!i.equals(Actual)) {
							Linea l = g.getLinea(r.getBus());
							for (Flecha f : listaConexiones) {
								if (f.getPuntoInicio().getNodo().equals(Actual)
										&& f.getPuntoFinal().getNodo().equals(i))
									f.setColor(l.getColor());
							}
							Actual = i;
						}
					}
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
			if(viajeSeleccionado != null)
				b8.setEnabled(true);
			else
				b8.setEnabled(false);
		});
	}

	private void comprarPasaje(JButton b) {

		b.addActionListener(e -> {
			
			JFrame Temp = new JFrame("Comprar Pasaje");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(7, 1, 10, 10));
			
			double costoTotal = 0;
			double distanciaTotal = 0;
			double duracionTotal = 0;
			ArrayList<Bus> buses = new ArrayList<Bus>();
			
			for (Ruta r : viajeSeleccionado) {
				costoTotal += r.getPrecio();
				distanciaTotal += r.getDistancia();
				duracionTotal += r.getTiempo();
				g.getLinea(r.getBus()).cargarPasajero();
				if(!buses.contains(r.getBus()))
					buses.add(r.getBus());
			}
			
			String s = "Buses en el trayecto:";
			
			for(Bus bu : buses) {
				s+= " ";
				s+=bu.getNumero();
			}
			int horas = (int) duracionTotal/60;
			int minutos = (int) duracionTotal%60;
			Temporal.add(new JLabel("Parada Inicial: " + paradaInicial.getNumeroParada()));
			Temporal.add(new JLabel("Parada Final: " + paradaFinal.getNumeroParada()));
			Temporal.add(new JLabel("Costo total del viaje: $" + costoTotal));
			if(horas!= 0)
				Temporal.add(new JLabel("Duracion estimada (HH:MM): " + horas + " : " + minutos));
			else
				Temporal.add(new JLabel("Duracion estimada en minutos: " + minutos));
			Temporal.add(new JLabel("Longitud del viaje: " + distanciaTotal));
			Temporal.add(new JLabel(s));
			
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			
			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled())
					Temp.dispose();
			});
			Temp.pack();
		});
	}
	
	private void limpiarPrograma(JButton b) {

		b.addActionListener(e -> {

			paradaFinal = null;
			paradaInicial = null;
			b5.setEnabled(false);
			b6.setEnabled(false);
			b7.setEnabled(false);
			b8.setEnabled(false);
			
			for (Punto p : listaParadas) {
				if (!p.getNodo().getEstado())
					p.setColor(Color.RED);
				else if (p.getNodo().esParada())
					p.setColor(Color.BLACK);
				else
					p.setColor(Color.GRAY);
			}
			for (Flecha f : listaConexiones) {
				f.setColor(Color.GRAY);
			}

			mapa.dibujarGrafo(listaParadas, listaConexiones);
		});
	}
}
