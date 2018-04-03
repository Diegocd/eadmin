package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class DocumentoPropuesta extends Documento {

	private final Integer codigoPropuesta;
	private final Integer ejercicio;
	private final String grupoPolitico;
	private final String documentoPropuestaEncriptado;

	public DocumentoPropuesta(Integer codigo, String nombre, Date fechaCreacion, Boolean publico,
			EstadoDocumento estado, Integer codigoProp, Integer ejercicio, String grupoPol,
			Date fechaUltimaModificacion) {
		super(codigo, nombre, fechaCreacion, publico, estado, fechaUltimaModificacion);
		this.codigoPropuesta = codigoProp;
		this.ejercicio = ejercicio;
		this.grupoPolitico = grupoPol;
		this.documentoPropuestaEncriptado = generarDocumentoPropuestaEncriptado();
	}

	public DocumentoPropuesta(Integer codigo, String nombre, Date fechaCreacion, Boolean publico,
			EstadoDocumento estado, Integer codigoProp, Integer ejercicio, String grupoPol,
			Date fechaUltimaModificacion, String documentoPropuestaEncriptado) {
		super(codigo, nombre, fechaCreacion, publico, estado, fechaUltimaModificacion);
		this.codigoPropuesta = codigoProp;
		this.ejercicio = ejercicio;
		this.grupoPolitico = grupoPol;
		this.documentoPropuestaEncriptado = verificarDocumentoPropuestaEncriptado(documentoPropuestaEncriptado);
	}

	public Integer getCodigoPropuesta() {
		return codigoPropuesta;
	}

	public Integer getEjercicio() {
		return ejercicio;
	}

	public String getGrupoPolitico() {
		return grupoPolitico;
	}

	public String getDocumentoPropuestaEncriptado() {

		return documentoPropuestaEncriptado;
	}

	public String generarDocumentoPropuestaEncriptado() {
		return DigestUtils.shaHex(codigo + nombre + fechaCreacion + publico + estado + fechaUltimaModificacion + codigoPropuesta + ejercicio + grupoPolitico);
	}
	
	public String verificarDocumentoPropuestaEncriptado(String documentoEncriptado) {
		if (!documentoEncriptado.equals(generarDocumentoPropuestaEncriptado())) {
			throw new IllegalArgumentException("Esa encriptación de documento de propuesta no es correcta");
		}
		return documentoEncriptado;
	}

	@Override
	public String toString() {
		return super.toString() + "CodigoPropuesta = " + codigoPropuesta + ", ejercicio = " + ejercicio
				+ ", grupoPolitico = " + grupoPolitico;
	}

}
