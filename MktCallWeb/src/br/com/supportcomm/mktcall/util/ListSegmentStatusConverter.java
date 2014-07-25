package br.com.supportcomm.mktcall.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="ListSegmentStatusConverter")
public class ListSegmentStatusConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object status) {
		
		String statusR = "";
		
		if (status.equals("0")) statusR = "Processo Finalizado";
		if (status.equals("1")) statusR = "Processo Abortado";
		if (status.equals("2")) statusR = "Processo em Andamento";
		
		return statusR;
	}

	

}
