package br.com.supportcomm.mktcall.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * Classe para validar email
 * 
 * @author eduardo.zimerer
 * 
 */

@FacesValidator(value = "EmailValidador")
public class EmailValidador implements Validator {
	private static final String EMAIL_REGEXP = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Método para validar email de acordo com as normas pré-estabelecidas
	 */
	@Override
	public void validate(FacesContext context, UIComponent c, Object val)
			throws ValidatorException {
		String email = (String) val;
		Pattern mask = null;
		mask = Pattern.compile(EMAIL_REGEXP);
		Matcher matcher = mask.matcher(email);

		if (!matcher.matches()) {
			FacesMessage message = new FacesMessage();
			message.setDetail(JSFUtil.getMessage("emailInvalido"));
			message.setSummary(JSFUtil.getMessage("emailInvalido"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}
}
