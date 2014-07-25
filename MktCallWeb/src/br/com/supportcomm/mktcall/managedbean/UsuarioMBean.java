package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.entity.UserType;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.service.user.UserService;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.util.Constantes;
import br.com.supportcomm.mktcall.util.EmailUtil;
import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * Classe backingbean de cadastro de usuário
 * 
 * @author andre.vilhalba
 * 
 */
@ManagedBean
@SessionScoped
public class UsuarioMBean extends AbstractManagedBean {
	private UserAccess usuarioSelecionado;
	private String passwordConfirmado;
	

	@EJB
	private UserService userService;
	@EJB
	private UserHistoryService historyService;

	private Logger logger = Logger.getLogger(UsuarioMBean.class);

	/**
	 * Método de carregamento da lista da datatable através do tipo do usuário
	 * logado
	 * 
	 * @return
	 */
	public List<UserAccess> getListaUsuarios() throws MktCallException {

		List<UserAccess> lista = new ArrayList<UserAccess>();

		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			lista = userService.getUserAccessAll();
		}
		else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
			lista = userService.getUserAccessUsuario(super.getUserAccess().getIdUserAccess());
		}

		return lista;
	}

	/**
	 * método de preencimento da lista utilizada na combo de tipo de usuario do
	 * cadastro de campanha
	 * 
	 * @return List<Spot>
	 * @throws MktCallException
	 */
	public List<SelectItem> getListaUserTypes() throws MktCallException {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<UserType> listaUserType = userService.getUserTypeAll();
		for (UserType c : listaUserType) {
			lista.add(new SelectItem(c.getIdUserType(), c.getNameUserType()));
		}
		return lista;
	}

	public UsuarioMBean() {
		super();

	}

	/**
	 * Chamada inicial da pagina de Usuarios
	 * 
	 * @throws IOException
	 */
	public String iniciarUsuario() throws IOException {
		this.usuarioSelecionado = new UserAccess();
		this.usuarioSelecionado.setUserType(new UserType());

		this.passwordConfirmado = "";

		return "usuario";
	}

	/**
	 * Método para iniciar um novo usuário
	 */
	public void iniciar() {
		try {
			this.getListaUsuarios();

			usuarioSelecionado = new UserAccess();
			usuarioSelecionado.setUserType(new UserType());

			this.passwordConfirmado = "";
		}
		catch (MktCallException e) {
			logger.error("Método iniciar(): " + e);		
			e.printStackTrace();
		}
	}

	/**
	 * Exclusão lógica, setar o campo status para inativo
	 */
	public void excluirUsuario() throws MktCallException {
		try {

			this.getUsuarioSelecionado().setStatus(Constantes.STATUS_INATIVO.getValor());
			userService.mergeUserAccess(this.getUsuarioSelecionado());
			super.addInfo(super.getMessage("usuarioExcluido"));
			saveHistory(super.getMessage("usuarioDeletado") +"- "  + this.getUsuarioSelecionado().getLogin(), null, null );
			logger.info("Usuario excluido com sucesso: " + this.getUsuarioSelecionado().getLogin());
			this.iniciar();
		}
		catch (Exception e) {
			logger.error("Erro ao excluir usuario: " + this.getUsuarioSelecionado().getLogin());
		}
	}

	/**
	 * Método salvar/alterar o objeto Utiliza o requestContext para fechar ou
	 * não, casa haja problema, o modal de cadastro
	 */
	public String salvarUsuario() {
//		RequestContext context = RequestContext.getCurrentInstance();
		super.setSucesso(true);
/*
 * 
 *      Desativado pois não permitia a alteração do usuário
		if (!userService.getUserAccessLogin(this.getUsuarioSelecionado().getLogin()).isEmpty()) {

			super.addError(super.getMessage("loginExistente"));
			super.setSucesso(false);
			return;

		}
*/
		try {
			if (super.isNullOrBlank(this.getUsuarioSelecionado().getIdUserAccess())) {
				if (this.getUsuarioSelecionado().getUserType().getIdUserType().intValue() == Constantes.USUARIO_AGENCIA.getValor()) {
					super.addWarn(super.getMessage("erroCadastroAgencia"));
					super.setSucesso(false);
				}
				else if (this.getUsuarioSelecionado().getUserType().getIdUserType().intValue() == Constantes.USUARIO_ANUNCIANTE.getValor()) {
					super.addWarn(super.getMessage("erroCadastroAnunciante"));
					super.setSucesso(false);
				}
			}
			if (!this.passwordConfirmado.equals(this.usuarioSelecionado.getPassword())) {
				super.addError(super.getMessage("erroConfirmarSenha"));
				super.setSucesso(false);
			}
			else {
				String msgUsuario = super.getMessage("usuarioHistorico");
				if (super.isNullOrBlank(this.getUsuarioSelecionado().getIdUserAccess())) {
					// Colocado essa condição pois não pode permitir cadastrar um novo usuario com Login Existente
					if (!userService.getUserAccessLogin(this.getUsuarioSelecionado().getLogin()).isEmpty()) {
						super.addError(super.getMessage("loginExistente"));
						super.setSucesso(false);
						FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
						return "usuario?faces-redirect=false";
					}
					userService.persistUserAccess(this.getUsuarioSelecionado());
					msgUsuario = super.getMessage("usuarioSalvo");
				}
				else {
					userService.mergeUserAccess(this.getUsuarioSelecionado());
				}
				saveHistory(msgUsuario + "- "  + this.getUsuarioSelecionado().getLogin(), null, null );	
				super.addInfo(super.getMessage("usuarioSalvo"));				
				EmailUtil.sendHTMLMail(super.getUsuarioAutenticado().getEmail(), this.getUsuarioSelecionado().getEmail(), this.getUsuarioSelecionado().getLogin(), "Usuário Chamadas Patrocinadas",
						super.getEmailCadastroUsuario(this.getUsuarioSelecionado().getLogin(), this.getUsuarioSelecionado().getPassword()));
				this.iniciar();
				logger.info("Usuario salvo com sucesso" + this.getUsuarioSelecionado().getLogin());
			}
		}
		catch (EmailException ee) {
			super.addError(JSFUtil.getEmailMessage("erroEnviarEmail"));
			super.setSucesso(false);
			logger.error("Método salvarUsuario - envio de email - message: " + ee.getMessage());	
		}
		catch (Exception e) {
			super.addError(super.getMessage("problemaUsuarios"));
			super.setSucesso(false);
			logger.error("Método salvarUsuario - problema em salvar usuário - message: " + e.getMessage());
		}
//		context.addCallbackParam("sucesso", super.isSucesso());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);		
		return "usuario?faces-redirect=true";		
	}

	public String getPasswordConfirmado() {
		return passwordConfirmado;
	}

	public void setPasswordConfirmado(String passwordConfirmado) {
		this.passwordConfirmado = passwordConfirmado;
	}

	public UserAccess getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(UserAccess usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	/**
	 * Método para desabilitar a combo tipo de usuário
	 * 
	 * @return
	 */
	public boolean getDesabilitarTipoUsuario() {
		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			return false;
		}
		return true;
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
}
