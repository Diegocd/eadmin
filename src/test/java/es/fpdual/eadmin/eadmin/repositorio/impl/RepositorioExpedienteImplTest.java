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
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioExpedienteImpl;

public class RepositorioExpedienteImplTest {

	private RepositorioExpedienteImpl repositorioExpedienteImpl;
	private Expediente expediente = mock(Expediente.class);
	private Documento documento = mock(Documento.class);
	

	@Before
	public void instaciarObjetos() {
		repositorioExpedienteImpl = new RepositorioExpedienteImpl();
		when(expediente.getCodigo()).thenReturn(1);
		when(documento.getCodigo()).thenReturn(1);
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
		repositorioExpedienteImpl.eliminarExpediente(1);

		assertTrue(repositorioExpedienteImpl.getListaExpedientes().isEmpty());

	}

	@Test
	public final void testEliminarDocumentoInexistente() {

		repositorioExpedienteImpl.eliminarExpediente(1);
		assertTrue(repositorioExpedienteImpl.getListaExpedientes().isEmpty());

	}
	
	@Test
	public final void testAsociarDocumentoAlExpediente() {
		when(expediente.getListaDocumento()).thenReturn(new ArrayList<Documento>());
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(1, documento);
		
		assertTrue(expediente.getListaDocumento().contains(documento));
	}
	
	@Test
	public final void testAsociarDocumentoAExpedienteInexistente() {
		
		assertNull(repositorioExpedienteImpl.asociarDocumentoAlExpediente(1, documento));
		
	}
	
	@Test
	public final void testAsociarDocumentoExistenteAExpediente() {
		
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(1, documento);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(1, documento);
		
		assertEquals(1, repositorioExpedienteImpl.getListaExpedientes().size());
	}
	
	@Test
	public final void testDesasociarDocumentoDelExpediente() {
		
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.asociarDocumentoAlExpediente(1, documento);
		repositorioExpedienteImpl.desasociarDocumentoDelExpediente(1, 1);
		
		assertFalse(expediente.getListaDocumento().contains(documento));
		assertTrue(expediente.getListaDocumento().isEmpty());
	}

	@Test
	public final void testDesasociarDocumentoDeExpedienteInexistente() {
		
		assertNull(repositorioExpedienteImpl.desasociarDocumentoDelExpediente(1, 1));
		assertTrue(expediente.getListaDocumento().isEmpty());
		
	}
	
	@Test
	public final void testDesasociarDocumentoInexistenteEnElExpediente() {
		
		repositorioExpedienteImpl.altaExpediente(expediente);
		repositorioExpedienteImpl.desasociarDocumentoDelExpediente(1, 1);
		
		assertFalse(expediente.getListaDocumento().contains(documento));
		assertTrue(expediente.getListaDocumento().isEmpty());
	}
	
	@Test 
	public final void testObtenerExpedientePorCodigo() {
		repositorioExpedienteImpl.getListaExpedientes().add(expediente);
		
		assertEquals(expediente, repositorioExpedienteImpl.obtenerExpedientePorCodigo(1));
		
	}
	
	@Test 
	public final void testObtenerExpedientePorCodigoInexistente() {
		
		assertEquals(null, repositorioExpedienteImpl.obtenerExpedientePorCodigo(1));
		
	}
	
	@Test
	public final void testObtenerTodosLosExpedientes() {
		
		assertEquals(repositorioExpedienteImpl.getListaExpedientes(),repositorioExpedienteImpl.obtenerTodosLosExpedientes());
	}
	

}
