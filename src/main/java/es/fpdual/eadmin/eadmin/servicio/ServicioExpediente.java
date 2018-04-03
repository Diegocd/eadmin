package es.fpdual.eadmin.eadmin.servicio;

import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface ServicioExpediente {
	
	public abstract Expediente altaExpediente(Expediente expediente);

	public abstract Expediente modificarExpediente(Expediente expediente);

	public abstract void eliminarExpediente(Integer codigo);
	
	public abstract Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento);
	
	public abstract Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento);
	
	public abstract Expediente obtenerExpedientePorCodigo(Integer codigo);

	public abstract List<Expediente> obtenerTodosLosExpedientes();

}
