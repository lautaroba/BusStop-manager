package Aplicacion;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Grafo extends JPanel {

	private ArrayList<Punto> listaParadas;
	private ArrayList<Flecha> listaConexiones;
	static int ind = 0;

	public Grafo(Graphics g, ArrayList<Punto> listaParadas, ArrayList<Flecha> listaConexiones) {
		this.setVisible(true);
		this.setBackground(Color.decode("#eaebe8"));
		this.setBounds(10, 10, 500, 500);
		this.listaParadas = listaParadas;
		this.listaConexiones = listaConexiones;
	}

	void dibujarParadas(Graphics g1, ArrayList<Punto> listaNuevaParadas, ArrayList<Flecha> listaNuevaConexiones) {

		super.paintComponent(g1);
		Graphics2D g2 = (Graphics2D) g1;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Flecha f : listaNuevaConexiones)
			this.pintarFlecha(getGraphics(), f, f.getColor());

		for (Punto p1 : listaNuevaParadas) {
			g2.setColor(Color.BLACK);
			g2.drawString(String.valueOf(p1.getNumeroParada()), (int) p1.getX() + 2, (int) p1.getY() - 2);
			g2.setColor(p1.getColor());
			g2.fill(p1);
		}
		g2.setColor(Color.BLACK);
		g2.drawString(String.valueOf("Ciudad de Santa Fe"), 300, 50);
		this.revalidate();

	}

	public ArrayList<Punto> getListaParadas() {
		return listaParadas;
	}

	public ArrayList<Flecha> getListaConexiones() {
		return listaConexiones;
	}

	void pintarFlecha(Graphics g1, Flecha f, Color var) {

		double x1 = f.getPuntoInicio().getX() + 5;
		double y1 = f.getPuntoInicio().getY() + 5;
		double x2 = f.getPuntoFinal().getX() + 5;
		double y2 = f.getPuntoFinal().getY() + 5;

		g1.setColor(var);
		((Graphics2D) g1).setStroke(new BasicStroke(2));
		g1.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

		double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// line length
		double d = l / 10; // arrowhead distance from end of line. you can use your own value.

		double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from
														// end of the line.
		double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from
														// end of the line.

		double dx = x2 - x1, dy = y2 - y1;
		double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter
												// clockwise)
		angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand
												// what we need to do.
		if (angle < 0) {
			angle = 360 + angle; // convert negative degrees to posative degree
		}
		angle = (-1) * angle; // convert to counter clockwise mode
		angle = Math.toRadians(angle);// convert Degree to Radians
		AffineTransform at = new AffineTransform();
		at.translate(newX, newY);// transport cursor to draw arrowhead position.
		at.rotate(angle);
		((Graphics2D) g1).transform(at);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(5, 0);
		arrowHead.addPoint(-5, 5);
		arrowHead.addPoint(-2, -0);
		arrowHead.addPoint(-5, -5);
		g1.setColor(var);
		((Graphics2D) g1).fill(arrowHead);
		g1.drawPolygon(arrowHead);
		this.revalidate();
	}
}
