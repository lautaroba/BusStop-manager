package Aplicacion;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Incidentes extends JPanel {
	
	JButton b1,b2,b3;
	static int valorX = 100;
	static int valorY = 0;
	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	private Mapa mapa;
	
	public Incidentes(Mapa m, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.setBackground(Color.decode("#DEDEDE"));
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
		this.mapa = m;
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
			JFrame Temporal = new JFrame("Registrar nuevo incidente");
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
							p.registrarIncidente(true);
							b2.setEnabled(true);
							b3.setEnabled(true);
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
	
	
	private void Eliminar(JButton b) {
		
		// HACER QUE CUANDO SE SELECIONE UNO ESTE SE ACTIVE
		
		b.addActionListener(e -> {
			JFrame Temporal = new JFrame("Eliminar incidente");
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
							p.registrarIncidente(false);
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
		
	// HACER QUE CUANDO SE SELECIONE UNO ESTE SE ACTIVE
		
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
								&& p.getEstadoIncidente() == true) {
							JFrame temp1 = new JFrame("Datos incidente");
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
	


