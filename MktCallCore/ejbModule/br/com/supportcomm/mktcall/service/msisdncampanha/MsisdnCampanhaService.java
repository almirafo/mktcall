package br.com.supportcomm.mktcall.service.msisdncampanha;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.MsisdnCampanha;
import br.com.supportcomm.mktcall.impl.msisdncampanha.MsisdnCampanhaBeanLocal;

/**
 * Session Bean implementation class MsisdnCampanhaService
 */
@Stateless
@LocalBean
public class MsisdnCampanhaService  {

	@EJB private MsisdnCampanhaBeanLocal msisdnCampanhaBean;
	
    /**
     * Default constructor. 
     */
    public MsisdnCampanhaService() {
        // TODO Auto-generated constructor stub
    }

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public MsisdnCampanha persistMsisdnCampanha(MsisdnCampanha msisdnCampanha) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.persistMsisdnCampanha(msisdnCampanha);
	}

	
	public MsisdnCampanha mergeMsisdnCampanha(MsisdnCampanha msisdnCampanha) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.mergeMsisdnCampanha(msisdnCampanha);
	}

	
	public void removeMsisdnCampanha(MsisdnCampanha msisdnCampanha) {
		// TODO Auto-generated method stub
		msisdnCampanhaBean.removeMsisdnCampanha(msisdnCampanha);
		
	}


	public List<MsisdnCampanha> getMsisdnCampanhaIdCampanha(Long idCampanha) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.getMsisdnCampanhaIdCampanha(idCampanha);
	}

	public List<MsisdnCampanha> getMsisdnCampanhaMsisdn(String msisdn) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.getMsisdnCampanhaMsisdn(msisdn);
	}

	public List<MsisdnCampanha> getMsisdnCampanhaAdvertisement(String msisdn, String callid) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.getMsisdnCampanhaAdvertisement(msisdn, callid);
	}

	public List<MsisdnCampanha> getMsisdnCampanhaId(String msisdnId) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.getMsisdnCampanhaId(msisdnId);
	}

	public List<MsisdnCampanha> getMsisdnCampanhaIdCampanhaMsisdn(Long idCampanha, String msisdn) {
		// TODO Auto-generated method stub
		return msisdnCampanhaBean.getMsisdnCampanhaIdCampanhaMsisdn(idCampanha, msisdn);
	}


	

	
}
