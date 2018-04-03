package es.fpdual.eadmin.eadmin.modelo.builder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public class ExpedienteBuilderTest {

	ExpedienteBuilder expedienteBuilder;
	private Expediente expediente = mock(Expediente.class);
	ArrayList<Documento> listaDocumentoDePrueba = mock(ArrayList.class);

	@Before
	public void InstanciarObjetos() {
		expedienteBuilder = new ExpedienteBuilder();
	}

	@Test
	public void testConstruir() {
		expedienteBuilder.conCodigo(1);
		expediente = expedienteBuilder.construir();

		assertEquals(Integer.valueOf(1), expediente.getCodigo());
	}

	@Test
	public void testConCodigo() {

		expedienteBuilder.conCodigo(1);

		assertEquals(Integer.valueOf(1), expedienteBuilder.construir().getCodigo());
	}

	@Test
	public void testConNombre() {

		expedienteBuilder.conNombre("nombre");

		assertEquals("nombre", expedienteBuilder.construir().getNombre());
	}

	@Test
	public void testConFechaCreacion() {

		expedienteBuilder.conFechaCreacion(new Date(16 / 12 / 2002));

		assertEquals(new Date(16 / 12 / 2002), expedienteBuilder.construir().getFechaCreacion());

	}

	@Test
	public void testConFechaArchivado() {

		expedienteBuilder.conFechaArchivado(new Date(17 / 12 / 2002));

		assertEquals(new Date(17 / 12 / 2002), expedienteBuilder.construir().getFechaArchivado());

	}

	@Test
	public void testConPublico() {

		expedienteBuilder.conPublico(true);

		assertEquals(true, expedienteBuilder.construir().getPublico());

	}

	@Test
	public void testConEstado() {

		expedienteBuilder.conEstado(EstadoExpediente.ARCHIVADO);

		assertEquals(EstadoExpediente.ARCHIVADO, expedienteBuilder.construir().getEstado());

	}

	@Test
	public void testConListaDocumento() {
		expedienteBuilder.conListaDocumento(listaDocumentoDePrueba);

		assertEquals(listaDocumentoDePrueba, expedienteBuilder.construir().getListaDocumento());

	}

	@Test
	public void testConFechaUltimaModificacion() {

		expedienteBuilder.conFechaUltimaModificacion(new Date(19 / 12 / 2002));

		assertEquals(new Date(19 / 12 / 2002), expedienteBuilder.construir().getFechaUltimaModificacion());

	}

	@Test
	public void testClonar() {
		Expediente expedienteParaClonar = new Expediente(1, "nombre", new Date(19 / 12 / 2002),
				new Date(19 / 12 / 2002), true, EstadoExpediente.ARCHIVADO, listaDocumentoDePrueba, new Date(19 / 12 / 2002));
		Expediente expedienteClonado = expedienteBuilder.clonar(expedienteParaClonar).construir();

		assertEquals(Integer.valueOf(1), expedienteClonado.getCodigo());
		assertEquals("nombre", expedienteClonado.getNombre());
		assertEquals(new Date(19/12/2002), expedienteClonado.getFechaCreacion());
		assertEquals(new Date(19/12/2002),expedienteClonado.getFechaArchivado());
		assertEquals(true,expedienteClonado.getPublico());
		assertEquals(EstadoExpediente.ARCHIVADO,expedienteClonado.getEstado());
		assertEquals(listaDocumentoDePrueba, expedienteClonado.getListaDocumento());
		assertEquals(new Date(19/12/2002),expedienteClonado.getFechaUltimaModificacion());

	}

}
