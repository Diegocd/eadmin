package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

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
		System.out.println("El documento se ha dado de alta con éxito");

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

		Optional<Documento> documentoEncontrado = null;
		documentoEncontrado = listaDocumentos.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();

		if (documentoEncontrado.isPresent()) {
			listaDocumentos.remove(documentoEncontrado.get());
		}

	}

	public Documento localizarDocumentoEnLaLista(Documento d) {
		return listaDocumentos.get(listaDocumentos.indexOf(d));
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		Optional<Documento> documentoEncontrado = listaDocumentos.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
		if (documentoEncontrado.isPresent()) {
			return documentoEncontrado.get();
		}
		return null;
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		return getListaDocumentos();
	}

}
