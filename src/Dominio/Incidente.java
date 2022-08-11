package Dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

import Exceptions.FechaIncidenteException;

public class Incidente implements Comparable<Incidente> {

	static int contador = 0;
	private int id;
	private Nodo parada;
	private LocalDate inicio, fin;
	private String descripcion;
	private boolean activa;

	public Incidente(LocalDate inicio, LocalDate fin, String d, Nodo p) throws FechaIncidenteException {

		if (fin != null) {
			if (inicio.isAfter(fin))
				throw new FechaIncidenteException("La fecha de inicio debe ser anterior a la fecha de fin");
		}
		this.inicio = inicio;
		this.fin = fin;
		this.descripcion = d;
		this.activa = true;
		this.parada = p;
		p.setEstado(false);
		this.id = contador;
		contador++;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setFin(String f) {
		this.fin = LocalDate.parse(f);
	}

	public LocalDate getFin() {
		return fin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Nodo getParada() {
		return parada;
	}

	public int getId() {
		return id;
	}

	public void actualizarEstado() {

		if (LocalDate.now().compareTo(getFin()) >= 0)
			setActiva(false);

	}

	public Optional<Long> getDuracionEnDias() {
		if (getInicio() != null && getFin() != null) {
			return Optional.of(getInicio().until(getFin(), ChronoUnit.DAYS));
		} else
			return Optional.empty();
	}

	public Long getDuracionAux() {
		return getInicio().until(LocalDate.now(), ChronoUnit.DAYS);

	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidente other = (Incidente) obj;
		return id == other.id;
	}

	@Override
	public int compareTo(Incidente o) {

		if (getFin() != null && o.getFin() != null) {
			return getDuracionEnDias().get().compareTo(o.getDuracionEnDias().get());

		} else if (getFin() == null && o.getFin() != null) {
			return getDuracionAux().compareTo(o.getDuracionEnDias().get());

		} else if (getFin() != null && o.getFin() == null) {
			return getDuracionEnDias().get().compareTo(o.getDuracionAux());

		} else {
			return getDuracionAux().compareTo(o.getDuracionAux());

		}
	}

	@Override
	public String toString() {
		return "Incidente [id=" + id + "\nparada=" + parada + "\ninicio=" + inicio + "\nfin=" + fin + "\ndescripcion="
				+ descripcion + "\nactiva=" + activa + "]\n";
	}

	public boolean menorQue(Incidente incidente) {
		return (this.compareTo(incidente) < 0);
	}

	public boolean mayorQue(Incidente incidente) {
		return (this.compareTo(incidente) > 0);
	}

	public void desactivar() {
		this.activa = false;

	}

}
