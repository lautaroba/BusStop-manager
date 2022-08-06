package Dominio;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Linea {
	
	protected ArrayList<Bus> buses;
	protected String color;  // Posible modificación  en un futuro
	protected String nombre;
	protected int capacidad;
	protected int velocidad;
	
	ArrayList<Bus> getBuses() {
		return buses;
	}
	
	public void setBuses(Bus bus) {
		this.buses.add(bus);
	}
	
	public String getColor() {
		return color;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	/*
	 Retorna todas las rutas que realiza una linea
	 */
	public Trayecto getTrayectoLinea(Gestor g) {
		
		Trayecto aux = new Trayecto();

		buses.stream()
			 .map(b -> b.getRutas(g)).forEach(new Consumer<Trayecto>() {
				 @Override
					public void accept(Trayecto t) {
						aux.addAll(t);
					}
			 });
		return aux;
	}
}
