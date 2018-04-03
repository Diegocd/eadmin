package es.fpdual.eadmin.eadmin.modelo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class DocumentoContable extends Documento {

	private final BigDecimal importe;
	private final String DNI_Interesado;
	private final String documentoContableEncriptado;

	public DocumentoContable(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado,
			BigDecimal imp, String DNI, Date fechaUltimaModificacion) {

		super(codigo, nombre, fechaCreacion, publico, estado, fechaUltimaModificacion);
		this.importe = imp;
		this.DNI_Interesado = DNI;
		this.documentoContableEncriptado = generarDocumentoContableEncriptado();
	}

	public DocumentoContable(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado,
			BigDecimal imp, String DNI, Date fechaUltimaModificacion, String codigoEncriptado) {

		super(codigo, nombre, fechaCreacion, publico, estado, fechaUltimaModificacion);
		this.importe = imp;
		this.DNI_Interesado = DNI;
		this.documentoContableEncriptado = verificarDocumentoContableEncriptado(codigoEncriptado);
	}

	public BigDecimal getImporte() {

		return importe;
	}

	public String getDNI_Interesado() {

		return DNI_Interesado;
	}

	public String getDocumentoContableEncriptado() {

		return documentoContableEncriptado;
	}

	public String generarDocumentoContableEncriptado() {
		return DigestUtils.shaHex(codigo + nombre + fechaCreacion + publico + estado + fechaUltimaModificacion + importe
				+ DNI_Interesado);
	}

	public String verificarDocumentoContableEncriptado(String documentoEncriptado) {
		if (!documentoEncriptado.equals(generarDocumentoContableEncriptado())) {
			throw new IllegalArgumentException("Esa encriptación de documento contable no es correcta");
		}
		return documentoEncriptado;
	}

	public String toString() {

		return super.toString() + " Importe = " + importe + " DNI_Interesado = " + DNI_Interesado;

	}

}
