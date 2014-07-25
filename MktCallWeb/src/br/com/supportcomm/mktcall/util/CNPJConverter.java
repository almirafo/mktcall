package br.com.supportcomm.mktcall.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="cnpjConverter")  
public class CNPJConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		return arg2;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		if (arg2 == null) {
			return "";
		}

		return formataCNPJ(arg2.toString());
	}

	public static String formataCNPJ(String cnpj) {
		if (cnpj == null || "".equals(cnpj)) {
			return "";
		}

		try {
		
			cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8 ) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
			
		} catch (Exception e) {
			
		}

		return cnpj;

	}
}