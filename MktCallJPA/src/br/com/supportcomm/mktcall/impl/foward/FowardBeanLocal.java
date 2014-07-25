package br.com.supportcomm.mktcall.impl.foward;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.Foward;


/**
 * @generated DT_ID=none
 */
@Local
public interface FowardBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Foward persistFoward(Foward foward);

    /**
     * @generated DT_ID=none
     */
    public Foward mergeFoward(Foward foward);

    /**
     * @generated DT_ID=none
     */
    public void removeFoward(Foward foward);

    /**
     * @generated DT_ID=none
     */
    public void removeFowardByCampanha(Long idCampanha);

    
    /**
     * @generated DT_ID=none
     */
    public Foward getFowardByCampanha(Long idCampanha);

    

}
