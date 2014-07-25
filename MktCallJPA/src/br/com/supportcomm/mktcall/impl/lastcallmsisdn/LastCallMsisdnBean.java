package br.com.supportcomm.mktcall.impl.lastcallmsisdn;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.supportcomm.mktcall.entity.LastCallMsisdn;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "LastCallMsisdnBean", mappedName = "MktCallJPA-LastCallMsisdnBean")
public class LastCallMsisdnBean
        implements LastCallMsisdnBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public LastCallMsisdnBean() {
    }



    /**
     * @generated DT_ID=none
     */
    public LastCallMsisdn persistLastCallMsisdn(LastCallMsisdn lastCallMsisdn) {
        em.persist(lastCallMsisdn);
        return lastCallMsisdn;
    }

    /**
     * @generated DT_ID=none
     */
    public LastCallMsisdn mergeLastCallMsisdn(LastCallMsisdn lastCallMsisdn) {
        return em.merge(lastCallMsisdn);
    }

  


    /**
     * @generated DT_ID=none
     */
    public LastCallMsisdn getLastCallMsisdnByIdMsisdn(String msisdn) {
    	@SuppressWarnings("unchecked")
		List<LastCallMsisdn> lastCallMsisdns =  em.createNamedQuery("Lastcallmsisdn.byMsisdn").setParameter("msisdn", msisdn).getResultList();
    	if (lastCallMsisdns.isEmpty()){
    		return new LastCallMsisdn();
    	}
    	
    	return lastCallMsisdns.get(0);
    }



 

   

}
