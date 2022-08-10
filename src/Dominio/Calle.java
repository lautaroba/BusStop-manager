package Dominio;

public class Calle {

	private double longitud;
	private Nodo inicial;
	private Nodo fin;

	public Calle(Nodo i, Nodo f, double l) {
		this.longitud = l;
		this.inicial = i;
		this.fin = f;
	}

	public double getLongitud() {
		return longitud;
	}

	public Nodo getInicial() {
		return inicial;
	}

	public Nodo getFin() {
		return fin;
	}

	public int comparaCamino(Calle c) {

		if (this.longitud >= c.longitud)
			return 1;
		else
			return -1;
	}

	public void Imprimir() {
		System.out.println("Empieza en: " + inicial.getNumero() + " termina en : " + fin.getNumero()
				+ " Total recorrido = " + longitud);
	}

}
