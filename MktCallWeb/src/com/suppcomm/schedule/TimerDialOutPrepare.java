package com.suppcomm.schedule;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.controledialout.ControleDialOutService;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.enums.ProcessStatus;
import br.com.supportcomm.mktcall.log.LogMktCall;
import br.com.supportcomm.mktcall.managedbean.AbstractManagedBean;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;

/*
 * Schedule que ira preencher a tabela para executar o dialout
 * 
 * */
@Stateless
public class TimerDialOutPrepare extends AbstractManagedBean{
	
	private static volatile boolean emExec = false;
	@EJB CampanhaService campanhaService;
	@EJB ControleDialOutService controleDialOutService;
	
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
	
    	LogMktCall.log(Level.INFO,"iniciando captura de msisdn para dialout");
		if (!TimerDialOutPrepare.mudaExec(true)) {
			LogMktCall.log(Level.ERROR, "Timer captura  in process!");
			return;
		}
		
		try{
			List<Campanha> campanhas = campanhaService.getCampanhabyProcessStatus(ProcessStatus.PREPARADO.getValue());
			
			for(Campanha campanha:campanhas){
				campanhaService.setCampanhaProcessStatus(campanha,ProcessStatus.PROCESSANDO.getValue());
				controleDialOutService.insertListOfMsisdnByCampanha(campanha);
				campanhaService.setCampanhaProcessStatus(campanha,ProcessStatus.DISCANDO.getValue());
			}
			
		}catch(Exception e){
			LogMktCall.log(Level.ERROR, e.getMessage());
		}
		finally {
			TimerDialOutPrepare.mudaExec(false);
			Logger.getLogger("Timer dialout End");
		}
	
	
	}

}
