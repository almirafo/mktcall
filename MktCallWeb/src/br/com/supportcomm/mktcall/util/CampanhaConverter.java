package br.com.supportcomm.mktcall.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;

/**
 * Conversor do objeto Permissao, se existe ou não permissão
 * 

 * 
 */
public class CampanhaConverter implements Converter {

	private CampanhaService campanhaService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		return campanhaService.getCampanhaId(Long.parseLong((arg2)));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		return ((Campanha) arg2).getName();
	}

}
