package Sistema;

public class Parada {
	
	private static int contador = 0;
	
	private int nroParada;
	private int numero;
	private String calle;
	
	
	public Parada(int numero, String calle) {
		super();
		this.nroParada = contador;
		this.numero = numero;
		this.calle = calle;
		contador++;
	}

	
	public int getNroParada() {
		return nroParada;
	}
	public void setNroParada(int nroParada) {
		this.nroParada = nroParada;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
}
