package es.fpdual.eadmin.eadmin.modelo.builder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito.*;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.DocumentoContable;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class DocumentoContableBuilderTest {

	DocumentoContableBuilder documentoContableBuilder;
	private Documento documento = mock(Documento.class);

	@Before
	public void InstanciarObjetos() {
		documentoContableBuilder = new DocumentoContableBuilder();
	}

	@Test
	public final void testConstruir() {

		documento = documentoContableBuilder.construir();

		assertEquals(null, documento.getCodigo());

	}

	@Test
	public final void testConImporte() {

		documentoContableBuilder.conImporte(new BigDecimal(5000));

		assertEquals(new BigDecimal(5000), documentoContableBuilder.construir().getImporte());

	}

	@Test
	public final void testConDNI_Interesado() {

		documentoContableBuilder.conDNI_Interesado("15404087E");

		assertEquals("15404087E", documentoContableBuilder.construir().getDNI_Interesado());

	}
	
	@Test
	public final void testConActualizarDocumentoEncriptado() {
		String documentoEncriptadoInicial = documentoContableBuilder.construir().getDocumentoContableEncriptado();
		documentoContableBuilder.conCodigo(1);

		assertNotEquals(documentoEncriptadoInicial, documentoContableBuilder.construir().getDocumentoContableEncriptado());
	}

	@Test
	public final void testClonarDocumentoContable() {
		
		DocumentoContable documentoContableParaClonar = new DocumentoContable(1,"nombre",new Date(12/12/2012), true, EstadoDocumento.ACTIVO, new BigDecimal(5000),
				"15404087E", new Date(12/12/2013));
		DocumentoContable documentoContableClonado = documentoContableBuilder.clonar(documentoContableParaClonar).construir();
		
		assertEquals(Integer.valueOf(1),documentoContableClonado.getCodigo());
		assertEquals("nombre",documentoContableClonado.getNombre());
		assertEquals(new Date(12/12/2012), documentoContableClonado.getFechaCreacion());
		assertEquals(true, documentoContableClonado.getPublico());
		assertEquals(EstadoDocumento.ACTIVO, documentoContableClonado.getEstado());
		assertEquals(new Date(12/12/2013), documentoContableClonado.getFechaUltimaModificacion());
		assertEquals(new BigDecimal(5000), documentoContableClonado.getImporte());
		assertEquals("15404087E", documentoContableClonado.getDNI_Interesado());
		assertEquals(documentoContableParaClonar.getDocumentoContableEncriptado(),documentoContableClonado.getDocumentoContableEncriptado());

	}

}
