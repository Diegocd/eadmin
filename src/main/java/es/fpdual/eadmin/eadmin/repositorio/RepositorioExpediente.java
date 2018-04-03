package es.fpdual.eadmin.eadmin.repositorio;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface RepositorioExpediente {

	public abstract void altaExpediente(Expediente d);

	public abstract void modificarExpediente(Expediente d);

	public abstract void eliminarExpediente(Integer codigo);

	Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento);

	Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento);

}
