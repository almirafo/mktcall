package br.com.supportcomm.mktcall.report.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.apache.jasper.JasperException;
import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author JEZimerer
 * @version 1.0.0
 */
public class JasperFactory {

	private static final Logger logger = Logger.getLogger(JasperFactory.class);

	private String arquivoRelatorio;

	private InputStream relatorios = null;

	private FacesContext context;

	private ServletContext servletContext;

	byte[] retorno;

	public JasperFactory() {
	}

	/**
	 * 
	 * Método responsável por compilar o relatório Jasper
	 * 
	 * @param relatorio
	 * @param map
	 * @param tipo
	 * @param lista
	 */
	public void compilar(String relatorio, Map<String, Object> map, TypeReportEnum tipo, List<?> lista) {

		try {

			context = FacesContext.getCurrentInstance();

			servletContext = (ServletContext) context.getExternalContext().getContext();

			arquivoRelatorio = propertiesLoader(relatorio);

			String caminhoBrasao = propertiesLoader("brasao");

			String separator = System.getProperty("file.separator");

			String caminhoRelatorio = servletContext.getRealPath(separator + "reports" + separator + arquivoRelatorio);

			caminhoBrasao = servletContext.getRealPath(separator + "images" + separator + caminhoBrasao);

			map.put("IMAGEM_BRASAO", caminhoBrasao);
			map.put("TIPO_RELATORIO", relatorio);

			JRBeanCollectionDataSource jBeanCollectionDataSource = new JRBeanCollectionDataSource(lista);

			JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, map, jBeanCollectionDataSource);

			ExternalContext contexts = context.getExternalContext();

			HttpServletResponse response = (HttpServletResponse) contexts.getResponse();

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			switch (tipo) {

			case PDF:
				exportarPdf(map, jasperPrint, response, out);
				break;
			case DOC:
				exportarDoc(map, jasperPrint, response, out);
				break;
			case HTML:
				exportarHtml(map, jasperPrint, response, out);
				break;
			case XLS:
				exportarXls(map, jasperPrint, response, out);
				break;
			}

		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Método responsável por Exportar o Relatório para PDF
	 * 
	 * @param map
	 * @param jasperPrint
	 * @param response
	 * @param out
	 * @throws JRException
	 * @throws IOException
	 */
	private void exportarXls(Map<String, Object> map, JasperPrint jasperPrint, HttpServletResponse response, ByteArrayOutputStream out) throws JRException, IOException {
		JRExporter xlsExporter = new JRXlsExporter();

		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

		xlsExporter.exportReport();

		relatorios = new ByteArrayInputStream(out.toByteArray());

		retorno = getBytes(relatorios);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=\"" + map.get("TIPO_RELATORIO") + ".xls" + "\"");
		response.setContentLength(retorno.length);

		ServletOutputStream xlsSaidaResponse = response.getOutputStream();

		xlsSaidaResponse.write(retorno, 0, retorno.length);
		xlsSaidaResponse.flush();
		xlsSaidaResponse.close();

		context.responseComplete();
	}

	/**
	 * Método responsável por exportar os relatórios para HTML
	 * 
	 * @param map
	 * @param jasperPrint
	 * @param response
	 * @param out
	 * @throws JRException
	 * @throws IOException
	 */
	private void exportarHtml(Map<String, Object> map, JasperPrint jasperPrint, HttpServletResponse response, ByteArrayOutputStream out) throws JRException, IOException {

		JRExporter htmlExporter = new JRHtmlExporter();

		htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		htmlExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

		htmlExporter.exportReport();

		relatorios = new ByteArrayInputStream(out.toByteArray());

		retorno = getBytes(relatorios);

		response.setContentType("text/html");
		response.setHeader("Content-disposition", "attachment; filename=\"" + map.get("TIPO_RELATORIO") + ".html" + "\"");
		response.setContentLength(retorno.length);

		ServletOutputStream htmlSaidaResponse = response.getOutputStream();

		htmlSaidaResponse.write(retorno, 0, retorno.length);
		htmlSaidaResponse.flush();
		htmlSaidaResponse.close();

		context.responseComplete();
	}

	/**
	 * Método responsável por exportar os relatórios para DOC
	 * 
	 * @param map
	 * @param jasperPrint
	 * @param response
	 * @param out
	 * @throws JRException
	 * @throws IOException
	 */
	private void exportarDoc(Map<String, Object> map, JasperPrint jasperPrint, HttpServletResponse response, ByteArrayOutputStream out) throws JRException, IOException {

		JRExporter docExporter = new JRDocxExporter();

		docExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		docExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");

		docExporter.exportReport();

		relatorios = new ByteArrayInputStream(out.toByteArray());

		retorno = getBytes(relatorios);

		response.setContentType("application/doc");
		response.setHeader("Content-disposition", "attachment; filename=\"" + map.get("TIPO_RELATORIO") + ".doc" + "\";");
		response.setContentLength(retorno.length);

		ServletOutputStream docSaidaResponse = response.getOutputStream();

		docSaidaResponse.write(retorno, 0, retorno.length);
		docSaidaResponse.flush();
		docSaidaResponse.close();

		context.responseComplete();
	}

	/**
	 * Método responsável por exportar os relatórios para PDF
	 * 
	 * @param map
	 * @param jasperPrint
	 * @param response
	 * @param out
	 * @throws JRException
	 * @throws IOException
	 */
	private void exportarPdf(Map<String, Object> map, JasperPrint jasperPrint, HttpServletResponse response, ByteArrayOutputStream out) throws JRException, IOException {

		try {
			JRExporter pdfExporter = new JRPdfExporter();

			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

			pdfExporter.exportReport();

			relatorios = new ByteArrayInputStream(out.toByteArray());

			retorno = getBytes(relatorios);

			HttpServletRequest request = (HttpServletRequest) this.context.getExternalContext().getRequest();

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=\"" + map.get("TIPO_RELATORIO") + ".pdf" + "\"");
			response.setContentLength(retorno.length);

			ServletOutputStream pdfSaidaResponse = response.getOutputStream();

			pdfSaidaResponse.write(retorno, 0, retorno.length);
			pdfSaidaResponse.flush();
			pdfSaidaResponse.close();

			context.responseComplete();
		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

	protected String propertiesLoader(String relatorio) throws IOException {

		String nomeRelatorio = "resources.relatorios";

		// mudar caminho
		ResourceBundle rb = ResourceBundle.getBundle(nomeRelatorio);
		nomeRelatorio = rb.getString(relatorio);

		return nomeRelatorio;
	}

	public static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		}
		else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

}
