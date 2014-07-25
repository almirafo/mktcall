package br.com.supportcomm.mktcall.util;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.service.areacode.AreaCodeService;

@ManagedBean
@SessionScoped
public class AreaCodeConverter implements Converter {
	
	@EJB AreaCodeService areaCodeService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		
		AreaCode areaCode = new AreaCode();
		areaCode.setIdAreaCode(Long.parseLong(value));
		return areaCodeService.getAreaCode(areaCode);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// TODO Auto-generated method stub
		return ((AreaCode) value).getIdAreaCode().toString();
	}

}
