package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.ModeloAdminElectronicaTest.ModeloAdminElectronicaFake;

public class DocumentoTest {

	private static final Integer CODIGO = 1;
	private static final String NOMBRE = "Nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Boolean PUBLICO = true;
	private static String documentoEncriptado;
	Documento documento, documentoConEncriptacion;

	@Before
	public void instanciarObjetos() {

		documento = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, FECHA_CREACION);
		documentoEncriptado = DigestUtils
				.shaHex(CODIGO + NOMBRE + FECHA_CREACION + PUBLICO + EstadoDocumento.ACTIVO + FECHA_CREACION);
		documentoConEncriptacion = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO,
				FECHA_CREACION, documentoEncriptado);

	}

	@Test
	public final void testHashCode() {

		assertEquals(documento.hashCode(), CODIGO.hashCode());

	}

	@Test
	public final void testGetters() {

		assertEquals(EstadoDocumento.ACTIVO, documento.getEstado());
		assertEquals(documentoEncriptado, documento.getDocumentoEncriptado());

	}

	@Test
	public final void testGenerarDocumentoEncriptado() {

		assertEquals(documentoEncriptado, documento.generarDocumentoEncriptado());
	}

	@Test
	public final void testVerificarDocumentoEncriptado() {

		assertEquals(documentoEncriptado, documento.verificarDocumentoEncriptado(documentoEncriptado));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void testValidarDocumentoEncriptadoQueFalla() {

				documento.verificarDocumentoEncriptado("prueba de encriptacion");
	}

	@Test
	public final void testToString() {

		assertEquals("Documento con código = 1", documento.toString());

	}

}
