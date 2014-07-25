package br.com.supportcomm.mktcall.impl.initialconfig;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.supportcomm.mktcall.entity.InitialConfig;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "InitialConfigBean", mappedName = "MktCallJPA-InitialConfigBean")
public class InitialConfigBean
        implements InitialConfigBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public InitialConfigBean() {
    }


	@Override
	public List<InitialConfig> getInitialConfigByType(String type) {
		@SuppressWarnings("unchecked")
		List<InitialConfig> rs =em.createNamedQuery("InitialConfig.getByType").setParameter("type", type).getResultList(); 
		return rs;
		
	}
	
	@Override
	public Boolean getEnableFTP(){
		Boolean retorno=false;
		InitialConfig initialConfig= (InitialConfig) em.createNamedQuery("InitialConfig.getEnableFTP").getSingleResult();
		if(initialConfig!=null){
			retorno=Boolean.parseBoolean(initialConfig.getPathFile()); 
		}
		return  retorno;
	}


	@Override
	public String getOriFTP() {
		String retorno = "";
		InitialConfig initialConfig= (InitialConfig) em.createNamedQuery("InitialConfig.getOriFTP").getSingleResult();
		if(initialConfig!=null){
			retorno=initialConfig.getPathFile(); 
		}
		return retorno;
	}


	@Override
	public String getLoginFTP() {
		String retorno = "";
		InitialConfig initialConfig= (InitialConfig) em.createNamedQuery("InitialConfig.getLoginFTP").getSingleResult();
		if(initialConfig!=null){
			retorno=initialConfig.getPathFile(); 
		}
		return retorno;

	}
	
}
