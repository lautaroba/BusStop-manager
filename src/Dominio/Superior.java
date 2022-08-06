package Dominio;

import java.util.ArrayList;

public class Superior extends Linea{
	
	private TipoServicio tipo;

	public Superior(String n, String c, int cantidad, TipoServicio t, int v) {
		this.buses = new ArrayList<Bus>();
		this.nombre = n;
		this.capacidad = cantidad;
		this.color = c;
		this.tipo = t;
		this.velocidad = v;
	}
	
	public TipoServicio getTipo() {
		return tipo;
	}
	
}
