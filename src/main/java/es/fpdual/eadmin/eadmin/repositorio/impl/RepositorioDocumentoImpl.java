package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositorioDocumento.class);
	private final List<Documento> listaDocumentos = new ArrayList<>();

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void guardarDatosEnArchivo() {
		String ruta = "ListaDocumentos.txt";
		FileWriter fichero = null;
		PrintWriter sw = null;
		try {
			fichero = new FileWriter(ruta, true);
			sw = new PrintWriter(fichero);
			for (Documento datos : listaDocumentos) {
				sw.println(datos.toString() + " Nombre: " + datos.getNombre() + " Fecha de creación: "
						+ datos.getFechaCreacion() + " Público: " + datos.getPublico() + " Estado: " + datos.getEstado()
						+ " Última modificación: " + datos.getFechaUltimaModificacion() + " Encriptación: "
						+ datos.getDocumentoEncriptado());
				exportExcel("LISTA", datos, "ListaDoc.xlsx");
				exportTodo();
			}
			sw.println(
					"****************************************************************************************************");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sw.close();
		}
	}

	public void guardarDocumento(Documento datos, int archivo) {
		String[] listaArchivos = { "AltaDocumentos.txt", "ModificarDocumentos.txt", "BajaDocumentos.txt" };
		FileWriter fichero = null;
		PrintWriter sw = null;
		try {
			fichero = new FileWriter(listaArchivos[archivo], true);
			sw = new PrintWriter(fichero);
			sw.println(datos.toString() + " Nombre: " + datos.getNombre() + " Fecha de creación: "
					+ datos.getFechaCreacion() + " Público: " + datos.getPublico() + " Estado: " + datos.getEstado()
					+ " Última modificación: " + datos.getFechaUltimaModificacion() + " Encriptación: "
					+ datos.getDocumentoEncriptado());
			sw.println(
					"****************************************************************************************************");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sw.close();
		}
	}

	@Override
	public void altaDocumento(Documento d) {

		if (listaDocumentos.contains(d)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		listaDocumentos.add(d);
		LOGGER.info(d.toString() + " se ha dado de alta con éxito");
		guardarDocumento(d, 0);
		exportExcel("ALTA", d, "AltaDoc.xlsx");
		exportTodo();
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
		guardarDocumento(d, 1);
		exportExcel("MOD", d, "ModDoc.xlsx");
		exportTodo();
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		LOGGER.info("Entrando en el método eliminarDocumento: ");
		Optional<Documento> documentoEncontrado = listaDocumentos.stream().filter(d -> d.getCodigo().equals(codigo))
				.findFirst();

		if (documentoEncontrado.isPresent()) {
			LOGGER.info("Documento encontrado. Borrando documento y saliendo del método EliminarDocumento...");
			listaDocumentos.remove(documentoEncontrado.get());
			guardarDocumento(documentoEncontrado.get(), 2);
			exportExcel("BAJA", documentoEncontrado.get(), "BajaDoc.xlsx");
			exportTodo();
		} else {
			LOGGER.info("Documento no encontrado. Saliendo del método EliminarDocumento...");
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

	public static boolean exportExcel(String nombreHoja, Documento documento, String fileName) {

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		Integer numeroLineas = 0;

		File archivoExcel = new File(fileName);
		if (!archivoExcel.exists()) {
			// Cabecera
			data.put("0", new Object[] { "ID", "NOMBRE", "FECHA", "PUBLICO", "ESTADO", "ULTIMA MOD", "ENCRIPTACIÓN" });
			numeroLineas++;
		} else {
			ArrayList<String[]> datosExcel = importExcel(fileName, 7);
			ListIterator<String[]> it = datosExcel.listIterator();

			while (it.hasNext()) {
				numeroLineas++;
				String[] datos = it.next();
				data.put(numeroLineas.toString(), datos);
			}
		}

		numeroLineas++;
		data.put(numeroLineas.toString(), new Object[] { documento.getCodigo(), documento.getNombre(),
				documento.getFechaCreacion().toString(), documento.getPublico().toString(), documento.getEstado().toString(),
				documento.getFechaUltimaModificacion().toString(), documento.getDocumentoEncriptado()});

		// Creamos el libro de trabajo
		XSSFWorkbook libro = new XSSFWorkbook();
		
		// Creamos un estilo de celda y le asignamos alineado central
		XSSFCellStyle style = libro.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		// Creacion de Hoja
		XSSFSheet hoja = libro.createSheet(nombreHoja);

		// Iteramos el map e insertamos los datos
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			// cramos la fila
			Row row = hoja.createRow(rownum++);
			// obtenemos los datos de la fila
			Object[] objArr = data.get(key);
			int cellnum = 0;
			// iteramos cada dato de la fila
			for (Object obj : objArr) {
				// Creamos la celda
				Cell cell = row.createCell(cellnum++);
				// Asignamos el estilo
				cell.setCellStyle(style);
				// Setteamos el valor con el tipo de dato correspondiente
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Escribimos en fichero
			FileOutputStream out = new FileOutputStream(new File(fileName));
			for (int h = 1; h < 8; h++) {
				hoja.autoSizeColumn(h);
			}
			libro.write(out);
			// cerramos el fichero y el libro
			out.close();
			libro.close();
			System.out.println("Excel exportado correctamente\n");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<String[]> importExcel(String fileName, int numColums) {

		// ArrayList donde guardaremos todos los datos del excel
		ArrayList<String[]> data = new ArrayList<>();

		try {
			// Acceso al fichero xlsx
			FileInputStream file = new FileInputStream(new File(fileName));

			// Creamos la referencia al libro del directorio dado
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Obtenemos la primera hoja
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterador de filas
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// Iterador de celdas
				Iterator<Cell> cellIterator = row.cellIterator();
				// contador para el array donde guardamos los datos de cada fila
				int contador = 0;
				// Array para guardar los datos de cada fila
				// y añadirlo al ArrayList
				String[] fila = new String[numColums];
				// iteramos las celdas de la fila
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// Guardamos los datos de la celda segun su tipo
					switch (cell.getCellType()) {
					// si es numerico
					case Cell.CELL_TYPE_NUMERIC:
						fila[contador] = (int) cell.getNumericCellValue() + "";
						break;
					// si es cadena de texto
					case Cell.CELL_TYPE_STRING:
						fila[contador] = cell.getStringCellValue() + "";
						break;
					}
					// Si hemos terminado con la ultima celda de la fila
					if ((contador + 1) % numColums == 0) {
						// Añadimos la fila al ArrayList con todos los datos
						data.add(fila);
					}
					// Incrementamos el contador
					// con cada fila terminada al redeclarar arriba el contador,
					// no obtenemos excepciones de ArrayIndexOfBounds
					contador++;
				}
			}
			// Cerramos el fichero y workbook
			file.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Excel importado correctamente\n");

		return data;
	}

	public void exportTodo() {

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		XSSFWorkbook libro = new XSSFWorkbook();
		XSSFCellStyle style = libro.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		String nombres[] = { "ALTA", "MOD", "BAJA", "LISTA" };
		String ficheros[] = { "AltaDoc.xlsx", "ModDoc.xlsx", "BajaDoc.xlsx", "ListaDoc.xlsx" };
		for (int i = 0; i < ficheros.length; i++) {
			
			XSSFSheet hoja = libro.createSheet(nombres[i]);
			Integer numeroLineas = 0;
			File archivoExcel = new File(ficheros[i]);
			if (!archivoExcel.exists()) {
					data.put("0", new Object[] { "ID", "NOMBRE", "FECHA", "PUBLICO", "ESTADO", "ULTIMA MOD", "ENCRIPTACIÓN" });
					numeroLineas++;
			}else {
			ArrayList<String[]> datosExcel = importExcel(ficheros[i], 7);
			ListIterator<String[]> it = datosExcel.listIterator();
			
			while (it.hasNext()) {
				numeroLineas++;
				String[] datos = it.next();
				data.put(numeroLineas.toString(), datos);
			}
			}
			Set<String> keyset = data.keySet();
			int rownum = 0;
			for (String key : keyset) {
				// cramos la fila
				Row row = hoja.createRow(rownum++);
				// obtenemos los datos de la fila
				Object[] objArr = data.get(key);
				int cellnum = 0;
				// iteramos cada dato de la fila
				for (Object obj : objArr) {
					// Creamos la celda
					Cell cell = row.createCell(cellnum++);
					cell.setCellStyle(style);
					// Setteamos el valor con el tipo de dato correspondiente
					if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Integer)
						cell.setCellValue((Integer) obj);
				}
			}
			for (int h = 1; h < 8; h++) {
				hoja.autoSizeColumn(h);
			}
			data.clear();
		}
		try {
			// Escribimos en fichero
			FileOutputStream out = new FileOutputStream(new File("TodosDoc.xlsx"));
			libro.write(out);
			// cerramos el fichero y el libro
			out.close();
			libro.close();
			System.out.println("Excel exportado correctamente\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
