package br.com.supportcomm.mktcall.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe de utilidades
 * 
 * @author andre.vilhalba
 * 
 */

public class JSFUtil {
	/**
	 * Adiciona mensagem de informação, somente aparecendo o título no campo
	 * messages na tela
	 * 
	 * @param titulo
	 */
	public static void addInfo(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, null));
	}

	/**
	 * Adiciona mensagem de atenção, somente aparecendo o título no campo
	 * messages na tela
	 * 
	 * @param titulo
	 */
	public static void addWarn(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, null));
	}

	/**
	 * Adiciona mensagem de erro, somente aparecendo o título no campo messages
	 * na tela
	 * 
	 * @param titulo
	 */
	public static void addError(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, null));
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Adiciona mensagem de erro fatal, somente aparecendo o título no campo
	 * messages na tela
	 * 
	 * @param titulo
	 */
	public static void addFatal(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, null));
	}

	/**
	 * Retorna a string de acordo com o arquivo de propriedades
	 * messages.properties
	 * 
	 * @param conteudo
	 * @return
	 */
	public static String getMessage(String conteudo) {
		if (isNullOrBlank((Locale) getSessionAtiva(false).getAttribute("locale"))) {
			return ResourceBundle.getBundle("resources.messages").getString(conteudo);
		}
		return ResourceBundle.getBundle("resources.messages", (Locale) getSessionAtiva(false).getAttribute("locale")).getString(conteudo);
	}

	/**
	 * Retorna a string de acordo com o arquivo de propriedades
	 * messages.properties
	 * 
	 * @param conteudo
	 * @return
	 */
	public static String getMessage(String conteudo, Locale locale) {
		if (isNullOrBlank("locale")) {
			return ResourceBundle.getBundle("resources.messages").getString(conteudo);
		}
		return ResourceBundle.getBundle("resources.messages", locale).getString(conteudo);
	}

	/**
	 * Retorna a string de acordo com o arquivo de propriedades email.properties
	 * 
	 * @param conteudo
	 * @return
	 */
	public static String getEmailMessage(String conteudo) {
		return ResourceBundle.getBundle("resources.email").getString(conteudo);
	}

	/**
	 * retorna o response de acordo com a contexto externo
	 * 
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return ((javax.servlet.http.HttpServletResponse) getExternalContext().getResponse());
	}

	/**
	 * retorna o request de acordo com a contexto externo
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((javax.servlet.http.HttpServletRequest) getExternalContext().getRequest());
	}

	/**
	 * retorna a sessão ativa de acordo com a contexto externo
	 * 
	 * @param ativa
	 * @return
	 */
	public static HttpSession getSessionAtiva(boolean ativa) {
		return ((HttpSession) getExternalContext().getSession(ativa));
	}

	/**
	 * retorna o contexto externo
	 * 
	 * @return
	 */
	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	/**
	 * retorna se o objeto está em branco ou nulo
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrBlank(Object obj) {
		if (obj == null || obj.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * retorna se a lista é nula ou vazia
	 * 
	 * @param lista
	 * @return
	 */
	public static boolean isNullOrEmpty(List<?> lista) {
		if (lista == null || lista.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * retorna data em extenso - exemplo(Sexta-feira, 08 de abril de 2011)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataExtenso(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("EEEEEE ',' dd ' de 'MMMM ' de ' yyyy");
		return df.format(data);
	}

	/**
	 * retorna data formatada - exemplo(08/04/2011)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataFormatada(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(data);
	}

	public static String getMesData(Date date) {

		// SimpleDateFormat sdf = new SimpleDateFormat("MM");
		// return sdf.format(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}

	public static String getDiaData(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * retorna data e hora formatada - exemplo(08/04/2011 - 14:21)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataHoraFormatada(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy '-' HH:mm");
		return df.format(data);
	}

	/**
	 * retorna valor formatado
	 * 
	 * @param valor
	 * @return
	 */
	public static String getValorFormatado(Object valor) {
		DecimalFormat nf = new DecimalFormat("##.00");
		return nf.format(valor);
	}

	/**
	 * retorna usuário logado e autenticado
	 * 
	 * @return
	 */

	/**
	 * retorna string do tipo telefone formatado
	 * 
	 * @param telefone
	 * @return
	 */
	public static String getFormataTelefone(String telefone) {
		return "(" + telefone.subSequence(0, 1) + ")" + telefone.subSequence(2, telefone.length() - 1);
	}

	/**
	 * retorna se através do id do componente de tela o usuário logado tem
	 * permissão para acesso
	 * 
	 * @param idComponente
	 * @return
	 */

	/**
	 * Calcula a diferença de duas datas em dias <br>
	 * <b>Importante:</b> Quando realiza a diferença em dias entre duas datas,
	 * este método considera as horas restantes e as converte em fração de dias.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return quantidade de dias existentes entre a dataInicial e dataFinal.
	 */
	public static Long diferencaEmDias(Date dataInicial, Date dataFinal) {
		Long differenceMilliSeconds = dataFinal.getTime() - dataInicial.getTime();
		return differenceMilliSeconds / 1000 / 60 / 60 / 24;
	}

	public static boolean validarDatas(Date dataInicio, Date dataFim) {
		if (isNullOrBlank(dataInicio)) {
			addError(JSFUtil.getMessage("dataInicio") + ": " + getMessage("campoObrigatorio"));
			return false;
		}
		else if (isNullOrBlank(dataFim)) {
			addError(JSFUtil.getMessage("dataFim") + ": " + getMessage("campoObrigatorio"));
			return false;
		}
		else if (dataInicio.after(dataFim)) {
			addError(JSFUtil.getMessage("dataInicio") + ": " + getMessage("dataInicioMaiorFim"));
			return false;
		}
		else if (dataFim.before(dataInicio)) {
			addError(JSFUtil.getMessage("dataFim") + ": " + getMessage("dataFimMenorInicio"));
			return false;
		}
		else {
			return true;
		}
	}

	public static String returnParam(String paramName) {
		Map<String, String> params = getExternalContext().getRequestParameterMap();
		return params.get(paramName);
	}

	public static Date parseDate(String data) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.parse(data);
		}
		catch (ParseException e) {
			return null;
		}
		catch (Exception e) {
			return null;
		}
	}
}
