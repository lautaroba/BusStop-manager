package Aplicacion;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Dominio.Bus;
import Dominio.Economica;
import Dominio.Gestor;
import Dominio.Linea;
import Dominio.Nodo;
import Dominio.Ruta;
import Dominio.Superior;
import Dominio.TipoServicio;

@SuppressWarnings("serial")
public class LineaInterfaz extends JPanel {

	Gestor g;
	public Mapa mapa;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	private Linea lineaSeleccionada;
	private Bus busSeleccionado;

	public LineaInterfaz(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gestor) {

		this.mapa = m;
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.g = gestor;
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
		b9 = new JButton("Trayecto de una linea");
		b9.setEnabled(false);
		this.add(b9, new GBC(0, 4).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));

		this.add(new JLabel("Buses"), new GBC(0, 5).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		// this.add(Box.createRigidArea(new Dimension(0,5)));

		b5 = new JButton("Nuevo");
		b5.setEnabled(false);
		this.add(b5, new GBC(0, 6).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b6 = new JButton("Buscar");
		b6.setEnabled(false);
		this.add(b6, new GBC(0, 7).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b7 = new JButton("Eliminar");
		b7.setEnabled(false);
		this.add(b7, new GBC(0, 8).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b8 = new JButton("Modificar");
		b8.setEnabled(false);
		this.add(b8, new GBC(0, 9).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b10 = new JButton("Trayecto de un Bus");
		b10.setEnabled(false);
		this.add(b10, new GBC(0, 10).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b11 = new JButton("Limpiar");
		b11.setEnabled(true);
		this.add(b11, new GBC(0, 11).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NuevaLinea(b1);
				BuscarLinea(b2);
				EliminarLinea(b3);
				ModificarLinea(b4);
				NuevoBus(b5);
				BuscarBus(b6);
				EliminarBus(b7);
				ModificarBus(b8);
				trayectoDeUnaLinea(b9);
				trayectoDeUnBus(b10);
				limpiarPrograma(b11);
			}
		});
	}

	private void NuevaLinea(JButton b) {

		b.addActionListener(e -> {

			JFrame Temp = new JFrame("Agregar linea");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(8, 2, 10, 10));
			Temporal.setSize(400, 200);

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
			porcentaje.setEnabled(false);
			JCheckBox wifi = new JCheckBox("WiFi");
			wifi.setEnabled(false);
			JCheckBox aire = new JCheckBox("Aire Acondicionado");
			aire.setEnabled(false);
			Temporal.add(wifi);
			Temporal.add(aire);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);

			eco.addActionListener(i -> {
				if (eco.isEnabled()) {
					sup.setSelected(false);
					wifi.setEnabled(false);
					aire.setEnabled(false);
					porcentaje.setEnabled(true);
					aire.setSelected(false);
					wifi.setSelected(false);
				}
			});
			sup.addActionListener(i -> {
				if (sup.isEnabled())
					eco.setSelected(false);
				wifi.setEnabled(true);
				aire.setEnabled(true);

				porcentaje.setEnabled(false);
			});

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {

					if (eco.isSelected()) {
						g.agregarLinea(new Economica(nomLinea.getText(), devolverColor(colorLinea.getText()),
								Integer.parseInt(cantidad.getText()), Double.parseDouble(porcentaje.getText()),
								Integer.parseInt(velocidad.getText())));
					} else if (sup.isSelected()) {
						g.agregarLinea(new Superior(nomLinea.getText(), devolverColor(colorLinea.getText()),
								Integer.parseInt(cantidad.getText()), TipoServicio.Wifi,
								Integer.parseInt(velocidad.getText())));
					}
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

	public Color devolverColor(String s) {
		if (s.equalsIgnoreCase("azul")) {
			return Color.BLUE;
		} else if (s.equalsIgnoreCase("amarillo"))
			return Color.YELLOW;
		else if (s.equalsIgnoreCase("magenta"))
			return Color.MAGENTA;
		else if (s.equalsIgnoreCase("cyan"))
			return Color.CYAN;
		else if (s.equalsIgnoreCase("naranja"))
			return Color.ORANGE;
		return Color.black;
	}

	private void ModificarLinea(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Modificar linea");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(3, 2, 10, 10));
			Temporal.setSize(400, 200);

			Temporal.add(new JLabel("Ingrese nombre de la linea: "));
			JTextField nomLinea = new JTextField();
			Temporal.add(nomLinea);
			Temporal.add(new JLabel("Ingrese color de la linea: "));
			JTextField colorLinea = new JTextField();
			Temporal.add(colorLinea);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					for (Linea l : g.getListaLineas()) {
						if (l.getNombre().equals(nomLinea.getText()) && l.getColor().equals(colorLinea.getText()))
							g.eliminarLinea(l);
					}
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

	private void EliminarLinea(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Eliminar linea");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridBagLayout());

			Temporal.add(new JLabel("¿Desea eliminar la linea seleccionada?"),
					new GBC(0, 0).setWeight(0.9, 0.9).setFill(GBC.HORIZONTAL).setInsets(5, 5, 5, 5));
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar, new GBC(0, 2).setWeight(0.5, 0.5).setInsets(5, 5, 5, 5));
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar, new GBC(0, 1).setWeight(0.5, 0.5).setInsets(5, 5, 5, 5));

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					g.eliminarLinea(lineaSeleccionada);
					lineaSeleccionada = null;
					b3.setEnabled(false);
					b4.setEnabled(false);
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

	private void BuscarLinea(JButton b) {

		b.addActionListener(e -> {

			JFrame Temp = new JFrame("Buscar linea");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(3, 2, 10, 10));

			Temporal.add(new JLabel("Ingrese nombre de la linea: "));
			JTextField nomLinea = new JTextField();
			Temporal.add(nomLinea);
			Temporal.add(new JLabel("Ingrese color de la linea: "));
			JTextField colorLinea = new JTextField();
			Temporal.add(colorLinea);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					Linea aux = null;
					for (Linea l : g.getListaLineas()) {
						if (l.getNombre().equals(nomLinea.getText())) // && l.getColor().equals(colorLinea.getText())
							aux = l;
					}
					if (aux != null) {
						b3.setEnabled(true);
						b4.setEnabled(true);
						b5.setEnabled(true);
						b6.setEnabled(true);
						b9.setEnabled(true);
						lineaSeleccionada = aux;
					} else {
						b3.setEnabled(false);
						b4.setEnabled(false);
						b5.setEnabled(false);
						b6.setEnabled(false);
						b9.setEnabled(false);
						lineaSeleccionada = null;
					}
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

	private void NuevoBus(JButton b) {

		b.addActionListener(e -> {

			JFrame Temp = new JFrame("Registrar nuevo bus");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(4, 2, 10, 10));

			Temporal.add(new JLabel("Ingrese numero del bus: "));
			JTextField numBus = new JTextField();
			Temporal.add(numBus);

			Temporal.add(new JLabel("Ingrese numero parada inicial: "));
			JTextField numPuntoInicial = new JTextField();
			Temporal.add(numPuntoInicial);

			Temporal.add(new JLabel("Ingrese numero parada final: "));
			JTextField numPuntoFinal = new JTextField();
			Temporal.add(numPuntoFinal);

			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			Temporal.setVisible(true);

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					Punto puntoInicial = (Punto) listaParadas.stream()
							.filter(pI -> pI.getNumeroParada() == Integer.parseInt(numPuntoInicial.getText())).findAny()
							.get();

					Punto puntoFinal = (Punto) listaParadas.stream()
							.filter(pF -> pF.getNumeroParada() == Integer.parseInt(numPuntoFinal.getText())).findAny()
							.get();

					lineaSeleccionada.setBuses(
							new Bus(puntoInicial.getNodo(), puntoFinal.getNodo(), Integer.parseInt(numBus.getText())));
					b6.setEnabled(true);
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

	private void ModificarBus(JButton b) {

		b.addActionListener(e -> {

			JFrame Temp = new JFrame("Modificar bus");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(4, 2, 10, 10));

			Temporal.add(new JLabel("Ingrese nuevo número del bus: "));
			JTextField numNuevoBus = new JTextField();
			Temporal.add(numNuevoBus);
			Temporal.add(new JLabel("Ingrese nueva parada inicial: "));
			JTextField numParadaInicial = new JTextField();
			Temporal.add(numParadaInicial);
			Temporal.add(new JLabel("Ingrese nueva parada final: "));
			JTextField numParadaFinal = new JTextField();
			Temporal.add(numParadaFinal);

			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					if (busSeleccionado != null) {
						Punto puntoInicial = listaParadas.stream()
								.filter(par -> par.getNumeroParada() == Integer.parseInt(numParadaInicial.getText()))
								.findAny().get();

						Punto puntoFinal = listaParadas.stream()
								.filter(par -> par.getNumeroParada() == Integer.parseInt(numParadaFinal.getText()))
								.findAny().get();

						busSeleccionado.modificarBus(puntoInicial.getNodo(), puntoFinal.getNodo(),
								Integer.parseInt(numNuevoBus.getText()));

					}
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

	private void EliminarBus(JButton b) {

		b.addActionListener(e -> {
			
			JFrame Temp = new JFrame("Eliminar bus");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridBagLayout());
			
			Temporal.add(new JLabel("¿Desea eliminar el bus seleccionado?"));
			
			Temporal.add(new JLabel("¿Desea eliminar el bus seleccionado?"),new GBC(0, 0).setWeight(0.9, 0.9).setFill(GBC.HORIZONTAL).setInsets(5, 5, 5, 5));
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar, new GBC(0, 2).setWeight(0.5, 0.5).setInsets(5, 5, 5, 5));
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar, new GBC(0, 1).setWeight(0.5, 0.5).setInsets(5, 5, 5, 5));

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					lineaSeleccionada.eliminarBus(busSeleccionado);
					busSeleccionado = null;
					b.setEnabled(false);
					b10.setEnabled(false);
					b8.setEnabled(false);
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

	private void BuscarBus(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Buscar bus");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(2, 2, 10, 10));

			Temporal.add(new JLabel("Ingrese número del bus: "));
			JTextField numBus = new JTextField();
			Temporal.add(numBus);

			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					Bus aux = g.busQueCoincide(Integer.parseInt(numBus.getText()), lineaSeleccionada);

					if (aux != null) {
						b6.setEnabled(true);
						b7.setEnabled(true);
						b8.setEnabled(true);
						b10.setEnabled(true);
						busSeleccionado = aux;
					} else {
						b6.setEnabled(false);
						b7.setEnabled(false);
						b8.setEnabled(false);
						b10.setEnabled(false);
						busSeleccionado = null;
					}
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

	private void trayectoDeUnaLinea(JButton b) {

		b.addActionListener(e -> {

			ArrayList<Ruta> a;
			a = g.trayectoTotalDeUnaLinea(lineaSeleccionada);

			for (Ruta r : a) {
				for (Flecha f : listaConexiones) {
					ArrayList<Nodo> p = r.getParadas();
					if (!p.isEmpty()) {
						Nodo Actual = p.get(0);
						for (Nodo i : p) {
							if (!i.equals(Actual)) {
								if (f.getPuntoInicio().getNodo().equals(Actual)
										&& f.getPuntoFinal().getNodo().equals(i))
									f.setColor(lineaSeleccionada.getColor());
							}
							Actual = i;
						}
					}
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
		});
	}

	private void trayectoDeUnBus(JButton b) {

		b.addActionListener(e -> {
			for (Ruta r : busSeleccionado.getRutas(g)) {
				for (Flecha f : listaConexiones) {
					ArrayList<Nodo> p = r.getParadas();
					if (!p.isEmpty()) {
						Nodo Actual = p.get(0);
						for (Nodo i : p) {
							if (!i.equals(Actual)) {
								if (f.getPuntoInicio().getNodo().equals(Actual)
										&& f.getPuntoFinal().getNodo().equals(i))
									f.setColor(lineaSeleccionada.getColor());
							}
							Actual = i;
						}
					}
				}
			}
			mapa.dibujarGrafo(listaParadas, listaConexiones);
		});
	}

	private void limpiarPrograma(JButton b) {

		b.addActionListener(e -> {

			lineaSeleccionada = null;
			busSeleccionado = null;
			
			b3.setEnabled(false);
			b4.setEnabled(false);
			b5.setEnabled(false);
			b6.setEnabled(false);
			b7.setEnabled(false);
			b8.setEnabled(false);
			b9.setEnabled(false);
			b10.setEnabled(false);

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
