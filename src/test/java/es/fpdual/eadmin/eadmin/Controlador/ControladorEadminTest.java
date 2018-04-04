package es.fpdual.eadmin.eadmin.Controlador;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

public class ControladorEadminTest {
	ControladorEadmin controladorEadmin;
	ServicioDocumento servicioDocumento = mock(ServicioDocumento.class);
	Documento documento = mock(Documento.class);
	
	@Before
	public final void instancias() {
		this.controladorEadmin = new ControladorEadmin(servicioDocumento);
		when(documento.getCodigo()).thenReturn(1);
	}

	@Test
	public final void testDeberiaMostrarUnDocumento() {
		when(this.servicioDocumento.obtenerDocumentoPorCodigo(1)).thenReturn(documento);
		Documento resultado = controladorEadmin.getDocumento(1).getBody();
		
		verify(this.servicioDocumento).obtenerDocumentoPorCodigo(1);
		assertSame(documento,resultado);
	}
	
	@Test
	public final void testDeberiaMostrarTodosLosDocumentos() {
		final List<Documento> LISTA = new ArrayList<Documento>();
		when(this.servicioDocumento.obtenerTodosLosDocumentos()).thenReturn(LISTA);
		List<Documento> resultado = this.controladorEadmin.getTodosDocumentos().getBody();
		
		assertSame(LISTA, resultado);
	}
	
	@Test
	public final void testDeberiaEliminarDocumento() {
		ResponseEntity<?> resultado = this.controladorEadmin.eliminarDocumento(1);
		verify(this.servicioDocumento).eliminarDocumento(1);
		assertEquals(HttpStatus.OK,resultado.getStatusCode());
	}

}
