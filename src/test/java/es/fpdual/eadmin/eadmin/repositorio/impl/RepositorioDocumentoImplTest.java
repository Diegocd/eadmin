package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class RepositorioDocumentoImplTest {

	private static final Date FECHA_CREACION = new Date();
	RepositorioDocumentoImpl repositorioDocumentoImpl;
	Documento documento1, documento2;

	@Before
	public void instaciarObjetos() {
		repositorioDocumentoImpl = new RepositorioDocumentoImpl();
		documento1 = new Documento(1, "Nombre", FECHA_CREACION, true, EstadoDocumento.ACTIVO, FECHA_CREACION);
		documento2 = new Documento(2, "Nombre", FECHA_CREACION, true, EstadoDocumento.ACTIVO, FECHA_CREACION);

	}

	@Test
	public void getters() {

		assertTrue(repositorioDocumentoImpl.getListaDocumentos().isEmpty());
	}

	@Test
	public final void testAltaNuevoDocumento() {
		repositorioDocumentoImpl.altaDocumento(documento1);

		assertEquals(0, repositorioDocumentoImpl.getListaDocumentos().indexOf(documento1));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testAltaDocumentoRepetido() {

		repositorioDocumentoImpl.altaDocumento(documento1);
		repositorioDocumentoImpl.altaDocumento(documento1);
	}

	@Test
	public final void testModificarDocumentoExistente() {

		repositorioDocumentoImpl.getListaDocumentos().add(documento2);
		repositorioDocumentoImpl.modificarDocumento(documento2);
		assertEquals(0, repositorioDocumentoImpl.getListaDocumentos().indexOf(documento2));

	}

	@Test(expected = IllegalArgumentException.class)
	public final void testModificarDocumentoInexistente() {
		
		repositorioDocumentoImpl.modificarDocumento(documento1);

	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public final void testModificarDocumentoModificadoIlegalmente() {
//		repositorioDocumentoImpl.altaDocumento(documento1);
//		repositorioDocumentoImpl.getListaDocumentos().get(0).setEstado(EstadoDocumento.ELIMINADO);
//		repositorioDocumentoImpl.modificarDocumento(documento1);
//
//	}

	@Test
	public final void testEliminarDocumentoExistente() {

		repositorioDocumentoImpl.getListaDocumentos().add(documento2);
		repositorioDocumentoImpl.eliminarDocumento(documento2.getCodigo());
		assertTrue(repositorioDocumentoImpl.getListaDocumentos().isEmpty());

	}
	
	@Test 
	public final void testEliminarDocumentoInexistente() {
		
		repositorioDocumentoImpl.eliminarDocumento(documento2.getCodigo());
		assertTrue(repositorioDocumentoImpl.getListaDocumentos().isEmpty());

	}
	
	@Test 
	public final void testObtenerDocumentoPorCodigo() {
		repositorioDocumentoImpl.getListaDocumentos().add(documento1);
		
		assertEquals(documento1, repositorioDocumentoImpl.obtenerDocumentoPorCodigo(documento1.getCodigo()));
		
	}
	
	@Test 
	public final void testObtenerDocumentoPorCodigoNoExistente() {
		
		assertEquals(null, repositorioDocumentoImpl.obtenerDocumentoPorCodigo(documento1.getCodigo()));
		
	}
	
	@Test
	public final void testObtenerListaDocumentos() {
		
		assertEquals(repositorioDocumentoImpl.getListaDocumentos(),repositorioDocumentoImpl.obtenerTodosLosDocumentos());
	}

}
