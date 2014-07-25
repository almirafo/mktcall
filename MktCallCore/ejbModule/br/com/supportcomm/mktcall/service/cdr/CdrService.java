package br.com.supportcomm.mktcall.service.cdr;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Cdr;
import br.com.supportcomm.mktcall.impl.cdr.CdrBeanLocal;

/**
 * Session Bean implementation class CdrService
 */
@Stateless
@LocalBean
public class CdrService  {

	@EJB private CdrBeanLocal cdrBean;
	
    /**
     * Default constructor. 
     */
    public CdrService() {
        // TODO Auto-generated constructor stub
    }

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return cdrBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public Cdr persistCdr(Cdr cdr) {
		// TODO Auto-generated method stub
		return cdrBean.persistCdr(cdr);
	}

	
	public Cdr mergeCdr(Cdr cdr) {
		// TODO Auto-generated method stub
		return cdrBean.mergeCdr(cdr);
	}

	
	public void removeCdr(Cdr cdr) {
		// TODO Auto-generated method stub
		cdrBean.removeCdr(cdr);
		
	}

	
	public List<Cdr> getCdrUsuario(Long idUserAccess) {
		// TODO Auto-generated method stub
		return cdrBean.getCdrUsuario(idUserAccess);
	}

	
	public List<Cdr> getCdrAgencia(Long idAgencia) {
		// TODO Auto-generated method stub
		return cdrBean.getCdrAgencia(idAgencia);
	}

	
	public List<Cdr> getCdrAgenciaUsuario(Long idUserAccess) {
		// TODO Auto-generated method stub
		return cdrBean.getCdrAgenciaUsuario(idUserAccess);
	}

	
	public List<Cdr> getCdrByMsisdn(String msisdnOriginator) {
		// TODO Auto-generated method stub
		return cdrBean.getCdrByMsisdn(msisdnOriginator);
	}

	
	public List<Cdr> getCdrTempoMedioChamada(Timestamp dataInicio, Timestamp dataFim, String ddd) {
		// TODO Auto-generated method stub
		return cdrBean.getCdrTempoMedioChamada(dataInicio, dataFim, ddd);
	}

	
	public List<Cdr> getCdrAll() {
		// TODO Auto-generated method stub
		return cdrBean.getCdrAll();
	}

	public List<Cdr> getCdrTodayOnce(Integer duration, String msisdnOriginator, int dialstatus) {
		// TODO Auto-generated method stub
		return cdrBean.getCdrTodayOnce(duration, msisdnOriginator, dialstatus);
	}

	
	public List<Cdr> getCdrTodayOnceDay(Integer duration, String msisdnOriginator, int dialstatus,Timestamp dataAtual) {
		// TODO Auto-generated method stub
		return cdrBean.getCdrTodayOnceDay(duration, msisdnOriginator, dialstatus,dataAtual);
	}


	public Cdr getCdrByCallId(String callid) {
		Cdr cdr =cdrBean.getCdrByCallId(callid); 
		return cdr;
	}
	
}
