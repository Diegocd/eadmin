package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class Documento extends ModeloAdminElectronica {

	protected final EstadoDocumento estado;
	protected final String documentoEncriptado;

	public Documento(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado,
			Date fechaUltimaModificacion) {
		super(codigo, nombre, fechaCreacion, publico, fechaUltimaModificacion);
		this.estado = estado;
		this.documentoEncriptado = generarDocumentoEncriptado();
	}
	
	public Documento(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado,
			Date fechaUltimaModificacion, String documentoEncriptado) {
		super(codigo, nombre, fechaCreacion, publico, fechaUltimaModificacion);
		this.estado = estado;
		this.documentoEncriptado = verificarDocumentoEncriptado(documentoEncriptado);
	}

	public EstadoDocumento getEstado() {
		return estado;
	}
	
	public String getDocumentoEncriptado(){
		return documentoEncriptado;
	}
	
	public String generarDocumentoEncriptado() {
		return DigestUtils.shaHex(codigo + nombre + fechaCreacion + publico + estado + fechaUltimaModificacion);
	}
	
	public String verificarDocumentoEncriptado(String documentoEncriptado) {
		if (!documentoEncriptado.equals(generarDocumentoEncriptado())) {
			throw new IllegalArgumentException("Esa encriptación de documento no es correcta");
		}
		return documentoEncriptado;
	}

	@Override
	public String toString() {
		return "Documento con código " + codigo;
	}

}
