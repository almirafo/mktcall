package br.com.supportcomm.mktcall.util;

import java.sql.Timestamp;
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
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.util.JSFUtil;
import br.com.supportcomm.mktcall.exception.MktCallException;

/**
 * Classe de utilidades
 * 
 * 
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
		SimpleDateFormat df = new SimpleDateFormat("dd ' de 'MMMM ' de ' yyyy");
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

	public static UserAccess getUsuarioAutenticado() {
		try {
			UserAccess user = new UserAccess();
			user = (UserAccess) getSessionAtiva(false).getAttribute(Constantes.USUARIO_AUTENTICADO.getDescricao());
			
			return user;
		} catch (Exception e) {
			return new UserAccess();
		}

	}

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
	 * <<<<<<< .mine retorna se atravï¿½s do id do componente de tela o usuario
	 * logado tem permissï¿½o para acesso ======= retorna se através do id do
	 * componente de tela o usuário logado tem permissão para acesso >>>>>>>
	 * .r156
	 * 
	 * @param idComponente
	 * @return
	 */

	public boolean permitirAcesso(String idComponente) {
		try {

			// Permissao permissao =
			// permissaoBean.getPermissaoCarregarPermissaoPorComponente(idComponente).get(0);
			// if (!isNullOrBlank(permissao))
			// {
			// TODO Permissao
			/*
			 * if (!isNullOrEmpty(getUsuarioAutenticado().getPermissaos())) {
			 * for (Permissao p : getUsuarioAutenticado().getPermissaos()) { if
			 * (permissao.getIdPermissao().intValue() == p.getIdPermissao()
			 * .intValue()) { return true; } } }
			 */
			return true;
			// }
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * <<<<<<< .mine retorna lista de permissï¿½es padrï¿½o por tipo de usuario,
	 * que estï¿½o configuradas no xml permissaoPadrao no caminho determinado no
	 * messages.properties - caminhoPermissao ======= retorna lista de
	 * permissões padrão por tipo de usuário, que estão configuradas no xml
	 * permissaoPadrao no caminho determinado no messages.properties -
	 * caminhoPermissao >>>>>>> .r156
	 * 
	 * @param iduserType
	 * @return
	 * @throws MktCallException
	 */
	public List getListaPermissaoPadrao(Integer iduserType) throws MktCallException {
		/*
		 * List<Permissao> listaPermissao = new ArrayList<Permissao>(); XStream
		 * xStream = new XStream(new DomDriver()); PermissoesPadrao
		 * permissoesPadrao = new PermissoesPadrao(); try { InputStream input =
		 * new FileInputStream(getMessage("caminhoPermissao"));
		 * xStream.autodetectAnnotations(true); xStream.processAnnotations(new
		 * Class[] { PermissoesPadrao.class, PermissaoPadrao.class });
		 * permissoesPadrao = (PermissoesPadrao) xStream.fromXML(input); } catch
		 * (FileNotFoundException e) { return null; } for (PermissaoPadrao p :
		 * permissoesPadrao.getPermissaoPadrao()) { if (iduserType.intValue() ==
		 * p.getTipoUsuario().intValue()) { for (String link :
		 * p.getLinkAcesso()) { Permissao permissao =
		 * permissaoBean.getPermissaoCarregarPermissaoPorComponente
		 * (link).get(0); listaPermissao.add(permissao); } } }
		 */
		// return listaPermissao;
		return null;
	}

	/**
	 * <<<<<<< .mine Calcula a diferenÃ§a de duas datas em dias <br>
	 * <b>Importante:</b> Quando realiza a diferenÃ§a em dias entre duas datas,
	 * este mÃ©todo considera as horas restantes e as converte em fraÃ§Ã£o de
	 * dias. ======= Calcula a diferença de duas datas em dias <br>
	 * <b>Importante:</b> Quando realiza a diferença em dias entre duas datas,
	 * este método considera as horas restantes e as converte em fração de dias.
	 * >>>>>>> .r156
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
		} else if (isNullOrBlank(dataFim)) {
			addError(JSFUtil.getMessage("dataFim") + ": " + getMessage("campoObrigatorio"));
			return false;
		} else if (dataInicio.after(dataFim)) {
			addError(JSFUtil.getMessage("dataInicio") + ": " + getMessage("dataInicioMaiorFim"));
			return false;
		} else if (dataFim.before(dataInicio)) {
			addError(JSFUtil.getMessage("dataFim") + ": " + getMessage("dataFimMenorInicio"));
			return false;
		} else {
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
		} catch (ParseException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String toSimpleDate(Timestamp data) {

		String dataFormat = "";
		
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

			java.util.Date parsedDate = dateFormat.parse(data.toString());
			
			java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");  
	      
			dataFormat = formatado.format(timestamp);
			
		} catch (Exception e) {

		}

		return dataFormat;
	}
	
	
	/**
	 * retorna a hora atual em minutos
	 * @return
	 */
	public long getRigthNowInMinutes(){
	    Calendar rightNow = Calendar.getInstance();
	    return rightNow.get(Calendar.HOUR_OF_DAY)*60 + rightNow.get(Calendar.MINUTE) ;
	}

	public long getHoursInMinutes(String hours){
		
		int hour = Integer.parseInt( hours.substring(0,2))*60;
		int minutes=Integer.parseInt( hours.substring(3));
		return hour+minutes;
		
	}
	
	public String covertFormat(Timestamp data,String _format){
		String dataFormat = _format;
		
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

			java.util.Date parsedDate = dateFormat.parse(data.toString());
			
			java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

			SimpleDateFormat formatado = new SimpleDateFormat(dataFormat);  
	      
			dataFormat = formatado.format(timestamp);
			
		} catch (Exception e) {

		}

		return dataFormat;
		
	}
	
	
}
