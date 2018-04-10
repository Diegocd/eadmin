package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Repository
public class RepositorioExpedienteImpl implements RepositorioExpediente {

	private final List<Expediente> listaExpedientes = new ArrayList<>();

	public List<Expediente> getListaExpedientes() {
		return listaExpedientes;
	}
	
	public void guardarTodosExpedientes() {
		for (Expediente e: getListaExpedientes()) {
			guardarExpediente(e,3);
		}
		
	}

	public void guardarExpediente(Expediente datos, int archivo) {
		String[] listaArchivos = { "C:\\Users\\formacion\\Desktop\\Proyectos Java\\eadmin\\AltaExpediente.txt",
				"C:\\Users\\formacion\\Desktop\\Proyectos Java\\eadmin\\ModificarExpediente.txt",
				"C:\\Users\\formacion\\Desktop\\Proyectos Java\\eadmin\\BajaExpediente.txt",
				"C:\\Users\\formacion\\Desktop\\Proyectos Java\\eadmin\\ListaExpedientes.txt" };
		FileWriter fichero = null;
		PrintWriter sw = null;
		try {
			fichero = new FileWriter(listaArchivos[archivo], true);
			sw = new PrintWriter(fichero);
			sw.println(datos.toString());
			if (!datos.getListaDocumento().isEmpty()) {
				sw.println("Lista de documentos del expediente " + datos.getCodigo());
			for (Documento d : datos.getListaDocumento()) {
				sw.println(d.toString() + " Nombre: " + d.getNombre()
						+ " Fecha de creación: " + d.getFechaCreacion() + " Público: " + d.getPublico() + " Estado: "
						+ d.getEstado() + " Última modificación: " + d.getFechaUltimaModificacion() + " Encriptación: "
						+ d.getDocumentoEncriptado());
			}
			}else {
				sw.println("La lista de documentos del expediente " + datos.getCodigo() + " está vacía");
			}
			sw.println(
					"****************************************************************************************************");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sw.close();
		}
	}

	@Override
	public void altaExpediente(Expediente d) {

		if (listaExpedientes.contains(d)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		listaExpedientes.add(d);
		guardarExpediente(d, 0);
		System.out.println("El expediente se ha dado de alta con éxito");

	}

	@Override
	public void modificarExpediente(Expediente d) {

		if (!listaExpedientes.contains(d)) {
			throw new IllegalArgumentException("El documento no existe");
		}
		listaExpedientes.set(listaExpedientes.indexOf(d), d);

		guardarExpediente(d, 1);

	}

	@Override
	public void eliminarExpediente(Integer codigo) {

		Optional<Expediente> expedienteEncontrado = null;
		expedienteEncontrado = listaExpedientes.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			listaExpedientes.remove(expedienteEncontrado.get());

			guardarExpediente(expedienteEncontrado.get(), 2);
		}

	}

	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		Optional<Expediente> expedienteEncontrado = listaExpedientes.stream()
				.filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			Optional<Documento> documentoEncontrado = expedienteEncontrado.get().getListaDocumento().stream()
					.filter(d -> d.getCodigo().equals(documento.getCodigo())).findFirst();
			if (!documentoEncontrado.isPresent()) {
				expedienteEncontrado.get().getListaDocumento().add(documento);
				modificarExpediente(expedienteEncontrado.get());
			}
			return expedienteEncontrado.get();
		}
		return null;
	}

	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {

		Optional<Expediente> expedienteEncontrado = listaExpedientes.stream()
				.filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			Optional<Documento> documentoEncontrado = expedienteEncontrado.get().getListaDocumento().stream()
					.filter(d -> d.getCodigo().equals(codigoDocumento)).findFirst();
			if (documentoEncontrado.isPresent()) {
				expedienteEncontrado.get().getListaDocumento().remove(documentoEncontrado.get());
				modificarExpediente(expedienteEncontrado.get());
			}
			return expedienteEncontrado.get();
		}
		return null;
	}

	@Override
	public Expediente obtenerExpedientePorCodigo(Integer codigo) {
		Optional<Expediente> expedienteEncontrado = listaExpedientes.stream().filter(e -> e.getCodigo().equals(codigo))
				.findFirst();
		if (expedienteEncontrado.isPresent()) {
			return expedienteEncontrado.get();
		}
		return null;
	}

	@Override
	public List<Expediente> obtenerTodosLosExpedientes() {

		return getListaExpedientes();
	}

}
