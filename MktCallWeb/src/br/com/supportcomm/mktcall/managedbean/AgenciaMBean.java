package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.service.agencia.AgenciaService;
import br.com.supportcomm.mktcall.service.anunciante.AnuncianteService;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.service.user.UserService;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.util.Constantes;
import br.com.supportcomm.mktcall.util.EmailUtil;
import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * Classe backingbean de cadastro de agência
 * 
 * @author andre.vilhalba
 * 
 */
@ManagedBean
@SessionScoped
public class AgenciaMBean extends AbstractManagedBean {
	private Agencia agenciaSelecionada;

	@EJB
	private AgenciaService agenciaService;

	@EJB
	private AnuncianteService anuncianteService;

	@EJB
	private UserService userService;
	
	@EJB
	private UserHistoryService historyService;	
	
	public String emailHidden;

	private Logger logger = Logger.getLogger(AgenciaMBean.class);

	public Agencia getAgenciaSelecionada() {
		return agenciaSelecionada;
	}

	public void setAgenciaSelecionada(Agencia agenciaSelecionada) {
		this.agenciaSelecionada = agenciaSelecionada;
	}

	/**
	 * Método de carregamento da lista da datatable através do tipo do usuário
	 * logado
	 * 
	 * @return
	 */
	public List<Agencia> getListaAgencias() throws MktCallException {
		List<Agencia> lista = new ArrayList<Agencia>();
		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			lista = agenciaService.getAgenciaAll();
		}
		else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
			lista = agenciaService.getAgenciaAnunciante(super.getUserAccess().getIdUserAccess());
		}
		else {
			lista = agenciaService.getAgenciaAnunciante(super.getUserAccess().getIdUserAccess());
		}
		return lista;
	}

	public AgenciaMBean() {
		super();

	}

	/**
	 * Chamada inicial da pagina de agencias
	 * 
	 * @throws IOException
	 */
	public String iniciarAgencia() throws IOException {
		this.agenciaSelecionada = new Agencia();
		return "agencia";

	}

	/**
	 * Método para recarregar a lista da datatable e criar um novo objeto
	 * 
	 * @throws MktCallException
	 */
	public void iniciar(ActionEvent action) throws MktCallException {
		this.getListaAgencias();
		agenciaSelecionada = new Agencia();
		agenciaSelecionada.setUserAccess(new UserAccess());

	}

	public String iniciar() {

		System.out.println("teste");
		return "";
	}

	/**
	 * Exclusão lógica, setar o campo status para inativo
	 */
	public void excluirAgencia() {
		try {
			this.getAgenciaSelecionada().getUserAccess().setStatus(Constantes.STATUS_INATIVO.getValor());
			userService.mergeUserAccess(this.getAgenciaSelecionada().getUserAccess());
			this.getAgenciaSelecionada().setStatus(Constantes.STATUS_INATIVO.getValor());
			agenciaService.mergeAgencia(this.getAgenciaSelecionada());
			saveHistory(super.getMessage("agenciaDeletada") + " Razao Social: " + this.getAgenciaSelecionada().getRazaoSocial(), null, null);						
			super.addInfo(super.getMessage("agenciaExcluido"));
			this.iniciar(null);
		}
		catch (MktCallException e) {
			super.addError(super.getMessage("problemaAgencias"));
			logger.error("Método excluirAgencia - message: " + e.getMessage());
		}
	}

	/**
	 * Método salvar/alterar o objeto Utiliza o requestContext para fechar ou
	 * não, casa haja problema, o modal de cadastro
	 */
	public String salvarAgencia() {
//		RequestContext context = RequestContext.getCurrentInstance();
		super.setSucesso(true);
		try {		
			this.agenciaSelecionada.getUserAccess().setEmail(this.getAgenciaSelecionada().getEmail());
			this.agenciaSelecionada.getUserAccess().setStatus(this.getAgenciaSelecionada().getStatus());

			String msgAuditing = super.getMessage("agenciaHistorico");
			if (!super.isNullOrBlank(this.getAgenciaSelecionada().getIdAgencia())) {
				// it is an UPDATE
//				if (isEmailDuplicated()){
//					setError("emailExistente");					
//					return "agencia?faces-redirect=false";	
//				}
				// update of an already existing Agency.
				agenciaService.mergeAgencia(this.getAgenciaSelecionada());
			}
			else {
				// since it is an INSERT, I am going to check if login duplicated ...
				if (isLoginInUse()) {
					setError("loginExistente");					
					return "agencia?faces-redirect=false";
				}
				// ... and if e-mail address already exists.
				if(isEmailInUse()){		
					setError("emailExistente");					
					return "agencia?faces-redirect=false";					
			    }
				msgAuditing = super.getMessage("agenciaSalva");				
				Agencia agenciaToSave = this.getAgenciaSelecionada();
				UserAccess userAcess = this.getAgenciaSelecionada().getUserAccess();
				userAcess.setUserType(userService.getUserTypeId(Long.valueOf(Constantes.USUARIO_AGENCIA.getValor())).get(0));
				userService.persistUserAccess(userAcess);
				agenciaToSave.setUserAccess(userAcess);
				agenciaToSave.setOperationDate(new Timestamp(System.currentTimeMillis()));
				agenciaService.mergeAgencia(agenciaToSave);
			}
			saveHistory(msgAuditing + " Razao Social: " + this.getAgenciaSelecionada().getRazaoSocial(), null, null);
			super.addInfo(super.getMessage("agenciaSalvo"));			
			EmailUtil.sendHTMLMail(super.getUsuarioAutenticado().getEmail(), this.getAgenciaSelecionada().getEmail(), this.getAgenciaSelecionada().getNomeContato(), "Agência Chamadas Patrocinadas",
					super.getEmailMessage("chamadasPatrocinadas"));			
			this.iniciar(null);
		}
		catch (EmailException ee) {
			super.addError(JSFUtil.getEmailMessage("erroEnviarEmail"));
			super.setSucesso(false);
			logger.error("Método salvarAgencia - envio de email - message: " + ee.getMessage());	
		}
		catch (MktCallException e) {
			super.addError(super.getMessage("problemaAgencias"));
			super.setSucesso(false);
			logger.error("Método salvarAgencia - message: " + e.getMessage());
		}
		catch (Exception e) {
			super.addError(super.getMessage("problemaAgencias"));
			super.setSucesso(false);
			logger.error("Método message: " + e.getMessage());
		}
//		context.addCallbackParam("sucesso", super.isSucesso());
//	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,super.getMessage("agenciaSalvo"),""));
	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true); //			
		return "agencia?faces-redirect=true";
	}
	
	private void setError(String errorType) {
		super.addError(super.getMessage(errorType));
		super.setSucesso(false);
	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	// Returns true if e-mail in use for update activity is duplicated.
	// This method is used only during update activity.
	private boolean isEmailDuplicated() {
		// check whether the e-mail is in use (if and only if the field of e-mail was updated).
		logger.info("AAAAAAAAAAA: o valor do e-mail: " + this.getEmailHidden());
		if (!this.getAgenciaSelecionada().getEmail().equalsIgnoreCase(this.getEmailHidden())) {
			if(isEmailInUse()){
				return true;
			}
		}
		return false;
	}
	
	// Checks on the DB if the e-mail (IngoreCase) is already in use.
	private boolean isEmailInUse() {
		logger.info("O endereço do e-mail: " + this.getAgenciaSelecionada().getEmail());
		return !(agenciaService.getAgenciaEmail(this.getAgenciaSelecionada().getEmail()).isEmpty());
	}
	
	private boolean isLoginInUse() {
		return !(userService.getUserAccessLogin(this.getAgenciaSelecionada().getUserAccess().getLogin()).isEmpty());
	}
	
	private void saveHistory(String action, Long idCampanha, Long idSpot) {
		try {			
			UserHistory userHistory = new UserHistory();
			userHistory.setDateInsered(new Timestamp(System.currentTimeMillis()));
			userHistory.setAction(action);
			userHistory.setIdCampanha(idCampanha);
			userHistory.setIdSpot(idSpot);
			userHistory.setIdUserAccess(super.getUserAccess().getIdUserAccess());
			userHistory.setUserLogin(super.getUserAccess().getLogin());
			userHistory.setUserType(super.getUserAccess().getUserType().getIdUserType());
			historyService.persistUserHistory(userHistory);			
		}
		catch (Exception e) {
			logger.error(e);
		}
	}

	public String getEmailHidden() {
		return emailHidden;
	}

	public void setEmailHidden(String emailHidden) {
		this.emailHidden = emailHidden;
	}
	
	
}
