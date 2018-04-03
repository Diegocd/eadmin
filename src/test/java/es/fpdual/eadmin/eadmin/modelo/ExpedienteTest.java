package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ExpedienteTest {
	private static final Integer CODIGO = 1;
	private static final String NOMBRE = "Nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ARCHIVADO = new Date();
	private static final Boolean PUBLICO = true;
	private static final EstadoExpediente ESTADO = EstadoExpediente.ARCHIVADO;
	private static final List<Documento> lISTA_DOCUMENTO = new ArrayList<>();
	Expediente prueba;
	Expediente prueba2;

	@Before
	public void crearInstancias() {
		prueba = new Expediente(CODIGO, NOMBRE, FECHA_CREACION, FECHA_ARCHIVADO, PUBLICO, ESTADO, lISTA_DOCUMENTO, FECHA_CREACION);
		prueba2 = new Expediente(CODIGO, NOMBRE, FECHA_CREACION, FECHA_ARCHIVADO, PUBLICO, ESTADO, lISTA_DOCUMENTO, FECHA_CREACION);
	}

	@Test
	public final void testHashCode() {

		assertEquals(CODIGO.hashCode() + NOMBRE.hashCode() + FECHA_CREACION.hashCode() + FECHA_ARCHIVADO.hashCode()
				+ PUBLICO.hashCode() + ESTADO.hashCode(), prueba.hashCode());

	}

	@Test
	public final void testGetters() {

		assertEquals(FECHA_ARCHIVADO, prueba.getFechaArchivado());
		assertEquals(ESTADO, prueba.getEstado());
		assertTrue(prueba.getListaDocumento().isEmpty()); 

	}

	@Test
	public final void testEqualsObject() {

		assertEquals(true, prueba.equals(prueba2));
		assertEquals(false, prueba.equals("Hola"));

	}

	@Test
	public final void testToString() {

		assertEquals(
				"Expediente código = " + CODIGO + ", nombre = " + NOMBRE + ", fechaCreacion = " + FECHA_CREACION
						+ ", fechaArchivado = " + FECHA_ARCHIVADO + ", publico = " + PUBLICO + ", estado = " + ESTADO,
				prueba.toString());

	}

}
