package es.fpdual.eadmin.eadmin.repositorio.impl;

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

	@Override
	public void altaExpediente(Expediente d) {

		if (listaExpedientes.contains(d)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		listaExpedientes.add(d);

	}

	@Override
	public void modificarExpediente(Expediente d) {
		
		if (!listaExpedientes.contains(d)) {
			throw new IllegalArgumentException("El documento no existe");
		}
		listaExpedientes.set(listaExpedientes.indexOf(d), d);

	}

	@Override
	public void eliminarExpediente(Integer codigo) {
		
		Optional <Expediente> expedienteEncontrado = null;
		expedienteEncontrado = listaExpedientes.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
		

		if (expedienteEncontrado.isPresent()) {
			listaExpedientes.remove(expedienteEncontrado.get());
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

}
