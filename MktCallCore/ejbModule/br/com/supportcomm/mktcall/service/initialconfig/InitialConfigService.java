package br.com.supportcomm.mktcall.service.initialconfig;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.InitialConfig;
import br.com.supportcomm.mktcall.impl.initialconfig.InitialConfigBeanLocal;

/**
 * Session Bean implementation class CdrService
 */
@Stateless
@LocalBean
public class InitialConfigService  {

	@EJB private InitialConfigBeanLocal initialConfigBean;
	
    /**
     * Default constructor. 
     */
    public InitialConfigService() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Retorna os Ips configurados e suas respectivs pastas para ftp<br/>
	 * 
	 * @param type valor file
	 * @return
	 */
	public List<InitialConfig> getInitialConfigByType(String type) {
		
		return initialConfigBean.getInitialConfigByType(type);
	}

	
	public Boolean getEnableFTP(){
		return initialConfigBean.getEnableFTP();
	}
	
	public String getOriFTP() {
		return  initialConfigBean.getOriFTP();
	}
	
	 public String getLoginFTP(){
		 return  initialConfigBean.getLoginFTP();
	 }
	
}
