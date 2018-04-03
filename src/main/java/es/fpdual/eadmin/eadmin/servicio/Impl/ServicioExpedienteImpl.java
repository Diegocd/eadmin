package es.fpdual.eadmin.eadmin.servicio.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.modelo.builder.DocumentoBuilder;
import es.fpdual.eadmin.eadmin.modelo.builder.ExpedienteBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioExpedienteImpl;
import es.fpdual.eadmin.eadmin.servicio.ServicioExpediente;

@Service
public class ServicioExpedienteImpl implements ServicioExpediente {

	final RepositorioExpediente repositorioExpediente;

	@Autowired
	public ServicioExpedienteImpl(RepositorioExpedienteImpl repositorioExpediente) {
		this.repositorioExpediente = repositorioExpediente;
	}

	@Override
	public Expediente altaExpediente(Expediente expediente) {

		Expediente expedienteCorrecto = obtenerExpedienteConFechaCorrectaParaAlta(expediente);
		repositorioExpediente.altaExpediente(expedienteCorrecto);
		return expedienteCorrecto;
	}

	@Override
	public Expediente modificarExpediente(Expediente expediente) {

		Expediente expedienteCorrecto = obtenerExpedienteConFechaCorrectaParaActualizar(expediente);
		repositorioExpediente.modificarExpediente(expedienteCorrecto);
		return expedienteCorrecto;
	}

	@Override
	public void eliminarExpediente(Integer codigo) {
		repositorioExpediente.eliminarExpediente(codigo);
	}

	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		
		return repositorioExpediente.asociarDocumentoAlExpediente(codigoExpediente, documento);

	}

	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {
		
		return repositorioExpediente.desasociarDocumentoDelExpediente(codigoExpediente, codigoDocumento);

	}

	protected Date dameFechaActual() {
		return new Date();
	}

	protected Expediente obtenerExpedienteConFechaCorrectaParaAlta(Expediente expediente) {

		return new ExpedienteBuilder().clonar(expediente).conFechaCreacion(dameFechaActual())
				.conFechaArchivado(dameFechaActual()).conFechaUltimaModificacion(dameFechaActual()).construir();
	}

	protected Expediente obtenerExpedienteConFechaCorrectaParaActualizar(Expediente expediente) {

		return new ExpedienteBuilder().clonar(expediente).conFechaArchivado(dameFechaActual())
				.conFechaUltimaModificacion(dameFechaActual()).construir();
	}

	@Override
	public Expediente obtenerExpedientePorCodigo(Integer codigo) {
		return repositorioExpediente.obtenerExpedientePorCodigo(codigo);
	}

	@Override
	public List<Expediente> obtenerTodosLosExpedientes() {
		return repositorioExpediente.obtenerTodosLosExpedientes();
	}

}
