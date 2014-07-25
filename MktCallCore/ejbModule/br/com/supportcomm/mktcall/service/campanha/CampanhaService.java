package br.com.supportcomm.mktcall.service.campanha;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Insertion;
import br.com.supportcomm.mktcall.enums.ProcessStatus;
import br.com.supportcomm.mktcall.impl.campanha.CampanhaBeanLocal;
import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;
import br.com.supportcomm.mktcall.vo.ListaDuracaoVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeConsolidadaVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeVO;
import br.com.supportcomm.mktcall.vo.ListaInteracaoVO;

/**
 * Session Bean implementation class CampanhaService
 */
@Stateless
@LocalBean
public class CampanhaService {

	@EJB
	private CampanhaBeanLocal campanhaBean;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	/**
	 * Default constructor.
	 */
	public CampanhaService() {
		// TODO Auto-generated constructor stub
	}

	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return campanhaBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	public Campanha persistCampanha(Campanha campanha) {
		// TODO Auto-generated method stub
		return campanhaBean.persistCampanha(campanha);
	}

	public Campanha mergeCampanha(Campanha campanha) {
		// TODO Auto-generated method stub
		return campanhaBean.mergeCampanha(campanha);
	}
	 public Campanha incremetReachCampanha(Campanha campanha) {
		 return campanhaBean.incremetReachCampanha(campanha);
	 }
	 public void desativaCampanha(Campanha campanha){
		 campanhaBean.desativaCampannha( campanha);
	 }
	public void removeCampanha(Campanha campanha) {
		// TODO Auto-generated method stub
		campanhaBean.removeCampanha(campanha);
	}

	public List<Campanha> getCampanhaAll() {
//		Logger logger = Logger.getLogger(getClass().getName());
		
		Timestamp intDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
	
		List<Campanha> rs = campanhaBean.getCampanhaAll();
		
		Timestamp endDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();		

		Long elap = endDate.getTime() - intDate.getTime();
		logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
		return rs;
	}

	
	public List<Campanha> getCampanhaAllNative() {
		// TODO Auto-generated method stub
//		 Logger logger = Logger.getLogger(getClass().getName());
		
		Timestamp intDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
	
		
		List<Campanha> rs = campanhaBean.getCampanhaAll();
		
		Timestamp endDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
		

		Long elap = endDate.getTime() - intDate.getTime();
		logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
		return rs;
	}
	
	public List<Campanha> getCampanhaAllNativeNotRegional() {
		// TODO Auto-generated method stub
//        Logger logger = Logger.getLogger(getClass().getName());
		
		Timestamp intDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
	
		
		List<Campanha> rs = campanhaBean.getCampanhaAllNotRegional();
		
		Timestamp endDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
		

		Long elap = endDate.getTime() - intDate.getTime();
		logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
		return rs;
	}
	
	public List<Campanha> getCampanhaByMsisdn(String msisdn) {
//		Logger logger = Logger.getLogger(getClass().getName());
		Timestamp intDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
		List<Campanha> rs = campanhaBean.getCampanhaByMsisdn(msisdn);		
		Timestamp endDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
		Long elap = endDate.getTime() - intDate.getTime();
		logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
		return rs;
	}	
	
	public List<Campanha> getCampanhaId(Long idCampanha) {
		// TODO Auto-generated method stub
//  	    Logger logger = Logger.getLogger(getClass().getName());
    	

    	Timestamp intDate = new Timestamp(System.currentTimeMillis());
    	List<Campanha> rs = campanhaBean.getCampanhaId(idCampanha);
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		Long elap = endTime.getTime() - intDate.getTime();
		logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
		
		
		
		return rs;
	}

	public List<Campanha> getCampanhaByUsuario(Long idUserAccess) {
		// TODO Auto-generated method stub
		return campanhaBean.getCampanhaByUsuario(idUserAccess);
	}

	public List<Campanha> getCampanhaSpot(Long idSpot) {
		// TODO Auto-generated method stub
		return campanhaBean.getCampanhaSpot(idSpot);
	}

	public List<ListaInteracaoVO> getListaInteracao(Long idCampanha, Timestamp dataInicial, Timestamp dataFinal) {
		return campanhaBean.getListaInteracao(idCampanha, dataInicial, dataFinal);
	}

	public List<ListaDuracaoVO> getListaDuracao(Long idCampanha, Timestamp dataInicial, Timestamp dataFinal) {
		return campanhaBean.getListaDuracao(idCampanha, dataInicial, dataFinal);
	}

	public List<ListaEfetividadeVO> getListaEfetividade(Long idCampanha, Timestamp dataInicial, Timestamp dataFinal) {
		return campanhaBean.getListaEfetividade(idCampanha, dataInicial, dataFinal);
	}
	
	 public List<ListaEfetividadeConsolidadaVO> getListaEfetividadeConsolidada(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal){
		 return campanhaBean.getListaEfetividadeConsolidada(idCampanha, dataInicial, dataFinal);
	 }

	public List<ListaCampanhaDialOutVO> getCampanhaDialOutAll() {
		return campanhaBean.getCampanhaDialOutAll();
	}
	
	public List<Campanha> getCampanhabyProcessStatus(String processStatus){
		return campanhaBean.getCampanhabyProcessStatus( processStatus);
		
	}

	public void setCampanhaProcessStatus(Campanha campanha,String processStatus) {
		campanhaBean.setCampanhaProcessStatus(campanha, processStatus);
		
	}

	public  boolean verificaSeTemCampanha(Long idList) {
		return verifyDay( campanhaBean.verificaSeTemCampanha(idList));
		
	}
	
	
	
	private boolean verifyDay(Campanha campanha) {

		Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
		
		boolean dayOK = false;
		Calendar now = Calendar.getInstance();
		now.setTime(dataDeHoje);
		int diaDaSemana =now.get(Calendar.DAY_OF_WEEK);
		if (campanha.getInsertions().isEmpty()){
			dayOK = true;
		}
		
		for (Insertion insertion : campanha.getInsertions()) {

			if (insertion.getDayOfWeek() == diaDaSemana ) {
				
				insertion.getStartTime().toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startTime;
				String endTime;
				String horaDeHoje; 
				startTime =  dataDeHoje.toString().substring(0, 11) + insertion.getStartTime().toString().substring(11,16)+":00";
				endTime   =  dataDeHoje.toString().substring(0, 11) + insertion.getEndTime().toString().substring(11,16)+":00";
				horaDeHoje = dataDeHoje.toString();
				;
				try {
					if (sdf.parse(startTime).equals(sdf.parse(horaDeHoje) ) ||sdf.parse(startTime).before(sdf.parse(horaDeHoje))) {
						if (sdf.parse(endTime).equals(sdf.parse(horaDeHoje) ) ||sdf.parse(endTime).after(sdf.parse(horaDeHoje))) {
							dayOK = true;
						}
					
					}
				} catch (ParseException e) {
					logger.warn("Método verifyDay - Parse Exception - message: " + e.getMessage());
					e.printStackTrace();
				}
				/*if (insertion.getStartTime().equals(dataDeHoje) || insertion.getStartTime().after(dataDeHoje)) {
					if (insertion.getEndTime().equals(dataDeHoje) || insertion.getEndTime().before(dataDeHoje)) {
						dayOK = true;
					}
				}*/
			}

		}
		return dayOK;

	}
	
	
	
}
