package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

import org.slf4j.Logger;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositorioDocumento.class);
	private final List<Documento> listaDocumentos = new ArrayList<>();

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	@Override
	public void altaDocumento(Documento d) {

		if (listaDocumentos.contains(d)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		listaDocumentos.add(d);
		LOGGER.info(d.toString() + " se ha dado de alta con éxito");
	}

	@Override
	public void modificarDocumento(Documento d) {

		if (!listaDocumentos.contains(d)) {
			throw new IllegalArgumentException("El documento no existe");
		}
		if (!localizarDocumentoEnLaLista(d).getDocumentoEncriptado()
				.equals(localizarDocumentoEnLaLista(d).generarDocumentoEncriptado())) {
			throw new IllegalArgumentException("El documento ha sido modificado ilegalmente");
		}
		listaDocumentos.set(listaDocumentos.indexOf(d), d);

	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		LOGGER.info("Entrando en el método eliminarDocumento: ");
		Optional<Documento> documentoEncontrado = null;
		documentoEncontrado = listaDocumentos.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();

		if (documentoEncontrado.isPresent()) {
			LOGGER.info("Documento encontrado. Borrando documento y saliendo del método ObtenerDocumentoPorCodigo...");
			listaDocumentos.remove(documentoEncontrado.get());
		}else {
		LOGGER.info("Documento no encontrado. Saliendo del método ObtenerDocumentoPorCodigo...");
		}
	}

	public Documento localizarDocumentoEnLaLista(Documento d) {
		return listaDocumentos.get(listaDocumentos.indexOf(d));
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		LOGGER.info("Entrando en el método ObtenerDocumentoPorCodigo: ");
		Optional<Documento> documentoEncontrado = listaDocumentos.stream().filter(d -> d.getCodigo().equals(codigo))
				.findFirst();
		if (documentoEncontrado.isPresent()) {
			LOGGER.info("Documento encontrado.Saliendo del método ObtenerDocumentoPorCodigo...");
			return documentoEncontrado.get();
		}
		LOGGER.info("Documento no encontrado.Saliendo del método ObtenerDocumentoPorCodigo...");
		return null;
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		LOGGER.info("Entrando en el método obtenerTodosLosDocumentos: ");
		for (Documento datos : getListaDocumentos()) {
			LOGGER.info(datos.toString() + " Nombre: " + datos.getNombre() + " Fecha de creación: "
					+ datos.getFechaCreacion() + " Público: " + datos.getPublico() + " Estado: " + datos.getEstado()
					+ " Última modificación: " + datos.getFechaUltimaModificacion() + " Encriptación: "
					+ datos.getDocumentoEncriptado());
		}
		LOGGER.info("Saliendo del método obtenerTodosLosDocumentos...");
		return getListaDocumentos();
	}

}

// getListaDocumentos().stream().forEach(d -> LOGGER.info(d.toString() + "
// Nombre: " + d.getNombre() + " Fecha de creación: "
// + d.getFechaCreacion() + " Público: " + d.getPublico() + " Estado: " +
// d.getEstado()
// + " Última modificación: " + d.getFechaUltimaModificacion() + " Encriptación:
// "
// + d.getDocumentoEncriptado()));