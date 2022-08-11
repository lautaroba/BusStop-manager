package Aplicacion;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.*;
import Dominio.Gestor;
import Dominio.Incidente;

@SuppressWarnings("serial")
public class IncidentesInterfaz extends JPanel {

	JButton b1, b2, b3;
	static int valorX = 100;
	static int valorY = 0;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	private Mapa mapa;
	private Gestor g;
	boolean paradaValida = false;

	public IncidentesInterfaz(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones, Gestor gestor) {

		this.mapa = m;
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.g = gestor;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setBackground(Color.decode("#DEDEDE"));

		b1 = new JButton("Nuevo");
		this.add(b1, new GBC(0, 0).setWeight(0.01, 0.01).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b2 = new JButton("Buscar");
		b2.setEnabled(true);
		this.add(b2, new GBC(0, 1).setWeight(0.01, 0.01).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		b3 = new JButton("Eliminar");
		b3.setEnabled(false);
		this.add(b3, new GBC(0, 2).setWeight(0.01, 0.01).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		JPanel pan = new JPanel();
		pan.setBackground(Color.decode("#DEDEDE"));
		this.add(pan, new GBC(0, 3).setWeight(0.99, 0.99).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Nuevo(b1);
				Buscar(b2);
				Eliminar(b3);
			}
		});
	}

	private void Nuevo(JButton b) {

		b.addActionListener(e -> {
			JFrame Temp = new JFrame("Agregar incidente");
			Temp.setVisible(true);
			Temp.setResizable(false);
			Temp.setLocationRelativeTo(null);
			JPanel Temporal = new JPanel();
			Temp.add(Temporal);
			Temporal.setVisible(true);
			Temporal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			Temporal.setLayout(new GridLayout(6, 1, 10, 10));

			Temporal.add(new JLabel("Ingrese nombre de la parada: "));
			JTextField nomParada = new JTextField();
			Temporal.add(nomParada);
			Temporal.add(new JLabel("Ingrese numero de la parada: "));
			JTextField numParada = new JTextField();
			Temporal.add(numParada);
			Temporal.add(new JLabel("Ingrese fecha de inicio del incidente (aaaa-mm-dd): "));
			JTextField fechaInicio = new JTextField();
			Temporal.add(fechaInicio);
			Temporal.add(new JLabel("Ingrese fecha de final del incidente (aaaa-mm-dd): "));
			JTextField fechaFinal = new JTextField();
			Temporal.add(fechaFinal);
			Temporal.add(new JLabel("Ingrese una descripción: "));
			JTextField descripcion = new JTextField();
			Temporal.add(descripcion);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);

			paradaValida = false; // comprobará al ingresar el usuario si la parada es válida o no

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					for (Punto p : listaParadas) {
						if (nomParada.getText().equalsIgnoreCase(p.getNombreParada())
								|| p.getNumeroParada() == Integer.parseInt(numParada.getText())) {
							paradaValida = true;
							p.setColor(Color.RED);
							p.getNodo().setEstado(false);
							if (fechaFinal.getText().equals(""))
								g.agregarIncidente(LocalDate.parse(fechaInicio.getText()), null, descripcion.getText(),
										p.getNodo());
							else
								g.agregarIncidente(LocalDate.parse(fechaInicio.getText()),
										LocalDate.parse(fechaFinal.getText()), descripcion.getText(), p.getNodo());
							b2.setEnabled(true);
							b3.setEnabled(true);
							mapa.dibujarGrafo(listaParadas, listaConexiones);
						}
					}
					if (!paradaValida)
						JOptionPane.showMessageDialog(null, "Ingrese una parada válida");
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
			JFrame Temp = new JFrame("Eliminar incidente");
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

			paradaValida = false; // comprobará al ingresar el usuario si la parada es válida o no

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					for (Punto p : listaParadas) {
						if (nomParada.getText().equalsIgnoreCase(p.getNombreParada())
								|| p.getNumeroParada() == Integer.parseInt(numParada.getText())) {
							paradaValida = true;
							if (p.getNodo().esParada())
								p.setColor(Color.BLACK);
							else
								p.setColor(Color.GRAY);

							p.getNodo().setEstado(true);
							mapa.dibujarGrafo(listaParadas, listaConexiones);
						}
					}
					if (!paradaValida)
						JOptionPane.showMessageDialog(null, "Ingrese una parada válida");
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
			JFrame Temp = new JFrame("Buscar incidente");
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

			paradaValida = false;

			Aceptar.addActionListener(i -> {
				if (Aceptar.isEnabled()) {
					for (Punto p : listaParadas) {
						if ((nomParada.getText().equalsIgnoreCase(p.getNombreParada())
								|| numParada.getText().equals(Integer.toString(p.getNumeroParada())))
								&& p.getNodo().getEstado() == false) {
							paradaValida = true;
							JFrame temp1 = new JFrame("Datos de los incidentes");
							JPanel Temporal2 = new JPanel();
							temp1.setVisible(true);
							temp1.setResizable(false);
							temp1.setLocationRelativeTo(null);
							temp1.add(Temporal2);
							ArrayList<Incidente> incidentesParada = g.getListaIncidentes().stream()
									.filter(inc -> inc.getParada().equals(p.getNodo()))
									.collect(Collectors.toCollection(ArrayList::new));
							Temporal2.setLayout(new GridLayout(2 + incidentesParada.size() * 4, 1, 10, 10));
							Temporal2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
							Temporal2.add(new JLabel("Nombre parada: " + p.getNombreParada()));
							Temporal2.add(new JLabel("Numero parada: " + p.getNumeroParada()));
							for (Incidente in : incidentesParada) {
								Temporal2.add(new JLabel("Incidente id: " + in.getId()));
								Temporal2.add(new JLabel("Fecha de inicio: " + in.getInicio()));
								if (in.getFin() != null)
									Temporal2.add(new JLabel("Fecha de finalización: " + in.getFin()));
								else
									Temporal2.add(new JLabel("Fecha de finalización indefinida"));
								Temporal2.add(new JLabel("Descripcion: " + in.getDescripcion()));
							}
							temp1.pack();
						}
					}
					if (!paradaValida)
						JOptionPane.showMessageDialog(null, "Ingrese una parada válida");
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
}