package Dominio;

import java.util.ArrayList;

public class Bus {

	private int numero;
	private Nodo inicio, fin, auxiliar;

	public Bus(Nodo a, Nodo b, int n) {
		this.inicio = a;
		this.fin = b;
		this.numero = n;
		this.auxiliar = null;
	}

	public Nodo getInicio() {
		return inicio;
	}

	public Nodo getFin() {
		return fin;
	}

	public Nodo getAuxiliar() {
		return auxiliar;
	}

	public void setAuxilia(Nodo a) {
		this.auxiliar = a;
	}

	public int getNumero() {
		return numero;
	}

	public boolean equals(Bus b) {
		if (this.numero == b.getNumero())
			return true;
		else
			return false;
	}

	public void modificarBus(Nodo a, Nodo b, int n) {
		this.inicio = a;
		this.fin = b;
		this.numero = n;
	}

	public ArrayList<Ruta> getRutas(Gestor g) {

		if (fin.getEstado())
			return g.buscarTodasLasRutasDeUnBus(inicio, fin, this);
		else
			return g.buscarTodasLasRutasDeUnBus(inicio, auxiliar, this);
	}

}
