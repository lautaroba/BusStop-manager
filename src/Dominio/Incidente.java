package Dominio;

import java.time.LocalDate;

public class Incidente {
	
	private Parada parada;
	private LocalDate inicio, fin;
	private String descripcion;
	private boolean activa;
	
	//"2015-02-20"
	
	public Incidente(String i, String f, String d, Parada p) {
		this.inicio = LocalDate.parse(i);
		this.fin = LocalDate.parse(f);
		this.descripcion = d;
		this.activa = true;
		this.parada = p;
	}
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	public LocalDate getInicio() {
		return inicio;
	}
	public void setFin(String f) {
		this.fin = LocalDate.parse(f);
	}
	public LocalDate getFin() {
		return fin;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Parada getParada() {
		return parada;
	}
	
	
	
}
