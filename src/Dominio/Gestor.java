package Dominio;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Gestor {
	
	private ArrayList<Linea> listaLineas;
	private ArrayList<Calle> listaCaminos;  // aristas
	private ArrayList<Parada> listaParadas;  // nodos  estas serian esquinas
	private ArrayList<Incidente> listaIncidentes;
	
	private ArrayList<Ruta> aristas;  
	private ArrayList<Parada> nodos;  // estas serian paradas
	
	public Gestor() {
		this.listaLineas = new ArrayList<Linea>();
		this.listaCaminos = new ArrayList<Calle>();
		this.listaParadas = new ArrayList<Parada>();
		this.listaIncidentes = new ArrayList<Incidente>();
		this.aristas = new ArrayList<Ruta>();
		this.nodos = new ArrayList<Parada>();
	}
	
	public void agregarLinea(Linea l) {
		this.listaLineas.add(l);
	}
	
	public void agregarParada(Parada p) {
		this.listaParadas.add(p);
	}
	
	public void agregarCamino(Parada p1, Parada p2, double longitud) {
		this.listaCaminos.add(new Calle(p1, p2, longitud));
	}
	
	public void agregarIncidente(String i, String f, String s, Parada p) {
		
		this.listaIncidentes.add(new Incidente(i, f, s, p));
		
		if(LocalDate.parse(i).equals(java.time.LocalDate.now())) {
			
			for(Parada j: listaParadas ) {
			if( j.equals(p))
				j.setEstado(false);
			}
			calcularParadas(p);
		}
	}
	
	/*
	Propone parada nueva despues de un incidente
	*/
	private void calcularParadas(Parada p) {
		
		Parada aux = this.getAdyacentes(p).stream().min(Comparator.comparingDouble(e -> this.getDistancia(p, e))).get();
		
		listaLineas.stream().flatMap(l -> l.getBuses().stream()).forEach(new Consumer<Bus>() {
			@Override
			public void accept(Bus b) {
				b.setAuxilia(aux);
			}
		});
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
	public double distanciaEntreDosParadas(List<Parada> p, Parada ini , Parada fin) {
        if(!p.isEmpty()) {
            Parada Actual = p.get(0);
            double suma = 0;
            for(Parada i : p) {
                if(!i.equals(Actual)) {
                    suma += this.getDistancia(Actual, i);
                }
                Actual = i;
            }
            return suma;
        }
        else {
            return -1;
        }
    }
	
	/*
	Retorna todos los caminos desde p1 a p2 pero solo difieren sus distancias
	distancia entre 2 paradas, hacerlo en ruta mas lindo
	*/
	public ArrayList<Ruta> buscarTodasLosCaminos(Parada p1, Parada p2) {
		
		ArrayList<List<Parada>> salida = new ArrayList<List<Parada>>();
		List<Parada> marcados = new ArrayList<Parada>();
		marcados.add(p1);
		buscarCaminosAux(p1,p2,marcados,salida);
		
		return salida.stream()
				.map(lp -> new Ruta(p1,p2, this.distanciaEntreDosParadas(lp, p1, p2), lp))
				.collect(Collectors.toCollection(ArrayList::new));		
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
				if(!copiaMarcados.contains(ady)) {
					copiaMarcados.add(ady);
					this.buscarCaminosAux(ady,p2,copiaMarcados,todos);
				}
			}
		}
	}
	
	/*
	Retorna todas las rutas desde p1 a p2 asociadas a 1 bus
	distancia entre 2 paradas, hacerlo en ruta mas lindo
	*/
	public ArrayList<Ruta> buscarTodasLasRutasDeUnBus(Parada p1, Parada p2, Bus b) {
		
		ArrayList<List<Parada>> salida = new ArrayList<List<Parada>>();
		List<Parada> marcados = new ArrayList<Parada>();
		marcados.add(p1);
		buscarCaminosAux(p1,p2,marcados,salida);
		
		Linea aux = listaLineas.stream().filter(e -> e.getBuses().contains(b)).findAny().get();
		
		return salida.stream()
				.map(lp -> new Ruta(p1,p2, this.distanciaEntreDosParadas(lp, p1, p2), 
						lp, this.distanciaEntreDosParadas(lp, p1, p2)/aux.getVelocidad(), 
						this.distanciaEntreDosParadas(lp, p1, p2) * aux.getPrecio(),b))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	/*
	Retorna todos las rutas q recorre una linea
	*/
	public ArrayList<Ruta> trayectoTotalDeUnaLinea(Linea l) {
		return l.getTrayectoLinea(this);
	}
	
	/*
	Retorna todas las rutas que existen sin importar la linea, entre 2 paradas
	*/
	public ArrayList<List<Ruta>> todasLasRutasEntreDosParadas(Parada p1, Parada p2) {
		
		listaLineas.stream()
					.map(l -> l.getTrayectoLinea(this))
					.forEach(new Consumer<ArrayList<Ruta>>() {
						 public void accept(ArrayList<Ruta> r) {
							aristas.addAll(r);
					 		for(Ruta c: r) {
					 			if(!nodos.contains(c.getPInicial()))
					 				nodos.add(c.getPInicial());
					 			if(!nodos.contains(c.getPFinal()))
					 				nodos.add(c.getPFinal());
					 		}
						}
					});
		
		ArrayList<List<Ruta>> resultado = new ArrayList<List<Ruta>>();
		List<Ruta> marcados = new ArrayList<Ruta>();
//		marcados.add(p1);
		shortestPathAux(p1,p2,marcados,resultado);
		
		return resultado;
		
//		for(Ruta c : aristas) {
//			c.Imprimir();
//		}
//		System.out.println("nodos = " + nodos.size() + " aristas = " + aristas.size());
	}
	/*
	Retorna los adyacentes segun rutas
	*/	
	public ArrayList<Ruta> siguienteParada(Parada p){
		return aristas.stream()
						.filter(e -> e.getPInicial().equals(p))
						.collect(Collectors.toCollection(ArrayList::new));
	}
	
	private void shortestPathAux(Parada p1, Parada p2, List<Ruta> marcados, List<List<Ruta>> todas) {
		
		List<Ruta> adyacentes = this.siguienteParada(p1);
		List<Ruta> copiaMarcados =null;
		
		for(Ruta ady: adyacentes){
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			
			if(ady.getPFinal().equals(p2)) {
				copiaMarcados.add(ady);
				todas.add(new ArrayList<Ruta>(copiaMarcados));
			}
			else {
				if(!copiaMarcados.contains(ady)) {
					copiaMarcados.add(ady);
					this.shortestPathAux(ady.getPFinal(),p2,copiaMarcados,todas);
				}
			}
		}
	}
	
	private double total(List<Ruta> a2) {
		double suma=0.0;
		for(Ruta a : a2) {
			suma+= a.getDistancia();
		}
		return suma;
	}
	
	public ArrayList<Ruta> rutaMasCorta(Parada p1, Parada p2){
		
		double suma=0;
		double bandera=99999;
		ArrayList<Ruta> aux2 = new ArrayList<Ruta>();
		
		for(List<Ruta> a: this.todasLasRutasEntreDosParadas(p1, p2)) {
			
			for(Ruta r : a) 
				suma+= r.getDistancia();
			
			if(suma < bandera) {
				aux2 = (ArrayList<Ruta>) a;
				bandera=suma;
			}
			suma=0;
		}
		return aux2;
	}
	
	public ArrayList<Ruta> rutaMasRapida(Parada p1, Parada p2){
		
		double suma=0;
		double bandera=99999;
		ArrayList<Ruta> aux2 = new ArrayList<Ruta>();
		
		for(List<Ruta> a: this.todasLasRutasEntreDosParadas(p1, p2)) {
			
			for(Ruta r : a) 
				suma += r.getTiempo();

			if(suma < bandera) {
				aux2 = (ArrayList<Ruta>) a;
				bandera=suma;
			}
			
			suma=0;
			
		}
		return aux2;
	}
	
	public ArrayList<Ruta> rutaMasBarata(Parada p1, Parada p2){
		
		double suma=0;
		double bandera=99999;
		ArrayList<Ruta> aux2 = new ArrayList<Ruta>();
		
		for(List<Ruta> a: this.todasLasRutasEntreDosParadas(p1, p2)) {
			
			for(Ruta r : a) 
				suma += r.getPrecio();

			if(suma < bandera) {
				aux2 = (ArrayList<Ruta>) a;
				bandera=suma;
			}
			
			suma=0;
			
		}
		return aux2;
	}
	
}




















