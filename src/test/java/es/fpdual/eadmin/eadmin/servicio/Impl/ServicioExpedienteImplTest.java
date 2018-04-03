package es.fpdual.eadmin.eadmin.servicio.Impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private ServicioExpedienteImpl servicioExpediente;
	private static final Expediente EXPEDIENTE = mock(Expediente.class);
	private static final Documento DOCUMENTO = mock(Documento.class);
	private RepositorioExpedienteImpl repositorioExpediente = mock(RepositorioExpedienteImpl.class);

	@Before
	public void instaciarObjetosDeServicio() {
		this.servicioExpediente = spy(new ServicioExpedienteImpl(repositorioExpediente));
		when(EXPEDIENTE.getCodigo()).thenReturn(1);
		when(EXPEDIENTE.getFechaCreacion()).thenReturn(new Date(16 / 12 / 2010));
		when(EXPEDIENTE.getNombre()).thenReturn("nombre");
	}

	@Test
	public final void testAltaExpediente() {

		final Expediente expedienteModificado = mock(Expediente.class);
		doReturn(expedienteModificado).when(servicioExpediente).obtenerExpedienteConFechaCorrectaParaAlta(EXPEDIENTE);
		final Expediente resultado = this.servicioExpediente.altaExpediente(EXPEDIENTE);

		verify(this.repositorioExpediente).altaExpediente(expedienteModificado);
		assertSame(resultado, expedienteModificado);

	}

	@Test
	public final void testModificarExpediente() {
		final Expediente expedienteModificado = mock(Expediente.class);
		doReturn(expedienteModificado).when(servicioExpediente)
				.obtenerExpedienteConFechaCorrectaParaActualizar(EXPEDIENTE);
		final Expediente resultado = this.servicioExpediente.modificarExpediente(EXPEDIENTE);

		verify(this.repositorioExpediente).modificarExpediente(expedienteModificado);
		assertSame(resultado, expedienteModificado);

	}

	@Test
	public final void testEliminarExpediente() {

		when(EXPEDIENTE.getCodigo()).thenReturn(1);

		this.servicioExpediente.eliminarExpediente(EXPEDIENTE.getCodigo());

		verify(this.repositorioExpediente).eliminarExpediente(1);

	}

	@Test
	public final void testAsociarDocumentoAlExpediente() {

		this.servicioExpediente.asociarDocumentoAlExpediente(1, DOCUMENTO);

		verify(this.repositorioExpediente).asociarDocumentoAlExpediente(1, DOCUMENTO);

	}

	@Test
	public final void testDesasociarDocumentoDelExpediete() {

		when(EXPEDIENTE.getCodigo()).thenReturn(1);
		when(DOCUMENTO.getCodigo()).thenReturn(1);
		this.servicioExpediente.desasociarDocumentoDelExpediente(1, 1);

		verify(this.repositorioExpediente).desasociarDocumentoDelExpediente(1, 1);

	}

	@Test
	public void deberiaLLamarAObtenerUnDocumento() {

		when(repositorioExpediente.obtenerExpedientePorCodigo(1)).thenReturn(EXPEDIENTE);
		final Expediente resultado = servicioExpediente.obtenerExpedientePorCodigo(1);

		assertSame(resultado, EXPEDIENTE);

	}

	@Test
	public void deberiaLlamarAObtenerTodosLosDocumentos() {
		List<Expediente> resultadoEsperado = new ArrayList<>();
		when(repositorioExpediente.obtenerTodosLosExpedientes()).thenReturn(resultadoEsperado);
		List<Expediente> resultado = this.servicioExpediente.obtenerTodosLosExpedientes();

		assertSame(resultado, resultadoEsperado);
	}

}
