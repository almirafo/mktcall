package com.suppcomm.schedule;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import br.com.supportcomm.dialout.util.Dialout;
import br.com.supportcomm.mktcall.constants.StatusOperation;
import br.com.supportcomm.mktcall.constants.StatusProcessing;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Dialing;
import br.com.supportcomm.mktcall.entity.Insertion;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.dialing.ConfigDelegate;
import br.com.supportcomm.mktcall.service.dialing.DialingDelegate;
import br.com.supportcomm.mktcall.service.insertion.InsertionService;
import br.com.supportcomm.mktcall.tools.SCTools;
import br.com.supportcomm.mktcall.util.JSFUtil;
import br.com.supportcomm.mktcall.util.SendSMS;



@Stateless
public class TimerDialOut {
	Logger logger = Logger.getLogger(getClass());
	private static volatile boolean emExec = false;
   
    @EJB
    private DialingDelegate dialingDelegate;
    

    @EJB
    private ConfigDelegate configDelegate;
    
    @EJB
    private CampanhaService campanhaService;
    
    @EJB 
    private InsertionService insertionService;
    
    
    @Context ServletContext servletContext;
    
	private static synchronized boolean mudaExec(boolean val) {
		if (val == emExec) {
			return false;
		}
		else {
			emExec = val;
			return true;
		}
	}
	
	
	 @Schedule( second="0" ,minute="0/1",hour="*", persistent=false)
	 //public static void main(String agrs[])
	 public void doWork() 
	 {
		 
			if (!TimerDialOut.mudaExec(true)) {
				
				logger.info("Timer DialOut in process!!");
				return;
			}

			try {
				JSFUtil util = new JSFUtil();
		    	long horaAtual = util.getRigthNowInMinutes();
		    	
		    	
		    	String timeRangeStartHour = configDelegate.getValueByIndentify("startProcessHour");
		    	String timeRangeEndHour=configDelegate.getValueByIndentify("endProcessHour");
		    	
			    long startProcessHour = util.getHoursInMinutes(configDelegate.getValueByIndentify("startProcessHour"));
			    long endProcessHour= util.getHoursInMinutes(configDelegate.getValueByIndentify("endProcessHour"));

		    	if(!( horaAtual >= startProcessHour && horaAtual<=endProcessHour)){
					logger.info("Hour out off bound!!");
					return;
		    		
		    	}
		    	
				logger.info("processing....");
		    	List<Channel> channels = new ArrayList<>();
		    	String context           = configDelegate.getValueByIndentify("context");
		    	String exter             = configDelegate.getValueByIndentify("extension");
		    	
		    	String ipConnection      = configDelegate.getValueByIndentify("serverIP");
		    	String loginAsterisk     = configDelegate.getValueByIndentify("login");
		    	String passAsterisk      = configDelegate.getValueByIndentify("senha");
		    	String channelPrefix     = configDelegate.getValueByIndentify("channelPrefix");
		    	String channelSuffix     = configDelegate.getValueByIndentify("channelSuffix");
		    	String timeout           = configDelegate.getValueByIndentify("timeout");
		    	String startLuaCondition = configDelegate.getValueByIndentify("startLuaCondition");
		    	String dedicatoria       = configDelegate.getValueByIndentify("dedicatoria");
		    	Integer maxAtemps        = Integer.parseInt(configDelegate.getValueByIndentify("maxAtemps"));
		    	Integer priority         = Integer.parseInt( configDelegate.getValueByIndentify("priority"));
		    	Integer maxResults       = Integer.parseInt( configDelegate.getValueByIndentify("maxResults"));
		    	Integer timoutDelayMinutes= Integer.parseInt( configDelegate.getValueByIndentify("timoutDelayMinutes"));
		    	String laNumber=configDelegate.getValueByIndentify("laNumber");
		    	
		    	
		    	String proxysms=configDelegate.getValueByIndentify("proxysms")==null?"":configDelegate.getValueByIndentify("proxysms");
		    	String portsms =configDelegate.getValueByIndentify("portsms")==null?"":configDelegate.getValueByIndentify("portsms");
		    	
		    	String remitterMsg = URLEncoder.encode(configDelegate.getValueByIndentify("remitterMsg"), "UTF8");
		    	String addresseeMsg= URLEncoder.encode(configDelegate.getValueByIndentify("addresseeMsg"), "UTF8");
		    	String noticeCallerSMS= URLEncoder.encode(configDelegate.getValueByIndentify("noticeCallerSMS"), "UTF8");
		    	
		    	// Listar todos que estao pendentes a Dialout
				List<Dialing> dialings = dialingDelegate.getListToProcess(maxResults);
				
		    	for (Dialing dialing:dialings){
		    		
		    		// tocar preciso verifiar se tem minutos promocionais para esse numero
		    		//if( campanhaService.verificaSeTemCampanha(dialing.getIdList().getIdList())){
		    			
		    			// verifica se tem schedule para essa campanha.
		    			Campanha campanha =  campanhaService.getCampanhaId(dialing.getIdCampanha().getIdCampanha()).get(0);
		    			Campanha cf=  getCampanhaFiltro(campanha, new Timestamp(System.currentTimeMillis()));
		    			if (cf!=null){
				    		Channel channel = new Channel();
				    		channel.setIdDialing(dialing.getId());
				    		channel.setMsisdn(dialing.getMsisdnDialing());
				    		channel.setIdCampanha( String.valueOf(dialing.getIdCampanha().getIdCampanha()));
				    		channel.setMsisdnOriginator(laNumber);
				    		channels.add(channel);
				    		
		    			}
		    		//}
		    	}
		    	
		    	List<Dialout> dialouts = new ArrayList<Dialout>();
		    	
		    	for(Channel chanel:channels){
		    		List<String> variables = new ArrayList<>();
		    		variables.add( startLuaCondition+"="+exter );
		    		
		    		variables.add("destinationNumber=".concat(chanel.getMsisdn()));			
		    		variables.add("idCampanha=".concat(chanel.getIdCampanha()));
		    		
		    		
		    		Dialout dialout = new Dialout();
		    		
		    	  	dialout.setCallerId(chanel.getMsisdnOriginator());
		    	  	if(!chanel.getMsisdn().contains("192")){
		    	  		dialout.setChannel(channelPrefix.concat(chanel.getMsisdn().concat(channelSuffix) ));
		    	  		dialout.setMsisdnOriginator(chanel.getMsisdn());
		    	  	}else{
		    	  		dialout.setChannel( channelPrefix.replace("0", "").concat(chanel.getMsisdn()));
		    	  	}
		    	  	
		        	dialout.setContext(context);
		        	dialout.setExten(exter);

		        	dialout.setIpConnection(ipConnection);

		        	dialout.setLoginAsterisk(loginAsterisk);
		        	dialout.setPassAsterisk(passAsterisk);
		        	dialout.setPriority(priority);
		        	dialout.setTimeout(Long.parseLong( timeout));
		        	dialout.setVariables(variables);
		        	
		        	dialout.setObject(chanel);
		        	Thread threadDoPdf = new Thread(dialout);
		        	threadDoPdf.start();
		        	dialouts.add(dialout);
		        	
		    	}
		    	
		    	
		    	boolean acabou=false;
		    	
		        while(!acabou){
		        	Iterator<Dialout> dial = dialouts.iterator();
		        	while(dial.hasNext()){
		        		Dialout dialout = dial.next();
		        		if(dialout.getStatus()!=null){
		        			
		        			logger.info("---------------------------------------------------");
		        			logger.info("Chamada-----"+ dialout.getChannel() );
		        			logger.info("Resposta da Ligação -------------------"+dialout.getResponseCode());
		        			logger.info("------------------- -------------------"+dialout.getResponseMessage());
		        			logger.info("Status---------------------------------" + dialout.getStatus());

		        			
		        			
		        			if(dialout.getResponseCode().equalsIgnoreCase(String.valueOf( StatusProcessing.SUCCESS.value()))){
		        				logger.info("informar a base que a ligação foi entregue ");
		        				SendSMS sendSMS = new SendSMS();
		        				logger.info("SMS Originator:" +dialout.getMsisdnOriginator());
		        				sendSMS.execute(dialout.getMsisdnOriginator(), laNumber, remitterMsg,proxysms,portsms);
		        				
		        				Channel channel = (Channel) dialout.getObject();
		        				
		        				Dialing dialing = dialingDelegate.load(channel.getIdDialing());
		        				dialing.setStatus(StatusOperation.OK.value());
		        				dialing.setResponseCode(dialout.getResponseCode());
		        				dialing.setResponseMessage(dialout.getResponseMessage());
		        				increaseAttemps(dialing); 
		                    	dialing.setDatetimeLastOperation(new Timestamp(System.currentTimeMillis()));
		                    	dialing = dialingDelegate.update(dialing);	
		        				
		        			}
		        			
		        			else{
		        				
		        				if (dialout.getResponseCode().equals(StatusProcessing.NETWORK_PROBLEMS_ERROR.value())){
		        					logger.info(StatusProcessing.NETWORK_PROBLEMS_ERROR.description());
		        				} else if (dialout.getResponseCode().equals(StatusProcessing.AUTHENTICATION_FAILED_ERROR.value())){
		        					logger.info(StatusProcessing.AUTHENTICATION_FAILED_ERROR.description());
		        					
		        				}else{
			        				Channel channel = (Channel) dialout.getObject();
			        				
			        				Dialing dialing = dialingDelegate.load(channel.getIdDialing());
			        				increaseAttemps(dialing);
			                        dialing.setResponseMessage(dialout.getResponseMessage());
			                        dialing.setResponseCode(dialout.getResponseCode());
			                        
			                        dialing.setDatetimeScheduled(SCTools.calculateDelayedTimestamp(timoutDelayMinutes, timeRangeStartHour, timeRangeEndHour));
			                        
			                        //if (dialing.getAttempts()>maxAtemps){
			                        	//dialing.setAction("SMS");
			                        	//dialing.setStatus(StatusOperation.OK.value());
			                        	//SendSMS sendSMS = new SendSMS();
				        				
				        				//sendSMS.execute( ((Channel) dialout.getObject()).getMsisdn() , addresseeMsg,proxysms,portsms);
				        				
				        				//sendSMS.execute(dialout.getMsisdnOriginator(), noticeCallerSMS,proxysms,portsms);
				        				
				        				
			                        	//logger.info("Enviar SMS Destino e Remetente");
			                        //}
			                        dialingDelegate.update(dialing);
		        				}
		        			}
		        			dial.remove();
		        		}
		        	}
		        	if (dialouts.size()==0) acabou=true;
		        	Thread.sleep(100);
		        }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				TimerDialOut.mudaExec(false);
			}
	 }


	private boolean temMinutosPromocionais(String msisdnDialing) {
		
		return false;
	}


	private void increaseAttemps(Dialing dialing) {
		if (!SCTools.isNullOrBlank(dialing.getAttempts()))
		{
		    dialing.setAttempts(dialing.getAttempts().intValue() + 1);
		} else {
			dialing.setAttempts(1);
		}
	}



	 private class Channel{
		 private String msisdn;
		 private String msisdnOriginator;
		 private Long idDialing;
		 private String idCampanha;
		public String getMsisdn() {
			return msisdn;
		}
		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}
		public Long getIdDialing() {
			return idDialing;
		}
		public void setIdDialing(Long idDialing) {
			this.idDialing = idDialing;
		}
		public String getMsisdnOriginator() {
			return msisdnOriginator;
		}
		public void setMsisdnOriginator(String msisdnOriginator) {
			this.msisdnOriginator = msisdnOriginator;
		}
		public String getIdCampanha() {
			return idCampanha;
		}
		public void setIdCampanha(String idCampanha) {
			this.idCampanha = idCampanha;
		}

			
	 }
	

	 
		private Campanha getCampanhaFiltro(Campanha campanha, Timestamp dataHoje) throws ParseException {
			List<Insertion> insertionList;
			long campanhaTocada=0; 
			
			List<Campanha> campanhasComSchedules = new ArrayList<>();
			List<Campanha> campanhasSemSchedules = new ArrayList<>();
			
				//somento os ativos
				if (campanha.getStatus() == 1) {
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
					String startTime =  campanha.getStartDatatime().toString().substring(0, 11) + "00:00:00";
					String endTime   =  campanha.getEndDatetime().toString().substring(0, 11) + "23:59:59";
					Timestamp stamp = new Timestamp(System.currentTimeMillis());
					Date hoje = new Date(dataHoje.getTime());
					
					Date startDate = formato.parse(startTime);
					Date endDate   = formato.parse(endTime);

					
					if(endDate.compareTo(hoje)>=0  &&	startDate.compareTo(hoje)<=0)
					 {
						
							//if (campanha.getInsertionReach() <= campanha.getInsertionContracted()) {
								insertionList = insertionService.getInsertionCampanha(campanha.getIdCampanha());
								campanha.setInsertions(insertionList);
								if (!campanha.getInsertions().isEmpty()) {
									if(campanhaTocada!=campanha.getIdCampanha()){
										if (verifyDay(campanha)) {
											campanhasComSchedules.add(campanha);
										}
									}
								}else{
									campanhasSemSchedules.add(campanha);
								}
							//}
						
					}
				}


			if (!campanhasComSchedules.isEmpty()){
				campanha=campanhasComSchedules.get(0);
			}else{
				if (!campanhasSemSchedules.isEmpty()){
					campanha=campanhasSemSchedules.get(0);
				}
			}
			
			return campanha;
			
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
				}

			}
			return dayOK;

		}
		
		
	 
	 
}

