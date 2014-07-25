package br.com.supportcomm.mktcall.service.areacode;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.impl.areacode.AreaCodeBeanLocal;

/**
 * Session Bean implementation class AreaCodeService
 */
@Stateless
@LocalBean
public class AreaCodeService {

	@EJB private AreaCodeBeanLocal areaCodeBean;
	
    /**
     * Default constructor. 
     */
    public AreaCodeService() {
        // TODO Auto-generated constructor stub
    }


    public List<AreaCode> getAreaCodeAll(){
    	return areaCodeBean.getAreaCodeAll();
    }
    
    
    public List<AreaCode> getAreaCodeGetSelected(Campanha campanha){
    	return areaCodeBean.getAreaCodeGetSelected( campanha);
    }

    public List<AreaCode> getAreaCodeGetNotSelected(Campanha campanha){
    	return areaCodeBean.getAreaCodeGetNotSelected( campanha);
    }


	public AreaCode getAreaCode(AreaCode areaCode) {
		return areaCodeBean.getAreaCode( areaCode);
		
	}

    
    
}
