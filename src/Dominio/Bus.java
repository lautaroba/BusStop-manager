package Dominio;

import java.util.ArrayList;

public class Bus {
	
	private int numero;
	private Parada inicio, fin, auxiliar;
	
	public Bus (Parada a, Parada b, int n) {
		this.inicio = a;
		this.fin = b;
		this.numero = n;
		this.auxiliar=null;
	}
	public Parada getInicio() {
		return inicio;
	}
	public Parada getFin() {
		return fin;
	}
	public Parada getAuxiliar() {
		return auxiliar;
	}
	public void setAuxilia(Parada a) {
		this.auxiliar = a;
	}
	public int GetNumero() {
		return numero;
	}
	
	public ArrayList<Ruta> getRutas(Gestor g) {
		
		if(fin.isEstado())
			return g.buscarTodasLasRutas(inicio, fin);
		else
			return g.buscarTodasLasRutas(inicio, auxiliar);
	}
	
}
