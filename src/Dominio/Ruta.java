package Dominio;

import java.util.*;

public class Ruta {

	private Bus bus;
	private double precio;
	private Nodo pInicial;
	private Nodo pFinal;
	private ArrayList<Nodo> listaParadas;
	private double tiempo; // en min
	private double distancia; // en km

	public Ruta(Nodo i, Nodo f, double d, List<Nodo> p, double t, double pr, Bus b) {
		super();
		this.distancia = d;
		this.pInicial = i;
		this.pFinal = f;
		this.listaParadas = (ArrayList<Nodo>) p;
		this.tiempo = t;
		this.bus = b;
		this.precio = pr;
	}

	public Ruta(Nodo i, Nodo f, double d, List<Nodo> p) {
		super();
		this.distancia = d;
		this.pInicial = i;
		this.pFinal = f;
		this.listaParadas = (ArrayList<Nodo>) p;
		this.tiempo = 0;
	}

	public void Imprimir() {
		System.out.println("Parada inicial: " + pInicial.getNumero() + " Parada final: " + pFinal.getNumero() + " Bus: "
				+ bus.getNumero() + " Tiempo: " + tiempo + " Distancia: " + distancia + " Precio: " + precio);
		System.out.println("Paradas Intermedias:");

		for (Nodo p : listaParadas)
			System.out.print(" " + p.getNumero());

		System.out.println("");
	}

	public void Imprimirr() {
		System.out.println("Parada inicial: " + pInicial.getNumero() + " Parada final: " + pFinal.getNumero()
				+ " Tiempo: " + tiempo + " Distancia: " + distancia + " Precio: " + precio);
		System.out.println("Paradas Intermedias:");

		for (Nodo p : listaParadas)
			System.out.print(" " + p.getNumero());

		System.out.println("");
	}

	public int getCantidadParadas() {
		return listaParadas.size();
	}

	public double getTiempo() {
		return tiempo;
	}

	public Nodo getPInicial() {
		return pInicial;
	}

	public Nodo getPFinal() {
		return pFinal;
	}

	public double getDistancia() {
		return distancia;
	}

	public ArrayList<Nodo> getParadas() {
		return listaParadas;
	}

	public Bus getBus() {
		return bus;
	}

	public double getPrecio() {
		return precio;
	}

	public boolean similar(Ruta r) {
		if (r.getPInicial().equals(this.pInicial) && r.getPFinal().equals(this.pFinal))
			return true;
		else
			return false;
	}

}
