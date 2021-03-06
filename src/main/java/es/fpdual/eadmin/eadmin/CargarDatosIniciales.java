package es.fpdual.eadmin.eadmin;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioExpedienteImpl;

@Component
public class CargarDatosIniciales implements ApplicationRunner {

	private final RepositorioDocumento repositorioDocumento;
	private final RepositorioExpediente repositorioExpediente;
	private RepositorioDocumentoImpl repositorioDocumentoImpl;
	private static final Date FECHA = new Date();

	@Autowired
	public CargarDatosIniciales(RepositorioDocumento repositorioDocumento,
			RepositorioExpediente repositorioExpediente, RepositorioDocumentoImpl repositorioDocumentoImpl) {
		this.repositorioDocumento = repositorioDocumento;
		this.repositorioExpediente = repositorioExpediente;
		this.repositorioDocumentoImpl = repositorioDocumentoImpl;
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repositorioDocumento.altaDocumento(new Documento(1, "documento1", FECHA, true, EstadoDocumento.ACTIVO, FECHA));
		repositorioDocumento
				.altaDocumento(new Documento(2, "documento2", FECHA, true, EstadoDocumento.APROBADO, FECHA));
		repositorioDocumento
				.altaDocumento(new Documento(3, "documento3", FECHA, false, EstadoDocumento.ELIMINADO, FECHA));
		repositorioDocumento
				.altaDocumento(new Documento(4, "documento4", FECHA, false, EstadoDocumento.ELIMINADO, FECHA));
		repositorioDocumento
				.altaDocumento(new Documento(5, "documento5", FECHA, false, EstadoDocumento.ELIMINADO, FECHA));

		repositorioDocumento.guardarDatosEnArchivo();

		repositorioDocumento
				.modificarDocumento(new Documento(2, "documento2", FECHA, false, EstadoDocumento.ELIMINADO, FECHA));
		repositorioDocumento
				.modificarDocumento(new Documento(4, "documento4", FECHA, true, EstadoDocumento.ELIMINADO, FECHA));

		repositorioDocumento.guardarDatosEnArchivo();

		repositorioDocumento.eliminarDocumento(1);
		repositorioDocumento.eliminarDocumento(3);
		repositorioDocumento.eliminarDocumento(5);

		repositorioDocumento.guardarDatosEnArchivo();
		RepositorioDocumentoImpl prueba = (RepositorioDocumentoImpl) this.repositorioDocumento;
		repositorioExpediente.altaExpediente(new Expediente(1, "expediente1", FECHA, FECHA, true,
				EstadoExpediente.ARCHIVADO, new ArrayList<Documento>(), FECHA));
		repositorioExpediente.altaExpediente(new Expediente(2, "expediente2", FECHA, FECHA, true,
				EstadoExpediente.EN_TRAMITE, prueba.getListaDocumentos(), FECHA));
		repositorioExpediente.altaExpediente(new Expediente(3, "expediente3", FECHA, FECHA, false,
				EstadoExpediente.INICIADO, new ArrayList<Documento>(), FECHA));
		repositorioExpediente.altaExpediente(new Expediente(4, "expediente4", FECHA, FECHA, true,
				EstadoExpediente.EN_TRAMITE, new ArrayList<Documento>(), FECHA));
		repositorioExpediente.altaExpediente(new Expediente(5, "expediente5", FECHA, FECHA, false,
				EstadoExpediente.INICIADO, new ArrayList<Documento>(), FECHA));
		
		repositorioExpediente.guardarTodosExpedientes();
		
		repositorioExpediente.modificarExpediente(new Expediente(2, "expediente2", FECHA, FECHA, true,
				EstadoExpediente.ARCHIVADO, new ArrayList<Documento>(), FECHA));
		repositorioExpediente.modificarExpediente(new Expediente(4, "expediente4", FECHA, FECHA, true,
				EstadoExpediente.ARCHIVADO, new ArrayList<Documento>(), FECHA));
		
		repositorioExpediente.guardarTodosExpedientes();
		
		repositorioExpediente.eliminarExpediente(1);
		repositorioExpediente.eliminarExpediente(3);
		repositorioExpediente.eliminarExpediente(5);
		
		repositorioExpediente.guardarTodosExpedientes();
	}

}
