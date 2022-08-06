package Dominio;

import java.util.ArrayList;

public class Economica extends Linea{
	
	@SuppressWarnings("unused")
	private double porcentaje; // debe ser menor o igual a 0.4;
	
	public Economica(String n, String c, int cantidad, double p) {
		this.buses = new ArrayList<Bus>();
		this.nombre = n;
		this.capacidad = cantidad;
		this.color = c;
		this.porcentaje = p;
	}
	
}
