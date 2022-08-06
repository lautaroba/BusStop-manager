package Dominio;

public class AppEliminar {
	
	public static void main(String[] args) {
		
		Gestor g = new Gestor();
		
		Parada p1 = new Parada(1, "Piedras");
		Parada p2 = new Parada(2, "Lavalle");
		Parada p3 = new Parada(3, "Roberto Godoy");
		Parada p4 = new Parada(4, "Obispo Boneo");
		Parada p5 = new Parada(5, "Obispo Principe");
		Parada p6 = new Parada(6, "Marcial Candioti");
		Parada p7 = new Parada(7, "General Paz");
		Parada p8 = new Parada(8, "Boulevard Galves");
		Parada p9 = new Parada(9, "Avenida Almirante Brown");
		Parada p10 = new Parada(10, "Avenida Alejandro Alem");
		Parada p11 = new Parada(11, "General Risso"); 
		
		g.agregarParada(p1);
		g.agregarParada(p2);
		g.agregarParada(p3);
		g.agregarParada(p4);
		g.agregarParada(p5);
		g.agregarParada(p6);
		g.agregarParada(p7);
		g.agregarParada(p8);
		g.agregarParada(p9);
		g.agregarParada(p10);
		g.agregarParada(p11);
		
		Linea sup = new Superior("Linea 1", "Azul", 100, TipoServicio.AirConditioner, 60);
		Linea eco = new Economica("Linea 1", "Azul", 100, 0.4, 40);
		
		g.agregarLinea(sup);
		g.agregarLinea(eco);
		
		g.agregarCamino(p1,p2,5);
		g.agregarCamino(p1,p6,7);
		g.agregarCamino(p1,p7,4);
		g.agregarCamino(p2,p3,10);
		g.agregarCamino(p2,p5,20);
		g.agregarCamino(p3,p4,3);
		g.agregarCamino(p4,p6,3);
		g.agregarCamino(p4,p10,5);
		g.agregarCamino(p5,p11,4);
		g.agregarCamino(p6,p7,5);
		g.agregarCamino(p6,p9,4);
		g.agregarCamino(p7,p8,4);
		g.agregarCamino(p8,p1,12);
		g.agregarCamino(p8,p9,5);
		g.agregarCamino(p9,p11,10);
		g.agregarCamino(p9,p3,25);
		g.agregarCamino(p10,p9,4);
		g.agregarCamino(p11,p1,35);
		
		Bus b1 = new Bus(p1, p11, 1);
		Bus b2 = new Bus(p7, p1, 4);
		
		sup.setBuses(b1);
		sup.setBuses(b2);
		
		Bus b3 = new Bus(p1, p4, 1);
		Bus b4 = new Bus(p4, p8, 2);
		Bus b5 = new Bus(p2, p10, 3);
		
		eco.setBuses(b3);
		eco.setBuses(b4);
		eco.setBuses(b5);
		
		//g.agregarIncidente("2022-08-03", "2022-09-26", "Alto accidente asheee", p4);
		
//		for(Calle h : g.buscarCaminos(p1, p11)) {
//			h.Imprimir();
//		}
//		System.out.println("Echo");	
//		g.buscarCaminoMasCorto(p1, p11).Imprimir();
		
//		for(Calle h : g.trayectoTotalDeUnaLinea(eco)) {
//		h.Imprimir();
//		}
//		System.out.println("            ");
//		for(Calle h : g.trayectoMasCorto()) {
//			h.Imprimir();
//		}
		
	
		System.out.println("Echo");	
	}
	
}
