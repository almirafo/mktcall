package br.com.supportcomm.mktcall.impl.controlflow;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.ControlFlow;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;


/**
 * @generated DT_ID=none
 */
@Local
public interface ControlFlowBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public ControlFlow persistControlFlow(ControlFlow controlFlow);
    
    public ControlFlow persistControlFlowBatch(ControlFlow controlFlow,long numIterate);

    /**
     * @generated DT_ID=none
     */
    public ControlFlow mergeControlFlow(ControlFlow controlFlow);

    /**
     * @generated DT_ID=none
     */
    public void removeControlFlow(ControlFlow controlFlow);

    


    /**
     * @generated DT_ID=none
     */
    public ControlFlow getControlFlowByCallId(String CallId);


    /**
     * @generated DT_ID=none
     */
    public List<ControlFlow> getControlFlowAll();

    public List<ControlFlow> getControlFlowAll(int minutes);

	long getTotalReach(Campanha campanhaFinal);
	public void executeControl(HttpServletRequest request,Campanha campanhaFinal);

	public long getCount(String idCampanha);
    

}
