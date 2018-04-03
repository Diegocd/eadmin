package es.fpdual.eadmin.eadmin.servicio.impl;

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

	private ServicioDocumento servicioDocumento;
	private static final Documento DOCUMENTO = mock(Documento.class);
	private RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);

	@Before
	public void instaciarObjetosDeServicio() {

		this.servicioDocumento = new ServicioDocumentoImpl(repositorioDocumento);
		when(DOCUMENTO.getCodigo()).thenReturn(1);
		when(DOCUMENTO.getFechaCreacion()).thenReturn(new Date(16/12/2010));
		when(DOCUMENTO.getNombre()).thenReturn("nombre");
	}

	@Test
	public void deberiaAlmacenarDocumento() {
		
		final Documento resultado = this.servicioDocumento.altaDocumento(DOCUMENTO);

		verify(this.repositorioDocumento).altaDocumento(any());
		assertEquals(resultado.getCodigo(), DOCUMENTO.getCodigo());
		assertEquals(resultado.getNombre(), DOCUMENTO.getNombre());
		assertNotEquals(resultado.getFechaCreacion(), DOCUMENTO.getFechaCreacion());

	}

	@Test
	public void deberiaModificarDocumento() {
		
		when(DOCUMENTO.getFechaUltimaModificacion()).thenReturn(new Date());
		
		final Documento resultado = this.servicioDocumento.modificarDocumento(DOCUMENTO);

		verify(this.repositorioDocumento).modificarDocumento(any());
		assertEquals(Integer.valueOf(1), DOCUMENTO.getCodigo());
		assertEquals("nombre", resultado.getNombre(), DOCUMENTO.getNombre());
		assertEquals(new Date(16/12/2010), DOCUMENTO.getFechaCreacion());
		assertNotEquals(DOCUMENTO.getFechaCreacion(), DOCUMENTO.getFechaUltimaModificacion());

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
		
		assertSame(resultado,DOCUMENTO);
		
	}
	
	@Test
	public void deberiaLlamarAObtenerTodosLosDocumentos() {
		List<Documento> resultadoEsperado = new ArrayList<Documento>();
		when(repositorioDocumento.obtenerTodosLosDocumentos()).thenReturn(resultadoEsperado);
		List<Documento> resultado = this.servicioDocumento.obtenerTodosLosDocumentos();

		assertSame(resultado,resultadoEsperado);
	}

}
