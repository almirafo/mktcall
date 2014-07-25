package br.com.supportcomm.mktcall.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Cdr;
import br.com.supportcomm.mktcall.entity.ControlFlow;
import br.com.supportcomm.mktcall.entity.LastCallMsisdn;
import br.com.supportcomm.mktcall.entity.MsisdnCampanha;
import br.com.supportcomm.mktcall.entity.SpotAudioFile;
import br.com.supportcomm.mktcall.entity.SpotResponse;
import br.com.supportcomm.mktcall.entity.Statistic;
import br.com.supportcomm.mktcall.constants.StatusOperation;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.controlflow.ControlFlowService;
import br.com.supportcomm.mktcall.service.lastcallmsisdn.LastCallMsisdnService;
import br.com.supportcomm.mktcall.service.msisdncampanha.MsisdnCampanhaService;
import br.com.supportcomm.mktcall.service.spot.SpotService;
import br.com.supportcomm.mktcall.service.statistic.StatsService;

/**
 * Servlet implementation class EndSessionServlet
 */
@WebServlet(name = "EndSession", description = "EndSession service", urlPatterns = { "/EndSession" })
public class EndSessionServlet extends HttpServlet {
	private static final int ATIVADA = 1;

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(getClass());

	@EJB
	private MsisdnCampanhaService msisdnCService;

	@EJB
	private SpotService spotService;

	@EJB
	private CampanhaService campanhaService;

	@EJB
	private CdrService cdrService;

	@EJB
	private StatsService statsService;
	
	@EJB
	private LastCallMsisdnService lastCallMsisdnService;
	
	@EJB 
	private ControlFlowService controlFlowService;

	public EndSessionServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * msisdn - Numero do celular do usuário do serviço (chamador) callid -
		 * id da chamada da URA campaignid - id da campanha obtida na API
		 * GetAdvertisement destinationcall - Número de destino (número de B)
		 * hangupcause - Causa da desconexão entre o número de A e B
		 * calldatetime - Data e Hora da realização da chamada para o numero de
		 * B answerdatetime - Data e Hora do atendimento da chamada pelo numero
		 * de B duration - Duração da chamada com o numero de B sessiontime -
		 * Tempo total da sessão na URA
		 */

		StatusOperation status = StatusOperation.OK;
		String noValue = "-1";

		String msisdn = request.getParameter("msisdn");
		String callid = request.getParameter("callid");
		String campaignId = request.getParameter("campaignid");
		String destinationCall = request.getParameter("destinationcall");
		String hangupCause = request.getParameter("hangupcause");
		String dialstatus = request.getParameter("dialstatus");
		String callDateTime = request.getParameter("calldatetime");
		String answerDateTime = request.getParameter("answerdatetime");
		String duration = request.getParameter("duration");
		String sessionTime = request.getParameter("sessiontime");
		String listencompleted = request.getParameter("listencompleted");

		String[] audioId = request.getParameterValues("audioid");
		String[] answer = request.getParameterValues("answer");

		if (msisdn == null) {
			status = StatusOperation.MISSING_MSISDN;
		}
		else if (callid == null) {
			status = StatusOperation.MISSING_CALLID;
		}
		else if (campaignId == null) {
			status = StatusOperation.MISSING_CAMPAIGNID;
		}
		else if (answerDateTime == null) {
			status = StatusOperation.MISSING_ANSWER_DATE_TIME;
		}
		else if (listencompleted == null) {
			status = StatusOperation.MISSING_LISTENCOMPLETEAD;
		}
		else if (callDateTime == null) {
			status = StatusOperation.MISSING_CALL_DATE_TIME;
		}
		else if (hangupCause == null) {
			status = StatusOperation.MISSING_HANGUP_CAUSE;
		}
		else if (destinationCall == null) {
			status = StatusOperation.MISSING_DESTINATION_CALL;
		}
		else if (duration == null) {
			status = StatusOperation.MISSING_DURATION;
		}
		else if (sessionTime == null) {
			status = StatusOperation.MISSING_SESSION_TIME;
		}
		else if (dialstatus == null) {
			status = StatusOperation.MISSING_DIAL_STATUS;
		}
		else {
			try {
				logger.info(getClass().getName().concat(" processing..."));
				Cdr cdr = new Cdr();
				MsisdnCampanha msisdnC = new MsisdnCampanha();

				if (!campaignId.equals(noValue)) {
					Campanha campanha = new Campanha();
					List<Campanha> campanhaList = campanhaService.getCampanhaId(Long.parseLong(campaignId));
					if (campanhaList.size() > 0) {
						campanha = campanhaList.get(0);
						//if (!campanha.getStatus().equals(0)) {
							//Campanha campanhaUp = campanha;
							
							SimpleDateFormat sdf = setaDataHoraChamada(noValue,	callDateTime, answerDateTime, cdr); //Atribui data e hora da chamada no cdr

							setCdrByDialStatus(noValue, dialstatus, cdr);//Atribui informações no CDR conforme Dial Status

							setaDadosAdicionaisCDR(noValue, msisdn, callid, destinationCall, hangupCause, duration, sessionTime, cdr, campanha);

							List<Cdr> cdrs = new ArrayList<Cdr>();
							cdrs.add(cdr);

							msisdnC.setMsisdn(msisdn);
							msisdnC.setOperationDate(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
							msisdnC.setCallid(callid);
							msisdnC.setCampanha(campanha);

							List<SpotResponse> spotResponses = new ArrayList<SpotResponse>();

							msisdnC = msisdnCService.persistMsisdnCampanha(msisdnC);

							if (audioId != null) {
								for (int i = 0; i < audioId.length; i++) {
									SpotAudioFile spotAudioFile = spotService.getSpotAudioFileById(Long.valueOf(audioId[i])).get(0);
									SpotResponse spotResponse = new SpotResponse();
									spotResponse.setSpotAudioFile(spotAudioFile);
									spotResponse.setMsisdnCampanha(msisdnC);
									spotResponse.setResponse(answer[i]);
									spotResponses.add(spotResponse);
								}
							}

							for (Cdr cdrSave : cdrs) {
								cdrService.persistCdr(cdrSave);
							}

							// msisdnC.setSpotResponses(spotResponses);

							for (SpotResponse spotR : spotResponses) {
								spotService.mergeSpotResponse(spotR);
							}

							insereEmEstatistica(msisdn, listencompleted, cdr,campanha); // Dados para inserir informção tabela Statistic
							
							setRollBackReach(listencompleted, campanha); // decrementa insertionReach da campanha. caso listencompleted= false
							ControlFlow controlFlow = new ControlFlow();
							controlFlow.setCallid(callid);
							removeControlFlow(controlFlow,listencompleted);
							LastCallMsisdn  lastcallmsisdn=  new LastCallMsisdn();
							lastcallmsisdn =lastCallMsisdnService.getStatisticByIdMsisdn(msisdn);
							if(lastcallmsisdn.getIdlastcallmsisdn()==null){
								lastcallmsisdn.setIdCampanha(campanha.getIdCampanha());
								lastcallmsisdn.setMsisdn(msisdn);
								lastcallmsisdn.setOperationDate(new Timestamp(System.currentTimeMillis()));
								lastCallMsisdnService.persistLastCallMsisdn(lastcallmsisdn);
							}else{
								lastCallMsisdnService.mergeLastCallMsisdn(lastcallmsisdn);
							}
						//}
					}
					else {
						status = StatusOperation.NO_CAMPAIGN_AVAILABLE;
					}
				}
				else {
					status = StatusOperation.NO_CAMPAIGN_RECORD;
				}
				logger.info(getClass().getName().concat(" end"));
			}
			catch (Exception e) {
				status = StatusOperation.INTERNAL_ERROR;
				logger.error(e);
			}
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		StringBuffer xml = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?>");

		xml.append("<response>");
		xml.append("<status>");
		xml.append(status.value());
		xml.append("</status>");
		xml.append("<body>");

		if (status.value() == StatusOperation.OK.value()) {
			// TODO: Inserir <tags> de retorno para operaÃ§Ã£o bem sucedida
			xml.append("<description>");
			xml.append(status.description());
			xml.append("</description>");
		}
		else {
			xml.append("<description>");
			xml.append(status.description());
			xml.append("</description>");
		}
		xml.append("</body>");
		xml.append("</response>");
		response.getWriter().write(xml.toString());
		
	}

	private void removeControlFlow(ControlFlow controlFlow, String listencompleted) {
		controlFlow= controlFlowService.getControlFlowByCallId(controlFlow.getCallid());
		if (listencompleted.equals("false")){
			controlFlowService.removeControlFlow(controlFlow);
		}else{
			controlFlow.setListenCompleted(true);
			controlFlowService.mergeControlFlow(controlFlow);
		}
	}

	/**
	 * @param noValue
	 * @param msisdn
	 * @param callid
	 * @param destinationCall
	 * @param hangupCause
	 * @param duration
	 * @param sessionTime
	 * @param cdr
	 * @param campanha
	 */
	private void setaDadosAdicionaisCDR(String noValue, String msisdn,
			String callid, String destinationCall, String hangupCause,
			String duration, String sessionTime, Cdr cdr, Campanha campanha) {
		if (!hangupCause.equals(noValue)) {
			cdr.setHangupCause(1);
		}

		if (!destinationCall.equals(noValue)) {
			cdr.setDestinationCall(destinationCall);
		}

		cdr.setCallid(callid);

		if (!duration.equals(noValue)) {
			cdr.setDuration(Integer.valueOf(duration));
		}

		if (!sessionTime.equals(noValue)) {
			cdr.setSessionTime(Integer.valueOf(sessionTime));
		}

		cdr.setMsisdnOriginator(msisdn);
		cdr.setCampanha(campanha);
	}

	/**
	 * Atribui data e hora da chamada no cdr
	 * @param noValue
	 * @param callDateTime
	 * @param answerDateTime
	 * @param cdr
	 * @return
	 * @throws ParseException
	 */
	private SimpleDateFormat setaDataHoraChamada(String noValue,
			String callDateTime, String answerDateTime, Cdr cdr)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!answerDateTime.equals(noValue)) {
			Date parsedDate = sdf.parse(answerDateTime);
			cdr.setAnswerDatetime(new Timestamp(parsedDate.getTime()));
		}

		if (!callDateTime.equals(noValue)) {

			Date parsedDate = sdf.parse(callDateTime);
			cdr.setCallDatetime(new Timestamp(parsedDate.getTime()));
		}
		return sdf;
	}

	/**
	 * Atribui informações no CDR conforme Dial Status
	 * @param noValue
	 * @param dialstatus
	 * @param cdr
	 */
	private void setCdrByDialStatus(String noValue, String dialstatus, Cdr cdr) {
		if (!dialstatus.equals(noValue)) {

			if (dialstatus.equals(StatusOperation.DIAL_STATUS_ANSWER.description())) {
				cdr.setDialstatus(StatusOperation.DIAL_STATUS_ANSWER.value());
			}

			if (dialstatus.equals(StatusOperation.DIAL_STATUS_CONGESTION.description())) {
				cdr.setDialstatus(StatusOperation.DIAL_STATUS_CONGESTION.value());
			}

			if (dialstatus.equals(StatusOperation.DIAL_STATUS_BUSY.description())) {
				cdr.setDialstatus(StatusOperation.DIAL_STATUS_BUSY.value());
			}

			if (dialstatus.equals(StatusOperation.DIAL_STATUS_CHANUAVAIL.description())) {
				cdr.setDialstatus(StatusOperation.DIAL_STATUS_CHANUAVAIL.value());
			}

			if (dialstatus.equals(StatusOperation.DIAL_STATUS_NO_ANSWER.description())) {
				cdr.setDialstatus(StatusOperation.DIAL_STATUS_NO_ANSWER.value());
			}

		}
	}

	/**
	 * Insere informações na tabela statistic
	 * @param msisdn
	 * @param listencompleted
	 * @param cdr
	 * @param campanhaUp
	 */
	private void insereEmEstatistica(String msisdn, String listencompleted,
			Cdr cdr, Campanha campanhaUp) {
		Statistic statistic = new Statistic();
		statistic.setIdCampanha(campanhaUp.getIdCampanha());
		statistic.setIdCdr(cdr.getIdCdr());
		statistic.setMsisdn(msisdn);
		statistic.setOperationDate(new Timestamp(System.currentTimeMillis()));
		statistic.setListenComplete(Boolean.parseBoolean(listencompleted));
		statsService.persistStatistic(statistic);
	}

	/**
	 * Decrementa o contador do Reach da tabela campanha caso não tenha escutado o audio da chamada patrocinada por completo
	 * @param listencompleted
	 * @param campanha
	 */
	private void setRollBackReach(String listencompleted, Campanha campanha) {
		if (listencompleted.equals("false")) {
			if (campanha.getInsertionReach() == (campanha.getInsertionContracted()+ (campanha.getInsertionReachComplement()==null?0: campanha.getInsertionReachComplement()))) {
				campanha.setInsertionReach(campanha.getInsertionReach()-1);
				campanha.setStatus(ATIVADA);
				
			}else{
				campanha.setInsertionReach(campanha.getInsertionReach()-1);
			}
			campanhaService.mergeCampanha(campanha);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
