package es.fpdual.eadmin.eadmin.servicio.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.builder.DocumentoBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

@Service
public class ServicioDocumentoImpl implements ServicioDocumento {

	final RepositorioDocumento repositorioDocumento;
	
	@Autowired
	public ServicioDocumentoImpl(RepositorioDocumento repositorioDocumento) {
		this.repositorioDocumento = repositorioDocumento;
	}

	@Override
	public Documento altaDocumento(Documento documento) {
		
		Documento documentoCorrecto = obtenerDocumentoConFechaCorrectaParaAlta(documento);
		repositorioDocumento.altaDocumento(documentoCorrecto);
		return documentoCorrecto;
	}

	@Override
	public Documento modificarDocumento(Documento documento) {

		Documento documentoCorrecto = obtenerDocumentoConFechaCorrectaParaModificar(documento);
		repositorioDocumento.modificarDocumento(documentoCorrecto);
		return documentoCorrecto;
	}

	
	@Override
	public void eliminarDocumento(Integer codigo) {
		
		repositorioDocumento.eliminarDocumento(codigo);
		
	}
	
	protected Date dameFechaActual() {
		return new Date();
	}
	
	protected Documento obtenerDocumentoConFechaCorrectaParaAlta(Documento documento) {
		return new DocumentoBuilder().clonar(documento).conFechaAlta(dameFechaActual()).conFechaUltimaModificacion(dameFechaActual()).construir();
	}
	
	protected Documento obtenerDocumentoConFechaCorrectaParaModificar(Documento documento) {
		return new DocumentoBuilder().clonar(documento).conFechaUltimaModificacion(dameFechaActual()).construir();
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		
		return repositorioDocumento.obtenerDocumentoPorCodigo(codigo);
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		
		return repositorioDocumento.obtenerTodosLosDocumentos();
	}

}
