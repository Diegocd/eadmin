package es.fpdual.eadmin.eadmin.servicio.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioExpedienteImpl;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ServicioExpediente;
import es.fpdual.eadmin.eadmin.servicio.Impl.ServicioDocumentoImpl;
import es.fpdual.eadmin.eadmin.servicio.Impl.ServicioExpedienteImpl;

public class ServicioExpedienteImplTest {

	private ServicioExpediente servicioExpediente;
	private static final Expediente EXPEDIENTE = mock(Expediente.class);
	private static final Documento DOCUMENTO = mock(Documento.class);
	private RepositorioExpedienteImpl repositorioExpedienteImpl = mock(RepositorioExpedienteImpl.class);

	@Before
	public void instaciarObjetosDeServicio() {
		this.servicioExpediente = new ServicioExpedienteImpl(repositorioExpedienteImpl);
	}

	@Test
	public final void testAltaExpediente() {

		when(EXPEDIENTE.getCodigo()).thenReturn(1);
		when(EXPEDIENTE.getFechaCreacion()).thenReturn(new Date(16 / 12 / 2010));
		when(EXPEDIENTE.getNombre()).thenReturn("nombre");

		final Expediente resultado = this.servicioExpediente.altaExpediente(EXPEDIENTE);

		verify(this.repositorioExpedienteImpl).altaExpediente(any());
		assertEquals(resultado.getCodigo(), EXPEDIENTE.getCodigo());
		assertEquals(resultado.getNombre(), EXPEDIENTE.getNombre());
		assertNotEquals(resultado.getFechaCreacion(), EXPEDIENTE.getFechaCreacion());

	}

	@Test
	public final void testModificarExpediente() {

		when(EXPEDIENTE.getCodigo()).thenReturn(1);
		when(EXPEDIENTE.getFechaCreacion()).thenReturn(new Date(16 / 12 / 2010));
		when(EXPEDIENTE.getNombre()).thenReturn("nombre");

		final Expediente resultado = this.servicioExpediente.modificarExpediente(EXPEDIENTE);

		verify(this.repositorioExpedienteImpl).modificarExpediente(any());
		assertEquals(resultado.getCodigo(), EXPEDIENTE.getCodigo());
		assertEquals(resultado.getNombre(), EXPEDIENTE.getNombre());
		assertNotEquals(resultado.getFechaCreacion(), resultado.getFechaUltimaModificacion());

	}

	@Test
	public final void testEliminarExpediente() {

		when(EXPEDIENTE.getCodigo()).thenReturn(1);

		this.servicioExpediente.eliminarExpediente(EXPEDIENTE.getCodigo());

		verify(this.repositorioExpedienteImpl).eliminarExpediente(1);

	}

	@Test
	public final void testAsociarDocumentoAlExpediente() {
		
		when(EXPEDIENTE.getCodigo()).thenReturn(1);
		this.servicioExpediente.asociarDocumentoAlExpediente(EXPEDIENTE.getCodigo(), DOCUMENTO);
		
		verify(this.repositorioExpedienteImpl).asociarDocumentoAlExpediente(1, DOCUMENTO);

	}

	@Test
	public final void testDesasociarDocumentoDelExpediete() {
		
		when(EXPEDIENTE.getCodigo()).thenReturn(1);
		when(DOCUMENTO.getCodigo()).thenReturn(1);
		this.servicioExpediente.desasociarDocumentoDelExpediente(EXPEDIENTE.getCodigo(), DOCUMENTO.getCodigo());
		
		verify(this.repositorioExpedienteImpl).desasociarDocumentoDelExpediente(1, 1);

	}

}
