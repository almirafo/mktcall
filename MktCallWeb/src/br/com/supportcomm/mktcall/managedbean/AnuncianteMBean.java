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
import javax.faces.model.SelectItem;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.Anunciante;
import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.service.agencia.AgenciaService;
import br.com.supportcomm.mktcall.service.anunciante.AnuncianteService;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.service.spot.SpotService;
import br.com.supportcomm.mktcall.service.user.UserService;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.util.Constantes;
import br.com.supportcomm.mktcall.util.EmailUtil;
import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * Classe backingbean de cadastro de anunciante
 * 
 * @author andre.vilhalba
 * 
 */
@ManagedBean
@SessionScoped
public class AnuncianteMBean extends AbstractManagedBean {

	@EJB
	private AnuncianteService anuncianteService;

	@EJB
	private AgenciaService agenciaService;

	@EJB
	private UserService userService;
	
	@EJB
	private SpotService spotService;
	
	@EJB
	private UserHistoryService historyService;	

	private Anunciante anuncianteSelecionado;
	private Logger logger = Logger.getLogger(AnuncianteMBean.class);
	private int idAgencia;
	
	private String emailHidden;

	public Anunciante getAnuncianteSelecionado() {
		return anuncianteSelecionado;
	}

	public void setAnuncianteSelecionado(Anunciante anuncianteSelecionado) {
		this.anuncianteSelecionado = anuncianteSelecionado;
	}

	/**
	 * Método de carregamento da lista da datatable através do tipo do usuário
	 * logado
	 * 
	 * @return
	 */
	public List<Anunciante> getListaAnunciantes() throws MktCallException {
		List<Anunciante> lista = new ArrayList<Anunciante>();
		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			lista = anuncianteService.getAnuncianteAll();
		} else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
			lista = anuncianteService.getAnuncianteUserAccessAgencia(super.getUserAccess().getIdUserAccess());
		}
		return lista;
	}

	/**
	 * Método de carregamento da lista do combobox de agências através do tipo
	 * do usuário logado
	 * 
	 * @return
	 */
	public List<SelectItem> getListaAgencias() throws MktCallException {
		List<SelectItem> listaRetorno = new ArrayList<SelectItem>();
		List<Agencia> lista = new ArrayList<Agencia>();
		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			lista = agenciaService.getAgenciaAll();
		} else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
			lista = agenciaService.getAgenciaAnunciante(super.getUserAccess().getIdUserAccess());
		} else {
			lista.add(this.getAnuncianteSelecionado().getAgencia());
		}
		for (Agencia a : lista) {
			listaRetorno.add(new SelectItem(a.getIdAgencia(), a.getRazaoSocial()));
		}
		return listaRetorno;
	}

	public AnuncianteMBean() {
		super();

	}

	/**
	 * Chamada inicial da pagina de anunciantes
	 * 
	 * @throws IOException
	 * @throws MktCallException
	 */
	public String iniciarAnunciante() throws IOException, MktCallException {
		this.anuncianteSelecionado = new Anunciante();
		this.setarAgencia();

		return "anunciante";

	}

	/**
	 * Método para recarregar a lista da datatable e criar um novo objeto
	 * 
	 * @throws MktCallException
	 */
	public void iniciar(ActionEvent event) throws MktCallException {
		this.getListaAnunciantes();
		this.anuncianteSelecionado = new Anunciante();
		this.anuncianteSelecionado.setAgencia(new Agencia());
		this.anuncianteSelecionado.setUserAccess(new UserAccess());
		this.setarAgencia();
	}

	/**
	 * Exclusão lógica, setar o campo status para inativo
	 */
	public void excluirAnunciante() {

		try {

			if(this.getAnuncianteSelecionado().getSpots().size() > 0){
				super.addError(super.getMessage("anuncianteComSpots"));
				return;
			}
			
			this.getAnuncianteSelecionado().setStatus(Constantes.STATUS_INATIVO.getValor());

			anuncianteService.removeAnunciante(this.getAnuncianteSelecionado());
			saveHistory(super.getMessage("anuncianteDeletado") + " Razao Social: " + this.getAnuncianteSelecionado().getRazaoSocial(), null, null);						

			super.addInfo(super.getMessage("anuncianteExcluido"));

			this.iniciar(null);

		} catch (MktCallException e) {
			super.addError(super.getMessage("problemaAnunciantes"));
			logger.error("Método excluirAnunciante - message: " + e.getMessage());
			logger.error("Método excluirAnunciante - cause: " + e.getCause());
		}
	}

	/**
	 * Método salvar/alterar o objeto Utiliza o requestContext para fechar ou
	 * não, casa haja problema, o modal de cadastro
	 */
	public String salvarAnunciante() {
//		RequestContext context = RequestContext.getCurrentInstance();
		super.setSucesso(true);
		
		try {
			if (super.isNullOrBlank(this.getAnuncianteSelecionado().getAgencia().getIdAgencia()) || this.getAnuncianteSelecionado().getAgencia().getIdAgencia().intValue() <= 0) {
				super.addError(super.getMessage("agencia") + ": " + super.getMessage("campoObrigatorio"));
				super.setSucesso(false);
			} else {
				this.getAnuncianteSelecionado().setAgencia(agenciaService.getAgenciaId(this.getAnuncianteSelecionado().getAgencia().getIdAgencia()).get(0));

				this.anuncianteSelecionado.getUserAccess().setEmail(this.getAnuncianteSelecionado().getEmail());
				this.anuncianteSelecionado.getUserAccess().setStatus(this.getAnuncianteSelecionado().getStatus());
                String msgAuditing = super.getMessage("anuncianteHistorico");
				if (super.isNullOrBlank(this.getAnuncianteSelecionado().getIdAnunciante())) {
					// sendo um INSERT, vou verificar que não tenha um login identico
					if(!userService.getUserAccessLogin(this.getAnuncianteSelecionado().getUserAccess().getLogin()).isEmpty()){		
						super.addError(super.getMessage("loginExistente"));
						super.setSucesso(false);
						return "anunciante?faces-redirect=false";
					}	
					if(!anuncianteService.getAnuncianteEmail(this.getAnuncianteSelecionado().getEmail()).isEmpty()){		
						super.addError(super.getMessage("emailExistente"));
						super.setSucesso(false);
						return "anunciante?faces-redirect=false";
				    } 

					this.anuncianteSelecionado.setOperationDate(new Timestamp(System.currentTimeMillis()));
					this.getAnuncianteSelecionado().getUserAccess().setUserType(userService.getUserTypeId(Long.valueOf(Constantes.USUARIO_ANUNCIANTE.getValor())).get(0));
					this.getAnuncianteSelecionado().setUserAccess(userService.persistUserAccess(this.getAnuncianteSelecionado().getUserAccess()));

					anuncianteService.mergeAnunciante(this.getAnuncianteSelecionado());
					msgAuditing = super.getMessage("anuncianteSalvo2");
					// FIXME arrumar email
					// EmailUtil.sendHTMLMail(super.getUsuarioAutenticado().getEmail(),
					// this.getAnuncianteSelecionado().getUserAccess().getEmail(),
					// this.getAnuncianteSelecionado().getUserAccess()
					// .getLogin(), "Usuário Chamadas Patrocinadas",
					// super.getEmailCadastroUsuario(this.getAnuncianteSelecionado().getUserAccess().getEmail(),
					// this.getAnuncianteSelecionado().getUserAccess().getLogin()));

				} else {
					this.getAnuncianteSelecionado().setUserAccess(userService.mergeUserAccess(this.getAnuncianteSelecionado().getUserAccess()));
					anuncianteService.mergeAnunciante(this.getAnuncianteSelecionado());
				}
				saveHistory(msgAuditing + " Razao Social: " + this.getAnuncianteSelecionado().getRazaoSocial(), null, null);
				EmailUtil.sendHTMLMail(super.getUsuarioAutenticado().getEmail(), this.getAnuncianteSelecionado().getUserAccess().getEmail(), this.getAnuncianteSelecionado().getUserAccess().getLogin(), "Anunciante Chamadas Patrocinadas",
						super.getEmailMessage("chamadasPatrocinadas"));				
				super.addInfo(super.getMessage("anuncianteSalvo"));
				this.iniciar(null);
			}
		} catch (EmailException ee) {
				super.addError(JSFUtil.getEmailMessage("erroEnviarEmail"));
				super.setSucesso(false);
				logger.error("Método salvarAnunciante - envio de email - message: " + ee.getMessage());	
		} catch (MktCallException e) {
			super.addError(super.getMessage("problemaAnunciantes"));
			super.setSucesso(false);
			logger.error("Método salvarAnunciante - message: " + e.getMessage());
			logger.error("Método salvarAnunciante - cause: " + e.getCause());
		}
//		context.addCallbackParam("sucesso", super.isSucesso());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "anunciante?faces-redirect=true";
	}

	/**
	 * Carregar a agência caso o tipo do usuário seja anunciante ou agência
	 */
	private void setarAgencia() throws MktCallException {
		if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
	//		this.anuncianteSelecionado.setAgencia(agenciaService.getAgenciaId(super.getUserAccess().getAgencias().get(0).getIdAgencia()).get(0));
		} else if (super.getUserType() == Constantes.USUARIO_ANUNCIANTE.getValor()) {
	
		}
	}

	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
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
