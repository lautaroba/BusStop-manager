package Dominio;

public class Nodo {

	private static int contador = 1;

	private int idParada;
	private int numeroCalle;
	private String calle;
	private boolean estadoIncidenteEnNodo; // si hay incidente o no
	private boolean esParada;

	public Nodo(int numero, String calle) {
		super();
		this.idParada = contador;
		this.numeroCalle = numero;
		this.calle = calle;
		this.estadoIncidenteEnNodo = true;
		this.esParada = false;
		contador++;
	}

	public boolean equals(Nodo p) {
		if (p.getNroParada() == this.idParada)
			return true;
		else
			return false;
	}

	public int getNroParada() {
		return idParada;
	}

	public int getNumero() {
		return numeroCalle;
	}

	public void setNumero(int numero) {
		this.numeroCalle = numero;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setEstado(boolean b) {
		this.estadoIncidenteEnNodo = b;
	}

	public boolean getEstado() {
		return this.estadoIncidenteEnNodo;
	}

	public boolean esParada() {
		return esParada;
	}

	public void setParada(boolean b) {
		esParada = b;
	}
}
