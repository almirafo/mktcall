package br.com.supportcomm.mktcall.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter( value="converterPermissao")
public class PermissaoConverter implements Converter
{

	
	 
    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
    {
     //   return permissaoBean.getPermissaoCarregarPermissaoPorComponente(arg2);
    	return null;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
    {
     //   return ((Permissao) arg2).getLinkAcesso();
    	return null;
    }

}

