package Aplicacion;

import Dominio.Calle;

public class Flecha {
	private Punto puntoInicio;
	private Punto puntoFinal;
	public Calle c;
	
	public Flecha(Punto pI, Punto pF, double l) {
		this.puntoInicio = pI;
		this.puntoFinal = pF;
		this.c = new Calle(pI.n, pF.n, l);
	}
	
	public Punto getPuntoInicio() {return puntoInicio;}
	public Punto getPuntoFinal() {return puntoFinal;}

}
