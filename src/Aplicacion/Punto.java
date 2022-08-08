package Aplicacion;

import java.awt.geom.Ellipse2D;

import Dominio.Nodo;

@SuppressWarnings("serial")
public class Punto extends Ellipse2D.Double{
	
	public static int contador = 0;
	private String nombreParada;
	private int numeroParada;
	private boolean incidenteRegistrado = false;
	private boolean esParada = false;
	public Nodo n;
	//private int id;
	
	public Punto(int x, int y) {
		//this.id = contador;
		this.x = x;
		this.y = y;
		this.height=10;
		this.width=10;
		this.incidenteRegistrado = false;
		contador++;
	}
	
	public Punto(int x, int y, String nombre, int numero) {
		//this.id = contador;
		this.x = x;
		this.y = y;
		this.height=10;
		this.width=10;
		contador++;
		this.nombreParada = nombre;
		this.numeroParada = numero;
		this.n = new Nodo(numero, nombre);
	}
	
	public double getX() { 
		return this.x;
	}
	public double getY() { 
		return this.y;
	}
	public String getNombreParada() { 
		return this.nombreParada;
	}
	public int getNumeroParada () { 
		return this.numeroParada;
	}
	public boolean getEstadoIncidente() { 
		return this.incidenteRegistrado;
	}
	public void registrarIncidente(boolean inc) { 
		this.incidenteRegistrado = inc;
	}
	public boolean getEstadoParada() { 
		return this.esParada;
	}
	public void registrarParada(boolean inc) {
		this.esParada = inc;
	}
}
