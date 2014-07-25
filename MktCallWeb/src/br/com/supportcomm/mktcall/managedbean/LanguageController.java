package br.com.supportcomm.mktcall.managedbean;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import br.com.supportcomm.mktcall.util.JSFUtil;

@ManagedBean
@SessionScoped
public class LanguageController extends AbstractManagedBean
{
    private Locale currentLocale = new Locale("pt", "BR");

   public String englishLocale()
    {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = Locale.US;
        JSFUtil.getSessionAtiva(true).setAttribute("locale", currentLocale);
        viewRoot.setLocale(currentLocale);
        setCurrentLocale(currentLocale);
                
        return null;
        
    }

   public String portugueseLocale()
    {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("pt", "BR");
        JSFUtil.getSessionAtiva(true).setAttribute("locale", currentLocale);
        viewRoot.setLocale(currentLocale);
                
        return null;
    }

   public String spanishLocale()
    {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("es", "ES");
        JSFUtil.getSessionAtiva(true).setAttribute("locale", currentLocale);
        viewRoot.setLocale(currentLocale);
                
        return null;
        
    }

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

 
    
}
