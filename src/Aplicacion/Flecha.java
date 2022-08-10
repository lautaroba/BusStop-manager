package Aplicacion;

import java.awt.Color;

import Dominio.Calle;

public class Flecha {
	private Punto puntoInicio;
	private Punto puntoFinal;
	private Color color;
	private Calle c;

	public Flecha(Punto pI, Punto pF, double l) {
		this.puntoInicio = pI;
		this.puntoFinal = pF;
		this.c = new Calle(pI.getNodo(), pF.getNodo(), l);
		this.color = Color.GRAY;
	}

	public Punto getPuntoInicio() {
		return puntoInicio;
	}

	public Punto getPuntoFinal() {
		return puntoFinal;
	}

	public Calle getCalle() {
		return c;
	}

	public void setColor(Color cl) {
		color = cl;
	}

	public Color getColor() {
		return color;
	}

	public void escribir() {
		System.out.println("nom pun : " + puntoInicio.getNombreParada() + " nom fin : " + puntoFinal.getNombreParada());
	}

}
