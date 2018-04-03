package es.fpdual.eadmin.eadmin.modelo.builder;

import java.util.Date;
import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public class ExpedienteBuilder {

	protected Integer codigo;
	protected String nombre;
	protected Date fechaCreacion;
	protected Boolean publico;
	protected Date fechaUltimaModificacion;
	protected Date fechaArchivado;
	protected EstadoExpediente estado;
	protected List<Documento> listaDocumento;

	public Expediente construir() {

		return new Expediente(codigo, nombre, fechaCreacion, fechaArchivado, publico, estado, listaDocumento,
				fechaUltimaModificacion);

	}

	public ExpedienteBuilder conCodigo(Integer codigo) {
		this.codigo = codigo;
		return this;
	}

	public ExpedienteBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public ExpedienteBuilder conFechaCreacion(Date fechaAlta) {
		this.fechaCreacion = fechaAlta;
		return this;
	}

	public ExpedienteBuilder conFechaArchivado(Date fechaArchivado) {
		this.fechaArchivado = fechaArchivado;
		return this;
	}

	public ExpedienteBuilder conPublico(Boolean publico) {
		this.publico = publico;
		return this;
	}

	public ExpedienteBuilder conEstado(EstadoExpediente estado) {
		this.estado = estado;
		return this;
	}

	public ExpedienteBuilder conListaDocumento(List<Documento> listaDocumento) {
		this.listaDocumento = listaDocumento;
		return this;
	}

	public ExpedienteBuilder conFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		return this;
	}

	public ExpedienteBuilder clonar(Expediente expediente) {

		this.codigo = expediente.getCodigo();
		this.nombre = expediente.getNombre();
		this.fechaCreacion = expediente.getFechaCreacion();
		this.fechaArchivado = expediente.getFechaArchivado();
		this.estado = expediente.getEstado();
		this.publico = expediente.getPublico();
		this.listaDocumento = expediente.getListaDocumento();
		this.fechaUltimaModificacion = expediente.getFechaUltimaModificacion();
		return this;

	}

}
