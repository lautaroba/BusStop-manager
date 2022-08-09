package Aplicacion;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import Dominio.Nodo;

@SuppressWarnings("serial")
public class Punto extends Ellipse2D.Double{
	
	public static int contador = 0;
	private String nombreParada;
	private int numeroParada;
	private boolean incidenteRegistrado = false;
	private Nodo n;
	private Color color;
	//private int id;
	
	public Punto(int x, int y) {
		//this.id = contador;
		this.x = x;
		this.y = y;
		this.height=15;
		this.width=15;
		this.incidenteRegistrado = false;
		contador++;
		this.setColor(Color.GRAY);
	}
	
	public Punto(int x, int y, String nombre, int numero) {
		//this.id = contador;
		this.setColor(Color.GRAY);
		this.x = x;
		this.y = y;
		this.height=10;
		this.width=10;
		contador++;
		this.nombreParada = nombre;
		this.numeroParada = numero;
		this.n = new Nodo(numero, nombre);
	}
	
	public Nodo getNodo() {
		return n;
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
	public void setColor(Color c) {
		this.color = c;
	}
	public Color getColor() {
		return this.color;
	}
}
