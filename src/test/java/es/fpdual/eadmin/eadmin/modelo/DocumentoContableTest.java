package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

public class DocumentoContableTest {

	private static final Integer CODIGO = 1;
	private static final String NOMBRE = "Nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Boolean PUBLICO = true;
	private static final EstadoDocumento ESTADO = EstadoDocumento.ACTIVO;
	private static final BigDecimal IMPORTE = new BigDecimal(5000);
	private static final String DNIDEINTERESADO = "15404015F";
	private static String documentoContableEncriptado;
	DocumentoContable documentoContable, documentoContableConEncriptacion;

	@Before
	public void InstanciasDeObjetos() {

		documentoContable = new DocumentoContable(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, ESTADO, IMPORTE,
				DNIDEINTERESADO, FECHA_CREACION);
		documentoContableEncriptado = DigestUtils.shaHex(
				CODIGO + NOMBRE + FECHA_CREACION + PUBLICO + ESTADO + FECHA_CREACION + IMPORTE + DNIDEINTERESADO);
		documentoContableConEncriptacion = new DocumentoContable(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, ESTADO, IMPORTE,
				DNIDEINTERESADO, FECHA_CREACION, documentoContableEncriptado);
	}

	@Test
	public final void testToString() {

		assertEquals(documentoContable.toString(),
				"Documento con código = " + CODIGO + " Importe = " + IMPORTE + " DNI_Interesado = " + DNIDEINTERESADO);

	}

	@Test
	public final void testGetters() {

		assertEquals(documentoContable.getImporte(), IMPORTE);
		assertEquals(documentoContable.getDNI_Interesado(), DNIDEINTERESADO);
		assertEquals(documentoContableEncriptado, documentoContable.getDocumentoContableEncriptado());

	}

	@Test
	public final void testGenerarDocumentoContableEncriptado() {

		assertEquals(documentoContableEncriptado, documentoContable.generarDocumentoContableEncriptado());
	}

	@Test
	public final void testValidarDocumentoContableEncriptado() {

		assertEquals(documentoContableEncriptado,
				documentoContable.verificarDocumentoContableEncriptado(documentoContableEncriptado));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void testValidarDocumentoContableEncriptadoQueFalla() {

				documentoContable.verificarDocumentoContableEncriptado("prueba de encriptacion");
	} 

}
