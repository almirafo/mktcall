package br.com.suppportcomm.mktcall.impl.foward;

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

	public Foward getFoward(Foward foward);
	public Foward getFowardByCampaingCode(String code) ;
	public Foward persistFoward(Foward foward);
	public Foward removeFoward(Foward foward);
	public Foward mergeFoward(Foward foward);

}
