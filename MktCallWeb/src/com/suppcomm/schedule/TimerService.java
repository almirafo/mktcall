package com.suppcomm.schedule;


import java.util.Scanner;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.log.LogMktCall;
import br.com.supportcomm.mktcall.service.listdetail.ListDetailService;
import br.com.supportcomm.mktcall.service.listsegment.ListSegmentService;
import br.com.supportcomm.mktcall.tools.FilesUtil;
import br.com.supportcomm.mktcall.tools.ReadCSV;
import br.com.supportcomm.mktcall.managedbean.AbstractManagedBean;



@Stateless
public class TimerService extends AbstractManagedBean{

	private static volatile boolean emExec = false;
	
	@EJB private ListSegmentService listSegmentService;
	@EJB private ListDetailService listDetailService;
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

    	
		if (!TimerService.mudaExec(true)) {
			LogMktCall.log(Level.ERROR, "Timer List Segment in process!");
			System.out.println("Timer List Segment in process!!");
			return;
		}
		
		try {
			LogMktCall.log(Level.DEBUG, "Timer List Segment Ini");
			   String path =  "/users/portais/4040/Arquivos/";//super.getMessage("localArquivoSegmentado");
			   ListDetail listDetail = listDetailService.findListDetailProcessing();
			   
			   if(listDetail==null){
				   return;
			   }
			   ReadCSV readCSV = new ReadCSV() ;
			   readCSV.readFile(path,listDetail.getFilename());
			 
				Scanner scanner = readCSV.getList();
			
			
			    listSegmentService.listSegmentationProcess(scanner, listDetail.getIdList(),path);
	
			    listDetail.setListActive(true);
				listDetail.setListStatus("0");
				listDetailService.mergeListDetail(listDetail);
				scanner.close();
				readCSV=null;
				FilesUtil filesUtil = new FilesUtil();
				filesUtil.moveFile(path.concat(listDetail.getFilename()), path.concat("Processados/").concat(listDetail.getFilename()));
				
		 	
		}
		catch (Exception e) {
			e.printStackTrace();
			LogMktCall.log(Level.ERROR, "Exceção no Job " + this.getClass().getName(), e); 
		}
		finally {
			TimerService.mudaExec(false);
			Logger.getLogger("Timer List Segment End");
		}
	
    }
}