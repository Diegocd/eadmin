package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

public class DocumentoPropuestaTest {

	private static final Integer CODIGO = 1;
	private static final String NOMBRE = "Nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Boolean PUBLICO = true;
	private static final EstadoDocumento ESTADO = EstadoDocumento.ACTIVO;
	private static final Integer CODIGOPROPUESTA = 11;
	private static final Integer EJERCICIO = 2010;
	private static final String GRUPOPOLITICO = "PP";
	private static String documentoPropuestaEncriptado;
	DocumentoPropuesta documentoPropuesta, documentoPropuestaConEncriptacion;

	@Before
	public void InstanciasDeObjetos() {

		documentoPropuesta = new DocumentoPropuesta(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, ESTADO, CODIGOPROPUESTA,
				EJERCICIO, GRUPOPOLITICO, FECHA_CREACION);
		documentoPropuestaEncriptado = DigestUtils.shaHex(CODIGO + NOMBRE + FECHA_CREACION + PUBLICO + ESTADO
				+ FECHA_CREACION + CODIGOPROPUESTA + EJERCICIO + GRUPOPOLITICO);
		documentoPropuestaConEncriptacion = new DocumentoPropuesta(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, ESTADO, CODIGOPROPUESTA,
				EJERCICIO, GRUPOPOLITICO, FECHA_CREACION, documentoPropuestaEncriptado);
	}

	@Test
	public final void testToString() {

		assertEquals(documentoPropuesta.toString(), "Documento con código = " + CODIGO + "CodigoPropuesta = "
				+ CODIGOPROPUESTA + ", ejercicio = " + EJERCICIO + ", grupoPolitico = " + GRUPOPOLITICO);

	}

	@Test
	public final void testGetters() {

		assertEquals(documentoPropuesta.getCodigoPropuesta(), CODIGOPROPUESTA);
		assertEquals(documentoPropuesta.getEjercicio(), EJERCICIO);
		assertEquals(documentoPropuesta.getGrupoPolitico(), GRUPOPOLITICO);
		assertEquals(documentoPropuestaEncriptado, documentoPropuesta.getDocumentoPropuestaEncriptado());

	}

	@Test
	public final void testGenerarDocumentoEncriptado() {

		assertEquals(documentoPropuestaEncriptado, documentoPropuesta.generarDocumentoPropuestaEncriptado());
	}

	@Test
	public final void testVerificarDocumentoEncriptado() {

		assertEquals(documentoPropuestaEncriptado,
				documentoPropuesta.verificarDocumentoPropuestaEncriptado(documentoPropuestaEncriptado));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void testValidarDocumentoPropuestaEncriptadoQueFalla() {

				documentoPropuesta.verificarDocumentoPropuestaEncriptado("prueba de encriptacion");
	}

}
