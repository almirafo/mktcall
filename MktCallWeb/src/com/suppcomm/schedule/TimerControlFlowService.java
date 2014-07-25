package com.suppcomm.schedule;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Cdr;
import br.com.supportcomm.mktcall.entity.ControlFlow;
import br.com.supportcomm.mktcall.entity.Statistic;
import br.com.supportcomm.mktcall.log.LogMktCall;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.controlflow.ControlFlowService;
import br.com.supportcomm.mktcall.service.statistic.StatsService;
import br.com.supportcomm.mktcall.managedbean.AbstractManagedBean;



@Stateless
public class TimerControlFlowService extends AbstractManagedBean{

	private static volatile boolean emExec = false;
	

	@EJB ControlFlowService controlFlowService;
	@EJB CampanhaService campanhaService;
	@EJB StatsService statsService;
	@EJB CdrService cdrService;
	
	private static synchronized boolean mudaExec(boolean val) {
		if (val == emExec) {
			return false;
		}
		else {
			emExec = val;
			return true;
		}
	}
	

    @Schedule( second="0" ,minute="0/2",hour="*", persistent=false)
    public void doWork(){

    	LogMktCall.log(Level.INFO,"iniciando coltrol flow");
		if (!TimerControlFlowService.mudaExec(true)) {
			LogMktCall.log(Level.ERROR, "Timer Control Flow in process!");
			System.out.println("Timer Control Flow in process!!");
			return;
		}
		
		try {
			
			// Carregar Lista ControlFlow com mais velhas que 3 minutos
			List<ControlFlow> controlFlows = controlFlowService.getControlFlowAll(6);
			for(ControlFlow controlFlow:controlFlows){
				
				try {
					Cdr cdr = new Cdr();
					cdr.setMsisdnOriginator(controlFlow.getMsisdn());
					cdr.setCallid(controlFlow.getCallid());
					cdr.setCallDatetime(controlFlow.getOperationDate());
					Campanha campanha = campanhaService.getCampanhaId(controlFlow.getIdCampanha()).get(0);
					campanha.setIdCampanha(controlFlow.getIdCampanha());
					
					cdr.setCampanha( campanha);
					cdr.setHangupCause(999);
					cdrService.persistCdr(cdr);
					
					
					// adicionar em statistcs com completed =m false
					
					Statistic statistic = new Statistic();
					statistic.setIdCampanha(controlFlow.getIdCampanha());
					statistic.setIdCdr(controlFlow.getIdCdr());
					statistic.setListenComplete(false);
					statistic.setMsisdn(controlFlow.getMsisdn());
					statistic.setOperationDate(controlFlow.getOperationDate());
					statsService.persistStatistic(statistic );
					//decrementar em capanha reach
					
					
					campanha.setInsertionReach(campanha.getInsertionReach()-1);
					campanhaService.mergeCampanha(campanha);
					//remover do controle
					controlFlowService.removeControlFlow(controlFlow);
					long totalReach=controlFlowService.getTotalReach(campanha)+(campanha.getInsertionReachComplement()==null?0:campanha.getInsertionReachComplement());
					if (totalReach<campanha.getInsertionContracted()){
						campanha.setStatus(1);
					}
					campanhaService.mergeCampanha(campanha);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogMktCall.log(Level.ERROR, "Exceção no loop id Control -- " + controlFlow.getIdControl()+" " + this.getClass().getName(), e);
				}
			}
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			LogMktCall.log(Level.ERROR, "Exceção no Job " + this.getClass().getName(), e); 
		}
		finally {
			TimerControlFlowService.mudaExec(false);
			Logger.getLogger("Timer Control Flow End");
		}
	
    }
}