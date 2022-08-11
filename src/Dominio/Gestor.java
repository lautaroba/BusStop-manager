package Dominio;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import Exceptions.FechaIncidenteException;

public class Gestor {

	private ArrayList<Linea> listaLineas;
	private ArrayList<Calle> listaCaminos;
	private ArrayList<Nodo> listaNodos; // estas serian esquinas
	private ArrayList<Incidente> listaIncidentes;
	private ArrayList<Ruta> aristas;

	public Gestor() {
		this.listaLineas = new ArrayList<Linea>();
		this.listaCaminos = new ArrayList<Calle>();
		this.listaNodos = new ArrayList<Nodo>();
		this.listaIncidentes = new ArrayList<Incidente>();
		this.aristas = new ArrayList<Ruta>();
	}

	public ArrayList<Linea> getListaLineas() {
		return listaLineas;
	}

	public ArrayList<Incidente> getListaIncidentes() {
		return listaIncidentes;
	}

	public void eliminarLinea(Linea l) {
		listaLineas.remove(l);
	}

	public void agregarLinea(Linea l) {
		this.listaLineas.add(l);
	}

	public void agregarParada(Nodo p) {
		this.listaNodos.add(p);
	}

	public Calle agregarCamino(Nodo p1, Nodo p2, double longitud) {
		Calle aux = new Calle(p1, p2, longitud);
		this.listaCaminos.add(aux);
		return aux;
	}

	public void agregarIncidente(LocalDate inicio, LocalDate fin, String d, Nodo p) {

		try {
			this.listaIncidentes.add(new Incidente(inicio, fin, d, p));
		} catch (FechaIncidenteException e) {
			e.printStackTrace();
		}

		// if (inicio.equals(java.time.LocalDate.now())) {
		for (Nodo j : listaNodos) {
			if (j.equals(p))
				j.setEstado(false);
		}
		calcularParadas(p);
		// }
	}

	/*
	 * Propone parada nueva despues de un incidente
	 */
	private void calcularParadas(Nodo p) {

		Nodo aux = this.getAdyacentes(p).stream().min(Comparator.comparingDouble(e -> this.getDistancia(p, e))).get();

		listaLineas.stream().flatMap(l -> l.getBuses().stream()).forEach(new Consumer<Bus>() {
			@Override
			public void accept(Bus b) {
				b.setAuxilia(aux);
			}
		});
	}

	public void terminarIncidente(Incidente i) {
		Nodo p;
		p = i.getParada();
		for (Nodo j : listaNodos) {
			if (j.equals(p))
				j.setEstado(true);
			;
		}
		listaIncidentes.remove(i);
	}

	public ArrayList<Nodo> getAdyacentes(Nodo p) {
		return listaCaminos.stream() // si es true es q esta activo, probar si saco esto que pasa
				.filter(e -> e.getInicial().equals(p) && e.getFin().getEstado()).map(e -> e.getFin())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/*
	 * Solo puedo tener 1 calle entre 2 nodos, no tendria sentido lo contrario
	 */
	public double getDistancia(Nodo p1, Nodo p2) {
		for (Calle c : listaCaminos) {
			if (c.getInicial().equals(p1) && c.getFin().equals(p2))
				return c.getLongitud();
		}
		return 0;
	}

	/*
	 * Retorna la distancia total entre 2 paradas
	 */
	public double distanciaEntreDosParadas(List<Nodo> p, Nodo ini, Nodo fin) {
		if (!p.isEmpty()) {
			Nodo Actual = p.get(0);
			double suma = 0;
			for (Nodo i : p) {
				if (!i.equals(Actual)) {
					suma += this.getDistancia(Actual, i);
				}
				Actual = i;
			}
			return suma;
		} else {
			return -1;
		}
	}

	/*
	 * Retorna todos los caminos desde p1 a p2 pero solo difieren sus distancias
	 * distancia entre 2 paradas, hacerlo en ruta mas lindo
	 */
	public ArrayList<Ruta> buscarTodasLosCaminos(Nodo p1, Nodo p2) {

		ArrayList<List<Nodo>> salida = new ArrayList<List<Nodo>>();
		List<Nodo> marcados = new ArrayList<Nodo>();
		marcados.add(p1);
		buscarCaminosAux(p1, p2, marcados, salida);

		return salida.stream().map(lp -> new Ruta(p1, p2, this.distanciaEntreDosParadas(lp, p1, p2), lp))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/*
	 * Retorna todas las combinaciones de calles entre 2 paradas , tambien una lista
	 * con todas las paradas intermedias
	 */
	private void buscarCaminosAux(Nodo p1, Nodo p2, List<Nodo> marcados, List<List<Nodo>> todos) {

		List<Nodo> adyacentes = this.getAdyacentes(p1);
		List<Nodo> copiaMarcados = null;
		for (Nodo ady : adyacentes) {
			copiaMarcados = marcados.stream().collect(Collectors.toList());

			if (ady.equals(p2)) {
				copiaMarcados.add(p2);
				todos.add(new ArrayList<Nodo>(copiaMarcados));
			} else {
				if (!copiaMarcados.contains(ady)) {
					copiaMarcados.add(ady);
					this.buscarCaminosAux(ady, p2, copiaMarcados, todos);
				}
			}
		}
	}

	/*
	 * Retorna todas las rutas desde p1 a p2 asociadas a 1 bus distancia entre 2
	 * paradas, hacerlo en ruta mas lindo
	 */
	public ArrayList<Ruta> buscarTodasLasRutasDeUnBus(Nodo p1, Nodo p2, Bus b) {

		ArrayList<List<Nodo>> salida = new ArrayList<List<Nodo>>();
		List<Nodo> marcados = new ArrayList<Nodo>();
		marcados.add(p1);
		buscarCaminosAux(p1, p2, marcados, salida);

		Linea aux = listaLineas.stream().filter(e -> e.getBuses().contains(b)).findAny().get();

		return salida.stream()
				.map(lp -> new Ruta(p1, p2, this.distanciaEntreDosParadas(lp, p1, p2), lp,
						this.distanciaEntreDosParadas(lp, p1, p2) / aux.getVelocidad(),
						this.distanciaEntreDosParadas(lp, p1, p2) * aux.getPrecio(), b))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/*
	 * Retorna todos las rutas q recorre una linea
	 */
	public ArrayList<Ruta> trayectoTotalDeUnaLinea(Linea l) {
		return l.getTrayectoLinea(this);
	}

	/*
	 * Retorna todas las rutas que existen sin importar la linea, entre 2 paradas
	 */
	public ArrayList<List<Ruta>> todasLasRutasEntreDosParadas(Nodo p1, Nodo p2) {

		listaLineas.stream().map(l -> l.getTrayectoLinea(this)).forEach(new Consumer<ArrayList<Ruta>>() {
			public void accept(ArrayList<Ruta> r) {
				aristas.addAll(r);
			}
		});

		ArrayList<List<Ruta>> resultado = new ArrayList<List<Ruta>>();
		List<Ruta> marcados = new ArrayList<Ruta>();
		shortestPathAux(p1, p2, marcados, resultado);

		return resultado;
	}

	/*
	 * Retorna los adyacentes segun rutas
	 */
	public ArrayList<Ruta> siguienteParada(Nodo p) {
		return aristas.stream().filter(e -> e.getPInicial().equals(p)).collect(Collectors.toCollection(ArrayList::new));
	}

	private void shortestPathAux(Nodo p1, Nodo p2, List<Ruta> marcados, List<List<Ruta>> todas) {

		List<Ruta> adyacentes = this.siguienteParada(p1);
		List<Ruta> copiaMarcados = null;

		for (Ruta ady : adyacentes) {
			copiaMarcados = marcados.stream().collect(Collectors.toList());

			if (ady.getPFinal().equals(p2)) {
				copiaMarcados.add(ady);
				todas.add(new ArrayList<Ruta>(copiaMarcados));
			} else {
				if (!copiaMarcados.contains(ady)) {
					copiaMarcados.add(ady);
					this.shortestPathAux(ady.getPFinal(), p2, copiaMarcados, todas);
				}
			}
		}
	}

	public ArrayList<Ruta> rutaMasCorta(Nodo p1, Nodo p2) {

		double suma = 0;
		double bandera = 99999;
		ArrayList<Ruta> aux2 = new ArrayList<Ruta>();

		for (List<Ruta> a : this.todasLasRutasEntreDosParadas(p1, p2)) {
			for (Ruta r : a)
				suma += r.getDistancia();
			if (suma < bandera) {
				aux2 = (ArrayList<Ruta>) a;
				bandera = suma;
			}
			suma = 0;
		}
		return aux2;
	}

	public ArrayList<Ruta> rutaMasRapida(Nodo p1, Nodo p2) {

		double suma = 0;
		double bandera = 99999;
		ArrayList<Ruta> aux2 = new ArrayList<Ruta>();

		for (List<Ruta> a : this.todasLasRutasEntreDosParadas(p1, p2)) {
			for (Ruta r : a)
				suma += r.getTiempo();
			if (suma < bandera) {
				aux2 = (ArrayList<Ruta>) a;
				bandera = suma;
			}

			suma = 0;

		}
		return aux2;
	}

	public ArrayList<Ruta> rutaMasBarata(Nodo p1, Nodo p2) {

		double suma = 0;
		double bandera = 99999;
		ArrayList<Ruta> aux2 = new ArrayList<Ruta>();

		for (List<Ruta> a : this.todasLasRutasEntreDosParadas(p1, p2)) {
			for (Ruta r : a)
				suma += r.getPrecio();
			if (suma < bandera) {
				aux2 = (ArrayList<Ruta>) a;
				bandera = suma;
			}

			suma = 0;

		}
		return aux2;
	}

	public Bus busQueCoincide(int n, Linea l) {
		return l.getBuses().stream().filter(b -> b.getNumero() == n).findFirst().get();

	}

	public Linea getLinea(Bus b) {
		return listaLineas.stream().filter(e -> e.getBuses().contains(b)).findFirst().get();
	}

}
