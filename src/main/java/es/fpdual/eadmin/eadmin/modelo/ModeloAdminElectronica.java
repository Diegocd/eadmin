package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public abstract class ModeloAdminElectronica {

	protected final Integer codigo;
	protected final String nombre;
	protected final Date fechaCreacion;
	protected final Boolean publico;
	protected final Date fechaUltimaModificacion;

	public ModeloAdminElectronica(Integer cod, String nomb, Date fechaCreacion, Boolean publico, Date fechaUltimaModificacion) {

		codigo = cod;
		nombre = nomb;
		this.fechaCreacion = fechaCreacion;
		this.publico = publico;
		this.fechaUltimaModificacion = fechaUltimaModificacion;

	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public Boolean getPublico() {
		return publico;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	@Override
	public int hashCode() {
		return codigo.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof ModeloAdminElectronica) {
			return codigo.equals(((ModeloAdminElectronica) obj).getCodigo());
		}
		return false;
	}

}
