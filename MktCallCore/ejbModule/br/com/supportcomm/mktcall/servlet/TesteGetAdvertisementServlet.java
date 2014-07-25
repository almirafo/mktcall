package br.com.supportcomm.mktcall.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Cdr;
import br.com.supportcomm.mktcall.entity.Insertion;
import br.com.supportcomm.mktcall.entity.LastCallMsisdn;
import br.com.supportcomm.mktcall.entity.ListaGeral;
import br.com.supportcomm.mktcall.entity.MsisdnCampanha;
import br.com.supportcomm.mktcall.entity.Region;
import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.entity.SpotAudioFile;
import br.com.supportcomm.mktcall.xml.status.advertisement.ResponseAdvertisement;
import br.com.supportcomm.mktcall.xml.status.advertisement.ResponseAdvertisement.Body.Audios;
import br.com.supportcomm.mktcall.constants.StatusOperation;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.insertion.InsertionService;
import br.com.supportcomm.mktcall.service.lastcallmsisdn.LastCallMsisdnService;
import br.com.supportcomm.mktcall.service.listageral.ListaGeralService;
import br.com.supportcomm.mktcall.service.msisdncampanha.MsisdnCampanhaService;
import br.com.supportcomm.mktcall.service.region.RegionService;
import br.com.supportcomm.mktcall.service.spot.SpotService;


public class TesteGetAdvertisementServlet  {
	

	//private Logger logger = Logger.getLogger(getClass());
	private Logger logger = Logger.getLogger(getClass().getName());
	private static Campanha campanhaFinal;

	@EJB
	private static CampanhaService campanhaService = new CampanhaService();

	@EJB
	private static MsisdnCampanhaService msisdnService =  new MsisdnCampanhaService();
	
	@EJB
	private static LastCallMsisdnService lastCallMsisdnService = new LastCallMsisdnService();

	@EJB
	private static RegionService regionService = new RegionService();

	@EJB
	private static SpotService spotService = new SpotService();

	@EJB
	private static InsertionService insertionService = new InsertionService();

	@EJB
	private static CdrService cdrService = new CdrService();

	@EJB
	private static ListaGeralService listaGeralService =  new ListaGeralService();

	public TesteGetAdvertisementServlet() {
		super();
	}

	public static void main(String[] args) {

		try {
			Timestamp intDate = new Timestamp(System.currentTimeMillis());
			
			String msisdn = "1396466696";//request.getParameter("msisdn");
			String callid = "1372980657.1364";//request.getParameter("callid");
			String cfuCode = "111416134040";// request.getParameter("cfucode");
			String whiteList = "false";//request.getParameter("whitelist");

			List<Region> regionList = new ArrayList<Region>();
			List<MsisdnCampanha> msisdnList = new ArrayList<MsisdnCampanha>();
			List<Campanha> campanhaList = new ArrayList<Campanha>();
			List<Campanha> campanhaFiltradas = new ArrayList<Campanha>();
			List<Campanha> campanhaP0 = new ArrayList<Campanha>();
			List<Campanha> campanhaP1 = new ArrayList<Campanha>();
			List<Campanha> campanhaP2 = new ArrayList<Campanha>();
			List<Campanha> campanhaP3 = new ArrayList<Campanha>();
			List<Campanha> campanhaP4 = new ArrayList<Campanha>();
			List<Campanha> campanhaP5 = new ArrayList<Campanha>();
			List<ListaGeral> listaGeral = new ArrayList<ListaGeral>();

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
			else if (cfuCode == null) {

				bodyR.setDescription(StatusOperation.MISSING_CFUCODE.description());
				RA.setStatus(String.valueOf(StatusOperation.MISSING_CFUCODE.value()));

			}
			else {

				try {

					//msisdnList = msisdnService.getMsisdnCampanhaAdvertisement(msisdn, callid);

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
								
								
								System.out.println(stringWriter.toString());
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
							//response.setContentType("text");
							//response.getWriter().write(stringWriter.toString());
							System.out.println(stringWriter.toString());
							return;

						}

					}

					listaGeral = listaGeralService.getListaGeralByMsisdn(msisdn);

					if (!listaGeral.isEmpty()) {

						for (ListaGeral lista : listaGeral) {
							Campanha campanhaFidelizada = new Campanha();
											
							campanhaFidelizada = campanhaService.getCampanhaId(lista.getIdCampanha()).get(0);
							
							if(verificarCampanhaListaFidelizada(campanhaFidelizada, dataHoje)) campanhaList.add(campanhaFidelizada);
						}

					}
					else {
						// Tabela de Região vazia 
						searchRegion(cfuCode, campanhaList);
					}

					if (campanhaList.isEmpty()) {

						List<Campanha> campanhaListRecovery = new ArrayList<Campanha>();
						
						campanhaListRecovery = campanhaService.getCampanhaAllNative();

						Collections.shuffle(campanhaListRecovery);

						campanhaList.addAll(campanhaListRecovery);
						// So permite uma chamada por dia.
						if (campanhaList.isEmpty()) {

							bodyR.setDescription(StatusOperation.NO_CAMPAIGN_AVAILABLE.description());
							RA.setStatus(String.valueOf(StatusOperation.NO_CAMPAIGN_AVAILABLE.value()));
							RA.setBody(bodyR);
							JAXBContext jaxbContext = JAXBContext.newInstance(ResponseAdvertisement.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							StringWriter stringWriter = new StringWriter();
							jaxbMarshaller.marshal(RA, stringWriter);
							//response.setContentType("text");
							//response.getWriter().write(stringWriter.toString());
							System.out.println(stringWriter.toString());
							return;
						}

					}

					// Filtrar campanhas que estejam dentro da data ativa e com status 1
					//List<Insertion> insertionList = new ArrayList<Insertion>();
					
					
					getCampanhaFiltro(campanhaList, campanhaFiltradas, dataHoje,msisdnList);
					
					
					for (Campanha campanhaPrioridade : campanhaFiltradas) {

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
					}

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

					if (campanhaFiltradas.isEmpty()) {

						bodyR.setDescription(StatusOperation.NO_CAMPAIGN_AVAILABLE.description());
						RA.setStatus(String.valueOf(StatusOperation.NO_CAMPAIGN_AVAILABLE.value()));

					}
					else {

						campanhaFinal = campanhaFiltradas.get(0);

						Spot spot = spotService.getSpotById(campanhaFinal.getSpot().getIdSpot()).get(0);
						//Spot spot =  new Spot();
						RA.setStatus(String.valueOf(StatusOperation.OK.value()));
						// verificar se o usuario existe
						// Se existe ou não o msisdn na campanha , 1 sim / 2 não
						if (msisdnList.isEmpty()) {
							bodyR.setStatusMSISDN(String.valueOf(StatusOperation.OK.value()));
							bodyR.setDescription(StatusOperation.OK.description());
						}
						else {
							bodyR.setStatusMSISDN(String.valueOf(StatusOperation.OK.value()));
							bodyR.setDescription(StatusOperation.OK.description());
						}

						// Id da campanha
						bodyR.setCampaignID(Integer.parseInt(campanhaFinal.getIdCampanha().toString()));
						// tipo do spot
						bodyR.setAdvertisementType(spotService.getSpotTipoId(Long.parseLong(spot.getSpotType().toString())).get(0).getCodeSpotTipo());
						// messageSMS do spot
						bodyR.setMessage(spot.getMessageSms());
						// server sms do spot
						bodyR.setServerURL(spot.getServerSms());
						// metodo do spot
						if(!isNullOrBlank(spot.getProtocol())){
							if(spot.getProtocol().equals("1")) bodyR.setProtocol("GET");
							if(spot.getProtocol().equals("2")) bodyR.setProtocol("POST");
						}
						// confirmation digit do spot confirm digit
						bodyR.setConfirmationDigit(spot.getSpotConfirmDigit().toString());
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

						// audio list
						Audios audios = new Audios();
						setAudioList(spot, audios);
						bodyR.setAudios(audios);
					}

				}
				catch (Exception e) {
					//Logger logger = Logger.getLogger(getClass().getName());
					RA.setStatus("1");
					bodyR.setStatusMSISDN(String.valueOf(StatusOperation.INTERNAL_ERROR.value()));
					bodyR.setDescription(StatusOperation.INTERNAL_ERROR.description());
					
					//logger.log(Level.WARNING,"Erro whiteList ----- : "+ e.getMessage());
				}
			}
			// Gerando o XML --------------------------------------------------------------------------
			Timestamp initTimeXML = new Timestamp(System.currentTimeMillis());
			RA.setBody(bodyR);
			JAXBContext jaxbContext = JAXBContext.newInstance(ResponseAdvertisement.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(RA, stringWriter);
			//response.setContentType("text/xml");
			//response.getWriter().write(stringWriter.toString());
			System.out.println(stringWriter.toString());
			Timestamp endTimeXML = new Timestamp(System.currentTimeMillis());
			Long elapXML = endTimeXML.getTime() - initTimeXML.getTime();
				// -----------------------------------------------------------------------------------------
			
			Timestamp endTime = new Timestamp(System.currentTimeMillis());//tempCalendar2.getTimeInMillis();
			Long elap = endTime.getTime() - intDate.getTime();
			if(elap>3000){
				}else{
			}
		}
		catch (Exception e) {
			//logger.severe(e.getMessage());
			e.printStackTrace();

		}
	}

	private static void setAudioList(Spot spot, Audios audios) {
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

	private static void getCampanhaFiltro(List<Campanha> campanhaList,	List<Campanha> campanhaFiltradas, Timestamp dataHoje, List<MsisdnCampanha> msisdnList) {
		List<Insertion> insertionList;
		long campanhaTocada=0; 
		//Vamos verificar se foi escutado a campanha hoje

		for( MsisdnCampanha msisdnCampanha:msisdnList){
			for (Campanha campanhaFiltro : campanhaList){
				if(msisdnCampanha.getCampanha().getIdCampanha()==campanhaFiltro.getIdCampanha()){
					
					String hoje = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					String dataDaCampanha = new SimpleDateFormat("yyyy-MM-dd").format(msisdnCampanha.getOperationDate());
					if(dataDaCampanha.equals(hoje) ){
						campanhaTocada = msisdnCampanha.getCampanha().getIdCampanha();
						break;
					}
				}
			}
			if (campanhaTocada!=0){
				break;
			}
		}
		
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
										campanhaFiltradas.add(campanhaFiltro);
									}
								}
							}
							}
							else {
								campanhaFiltradas.add(campanhaFiltro);
							}

						}

					}
				}

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
		
		
		
	}

	private static void searchRegion(String cfuCode, List<Campanha> campanhaList) {
		List<Region> regionList;
		//regionList = regionService.getRegionAdvertisement(cfuCode);

		//for (Region region : regionList) {

			//if (region.getCampanha().getIdCampanha() != null && region.getCfu().getIdCfu() != null) campanhaList.add(region.getCampanha());

		//}
	}

	private static boolean verifyDay(Campanha campanha) {

		Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
		
		boolean dayOK = false;
		Calendar now = Calendar.getInstance();
		now.setTime(dataDeHoje);
		int diaDaSemana =now.get(Calendar.DAY_OF_WEEK);
		
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
		this.logger.info(" doPost()");
		StatusOperation status = StatusOperation.METHOD_POST_ERROR;
		this.logger.severe(" - " + status.description());
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
			logger.severe(e.getMessage());
		}
		return times;
	}

	public static List<Cdr> getCdrList(String msisdn) {
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

	public static boolean verifyTimestampDay(Timestamp dayOne, Timestamp dayTwo) {
		String frist = new SimpleDateFormat("yyyy-MM-dd").format(dayOne);
		String second = new SimpleDateFormat("yyyy-MM-dd").format(dayTwo);
		if (frist.equals(second)) {
			return true;
		}
		return false;

	}
	
	public static boolean verificarCampanhaListaFidelizada(Campanha campanhaFiltro,Timestamp dataHoje){
		
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

		
	
	public static boolean isNullOrBlank(Object obj) {
		if (obj == null || obj.equals("")) {
			return true;
		}
		return false;
	}

	public Campanha getCampanhaFinal() {
		return campanhaFinal;
	}

	public void setCampanhaFinal(Campanha campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}

}
