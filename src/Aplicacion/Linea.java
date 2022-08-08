package Aplicacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import Dominio.Economica;
import Dominio.Gestor;
import Dominio.Nodo;
import Dominio.Superior;
import Dominio.TipoServicio;

@SuppressWarnings("serial")
public class Linea extends JPanel {

	Gestor g;
	public Mapa mapa;
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
	static int valorX = 100;
	static int valorY = 0;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;

	public Linea(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gestor) {
		
		this.mapa = m;
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.g = gestor;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.setBackground(Color.decode("#DEDEDE"));
		
		b1 = new JButton("Nuevo");
		this.add(b1, new GBC(0,0).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b2 = new JButton("Buscar");
		b2.setEnabled(false);
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
		
		this.add(new JLabel("Buses"), new GBC(0,4).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		//this.add(Box.createRigidArea(new Dimension(0,5)));
		
		b5 = new JButton("Nuevo");
		b5.setEnabled(false);
		this.add(b5, new GBC(0,5).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b6 = new JButton("Buscar");
		b6.setEnabled(false);
		this.add(b6, new GBC(0,6).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b7 = new JButton("Eliminar");
		b7.setEnabled(false);
		this.add(b7, new GBC(0,7).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b8 = new JButton("Modificar");
		b8.setEnabled(false);
		this.add(b8, new GBC(0,8).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
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
			
				JFrame Temporal = new JFrame("Registrar nueva linea");
				Temporal.setVisible(true);
				Temporal.setLayout(new GridLayout(8,2, 10, 10));
				Temporal.setSize(600,300);
				Temporal.setResizable(false);
				
				Temporal.add(new JLabel("Ingrese nombre de la linea: "));
				JTextField nomLinea = new JTextField();
				Temporal.add(nomLinea);
				Temporal.add(new JLabel("Ingrese color de la linea: "));
				JTextField colorLinea = new JTextField();
				Temporal.add(colorLinea);
				Temporal.add(new JLabel("Ingrese velocidad de la linea: "));
				JTextField velocidad = new JTextField();
				Temporal.add(velocidad);
				Temporal.add(new JLabel("Ingrese cantidad de pasajeros: "));
				JTextField cantidad = new JTextField();
				Temporal.add(cantidad);
				JRadioButton eco = new JRadioButton("Economica");
				Temporal.add(eco);
				JRadioButton sup = new JRadioButton("Superior");
				Temporal.add(sup);
				Temporal.add(new JLabel("Ingrese porcentaje: "));
				JTextField porcentaje = new JTextField();
				Temporal.add(porcentaje);
				porcentaje.setEditable(false);
				JCheckBox wifi= new JCheckBox("WiFi");
				wifi.setEnabled(false);
				JCheckBox aire= new JCheckBox("Aire Acondicionado");
				aire.setEnabled(false);
				Temporal.add(wifi);
				Temporal.add(aire);
				JButton Cancelar = new JButton("Cancelar");
				Temporal.add(Cancelar);
				JButton Aceptar = new JButton("Aceptar");
				Temporal.add(Aceptar);
				
				eco.addActionListener(i -> {
					if(eco.isEnabled()) {
						sup.setSelected(false);
						wifi.setEnabled(false);
						aire.setEnabled(false);
						porcentaje.setEditable(true);
						aire.setSelected(false);
						wifi.setSelected(false);
					}
				});
				sup.addActionListener(i -> {
					if(sup.isEnabled())
						eco.setSelected(false);
						wifi.setEnabled(true);
						aire.setEnabled(true);
						
						porcentaje.setEnabled(false);
				});
				
				Aceptar.addActionListener(i -> {
					if(Aceptar.isEnabled()) {
						
						if(eco.isSelected())
							g.agregarLinea(new Economica(nomLinea.getText(), colorLinea.getText(),Integer.parseInt(cantidad.getText()) ,Double.parseDouble(porcentaje.getText()), Integer.parseInt(velocidad.getText())));
						else if(sup.isSelected())
							g.agregarLinea(new Superior(nomLinea.getText(), colorLinea.getText(), Integer.parseInt(cantidad.getText()),TipoServicio.Wifi, Integer.parseInt(velocidad.getText())));
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
							p.n.setParada(false);
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
	
	private void NuevoBus(JButton b) {
		
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
								p.n.setParada(true);
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

	private void ModificarBus(JButton b) {
		
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

	private void EliminarBus(JButton b) {
		
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
							p.n.setParada(false);
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

	private void BuscarBus(JButton b) {
		
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
	
