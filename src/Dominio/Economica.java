package Dominio;

import java.awt.Color;
import java.util.ArrayList;

public class Economica extends Linea {

	@SuppressWarnings("unused")
	private double porcentaje; // debe ser menor o igual a 0.4;

	public Economica(String n, Color c, int cantidad, double p, int v) {
		this.buses = new ArrayList<Bus>();
		this.nombre = n;
		this.capacidad = (int) (cantidad * (1 + p));
		this.color = c;
		this.porcentaje = p;
		this.velocidad = v;
	}

	public double getPrecio() {
		return (this.tarifa * 1.02);
	}

}
