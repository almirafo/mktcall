package br.com.supportcomm.mktcall.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Cdr;
import br.com.supportcomm.mktcall.entity.ControlFlow;
import br.com.supportcomm.mktcall.entity.Insertion;
import br.com.supportcomm.mktcall.entity.LastCallMsisdn;
import br.com.supportcomm.mktcall.entity.MsisdnCampanha;
import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.entity.SpotAudioFile;
import br.com.supportcomm.mktcall.xml.status.advertisement.ResponseAdvertisement;
import br.com.supportcomm.mktcall.xml.status.advertisement.ResponseAdvertisement.Body.Audios;
import br.com.supportcomm.mktcall.xml.status.advertisement.ResponseAdvertisement.Body.Forward;
import br.com.supportcomm.mktcall.constants.StatusOperation;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.config.ConfigService;
import br.com.supportcomm.mktcall.service.controlflow.ControlFlowService;
import br.com.supportcomm.mktcall.service.foward.FowardService;
import br.com.supportcomm.mktcall.service.insertion.InsertionService;
import br.com.supportcomm.mktcall.service.lastcallmsisdn.LastCallMsisdnService;
import br.com.supportcomm.mktcall.service.listageral.ListaGeralService;
import br.com.supportcomm.mktcall.service.msisdncampanha.MsisdnCampanhaService;
import br.com.supportcomm.mktcall.service.region.RegionService;
import br.com.supportcomm.mktcall.service.spot.SpotService;

/**
 * Servlet implementation class GetAdvertisementServlet
 */
@WebServlet(name = "GetAdvertisement", description = "Get Advertisement", urlPatterns = { "/GetAdvertisement" })
public class GetAdvertisementServlet extends HttpServlet {
	private static final int DESATIVADA = 0;

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(getClass().getName());

	@EJB
	private CampanhaService campanhaService;

	@EJB
	private MsisdnCampanhaService msisdnService;
	
	@EJB
	private LastCallMsisdnService lastCallMsisdnService;

	@EJB
	private RegionService regionService;

	@EJB
	private SpotService spotService;

	@EJB
	private InsertionService insertionService;

	@EJB
	private CdrService cdrService;

	@EJB
	private ListaGeralService listaGeralService;

	@EJB private ControlFlowService controlFlowService;
	
	@EJB private ConfigService configService;
	
	@EJB private FowardService fowardService;
	
	public GetAdvertisementServlet() {
		super();
	}
	private static Object objeto= new Object();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Campanha campanhaFinal = new Campanha();
		
		logger.info("GetAdvertisement processing...");
		
		boolean naoCampanhas=false;
		try {
			Timestamp intDate = new Timestamp(System.currentTimeMillis());
			
			String msisdn = request.getParameter("msisdn");
			String callid = request.getParameter("callid");
			String cfuCode = request.getParameter("cfucode");
			String whiteList = request.getParameter("whitelist");
			String areaCode =  request.getParameter("region");

			List<MsisdnCampanha> msisdnList = new ArrayList<MsisdnCampanha>();
			List<Campanha> campanhaList = new ArrayList<Campanha>();
			List<Campanha> campanhaFiltradas = new ArrayList<Campanha>();
			List<Campanha> campanhaP0 = new ArrayList<Campanha>();
			List<Campanha> campanhaP1 = new ArrayList<Campanha>();
			List<Campanha> campanhaP2 = new ArrayList<Campanha>();
			List<Campanha> campanhaP3 = new ArrayList<Campanha>();
			List<Campanha> campanhaP4 = new ArrayList<Campanha>();
			List<Campanha> campanhaP5 = new ArrayList<Campanha>();

			Timestamp time = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 12:00:00");
			String formatedDate = format.format(time.getTime());
			Timestamp dataHoje = Timestamp.valueOf(formatedDate);

			ResponseAdvertisement RA = new ResponseAdvertisement();
			RA.setBody(new ResponseAdvertisement.Body());
			ResponseAdvertisement.Body bodyR = new ResponseAdvertisement.Body();

			if (msisdn == null) {

				bodyR.setDescription(StatusOperation.MISSING_MSISDN.description());
				RA.setStatus(String.valueOf(StatusOperation.MISSING_MSISDN.value()));

			}
			else if (callid == null) {

				bodyR.setDescription(StatusOperation.MISSING_CALLID.description());
				RA.setStatus(String.valueOf(StatusOperation.MISSING_CALLID.value()));

			}
			else if (areaCode == null) {

				bodyR.setDescription(StatusOperation.MISSING_AREACODE.description());
				RA.setStatus(String.valueOf(StatusOperation.MISSING_AREACODE.value()));

			}

			
			else if (cfuCode == null) {

				bodyR.setDescription(StatusOperation.MISSING_CFUCODE.description());
				RA.setStatus(String.valueOf(StatusOperation.MISSING_CFUCODE.value()));

			}
			else {

				try {
					
					msisdnList = msisdnService.getMsisdnCampanhaMsisdn(msisdn);
					
					/*
					 * Logicas e regras
					 */

					if (!isNullOrBlank(whiteList)) {

						if (!Boolean.parseBoolean(whiteList)) {

							List<Cdr> cdr =getCdrList(msisdn);

							if (!cdr.isEmpty()) {

								bodyR.setDescription(StatusOperation.USER_OFFLIST_LIMIT_REACH.description());
								RA.setStatus(String.valueOf(StatusOperation.USER_OFFLIST_LIMIT_REACH.value()));
								RA.setBody(bodyR);
								JAXBContext jaxbContext = JAXBContext.newInstance(ResponseAdvertisement.class);
								Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
								StringWriter stringWriter = new StringWriter();
								jaxbMarshaller.marshal(RA, stringWriter);
								response.setContentType("text");
								response.getWriter().write(stringWriter.toString());
								return;

							}

						}
					}
					else {

						List<Cdr> cdr =getCdrList(msisdn);

						if (!cdr.isEmpty()) {

							bodyR.setDescription(StatusOperation.USER_OFFLIST_LIMIT_REACH.description());
							RA.setStatus(String.valueOf(StatusOperation.USER_OFFLIST_LIMIT_REACH.value()));
							RA.setBody(bodyR);
							JAXBContext jaxbContext = JAXBContext.newInstance(ResponseAdvertisement.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							StringWriter stringWriter = new StringWriter();
							jaxbMarshaller.marshal(RA, stringWriter);
							response.setContentType("text");
							response.getWriter().write(stringWriter.toString());
							return;

						}

					}
					
					// Gets a list of ACTIVE Campaigns for that particular msisdn (if any).
					List<Campanha> listaCampanhasSegmentadas =campanhaService.getCampanhaByMsisdn(msisdn);
					if (!listaCampanhasSegmentadas.isEmpty()) {
						for (Campanha campanhaSegmentada : listaCampanhasSegmentadas) {
							if(campanhaSegmentada.getEndDatetime().compareTo(new Timestamp(System.currentTimeMillis()))>=0) {							
								if(checkCampaignSegmentationList(campanhaSegmentada, dataHoje)) {
									campanhaList.add(campanhaSegmentada);
								}
							}	
						}
					}					

					
					// Does Regionalization only if he could not find any Campanha Segmentada.
//					if (campanhaList.isEmpty()) {
//						// Tabela de Região vazia 						
//						campanhaList = searchRegion(areaCode,dataHoje);
//					}
					
					if (campanhaList.isEmpty()) {

						List<Campanha> campanhaListRecovery = new ArrayList<Campanha>();
						
						campanhaListRecovery = campanhaService.getCampanhaAllNativeNotRegional();

						Collections.shuffle(campanhaListRecovery);

						campanhaList.addAll(campanhaListRecovery);
						// So permite uma chamada por dia.
						if (campanhaList.isEmpty()) {
							naoCampanhas=true;
							bodyR.setDescription(StatusOperation.NO_CAMPAIGN_AVAILABLE.description());
							RA.setStatus(String.valueOf(StatusOperation.NO_CAMPAIGN_AVAILABLE.value()));
							RA.setBody(bodyR);
							JAXBContext jaxbContext = JAXBContext.newInstance(ResponseAdvertisement.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							StringWriter stringWriter = new StringWriter();
							jaxbMarshaller.marshal(RA, stringWriter);
							response.setContentType("text");
							response.getWriter().write(stringWriter.toString());
							return;
						}

					}

					// Filtrar campanhas que estejam dentro da data ativa e com status 1
					//List<Insertion> insertionList = new ArrayList<Insertion>();
					
					
					campanhaFiltradas= getCampanhaFiltro(campanhaList, campanhaFiltradas, dataHoje,msisdnList);
					
					
					for (Campanha campanhaPrioridade : campanhaFiltradas) {
						
						
						// Antes de ver a prioridade verificar se ela esta dentro do horário para tocar
						
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
						
						Timestamp stamp = new Timestamp(System.currentTimeMillis());
						Date hoje = new Date(stamp.getTime());
						
						
						
						String startTime =  campanhaPrioridade.getStartDatatime().toString().substring(0, 11) + "00:00:00";
						String endTime   =  campanhaPrioridade.getEndDatetime().toString().substring(0, 11) + "23:59:59";

						
						Date startDate = formato.parse(startTime);
						Date endDate   = formato.parse(endTime);
						
						
						
						if(endDate.compareTo(hoje)>=0  &&	startDate.compareTo(hoje)<=0)	{
							//if(verifyDay(campanhaPrioridade)){
								switch (campanhaPrioridade.getPriority()) {
		
								case 0:
									campanhaP0.add(campanhaPrioridade);
									break;
								case 1:
									campanhaP1.add(campanhaPrioridade);
									break;
								case 2:
									campanhaP2.add(campanhaPrioridade);
									break;
								case 3:
									campanhaP3.add(campanhaPrioridade);
									break;
								case 4:
									campanhaP4.add(campanhaPrioridade);
									break;
								case 5:
									campanhaP5.add(campanhaPrioridade);
									break;
								default:
									break;
								}
							
							//}
							
						}
						
					}

					campanhaFiltradas = new ArrayList<Campanha>();
					if (!campanhaP0.isEmpty()) {
						campanhaFiltradas = new ArrayList<Campanha>();
						campanhaFiltradas.addAll(campanhaP0);
					}
					else if (!campanhaP1.isEmpty()) {
						campanhaFiltradas = new ArrayList<Campanha>();
						campanhaFiltradas.addAll(campanhaP1);
					}
					else if (!campanhaP2.isEmpty()) {
						campanhaFiltradas = new ArrayList<Campanha>();
						campanhaFiltradas.addAll(campanhaP2);
					}
					else if (!campanhaP3.isEmpty()) {
						campanhaFiltradas = new ArrayList<Campanha>();
						campanhaFiltradas.addAll(campanhaP3);
					}
					else if (!campanhaP4.isEmpty()) {
						campanhaFiltradas = new ArrayList<Campanha>();
						campanhaFiltradas.addAll(campanhaP4);
					}
					else if (!campanhaP5.isEmpty()) {
						campanhaFiltradas = new ArrayList<Campanha>();
						campanhaFiltradas.addAll(campanhaP5);
					}

					Collections.shuffle(campanhaFiltradas);
					campanhaFinal =  new Campanha();
					if (campanhaFiltradas.isEmpty()) {
						naoCampanhas = true;
						bodyR.setDescription(StatusOperation.NO_CAMPAIGN_AVAILABLE.description());
						RA.setStatus(String.valueOf(StatusOperation.NO_CAMPAIGN_AVAILABLE.value()));

					}
					else {
						
						campanhaFinal = campanhaFiltradas.get(0);

						Spot spot = spotService.getSpotById(campanhaFinal.getSpot().getIdSpot()).get(0);
						//Spot spot =  new Spot();
						RA.setStatus(String.valueOf(StatusOperation.OK.value()));
						// AA - I commented it out because the two set of statements are the same.
						// verificar se o usuario existe
						// Se existe ou não o msisdn na campanha , 1 sim / 2 não
//						if (msisdnList.isEmpty()) {
//							bodyR.setStatusMSISDN(String.valueOf(StatusOperation.OK.value()));
//							bodyR.setDescription(StatusOperation.OK.description());
//						}
//						else {
							bodyR.setStatusMSISDN(String.valueOf(StatusOperation.OK.value()));
							bodyR.setDescription(StatusOperation.OK.description());
//						}

						// Id da campanha
						bodyR.setCampaignID(Integer.parseInt(campanhaFinal.getIdCampanha().toString()));
						// tipo do spot
						bodyR.setAdvertisementType(spotService.getSpotTipoId(Long.parseLong(spot.getSpotType().toString())).get(0).getCodeSpotTipo());
						// messageSMS do spot
						bodyR.setMessage(spot.getMessageSms());
						// server sms do spot
						
						if(spot.getInternal()!=null &&  spot.getInternal()  ){
							bodyR.setServerURL("INTERNAL");
						}
						else{
							bodyR.setServerURL(spot.getServerSms());
						}
						// metodo do spot
						if(!isNullOrBlank(spot.getProtocol())){
							if(spot.getProtocol().equals("1")) bodyR.setProtocol("GET");
							if(spot.getProtocol().equals("2")) bodyR.setProtocol("POST");
						}
						// confirmation digit do spot confirm digit
						if (isNullOrBlank(spot.getSpotConfirmDigit())) {
							bodyR.setConfirmationDigit("");	
						} else {
						    bodyR.setConfirmationDigit(spot.getSpotConfirmDigit().toString());
						}
						// call duration 60
						//bodyR.setCallDuration("60");
						bodyR.setCallDuration(campanhaFinal.getCallDuration().toString());
						// allow on net
						bodyR.setAllowOnNet(true);
						// allow off net
						bodyR.setAllowOffNet(false);
						// AllowLandlineOnNet
						bodyR.setAllowLandlineOnNet(true);
						// allow landing off net
						bodyR.setAllowLandlineOffNet(true);

						// nextcamping
						if (campanhaFinal.getOnce()) {
							bodyR.setNextCampaign(true);
						}
						else {
							bodyR.setNextCampaign(false);
						}

						bodyR.setPlayWelcome(true);
						bodyR.setNewUser(true);

						// playWelcome // new user
						LastCallMsisdn lastCallMsisdn = lastCallMsisdnService.getStatisticByIdMsisdn(msisdn);
						if (lastCallMsisdn.getMsisdn()==null) {
							bodyR.setPlayWelcome(true);
							bodyR.setNewUser(true);
						}
						else {
							bodyR.setPlayWelcome(false);
							bodyR.setNewUser(false);
						}

						// audio list  ---- implementar depois.
						Audios audios = new Audios();
						setAudioList(spot, audios);
						bodyR.setAudios(audios);
						
						br.com.supportcomm.mktcall.entity.Foward foward =  fowardService.getFowardByCampanha(campanhaFinal.getIdCampanha());
						
						
						Forward foward1 = new Forward();
						if(foward.getFowardEnabled()!=null){
							
							if(foward.getAudioPosition().equalsIgnoreCase("before")){
								foward1.setAudioFileName(configService.getValueByIndentify("audioMdivulgaBefore"));
							}
							else{
								foward1.setAudioFileName(configService.getValueByIndentify("audioMdivulgaAfter"));
							}
							
							foward1.setAudioPosition(foward.getAudioPosition());
							foward1.setCfu(foward.getCfu());
							foward1.setForwardEnabled(foward.getFowardEnabled());
						}else
						{
							foward1.setForwardEnabled(false);
						}
						bodyR.setforwad(foward1);
						
					}

				}
				catch (Exception e) {
					RA.setStatus("1");
					bodyR.setStatusMSISDN(String.valueOf(StatusOperation.INTERNAL_ERROR.value()));
					bodyR.setDescription(StatusOperation.INTERNAL_ERROR.description());
					
					logger.warn("Erro whiteList ----- : "+ e.getMessage());
				}
			}
			// Gerando o XML --------------------------------------------------------------------------
			Timestamp initTimeXML = new Timestamp(System.currentTimeMillis());
			RA.setBody(bodyR);
			JAXBContext jaxbContext = JAXBContext.newInstance(ResponseAdvertisement.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(RA, stringWriter);
			response.setContentType("text/xml");
			response.getWriter().write(stringWriter.toString());
			Timestamp endTimeXML = new Timestamp(System.currentTimeMillis());
			Long elapXML = endTimeXML.getTime() - initTimeXML.getTime();
			logger.debug("Elaptime "+ this.getClass().getName() +"  Gerando o XML -------->" + elapXML);
			// -----------------------------------------------------------------------------------------
			

			/*long totalReach=0;
			boolean jaAtingiuOMax = false;
			//Incrementando o Reach para cada campannha exibida
			if (campanhaFinal.getIdCampanha()!= null){
				totalReach=controlFlowService.getTotalReach(campanhaFinal);
				jaAtingiuOMax = totalReach>= (campanhaFinal.getInsertionContracted() )?true:false ;
				if (jaAtingiuOMax ) {
					//campanhaFinal.setInsertionReach(campanhaFinal.getInsertionReach() + 1);
					campanhaFinal.setStatus(DESATIVADA);
					campanhaService.desativaCampanha(campanhaFinal);
					logger.info("Almir Atingiu o Max" + totalReach);
				}
				else {
					//campanhaFinal.setInsertionReach(campanhaFinal.getInsertionReach() + 1);
				}
	
				
				//campanhaService.incremetReachCampanha(campanhaFinal);
				//---------------------------------------------------------------------------------------
				// Aqui no final gerar controle de queda
				if (!jaAtingiuOMax){
					logger.info("Almir totalReach" + totalReach);
					logger.info("Almir Capanha ID" + campanhaFinal.getIdCampanha());
					setControlFlow(request,campanhaFinal);
				}*/
			    
			//}else{
			//	logger.info("Almir campanhaFiltradas esta vazio por isso que não fez");
			//}
			synchronized (objeto){
				controlFlowService.executeControl(request, campanhaFinal);
			}
		   
			
			logger.info("GetAdvertisement end");
			
		}
		catch (Exception e) {
			logger.error("GetAdvertisement ERROR");
			logger.error(e.getMessage());
			e.printStackTrace();

		}
	}
	/**
	 * Método ira gerar informações para controle ligação do usuário
	 * @param campanhaFinal 
	 * 
	 */
	private void setControlFlow(HttpServletRequest request, Campanha campanhaFinal1) {
		ControlFlow controlFlow =  new ControlFlow();
		String msisdn          = request.getParameter("msisdn");
		String callid          = request.getParameter("callid");
		Long campaignId      = campanhaFinal1.getIdCampanha();
		
		//Cdr cdr =  cdrService.getCdrByCallId(callid);
		controlFlow.setCallid(callid);
		controlFlow.setIdCampanha( campaignId);
		controlFlow.setIdCdr(99l);
		controlFlow.setMsisdn(msisdn);
		controlFlow.setListenCompleted(false);
		controlFlow.setOperationDate(new Timestamp(System.currentTimeMillis()));
		
		controlFlowService.persistControlFlow(controlFlow);
		
	}

	private void setAudioList(Spot spot, Audios audios) {
		// medida provisória
		List<SpotAudioFile> spotAudioFileList = spotService.getSpotAudioFileBySpotId(spot.getIdSpot());
		spot.setSpotAudioFiles(spotAudioFileList);

		for (SpotAudioFile spotAudio : spot.getSpotAudioFiles()) {

			br.com.supportcomm.mktcall.xml.status.advertisement.ResponseAdvertisement.Body.Audios.Audio audio = new br.com.supportcomm.mktcall.xml.status.advertisement.ResponseAdvertisement.Body.Audios.Audio();
			audio.setId(Integer.parseInt(spotAudio.getIdSpotAudioFiles().toString()));
			audio.setOrder(spotAudio.getSpotAudioOrder());
			audio.setFileName(spotAudio.getSpotFilename().substring(0, spotAudio.getSpotFilename().indexOf(".")));
			audio.setLengthDigit(spotAudio.getDigits());

			audios.getAudio().add(audio);

		}
	}

	private List<Campanha> getCampanhaFiltro(List<Campanha> campanhaList,	List<Campanha> campanhaFiltradas, Timestamp dataHoje, List<MsisdnCampanha> msisdnList) {
		List<Insertion> insertionList;
		long campanhaTocada=0; 
		//Vamos verificar se foi escutado a campanha hoje

		for( MsisdnCampanha msisdnCampanha:msisdnList){
			for (Campanha campanhaFiltro : campanhaList){
				if(msisdnCampanha.getCampanha().getIdCampanha()==campanhaFiltro.getIdCampanha()){
					
					String hoje = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					String dataDaCampanha = new SimpleDateFormat("yyyy-MM-dd").format(msisdnCampanha.getOperationDate());
					if(dataDaCampanha.equals(hoje) ){
						if( msisdnCampanha.getCampanha().getInsertions().isEmpty()){
						campanhaTocada = msisdnCampanha.getCampanha().getIdCampanha();
						break;
						}
					}
				}
			}
			if (campanhaTocada!=0){
				break;
			}
		}
		
		
		List<Campanha> campanhasComSchedules = new ArrayList<>();
		List<Campanha> campanhasSemSchedules = new ArrayList<>();
		
		
		for (Campanha campanhaFiltro : campanhaList) {
			//somento os ativos
			if (campanhaFiltro.getStatus() == 1) {
                
				if (campanhaFiltro.getEndDatetime().after(dataHoje) || campanhaFiltro.getEndDatetime().equals(dataHoje)) {
					
					if (campanhaFiltro.getStartDatatime().before(dataHoje) || campanhaFiltro.getStartDatatime().equals(dataHoje)) {

						if (campanhaFiltro.getInsertionReach() <= campanhaFiltro.getInsertionContracted()) {

							insertionList = insertionService.getInsertionCampanha(campanhaFiltro.getIdCampanha());

							campanhaFiltro.setInsertions(insertionList);

							if (!campanhaFiltro.getInsertions().isEmpty()) {
								
								if(campanhaTocada!=campanhaFiltro.getIdCampanha()){
								
									if (verifyDay(campanhaFiltro)) {
										//campanhaFiltradas.add(campanhaFiltro);
										campanhasComSchedules.add(campanhaFiltro);
									}
								}
							}else{
								//campanhaFiltradas.add(campanhaFiltro);
								campanhasSemSchedules.add(campanhaFiltro);
							}
							}
							

						}

					}
				}

			}

		if (!campanhasComSchedules.isEmpty()){
			campanhaFiltradas=campanhasComSchedules;
		}else{
			campanhaFiltradas=campanhasSemSchedules;
		}
			
		
		if(campanhaFiltradas.isEmpty()){
			
			// Se não tem nenhuma campanha com programação de horário pegar as outras ativas
			for (Campanha campanhaFiltro : campanhaList) {
				//somento os ativos
				if (campanhaFiltro.getStatus() == 1) {
					if(campanhaTocada!=campanhaFiltro.getIdCampanha()){
						campanhaFiltradas.add(campanhaFiltro);
					}
				}
			}
		}
		
		return campanhaFiltradas;
		
	}
	/**
	 * Aqui verifica em que região o cfu faz parte e quais as campanhas assoiadas para tocar<br/>
	 * Caso Não exista regionalização para aquele cfu vai tocar os nacionais.
	 * 
	 * @param cfuCode
	 * @param campanhaList
	 */
	private  List<Campanha> searchRegion(String areaCode,Timestamp dataHoje) {
		List<Insertion> insertionList;
		long campanhaTocada=0; 
		 List<Campanha> campanhas= regionService.getRegionAdvertisement(areaCode);
		 List<Campanha> campanhasSelecionadas = new ArrayList<>();
		 
		 for(Campanha campanha:campanhas){
			 if(campanha.getStatus()==1){
				 
				 if (campanha.getEndDatetime().after(dataHoje) || campanha.getEndDatetime().equals(dataHoje)) {
					 if (campanha.getStartDatatime().before(dataHoje) || campanha.getStartDatatime().equals(dataHoje)) {
						 if (campanha.getInsertionReach() <= campanha.getInsertionContracted()) {
							 insertionList = insertionService.getInsertionCampanha(campanha.getIdCampanha());
							 campanha.setInsertions(insertionList);
							 if (!campanha.getInsertions().isEmpty()) {
									
									if(campanhaTocada!=campanha.getIdCampanha()){
										 if(verifyDay(campanha)){
											 campanhasSelecionadas.add(campanha);
										 }
									}
							 }else{
								 campanhasSelecionadas.add(campanha);
							 }
						 }
					 }
				 }
				 
				 
			 }
		 }
		 
		 
		return campanhasSelecionadas;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(" doPost()");
		StatusOperation status = StatusOperation.METHOD_POST_ERROR;
		logger.error(" - " + status.description());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public Timestamp getTime(String time) {
		Timestamp times = null;
		Calendar cal;

		try {
			cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(sdf.parse(time));
			times = new Timestamp(cal.getTimeInMillis());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return times;
	}

	public List<Cdr> getCdrList(String msisdn) {
		List<Cdr> cdrList = cdrService.getCdrTodayOnceDay(4, msisdn, 1, new Timestamp(System.currentTimeMillis()));
		List<Cdr> cdrListReturn = new ArrayList<Cdr>();
		if(!cdrList.isEmpty()){
			for ( int i=0; i<cdrList.size();i++) {
				Timestamp ts = cdrList.get(i).getCallDatetime() ;
				if (verifyTimestampDay(ts, new Timestamp(System.currentTimeMillis()))) {
					cdrListReturn.add(cdrList.get(i));
				}
			}
		}
		return cdrListReturn;
	}

	public boolean verifyTimestampDay(Timestamp dayOne, Timestamp dayTwo) {
		String frist = new SimpleDateFormat("yyyy-MM-dd").format(dayOne);
		String second = new SimpleDateFormat("yyyy-MM-dd").format(dayTwo);
		if (frist.equals(second)) {
			return true;
		}
		return false;

	}
	
	public boolean verificarCampanhaListaFidelizada(Campanha campanhaFiltro,Timestamp dataHoje){
		
		List<Insertion> insertionList = new ArrayList<Insertion>();

			if (campanhaFiltro.getStatus() == 1) {
				if (campanhaFiltro.getEndDatetime().after(dataHoje) || campanhaFiltro.getEndDatetime().equals(dataHoje)) {
					if (campanhaFiltro.getStartDatatime().before(dataHoje) || campanhaFiltro.getStartDatatime().equals(dataHoje)) {
						if (campanhaFiltro.getInsertionReach() <= campanhaFiltro.getInsertionContracted()) {
							insertionList = insertionService.getInsertionCampanha(campanhaFiltro.getIdCampanha());
							campanhaFiltro.setInsertions(insertionList);
							if (!campanhaFiltro.getInsertions().isEmpty()) {
								if (verifyDay(campanhaFiltro)) {
									return true;
								}
							} else {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks whether a campaign has a proper time schedule to run in this very moment.
	 * @param campanhaFiltro
	 * @param dataHoje - time of comparison against campaign schedule.
	 * @return
	 */
	public boolean checkCampaignSegmentationList(Campanha campanhaFiltro,Timestamp dataHoje){	
		List<Insertion> insertionList = new ArrayList<Insertion>();

		if (campanhaFiltro.getEndDatetime().after(dataHoje) || campanhaFiltro.getEndDatetime().equals(dataHoje)) {
			if (campanhaFiltro.getStartDatatime().before(dataHoje) || campanhaFiltro.getStartDatatime().equals(dataHoje)) {
				if (campanhaFiltro.getInsertionReach() <= campanhaFiltro.getInsertionContracted()) {
					insertionList = insertionService.getInsertionCampanha(campanhaFiltro.getIdCampanha());
					campanhaFiltro.setInsertions(insertionList);
					if (!campanhaFiltro.getInsertions().isEmpty()) {
						if (verifyDay(campanhaFiltro)) {
							return true;
						}
					} else {
					    return true;
				    }
			    }
			}
		}
		return false;
	}	

		
	
	public static boolean isNullOrBlank(Object obj) {
		if (obj == null || obj.equals("")) {
			return true;
		}
		return false;
	}



}
