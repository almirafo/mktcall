package br.com.supportcomm.mktcall.service.controlflow;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.ControlFlow;
import br.com.supportcomm.mktcall.impl.controlflow.ControlFlowBeanLocal;

/**
 * Session Bean implementation class ControlFlowService
 */
@Stateless
@LocalBean
public class ControlFlowService  {

	@EJB private ControlFlowBeanLocal controlFlowBean;
	
    /**
     * Default constructor. 
     */
    public ControlFlowService() {
       
    }

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		
		return controlFlowBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public ControlFlow persistControlFlow(ControlFlow controlFlow) {
		
		return controlFlowBean.persistControlFlow(controlFlow);
	}

	
	 public ControlFlow persistControlFlowBatch(ControlFlow controlFlow,long numIterate){
		 return  controlFlowBean.persistControlFlowBatch( controlFlow, numIterate);
	 }
	
	public ControlFlow mergeControlFlow(ControlFlow controlFlow) {
		
		return controlFlowBean.mergeControlFlow(controlFlow);
	}

	
	public void removeControlFlow(ControlFlow controlFlow) {
		
		controlFlowBean.removeControlFlow(controlFlow);
		
	}

	
	public ControlFlow getControlFlowByCallId(String CallId) {
		
		return controlFlowBean.getControlFlowByCallId(CallId);
	}
	

	
	public List<ControlFlow> getControlFlowAll() {
		
		return controlFlowBean.getControlFlowAll();
	}


	public List<ControlFlow> getControlFlowAll(int minutes) {
		return controlFlowBean.getControlFlowAll(minutes);
	}


	public long getTotalReach(Campanha campanhaFinal) {
		// TODO Auto-generated method stub
		return controlFlowBean.getTotalReach( campanhaFinal);
	}

	public void executeControl(HttpServletRequest request,Campanha campanhaFinal){
		 controlFlowBean.executeControl( request, campanhaFinal);
	}


	public long getCount(String idCampanha) {
		// TODO Auto-generated method stub
		return controlFlowBean.getCount( idCampanha);
	}
	
}
