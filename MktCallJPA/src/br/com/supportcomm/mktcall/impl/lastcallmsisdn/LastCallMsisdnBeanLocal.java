package br.com.supportcomm.mktcall.impl.lastcallmsisdn;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.LastCallMsisdn;

/**
 * @generated DT_ID=none
 */
@Local
public interface LastCallMsisdnBeanLocal
{

 
    /**
     * @generated DT_ID=none
     */
    public LastCallMsisdn persistLastCallMsisdn(LastCallMsisdn lastCallMsisdn);

    /**
     * @generated DT_ID=none
     */
    public LastCallMsisdn mergeLastCallMsisdn(LastCallMsisdn lastCallMsisdn);

 
    /**
     * @generated DT_ID=none
     */
    public LastCallMsisdn getLastCallMsisdnByIdMsisdn(String msisdn);


 
 
}
