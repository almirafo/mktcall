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
import br.com.supportcomm.mktcall.constants.StatusOperation;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.controlflow.ControlFlowService;
import br.com.supportcomm.mktcall.service.insertion.InsertionService;
import br.com.supportcomm.mktcall.service.lastcallmsisdn.LastCallMsisdnService;
import br.com.supportcomm.mktcall.service.listageral.ListaGeralService;
import br.com.supportcomm.mktcall.service.msisdncampanha.MsisdnCampanhaService;
import br.com.supportcomm.mktcall.service.region.RegionService;
import br.com.supportcomm.mktcall.service.spot.SpotService;

/**
 * Servlet implementation class GetAdvertisementServlet
 */
@WebServlet(name = "AjustCampaingReachServlet", description = "AjustCampaingReachServlet ", urlPatterns = { "/AjustCampaingReachServlet" })
public class AjustCampaingReachServlet extends HttpServlet {
	private static final int DESATIVADA = 0;

	private static final long serialVersionUID = 1L;


	private Logger logger = Logger.getLogger(getClass().getName());
	

	@EJB
	private CampanhaService campanhaService;


	@EJB private ControlFlowService controlFlowService;
	
	public AjustCampaingReachServlet() {
		super();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("AjustCampaingReachServlet processing...");
		
		
		try {
			Timestamp intDate = new Timestamp(System.currentTimeMillis());
			
			String idCampanha = request.getParameter("idcampanha");
					Campanha campanha = campanhaService.getCampanhaId(Long.parseLong(idCampanha)).get(0);
				/*	for(long count = 1; count<= campanha.getInsertionReach();count++){
						ControlFlow controlFlow = new ControlFlow();
						controlFlow.setCallid("999999");
						controlFlow.setMsisdn("999999");
						controlFlow.setIdCdr(888888l);
						controlFlow.setOperationDate(intDate);
						controlFlow.setListenCompleted(true);
						controlFlow.setIdCampanha(campanha.getIdCampanha());
						controlFlowService.persistControlFlow(controlFlow);
					}*/
					ControlFlow controlFlow = new ControlFlow();
					controlFlow.setCallid("999999");
					controlFlow.setMsisdn("999999");
					controlFlow.setIdCdr(888888l);
					controlFlow.setOperationDate(intDate);
					controlFlow.setListenCompleted(true);
					controlFlow.setIdCampanha(campanha.getIdCampanha());
					controlFlowService.persistControlFlowBatch( controlFlow,campanha.getInsertionReach());
					
					
			logger.info("AjustCampaingReachServlet end");
			
		}
		catch (Exception e) {
			logger.error("GetAdvertisement ERROR");
			logger.error(e.getMessage());
			e.printStackTrace();

		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(" doPost()");
		StatusOperation status = StatusOperation.METHOD_POST_ERROR;
		logger.error(" - " + status.description());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


}
