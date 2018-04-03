package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public class RepositorioExpedienteImplTest {

	private static final Date FECHA_CREACION = new Date();
	private RepositorioExpedienteImpl repositorioExpedienteImpl;
	private Expediente expediente;
	private Documento documento = mock(Documento.class);
	

	@Before
	public void instaciarObjetos() {
		repositorioExpedienteImpl = new RepositorioExpedienteImpl();
		expediente = new Expediente(1, "Nombre", FECHA_CREACION, FECHA_CREACION, true, EstadoExpediente.ARCHIVADO,
				new ArrayList<Documento>(), FECHA_CREACION);

	}

	@Test
	public final void testGetListaExpedientes() {

		assertTrue(repositorioExpedienteImpl.getListaExpedientes().isEmpty());

	}

	@Test
	public final void testAltaExpediente() {

		repositorioExpedienteImpl.altaExpediente(expediente);

		assertEquals(0, repositorioExpedienteImpl.getListaExpedientes().indexOf(expediente));

	}

	@Test(expected = IllegalArgumentException.class)
	public final void testAltaExpedienteRepetido() {

		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.altaExpediente(expediente);
	}

	@Test
	public final void testModificarExpediente() {

		repositorioExpedienteImpl.getListaExpedientes().add(expediente);
		repositorioExpedienteImpl.modificarExpediente(expediente);

		assertEquals(0, repositorioExpedienteImpl.getListaExpedientes().indexOf(expediente));

	}

	@Test(expected = IllegalArgumentException.class)
	public final void testModificarExpedienteInexistente() {

		repositorioExpedienteImpl.modificarExpediente(expediente);

	}

	@Test
	public final void testEliminarExpediente() {

		repositorioExpedienteImpl.getListaExpedientes().add(expediente);
		repositorioExpedienteImpl.eliminarExpediente(expediente.getCodigo());

		assertTrue(repositorioExpedienteImpl.getListaExpedientes().isEmpty());

	}

	@Test
	public final void testEliminarDocumentoInexistente() {

		repositorioExpedienteImpl.eliminarExpediente(expediente.getCodigo());
		assertTrue(repositorioExpedienteImpl.getListaExpedientes().isEmpty());

	}
	
	@Test
	public final void testAsociarDocumentoAlExpediente() {
		
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(expediente.getCodigo(), documento);
		
		assertTrue(expediente.getListaDocumento().contains(documento));
	}
	
	@Test
	public final void testAsociarDocumentoAExpedienteInexistente() {
		
		assertNull(repositorioExpedienteImpl.asociarDocumentoAlExpediente(expediente.getCodigo(), documento));
		
	}
	
	@Test
	public final void testAsociarDocumentoExistenteAExpediente() {
		
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(expediente.getCodigo(), documento);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(expediente.getCodigo(), documento);
		
		assertEquals(1, repositorioExpedienteImpl.getListaExpedientes().size());
	}
	
	@Test
	public final void testDesasociarDocumentoDelExpediente() {
		
		when(documento.getCodigo()).thenReturn(1);
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(expediente.getCodigo(), documento);
		repositorioExpedienteImpl.desasociarDocumentoDelExpediente(expediente.getCodigo(), documento.getCodigo());
		
		assertFalse(expediente.getListaDocumento().contains(documento));
	}

	@Test
	public final void testDessociarDocumentoDeExpedienteInexistente() {
		
		assertNull(repositorioExpedienteImpl.desasociarDocumentoDelExpediente(expediente.getCodigo(), documento.getCodigo()));
		
	}
	
	@Test
	public final void testDesasociarDocumentoInexistenteEnElExpediente() {
		
		when(documento.getCodigo()).thenReturn(1);
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.desasociarDocumentoDelExpediente(expediente.getCodigo(), documento.getCodigo());
		
		assertFalse(expediente.getListaDocumento().contains(documento));
	}
	

}
