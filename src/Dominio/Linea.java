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
	public Ruta getTrayectoLinea(Gestor g) {
		
		Ruta aux = new Ruta();

		buses.stream()
			 .flatMap(b -> b.getRutas(g).stream()).forEach(new Consumer<Ruta>() {
				 @Override
					public void accept(Ruta t) {
						aux.addAll(t);
					}
			 });
		return aux;
	}
}
