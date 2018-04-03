package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class DocumentoRegistro extends Documento{
	
	private final String DNI_Interesado;
	private final String codigoRegistro;
	private final String documentoRegistroEncriptado;

	public DocumentoRegistro(Integer codigo, String nombre, Date fechaCreacion, Boolean publico,
			EstadoDocumento estado, String DNI, String codigoReg,Date fechaUltimaModificacion) {
		super(codigo, nombre, fechaCreacion, publico, estado, fechaUltimaModificacion);
		this.codigoRegistro = codigoReg;
		this.DNI_Interesado = DNI;
		this.documentoRegistroEncriptado = generarDocumentoRegistroEncriptado();
	}
	
	public DocumentoRegistro(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado,
			String DNI, String codigoReg, Date fechaUltimaModificacion, String codigoEncriptado) {
		super(codigo, nombre, fechaCreacion, publico, estado, fechaUltimaModificacion);
		this.codigoRegistro = codigoReg;
		this.DNI_Interesado = DNI;
		this.documentoRegistroEncriptado = verificarDocumentoRegistroEncriptado(codigoEncriptado);
	}

	public String getDNI_Interesado() {
		return DNI_Interesado;
	}

	public String getCodigoRegistro() {
		return codigoRegistro;
	}
	
	public String getDocumentoRegistroEncriptado() {
		return documentoRegistroEncriptado;
	}
	
	public String generarDocumentoRegistroEncriptado() {
		return DigestUtils.shaHex(codigo + nombre + fechaCreacion + publico + estado + fechaUltimaModificacion + codigoRegistro + DNI_Interesado);
	}
	
	public String verificarDocumentoRegistroEncriptado(String documentoEncriptado) {
		if (!documentoEncriptado.equals(generarDocumentoRegistroEncriptado())) {
			throw new IllegalArgumentException("Esa encriptación de documento de registro no es correcta");
		}
		return documentoEncriptado;
	}

	@Override
	public String toString() {
		return super.toString() + "DNI_Interesado = " + DNI_Interesado + ", codigoRegistro = " + codigoRegistro;
	}
	
	

}
