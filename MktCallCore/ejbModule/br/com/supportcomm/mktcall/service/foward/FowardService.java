package br.com.supportcomm.mktcall.service.foward;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Foward;
import br.com.supportcomm.mktcall.impl.foward.FowardBeanLocal;


@Stateless
@LocalBean
public class FowardService {

	public FowardService()
	{
		
	}
	
	@EJB
	private FowardBeanLocal fowardBean;
	
	 /**
     * @generated DT_ID=none
     */
    public Foward persistFoward(Foward foward){
    	return fowardBean.persistFoward(foward);
    }

    /**
     * @generated DT_ID=none
     */
    public Foward mergeFoward(Foward foward){
    	return fowardBean.mergeFoward(foward);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeFoward(Foward foward){
    	fowardBean.removeFoward(foward);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeFowardByCampanha(Long idCampanha){
    	fowardBean.removeFowardByCampanha(idCampanha);
    }

    
    /**
     * @generated DT_ID=none
     */
    public Foward getFowardByCampanha(Long idCampanha){
    	return fowardBean.getFowardByCampanha(idCampanha);
    }

    
	
}
