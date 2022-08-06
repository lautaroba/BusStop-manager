package Dominio;

import java.util.*;


@SuppressWarnings("serial")
public class Ruta extends ArrayList<Calle> {
	
//	private Bus bus;
	
	private Parada pInicial;
	private Parada pFinal;
	private ArrayList<Parada> listaParadas;
	private double tiempo; // en min
	private double distancia; // en km
	
	public Ruta(Parada i, Parada f, double d, List<Parada> p) {
		super();
		this.distancia = d;
		this.pInicial = i;
		this.pFinal = f;
		this.listaParadas = (ArrayList<Parada>) p;
		this.tiempo=0; // modificar
	}
	
	public Ruta() {
		super();
//		this.tiempo = tiempo;
//		this.pInicial = pInicial;
//		this.pFinal = pFinal;
//		this.distancia = distancia;
	}
	
	public void Imprimir() {
		System.out.println("Tiempo: " + tiempo + " Parada inicial: " + pInicial.getNumero() + " Parada final: " + pFinal.getNumero() + " Distancia: " + distancia);
		System.out.println("Paradas Intermedias: \n");
		
		for(Parada p : listaParadas) 
			System.out.println(" " + p.getNumero());
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

}
