package Dominio;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class Linea {
	
	protected ArrayList<Bus> buses;
	protected String color;  // Posible modificación  en un futuro
	protected String nombre;
	protected int capacidad;
	
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
	
	/*
	 Retorna todas las rutas que realiza una linea
	 */
	public Trayecto getTrayecto(Gestor g) {
		
		Trayecto aux = new Trayecto();

		this.getBuses().stream()
				.map(b -> (b.getFin().isEstado()) ? g.buscarCaminos(b.getInicio(), b.getFin()): g.buscarCaminos(b.getInicio(), b.getFin()))
				.flatMap(c -> c.stream()).forEach(new Consumer<Calle>() {
					@Override
					public void accept(Calle c) {
						aux.add(c);
					}
				});
		return aux;
	}
	
	public Trayecto getTrayectoMasCorto(Gestor g, Parada p1, Parada p2) {
		// si no existe debe tirar excepcion
		return this.getBuses().stream()
				.map(b -> (b.getFin().isEstado()) ? g.buscarCaminoMasCorto(p1, p2): g.buscarCaminoMasCorto(b.getInicio(), b.getFin()))
				.collect(Collectors.toCollection(Trayecto::new));
		
	}
	
	
}
