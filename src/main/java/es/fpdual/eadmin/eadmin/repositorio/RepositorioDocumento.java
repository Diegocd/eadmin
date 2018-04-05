package es.fpdual.eadmin.eadmin.repositorio;

import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;

public interface RepositorioDocumento {
	
	public abstract void altaDocumento(Documento d);
	
	public abstract void modificarDocumento(Documento d);
	
	public abstract void eliminarDocumento(Integer codigo);
	
	public abstract Documento obtenerDocumentoPorCodigo (Integer codigo);
	
	public abstract List<Documento> obtenerTodosLosDocumentos();
	
	public abstract void guardarDatosEnArchivo();
}
