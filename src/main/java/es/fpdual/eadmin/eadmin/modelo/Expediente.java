package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;
import java.util.List;

public class Expediente extends ModeloAdminElectronica {

	private final Date fechaArchivado;
	private final EstadoExpediente estado;
	private final List<Documento> listaDocumento;

	public Expediente(Integer codigo, String nombre, Date fechaCreacion, Date fechaArchivado, Boolean publico,
			EstadoExpediente estado, List<Documento> listaDocumento, Date fechaUltimaModificacion) {
		super(codigo, nombre, fechaCreacion, publico, fechaUltimaModificacion);
		this.fechaArchivado = fechaArchivado;
		this.estado = estado;
		this.listaDocumento = listaDocumento;
	}

	public Date getFechaArchivado() {
		return fechaArchivado;
	}

	public EstadoExpediente getEstado() {
		return estado;
	}

	public List<Documento> getListaDocumento() {
		return listaDocumento;
	}

	@Override
	public int hashCode() {
		return codigo.hashCode() + nombre.hashCode() + fechaCreacion.hashCode() + fechaArchivado.hashCode()
				+ publico.hashCode() + estado.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Expediente) {
			return codigo.equals(((Expediente) obj).getCodigo()) && nombre.equals(((Expediente) obj).getNombre())
					&& fechaCreacion.equals(((Expediente) obj).getFechaCreacion())
					&& fechaArchivado.equals(((Expediente) obj).getFechaArchivado())
					&& publico.equals(((Expediente) obj).getPublico()) && estado.equals(((Expediente) obj).getEstado());
		}
		return false;

	}

	@Override
	public String toString() {
		return "Expediente código = " + codigo + ", nombre = " + nombre + ", fechaCreacion = " + fechaCreacion
				+ ", fechaArchivado = " + fechaArchivado + ", publico = " + publico + ", estado = " + estado;
	}

}
