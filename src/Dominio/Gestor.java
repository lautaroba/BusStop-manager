package Dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Gestor {
	
	private ArrayList<Linea> listaLineas;
	private ArrayList<Calle> listaCaminos;  // aristas
	private ArrayList<Parada> listaParadas;  // nodos
	private ArrayList<Incidente> listaIncidentes;
	
	public Gestor() {
		this.listaLineas = new ArrayList<Linea>();
		this.listaCaminos = new ArrayList<Calle>();
		this.listaParadas = new ArrayList<Parada>();
		this.listaIncidentes = new ArrayList<Incidente>();
	}
	
	public void agregarLinea(Linea l) {
		this.listaLineas.add(l);
	}
	
	public void agregarParada(Parada p) {
		this.listaParadas.add(p);
	}
	
	public void agregarCamino(Parada p1, Parada p2, double longitud) {
		this.listaCaminos.add(new Calle(p1, p2, longitud));
		//this.listaCaminos.add(new Calle(longitud, p2, p1)); NO SE SI HACERLO IDA Y VUELTA, evaluar
	}
	
	public void agregarIncidente(String i, String f, String s, Parada p) {
		
		this.listaIncidentes.add(new Incidente(i, f, s, p));
		
		if(LocalDate.parse(i).equals(java.time.LocalDate.now())) {
			
			for(Parada j: listaParadas ) {
			if( j.equals(p)){
				j.setEstado(false);
				//System.out.println(" la parada es " + j.getNroParada());
				}
			}
			calcularParadas(p);
		}
	}
	
	public void terminarIncidente(Incidente i) {
		Parada p;
		p = i.getParada();
		for(Parada j: listaParadas ) {
			if( j.equals(p))
				j.setEstado(true);
		}
		listaIncidentes.remove(i);
	}
	
	public ArrayList<Parada> getAdyacentes(Parada p){
		return listaCaminos.stream()							// si es true es q esta activo
						.filter(e -> e.getInicial().equals(p) && e.getFin().isEstado())
						.map(e -> e.getFin())
						.collect(Collectors.toCollection(ArrayList::new));
	}
	
	/*
	Solo puedo tener 1 calle entre 2 nodos, no tendria sentido lo contrario
	*/
	public double getDistancia(Parada p1, Parada p2) {
		for(Calle c : listaCaminos) {
			if(c.getInicial().equals(p1) && c.getFin().equals(p2))
				return c.getLongitud();
		}
		// AGREGAR Q SI NO EXISTE RETORNE UNA EXEPCION
		return 0;
	}
	
	/*
	Retorna la distancia total entre 2 paradas
	*/
	public Trayecto distanciaEntreDosParadas(List<Parada> p, Parada ini , Parada fin) {
        if(!p.isEmpty()) {
            Parada Actual = p.get(0);
            double suma = 0;
            for(Parada i : p) {
                if(!i.equals(Actual)) {
                    suma += this.getDistancia(Actual, i);
                }
                Actual = i;
            }
            return new Trayecto(ini, fin, suma, p);
        }
        else {
            return null;
        }
    }
	
//	/*
//	Retorna el Calle mas corto de p1 a p2
//	*/
//	public Calle buscarCaminoMasCorto(Parada p1, Parada p2) {
//		
//		Trayecto aux = new Trayecto();
//		
//		ArrayList<List<Parada>> salida = new ArrayList<List<Parada>>();
//		List<Parada> marcados = new ArrayList<Parada>();
//		marcados.add(p1);
//		buscarCaminosAux(p1,p2,marcados,salida);
//		aux = salida.stream()
//				.map(e -> this.distanciaEntreDosParadas(e, p1, p2))
//				.collect(Collectors.toCollection(Trayecto::new));
//		
//		aux.sort(new Comparator<Calle>() {
//			@Override
//			public int compare(Calle c1, Calle c2) {
//				return c1.comparaCamino(c2);
//			}
//		});
//		
//		return aux.get(0);
//	}
	
	/*
	Retorna todos los caminos desde p1 a p2 + lista de paradas intermedias en cada ruta
	Analizar si se puede hacer sobrecargando con el meteodo de abajo q son similares
	*/
	public Trayecto buscarCaminos(Parada p1, Parada p2, ArrayList<List<Parada>> p) {
		
		ArrayList<List<Parada>> salida = new ArrayList<List<Parada>>();
		List<Parada> marcados = new ArrayList<Parada>();
		marcados.add(p1);
		buscarCaminosAux(p1,p2,marcados,salida);
		p = salida;
		
		Trayecto aux = new Trayecto();
		
		aux = salida.stream()
				.map(e -> this.distanciaEntreDosParadas(e, p1, p2))
				.collect();
		return aux;
	}
	
	/*
	Retorna todos los caminos desde p1 a p2 pero solo difieren sus distancias
	*/
	public Trayecto buscarCaminos(Parada p1, Parada p2) {
		
		ArrayList<List<Parada>> salida = new ArrayList<List<Parada>>();
		List<Parada> marcados = new ArrayList<Parada>();
		marcados.add(p1);
		buscarCaminosAux(p1,p2,marcados,salida);
		
		return salida.stream()
				.map(e -> this.distanciaEntreDosParadas(e, p1, p2))
				.collect(Collectors.toCollection(Trayecto::new));
		
	}
	
	/*
	Retorna todas las combinaciones de calles entre 2 paradas , tambien una lista con todas las paradas intermedias
	*/
	private void buscarCaminosAux(Parada p1, Parada p2, List<Parada> marcados, List<List<Parada>> todos) {
		
		List<Parada> adyacentes = this.getAdyacentes(p1);
		List<Parada> copiaMarcados =null;
		for(Parada ady: adyacentes){
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			
			if(ady.equals(p2)) {
				copiaMarcados.add(p2);
				todos.add(new ArrayList<Parada>(copiaMarcados));
			}
			else {
				if( !copiaMarcados.contains(ady)) {
					copiaMarcados.add(ady);
					this.buscarCaminosAux(ady,p2,copiaMarcados,todos);
				}
			}
		}
	}
	
	/*
	Propone parada nueva
	*/
	public void calcularParadas(Parada p) {
		
		Parada aux = this.getAdyacentes(p).stream().min(Comparator.comparingDouble(e -> this.getDistancia(p, e))).get();
		
		listaLineas.stream().flatMap(l -> l.getBuses().stream()).forEach(new Consumer<Bus>() {
			@Override
			public void accept(Bus b) {
				b.setAuxilia(aux);
			}
		});
	}
	
	/*
	Retorna todos las calles q recorre una linea
	*/
	public Trayecto trayectoTotalDeUnaLinea(Linea l) {
		return l.getTrayectoLinea(this);
	}
	
	/*
	Trayecto mas corto
	*/
	public Trayecto trayectoMasCorto(Parada p1, Parada p2) {
		
		Trayecto aux = new Trayecto();
		Trayecto camino = this.buscarCaminoMasCorto(p1, p2);
		
		listaLineas.stream();
		
		return aux;
	}
	
}




















