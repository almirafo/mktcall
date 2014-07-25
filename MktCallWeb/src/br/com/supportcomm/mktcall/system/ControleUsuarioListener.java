package br.com.supportcomm.mktcall.system;

import java.io.IOException;
import java.util.Locale;

import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * 
 */
public class ControleUsuarioListener implements PhaseListener {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	public void beforePhase(PhaseEvent event) {
	}

	/**
	 * Método executado antes da execu��o da fase, confirmando se o
	 * usuário est� logado ou n�o. Se sim, continua normalmente, caso
	 * contr�rio, retorna para a p�gina de login
	 */
	public void afterPhase(PhaseEvent event) {

		Locale newLocale = (Locale) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("locale");

		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

		viewRoot.setLocale(newLocale);
		JSFUtil.getSessionAtiva(true).setAttribute("locale", newLocale);

		FacesContext fc = event.getFacesContext();
		HttpSession session = JSFUtil.getSessionAtiva(true);

		// Check to see if they are on the login page.
		boolean loginPage = fc.getViewRoot().getViewId().lastIndexOf("index") > -1 || fc.getViewRoot().getViewId().lastIndexOf("seguranca") > -1
				|| fc.getViewRoot().getViewId().lastIndexOf("termos") > -1 ? true : false;
		if (!loginPage && notLoggedIn(session)) {
			session.invalidate();
			try {
				JSFUtil.getExternalContext().redirect(JSFUtil.getExternalContext().getRequestContextPath() + "/index.xhtml");
			} catch (IOException e) {
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "/index.xhtml");
			}
		}
	}

	/**
	 * Método que retorna usuário logado
	 * 
	 * @param session
	 * @return
	 */
	private boolean notLoggedIn(HttpSession session) {
		return JSFUtil.isNullOrBlank(session.getAttribute("usuarioAutenticado"));
	}

}
