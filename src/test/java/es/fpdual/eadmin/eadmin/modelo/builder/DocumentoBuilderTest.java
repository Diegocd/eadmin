package es.fpdual.eadmin.eadmin.modelo.builder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class DocumentoBuilderTest {
	DocumentoBuilder documentoBuilder;
	Documento documentoParaProbar;

	@Before
	public void InstanciarObjetos() {
		documentoBuilder = new DocumentoBuilder();
		documentoParaProbar = new Documento(1, "nombre", new Date(12 / 12 / 2012), true, EstadoDocumento.ACTIVO,
				new Date(12 / 12 / 2013));
	}

	@Test
	public final void testConstruir() {
		Documento documento = documentoBuilder.construir();

		assertEquals(null, documento.getCodigo());

	}

	@Test
	public final void testConCodigo() {

		documentoBuilder.conCodigo(1);

		assertEquals(Integer.valueOf(1), documentoBuilder.construir().getCodigo());

	}

	@Test
	public final void testConNombre() {

		documentoBuilder.conNombre("nombre");

		assertEquals("nombre", documentoBuilder.construir().getNombre());

	}

	@Test
	public final void testConFechaAlta() {

		documentoBuilder.conFechaAlta(new Date(20 / 02 / 2002));

		assertEquals(new Date(20 / 02 / 2002), documentoBuilder.construir().getFechaCreacion());

	}

	@Test
	public final void testConPublico() {

		documentoBuilder.conPublico(true);

		assertEquals(true, documentoBuilder.construir().getPublico());

	}

	@Test
	public final void testConEstado() {

		documentoBuilder.conEstado(EstadoDocumento.ACTIVO);

		assertEquals(EstadoDocumento.ACTIVO, documentoBuilder.construir().getEstado());

	}

	@Test
	public final void testConFechaUltimaModificacion() {

		documentoBuilder.conFechaUltimaModificacion(new Date(12 / 12 / 2012));

		assertEquals(new Date(12 / 12 / 2012), documentoBuilder.construir().getFechaUltimaModificacion());

	}

	@Test
	public final void testActualizarDocumentoEncriptado() {
		String documentoEncriptadoInicial = documentoBuilder.construir().getDocumentoEncriptado();
		documentoBuilder.conCodigo(1);

		assertNotEquals(documentoEncriptadoInicial, documentoBuilder.construir().getDocumentoEncriptado());
	}

	@Test
	public final void testClonar() {

		Documento documentoClonado = documentoBuilder.clonar(documentoParaProbar).construir();

		assertEquals(Integer.valueOf(1), documentoClonado.getCodigo());
		assertEquals("nombre", documentoClonado.getNombre());
		assertEquals(new Date(12 / 12 / 2012), documentoClonado.getFechaCreacion());
		assertEquals(true, documentoClonado.getPublico());
		assertEquals(EstadoDocumento.ACTIVO, documentoClonado.getEstado());
		assertEquals(new Date(12 / 12 / 2013), documentoClonado.getFechaUltimaModificacion());
		assertEquals(documentoParaProbar.getDocumentoEncriptado(), documentoClonado.getDocumentoEncriptado());

	}

}
