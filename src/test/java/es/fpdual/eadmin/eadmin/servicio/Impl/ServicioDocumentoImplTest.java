package es.fpdual.eadmin.eadmin.servicio.Impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;
import es.fpdual.eadmin.eadmin.servicio.Impl.ServicioDocumentoImpl;

public class ServicioDocumentoImplTest {

	private ServicioDocumentoImpl servicioDocumento;
	private static final Documento DOCUMENTO = mock(Documento.class);
	private RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);

	@Before
	public void instaciarObjetosDeServicio() {

		this.servicioDocumento = spy(new ServicioDocumentoImpl(repositorioDocumento));
		when(DOCUMENTO.getCodigo()).thenReturn(1);
		when(DOCUMENTO.getFechaCreacion()).thenReturn(new Date(16 / 12 / 2010));
		when(DOCUMENTO.getNombre()).thenReturn("nombre");
		when(DOCUMENTO.getFechaUltimaModificacion()).thenReturn(new Date());
	}

	@Test
	public void deberiaAlmacenarDocumento() {
		final Documento documentoModificado = mock(Documento.class);
		doReturn(documentoModificado).when(servicioDocumento).obtenerDocumentoConFechaCorrectaParaAlta(DOCUMENTO);
		Documento resultado = this.servicioDocumento.altaDocumento(DOCUMENTO);

		verify(this.repositorioDocumento).altaDocumento(documentoModificado);
		assertSame(documentoModificado, resultado);

	}

	@Test
	public void deberiaModificarDocumento() {

		final Documento documentoModificado = mock(Documento.class);
		doReturn(documentoModificado).when(servicioDocumento).obtenerDocumentoConFechaCorrectaParaModificar(DOCUMENTO);
		Documento resultado = this.servicioDocumento.modificarDocumento(DOCUMENTO);

		verify(this.repositorioDocumento).modificarDocumento(documentoModificado);
		assertSame(documentoModificado, resultado);

	}

	@Test
	public void deberiaEliminarDocumento() {

		this.servicioDocumento.eliminarDocumento(DOCUMENTO.getCodigo());

		verify(this.repositorioDocumento).eliminarDocumento(1);

	}

	@Test
	public void deberiaLLamarAObtenerUnDocumento() {

		when(repositorioDocumento.obtenerDocumentoPorCodigo(1)).thenReturn(DOCUMENTO);
		final Documento resultado = servicioDocumento.obtenerDocumentoPorCodigo(1);

		assertSame(resultado, DOCUMENTO);

	}

	@Test
	public void deberiaLlamarAObtenerTodosLosDocumentos() {
		List<Documento> resultadoEsperado = new ArrayList<Documento>();
		when(repositorioDocumento.obtenerTodosLosDocumentos()).thenReturn(resultadoEsperado);
		List<Documento> resultado = this.servicioDocumento.obtenerTodosLosDocumentos();

		assertSame(resultado, resultadoEsperado);
	}

}
