package br.com.supportcomm.mktcall.service.lastcallmsisdn;

import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;


import br.com.supportcomm.mktcall.entity.LastCallMsisdn;
import br.com.supportcomm.mktcall.impl.lastcallmsisdn.LastCallMsisdnBeanLocal;


/**
 * Session Bean implementation class StatsService
 */
@Stateless
@LocalBean
public class LastCallMsisdnService {
	private Logger logger = Logger.getLogger(getClass().getName());
	@EJB
	private  LastCallMsisdnBeanLocal  lastCallMsisdnBean;

	/**
	 * Default constructor.
	 */
	public LastCallMsisdnService() {
		// TODO Auto-generated constructor stub
	}


	public LastCallMsisdn persistLastCallMsisdn(LastCallMsisdn  lastCallMsisdn) {
		// TODO Auto-generated method stub
		return lastCallMsisdnBean.persistLastCallMsisdn( lastCallMsisdn);
	}

	public LastCallMsisdn mergeLastCallMsisdn(LastCallMsisdn  lastCallMsisdn) {
		// TODO Auto-generated method stub
		return lastCallMsisdnBean.mergeLastCallMsisdn( lastCallMsisdn);
	}




	public LastCallMsisdn getStatisticByIdMsisdn(String msisdn) {
		// TODO Auto-generated method stub
		Timestamp intDate = new Timestamp(System.currentTimeMillis());
		LastCallMsisdn rs =  lastCallMsisdnBean.getLastCallMsisdnByIdMsisdn(msisdn);
		Timestamp endDate = new Timestamp(System.currentTimeMillis());
		Long elap = endDate.getTime() - intDate.getTime();

		logger.debug("Elaptime "+ this.getClass().getName() +"getStatisticByIdMsisdn -------->" + elap);
		return rs;
		
		
	}






}
