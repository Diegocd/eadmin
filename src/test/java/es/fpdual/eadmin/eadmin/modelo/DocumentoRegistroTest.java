package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

public class DocumentoRegistroTest {

	private static final Integer CODIGO = 1;
	private static final String NOMBRE = "Nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Boolean PUBLICO = true;
	private static final EstadoDocumento ESTADO = EstadoDocumento.ACTIVO;
	private static final String CODIGOREGISTRO = "11235813";
	private static final String DNIDEINTERESADO = "15404015D";
	private static String documentoRegistroEncriptado;
	DocumentoRegistro documentoRegistro, documentoRegistroConEncriptacion;

	@Before
	public void InstanciasDeObjetos() {

		documentoRegistro = new DocumentoRegistro(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, ESTADO, DNIDEINTERESADO,
				CODIGOREGISTRO, FECHA_CREACION);
		documentoRegistroEncriptado = DigestUtils.shaHex(CODIGO + NOMBRE + FECHA_CREACION + PUBLICO + ESTADO
				+ FECHA_CREACION + CODIGOREGISTRO + DNIDEINTERESADO);
		documentoRegistroConEncriptacion = new DocumentoRegistro(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, ESTADO, DNIDEINTERESADO,
				CODIGOREGISTRO, FECHA_CREACION, documentoRegistroEncriptado);

	}

	@Test
	public void testToString() {

		assertEquals(documentoRegistro.toString(), "Documento con código = " + CODIGO + "DNI_Interesado = "
				+ DNIDEINTERESADO + ", codigoRegistro = " + CODIGOREGISTRO);

	}

	@Test
	public void testGetters() {

		assertEquals(documentoRegistro.getCodigoRegistro(), CODIGOREGISTRO);
		assertEquals(documentoRegistro.getDNI_Interesado(), DNIDEINTERESADO);
		assertEquals(documentoRegistroEncriptado, documentoRegistro.getDocumentoRegistroEncriptado());

	}

	@Test
	public final void testGenerarDocumentoRegistroEncriptado() {

		assertEquals(documentoRegistroEncriptado, documentoRegistro.generarDocumentoRegistroEncriptado());
	}

	@Test
	public final void testVerificarDocumentoRegistroEncriptado() {

		assertEquals(documentoRegistroEncriptado,
				documentoRegistro.verificarDocumentoRegistroEncriptado(documentoRegistroEncriptado));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void testValidarDocumentoRegistroEncriptadoQueFalla() {

				documentoRegistro.verificarDocumentoRegistroEncriptado("prueba de encriptacion");
	}

}
