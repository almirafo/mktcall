package br.com.supportcomm.mktcall.service.config;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.config.ConfigBeanLocal;
import br.com.supportcomm.mktcall.entity.Config;

/**
 * Session Bean implementation class CdrService
 */
@Stateless
@LocalBean
public class ConfigService  {

	@EJB private ConfigBeanLocal configBean;
	
    /**
     * Default constructor. 
     */
    public ConfigService() {
        // TODO Auto-generated constructor stub
    	 
    }

	/**
	 * Retorna os Ips configurados e suas respectivs pastas para ftp<br/>
	 * 
	 * @param type valor file
	 * @return
	 */
	
    public String getValueByIndentify(String indentify) {
    	
    	return configBean.getValueByIndentify(indentify);
		
	}
}
