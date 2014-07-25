package br.com.supportcomm.mktcall.report.util;





/**
 * Enum para definir o tipo de report a ser gerado.
 * 
 * @author Claudio Santos
 * 
 * 
 */
public enum TypeReportEnum {
	
	/**
	 * Arquivo em formato PDF para ser imprimido.
	 */
	PDF,
	/**
	 * Arquivo em formado XLS, como padrão microsoft Excel.
	 */
	XLS,
	/** 
	 * Arquivo em formato DOC, como padrão microsoft Word.
	 */
	DOC,
	/**
	 * Arquivo em formato HTML.
	 */
	HTML
}
