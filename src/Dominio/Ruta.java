package Dominio;

import java.util.*;

public class Ruta {
	
	private Bus bus;
	private double precio;
	private Parada pInicial;
	private Parada pFinal;
	private ArrayList<Parada> listaParadas;
	private double tiempo; // en min
	private double distancia; // en km

	public Ruta(Parada i, Parada f, double d, List<Parada> p, double t, double pr, Bus b) {
		super();
		this.distancia = d;
		this.pInicial = i;
		this.pFinal = f;
		this.listaParadas = (ArrayList<Parada>) p;
		this.tiempo=t; 
		this.bus = b;
		this.precio = pr;
		// modificar
	}
	// eseria un camino
	public Ruta(Parada i, Parada f, double d, List<Parada> p) {
		super();
		this.distancia = d;
		this.pInicial = i;
		this.pFinal = f;
		this.listaParadas = (ArrayList<Parada>) p;
		this.tiempo=0; 
		// modificar
	}
	
	public void Imprimir() {
		System.out.println("Parada inicial: " + pInicial.getNumero()
				+ " Parada final: " + pFinal.getNumero() 
				+ " Bus: " + bus.GetNumero()
				+ " Tiempo: " + tiempo 
				+ " Distancia: " + distancia 
				+ " Precio: " + precio
				);
		System.out.println("Paradas Intermedias:");
		
		for(Parada p : listaParadas) 
			System.out.print(" " + p.getNumero());
		
		System.out.println("");
	}
	
	public int getCantidadParadas(){
		return listaParadas.size();
	}

	public double getTiempo() {
		return tiempo;
	}

	public Parada getPInicial() {
		return pInicial;
	}

	public Parada getPFinal() {
		return pFinal;
	}

	public double getDistancia() {
		return distancia;
	}
	
	public ArrayList<Parada> getParadas(){
		return listaParadas;
	}
	
	public Bus getBus() {
		return bus;
	}
	
	public double getPrecio(){
		return precio;
	}
	
	public boolean similar(Ruta r) {
		if(r.getPInicial().equals(this.pInicial) && r.getPFinal().equals(this.pFinal))
			return true;
		else
			return false;
	}
	
//	public void addParadas(ArrayList<Parada> p) {
//		
//		listaParadas.addAll(p);
//		
//		
//		
//	}
	
//	public Ruta menor(Ruta r) {
//		if(this.similar(r)) {
//			return (r.getDistancia() > this.distancia)? this: r;
//		}
//		else
//			return null;
//		
//	}
	
}
