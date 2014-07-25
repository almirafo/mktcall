package br.com.supportcomm.mktcall.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * Validador de usuário, se está preenchido, se já existe login.
 * 
 * 
 */
@FacesValidator(value = "UserValidador")
public class UsuarioValidador implements Validator {
	/**
	 * Método validate, validar se existe login, se digitado.
	 */

	// private UserBeanLocal userBean = EJB.lookup(UserBean.class);

	@Override
	public void validate(FacesContext context, UIComponent c, Object val) throws ValidatorException {
		HtmlInputText inputText = (HtmlInputText) c;
		if (!inputText.isDisabled()) {
			if (JSFUtil.isNullOrBlank(val)) {
				FacesMessage message = new FacesMessage();
				message.setDetail(JSFUtil.getMessage("login") + ":" + JSFUtil.getMessage("loginNaoDigitado"));
				message.setSummary(JSFUtil.getMessage("login") + ":" + JSFUtil.getMessage("loginNaoDigitado"));
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
			// String login = (String) val;

			// List<UserAccess> lista = new ArrayList<UserAccess>();
			/*
			 * lista = userBean.getUserAccessFindByLogin(login); if
			 * (!JSFUtil.isNullOrEmpty(lista)) { FacesMessage message = new
			 * FacesMessage();
			 * message.setDetail(JSFUtil.getMessage("loginExistente"));
			 * message.setSummary(JSFUtil.getMessage("loginExistente"));
			 * message.setSeverity(FacesMessage.SEVERITY_ERROR); throw new
			 * ValidatorException(message); }
			 */
		}
	}

}
