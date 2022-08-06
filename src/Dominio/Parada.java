package Dominio;

public class Parada {
	
	private static int contador = 1;
	
	private int idParada;
	private int numeroCalle;
	private String calle;
	private boolean estado;
	
	public Parada(int numero, String calle) {
		super();
		this.idParada = contador;
		this.numeroCalle = numero;
		this.calle = calle;
		this.estado=true;
		contador++;
	}

	public boolean equals(Parada p) {
		if(p.getNroParada() == this.idParada)
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
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
