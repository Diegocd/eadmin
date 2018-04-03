package es.fpdual.eadmin.eadmin.repositorio;

import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface RepositorioExpediente {

	public abstract void altaExpediente(Expediente d);

	public abstract void modificarExpediente(Expediente d);

	public abstract void eliminarExpediente(Integer codigo);

	public abstract Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento);

	public abstract Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento);

	public abstract Expediente obtenerExpedientePorCodigo(Integer codigo);

	public abstract List<Expediente> obtenerTodosLosExpedientes();

}
