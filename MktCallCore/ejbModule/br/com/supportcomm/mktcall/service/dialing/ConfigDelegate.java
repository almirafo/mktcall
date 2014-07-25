package br.com.supportcomm.mktcall.service.dialing;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.exception.DialingException;
import br.com.supportcomm.mktcall.config.ConfigBeanLocal;

@Stateless
@LocalBean
public class ConfigDelegate {
	
	@EJB
	ConfigBeanLocal configBean;
	
	 public String getValueByIndentify(String indentify) throws DialingException{
		 return configBean.getValueByIndentify( indentify);
	 }
}
