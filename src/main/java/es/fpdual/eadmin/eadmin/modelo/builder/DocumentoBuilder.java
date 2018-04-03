package es.fpdual.eadmin.eadmin.modelo.builder;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class DocumentoBuilder {
	
	protected Integer codigo;
	protected String nombre;
	protected Date fechaAlta;
	protected Boolean publico;
	protected EstadoDocumento estado;
	protected Date fechaUltimaModificacion;
	protected String documentoEncriptado;
	
	public DocumentoBuilder() {
		actualizarDocumentoEncriptado();
	}
	
	public Documento construir() {
		return new Documento(codigo, nombre, fechaAlta, publico, estado, fechaUltimaModificacion);
	}
	
	public DocumentoBuilder conCodigo(Integer codigo) {
		this.codigo = codigo;
		actualizarDocumentoEncriptado();
		return this;
	}
	
	public DocumentoBuilder conNombre(String nombre) {
		this.nombre = nombre;
		actualizarDocumentoEncriptado();
		return this;
	}
	
	public DocumentoBuilder conFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
		actualizarDocumentoEncriptado();
		return this;
	}
	
	public DocumentoBuilder conPublico(Boolean publico) {
		this.publico = publico;
		actualizarDocumentoEncriptado();
		return this;
	}
	
	public DocumentoBuilder conEstado(EstadoDocumento estado) {
		this.estado = estado;
		actualizarDocumentoEncriptado();
		return this;
	}
	
	public DocumentoBuilder conFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		actualizarDocumentoEncriptado();
		return this;
	}
	
	public DocumentoBuilder clonar(Documento documento) {
		
		this.codigo = documento.getCodigo();
		this.nombre = documento.getNombre();
		this.fechaAlta = documento.getFechaCreacion();
		this.estado = documento.getEstado();
		this.publico = documento.getPublico();
		this.fechaUltimaModificacion = documento.getFechaUltimaModificacion();
		actualizarDocumentoEncriptado();
		return this;
		
	}
	
	public void actualizarDocumentoEncriptado() {
		this.documentoEncriptado = DigestUtils.shaHex(codigo + nombre + fechaAlta + estado + publico + fechaUltimaModificacion);
	}

}
