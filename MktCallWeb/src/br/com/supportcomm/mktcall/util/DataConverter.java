package br.com.supportcomm.mktcall.util;

import java.text.SimpleDateFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="DataConverter")
public class DataConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object data) {

		String dataFormat = "";
		
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

			java.util.Date parsedDate = dateFormat.parse(data.toString());
			
			java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");  
	      
			dataFormat = formatado.format(timestamp);
			
		} catch (Exception e) {

		}

		return dataFormat;
	}

}
