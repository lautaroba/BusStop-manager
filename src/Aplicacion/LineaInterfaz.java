package Aplicacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
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
		b9 = new JButton("Trayecto de una linea");
		b9.setEnabled(false);
		this.add(b9, new GBC(0,4).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		
		this.add(new JLabel("Buses"), new GBC(0,5).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		//this.add(Box.createRigidArea(new Dimension(0,5)));
		
		b5 = new JButton("Nuevo");
		b5.setEnabled(false);
		this.add(b5, new GBC(0,6).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b6 = new JButton("Buscar");
		b6.setEnabled(false);
		this.add(b6, new GBC(0,7).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b7 = new JButton("Eliminar");
		b7.setEnabled(false);
		this.add(b7, new GBC(0,8).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b8 = new JButton("Modificar");
		b8.setEnabled(false);
		this.add(b8, new GBC(0,9).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		b10 = new JButton("Trayecto de un Bus");
		b10.setEnabled(false);
		this.add(b10, new GBC(0,10).setWeight(0.9, 0.9).setFill(GBC.BOTH).setInsets(5,5,5,5));
		this.add(Box.createRigidArea(new Dimension(0,5)));
		
		
		SwingUtilities.invokeLater(new Runnable() {
	           public void run() {
	               Nuevo(b1);
	               Buscar(b2);
	               Eliminar(b3);
	               Modificar(b4);
	               NuevoBus(b5);
	               BuscarBus(b6);
	               EliminarBus(b7);
	               ModificarBus(b8);
	               trayectoDeUnBus(b10);
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
				porcentaje.setEnabled(false);
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
						porcentaje.setEnabled(true);
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
						
						if(eco.isSelected()) {
							g.agregarLinea(new Economica(nomLinea.getText(), devolverColor(colorLinea.getText()),Integer.parseInt(cantidad.getText()) ,Double.parseDouble(porcentaje.getText()), Integer.parseInt(velocidad.getText())));
						}
						else if(sup.isSelected()) {
							g.agregarLinea(new Superior(nomLinea.getText(), devolverColor(colorLinea.getText()), Integer.parseInt(cantidad.getText()) ,TipoServicio.Wifi, Integer.parseInt(velocidad.getText())));
						}
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

	public Color devolverColor(String s) {
	    if(s.equalsIgnoreCase("azul")) return Color.BLUE;
	    else if (s.equalsIgnoreCase("amarillo")) return Color.YELLOW;
	    else if (s.equalsIgnoreCase("magenta")) return Color.MAGENTA;
	    else if(s.equalsIgnoreCase("cyan")) return Color.CYAN;
	    else if (s.equalsIgnoreCase("naranja")) return Color.ORANGE;
		return Color.black;
	}
	
	private void Modificar(JButton b) {
		
		b.addActionListener(e -> {
			
			JFrame Temporal = new JFrame("Modificar linea");
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
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			
			Aceptar.addActionListener(i -> {
				if(Aceptar.isEnabled()) {
					for(Linea l : g.getListaLineas()) {
						if(l.getNombre().equals(nomLinea.getText()) && l.getColor().equals(colorLinea.getText()))
							g.eliminarLinea(l);
					}
				Temporal.dispose();
				}
			});
			
			Cancelar.addActionListener(i -> {
				if(Cancelar.isEnabled()) 
					Temporal.dispose();
			});
		});
	}
	
	private void Eliminar(JButton b) {
		
		b.addActionListener(e -> {
			
			JFrame Temporal = new JFrame("Eliminar linea");
			Temporal.setVisible(true);
			Temporal.setLayout(new GridLayout(8,2, 10, 10));
			Temporal.setSize(600,300);
			Temporal.setResizable(false);
			JButton Cancelar = new JButton("Cancelar");
			Temporal.add(Cancelar);
			JButton Aceptar = new JButton("Aceptar");
			Temporal.add(Aceptar);
			
			Aceptar.addActionListener(i -> {
				if(Aceptar.isEnabled()) {
				g.eliminarLinea(lineaSeleccionada);
				lineaSeleccionada = null;
				b3.setEnabled(false);
				b4.setEnabled(false);
				//System.out.println("ashee " + g.getListaLineas().size());
				Temporal.dispose();
				}
			});
			
			Cancelar.addActionListener(i -> {
				if(Cancelar.isEnabled()) 
					Temporal.dispose();
			});
		});
	}

	private void Buscar(JButton b) {
		
		b.addActionListener(e -> {
			
			JFrame Temporal = new JFrame("Buscar linea");
			Temporal.setVisible(true);
			Temporal.setLayout(new GridLayout(6,2, 10, 10));
			Temporal.setSize(400,300);
			Temporal.setResizable(false);
			
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
				if(Aceptar.isEnabled()) {
					Linea aux = null;
					for(Linea l : g.getListaLineas()) {
						if(l.getNombre().equals(nomLinea.getText()))  // && l.getColor().equals(colorLinea.getText())
							aux = l;
					}
					
					if(aux != null) {			
						b3.setEnabled(true);
						b4.setEnabled(true);
						b5.setEnabled(true);
						b9.setEnabled(true);
						lineaSeleccionada = aux;
					}
					else {
						b3.setEnabled(false);
						b4.setEnabled(false);
						b5.setEnabled(false);
						b9.setEnabled(false);
						lineaSeleccionada = null;
					}
					Temporal.dispose();
				}
			});
			
			Cancelar.addActionListener(i -> {
				if(Cancelar.isEnabled()) 
					Temporal.dispose();
			});
		});
		
	}
	

private void NuevoBus(JButton b) {
	
	b.addActionListener(e -> {
		
			JFrame Temporal = new JFrame("Registrar nuevo bus");
			Temporal.setVisible(true);
			Temporal.setLayout(new GridLayout(4,2, 10, 10));
			Temporal.setSize(400,150);
			Temporal.setResizable(false);
			
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
				if(Aceptar.isEnabled()) {
					Punto puntoInicial = (Punto) listaParadas.stream()
							.filter(pI -> pI.getNumeroParada() == Integer.parseInt(numPuntoInicial.getText())).findAny().get();
					
					Punto puntoFinal = (Punto) listaParadas.stream()
							.filter(pF -> pF.getNumeroParada()  == Integer.parseInt(numPuntoFinal.getText())).findAny().get();
					
					lineaSeleccionada.setBuses(new Bus(puntoInicial.getNodo(), puntoFinal.getNodo(), Integer.parseInt(numBus.getText())));
					b6.setEnabled(true); // falta desactivar buscar dsp de eliminar y modificar asheeee
					Temporal.dispose();
					}
				}
			);
			
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
		JFrame Temporal = new JFrame("Modificar Bus");
		Temporal.setVisible(true);
		Temporal.setLayout(new GridLayout(3,2, 10, 10));
		Temporal.setSize(400,150);
		Temporal.setResizable(false);
		
		Temporal.add(new JLabel("Ingrese nuevo número del bus: "));
		JTextField numNuevoBus = new JTextField();
		Temporal.add(numNuevoBus);
		
		Temporal.add(new JLabel("Ingrese nueva parada inicial: "));
		JTextField numParadaInicial = new JTextField();
		Temporal.add(numParadaInicial);
		Punto puntoInicial = (Punto) listaParadas.stream()
				.filter(pI -> Integer.toString(pI.getNumeroParada()).equals(numParadaInicial.getText()));
		
		Temporal.add(new JLabel("Ingrese nueva parada final: "));
		JTextField numParadaFinal = new JTextField();
		Temporal.add(numParadaFinal);
		Punto puntoFinal = (Punto) listaParadas.stream()
				.filter(pF -> Integer.toString(pF.getNumeroParada()).equals(numParadaFinal.getText()));
		
		JButton Cancelar = new JButton("Cancelar");
		Temporal.add(Cancelar);
		JButton Aceptar = new JButton("Aceptar");
		Temporal.add(Aceptar);
		// FALTA CAPTURAR EL TEXTO
		Aceptar.addActionListener(i -> {
			if(Aceptar.isEnabled()) {
				if(busSeleccionado != null) {
					busSeleccionado.modificarBus(puntoInicial.getNodo(), puntoFinal.getNodo(), Integer.parseInt(numNuevoBus.getText()));
				} // AGREGAR MENSAJE DE ERROR DE QUE NO HAY BUS SELECCIONADO
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
		JFrame Temporal = new JFrame("Eliminar Bus");
		Temporal.setVisible(true);
		Temporal.setLayout(new GridLayout(3,2, 10, 10));
		Temporal.setSize(400,150);
		Temporal.setResizable(false);
		
		Temporal.add(new JLabel("¿Desea eliminar el bus seleccionado?"));
		
		JButton Cancelar = new JButton("Cancelar");
		Temporal.add(Cancelar);
		JButton Aceptar = new JButton("Aceptar");
		Temporal.add(Aceptar);
		
		
		Aceptar.addActionListener(i -> {
			if(Aceptar.isEnabled()) {
				lineaSeleccionada.eliminarBus(busSeleccionado);
				busSeleccionado = null;
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

private void BuscarBus(JButton b) {
    
    b.addActionListener(e -> {

        JFrame Temporal = new JFrame("Buscar bus");
        Temporal.setVisible(true);
        Temporal.setLayout(new GridLayout(8,2, 10, 10));
        Temporal.setSize(600,300);
        Temporal.setResizable(false);
        
        Temporal.add(new JLabel("Ingrese número del bus: "));
        JTextField numBus = new JTextField();
        Temporal.add(numBus);

        JButton Cancelar = new JButton("Cancelar");
        Temporal.add(Cancelar);
        JButton Aceptar = new JButton("Aceptar");
        Temporal.add(Aceptar);
        
        Aceptar.addActionListener(i -> {
            if(Aceptar.isEnabled()) {
            	Bus aux = g.busQueCoincide(Integer.parseInt(numBus.getText()));
            	
                if(aux != null) {
                	b6.setEnabled(true);
                	b7.setEnabled(true);
                	b10.setEnabled(true);
                	busSeleccionado = aux;
                }else {
                    b6.setEnabled(false);
                	b7.setEnabled(false);
                	b10.setEnabled(false);
                	busSeleccionado = null;
                }
            Temporal.dispose();
            }
        });
        
        Cancelar.addActionListener(i -> {
            if(Cancelar.isEnabled()) 
                Temporal.dispose();
        });
    });
    

}

	
	private void trayectoDeUnaLinea(JButton b) {
		
		b.addActionListener(e -> {
			
			ArrayList<Ruta> a;
			a = g.trayectoTotalDeUnaLinea(lineaSeleccionada);
			
			for(Ruta r : a) {
				
				
				
			}
			
		});
	}
	
	private void trayectoDeUnBus(JButton b) {
		
		ArrayList<Flecha> f = listaConexiones;
		
		b.addActionListener(e -> {
			
			for(Ruta r : busSeleccionado.getRutas(g)) {
				for(Flecha fl : f) {
					if(fl.getPuntoInicio().getNodo().equals(r.getPInicial()) && fl.getPuntoFinal().getNodo().equals(r.getPFinal()))
						fl.setColor(lineaSeleccionada.getColor());
				}
			}
			mapa.dibujarGrafo(listaParadas, f);
		});
	}
	
}
	
