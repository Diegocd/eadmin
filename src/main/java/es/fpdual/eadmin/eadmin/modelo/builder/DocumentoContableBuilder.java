package es.fpdual.eadmin.eadmin.modelo.builder;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.DocumentoContable;

public class DocumentoContableBuilder extends DocumentoBuilder {

	protected BigDecimal importe;
	protected String DNI_Interesado;
	protected String documentoContableEncriptado;
	
	public DocumentoContableBuilder() {
		actualizarDocumentoContableEncriptado();
	}

	public DocumentoContable construir() {
		return new DocumentoContable(this.codigo, this.nombre, this.fechaAlta, this.publico, this.estado, this.importe,
				this.DNI_Interesado, this.fechaUltimaModificacion);
	}

	public DocumentoContableBuilder conImporte(BigDecimal importe) {
		this.importe = importe;
		actualizarDocumentoContableEncriptado();
		return this;
	}

	public DocumentoContableBuilder conDNI_Interesado(String DNI_Interesado) {
		this.DNI_Interesado = DNI_Interesado;
		actualizarDocumentoContableEncriptado();
		return this;
	}

	public DocumentoContableBuilder clonar(DocumentoContable documento) {
		super.clonar(documento);
		this.importe = documento.getImporte();
		this.DNI_Interesado = documento.getDNI_Interesado();
		actualizarDocumentoContableEncriptado();
		return this;
	}

	protected void actualizarDocumentoContableEncriptado() {
		this.documentoEncriptado = DigestUtils.shaHex(
				codigo + nombre + fechaAlta + estado + publico + fechaUltimaModificacion + importe + DNI_Interesado);
	}

}
