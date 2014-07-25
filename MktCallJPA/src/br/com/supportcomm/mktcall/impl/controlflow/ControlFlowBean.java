package br.com.supportcomm.mktcall.impl.controlflow;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.ControlFlow;
import br.com.supportcomm.mktcall.util.GenerateCsv;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLTimeoutException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "ControlFlow", mappedName = "MktCallJPA-ControlFlow")
public class ControlFlowBean
        implements ControlFlowBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public ControlFlowBean() {
    }

    /**
     * @generated DT_ID=none
     */
    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public ControlFlow persistControlFlowBatch(ControlFlow controlFlow,long numIterate) {
    	
      
		 Timestamp intDate = new Timestamp(System.currentTimeMillis()); 
		 
		try {
			
/*			Session session = em.unwrap(Session.class);
			SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
			 connectionProvider = sessionFactoryImplementation.getConnectionProvider();
			Connection connection = connectionProvider.getConnection();
*/
			String sql= "insert into control_flow( id_campanha ,  id_cdr ,  callid,  msisdn ,  operation_date) values(?,?,?,?,?)";
			//PreparedStatement ps= connection.prepareStatement(sql);
			long count1=0;
			for(long count = 1; count<= numIterate;count++){

				em.createNativeQuery(sql)
				.setParameter(1, controlFlow.getIdCampanha())
				.setParameter(2, controlFlow.getIdCdr())
				.setParameter(3, controlFlow.getCallid())
				.setParameter(4, controlFlow.getMsisdn())
				.setParameter(5, controlFlow.getOperationDate()).executeUpdate();
				
/*					ps.setLong(1, controlFlow.getIdCampanha());
					ps.setLong(2,controlFlow.getIdCdr());
					ps.setString(3,controlFlow.getCallid());
					ps.setString(4,controlFlow.getMsisdn());
					ps.setTimestamp(5, controlFlow.getOperationDate());
					ps.addBatch();
					if(count1++>10){
						ps.executeBatch();
						connection.commit();
						 System.out.println("commit");
						count1=0;
					}
*/				
			}
			
/*			if (count1>0){
				ps.executeBatch();
				connection.commit();
				
			}
			
			ps.executeBatch();
			connection.commit();
*/			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} 
		Timestamp endDate = new Timestamp(System.currentTimeMillis());
	    Long elap = endDate.getTime() - intDate.getTime();
	    Logger.getLogger("Elap time: " + elap);
        return controlFlow;
    }

    /**
     * @generated DT_ID=none
     */
    public ControlFlow mergeControlFlow(ControlFlow controlFlow) {
        return em.merge(controlFlow);
    }

    public ControlFlow persistControlFlow(ControlFlow controlFlow) {
    	em.persist(controlFlow);
    	return controlFlow;
    }
    
    /**
     * @generated DT_ID=none
     */
    public void removeControlFlow(ControlFlow controlFlow) {
        controlFlow = em.find(ControlFlow.class, controlFlow.getIdControl());
        em.remove(controlFlow);
    }

    
   
   

    
    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<ControlFlow> getControlFlowAll() {
        return em.createNamedQuery("ControlFlow.All").getResultList();
    }

 
    public List<ControlFlow> getControlFlowAll(int minutes) {
    	
    	
        return em.createNamedQuery("ControlFlow.AllMinutes").setParameter("minutes", subtractMinutes(minutes)).getResultList();
    }

    
    

	@Override
	public ControlFlow getControlFlowByCallId(String CallId) {
		
		@SuppressWarnings("unchecked")
		List<ControlFlow> controFlows = em.createNamedQuery("ControlFlow.ByCallId").setParameter("callId", CallId).getResultList();
		if (!controFlows.isEmpty()){
			return controFlows.get(0);
		}
		return new ControlFlow();
		
	}

	private Timestamp subtractMinutes(int minutes)
	{
		Timestamp HourAgo = new Timestamp(System.currentTimeMillis() - ( minutes * 60 * 1000));
	    return HourAgo;
	}

	@Override
	public long getTotalReach(Campanha campanhaFinal) {
		em.createQuery("Update ControlFlow c  set  c.idCampanha = :idCampanha where c.idCampanha = :idCampanha")
        .setParameter("idCampanha", campanhaFinal.getIdCampanha()).executeUpdate();	
		
		long totalReach = (Long) em.createQuery("Select count(*) from ControlFlow c where c.idCampanha = :idCampanha")
				          .setParameter("idCampanha", campanhaFinal.getIdCampanha())
				          .getResultList().get(0);

		return totalReach;
	}

	
	public void executeControl(HttpServletRequest request,Campanha campanhaFinal){
		
		//EntityTransaction trans = em.getTransaction();
		//trans.begin();
		long totalReach= getTotalReach(campanhaFinal) + (campanhaFinal.getInsertionReachComplement()==null?0:campanhaFinal.getInsertionReachComplement());
		boolean jaAtingiuOMax = totalReach>= (campanhaFinal.getInsertionContracted() )?true:false ;
		if (jaAtingiuOMax ) {
			
			
			desativaCampanha(campanhaFinal);
			Logger.getLogger("Almir Atingiu o Max" + totalReach);
		}
		else {
			//campanhaFinal.setInsertionReach(campanhaFinal.getInsertionReach() + 1);
		}

		
		//campanhaService.incremetReachCampanha(campanhaFinal);
		//---------------------------------------------------------------------------------------
		// Aqui no final gerar controle de queda
		if (!jaAtingiuOMax){
			Logger.getLogger("Almir totalReach" + totalReach);
			Logger.getLogger("Almir Capanha ID" + campanhaFinal.getIdCampanha());
			setControlFlow(request,campanhaFinal);
		}
		//trans.commit();
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
			
			persistControlFlow(controlFlow);
			
		}	
		
		private void desativaCampanha(Campanha campanha){
	    	try {
	    		
				em.createQuery("update Campanha c set c.status = 0 where c.idCampanha= :campanha")
				   .setParameter("campanha", campanha.getIdCampanha()).executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

		public long getCount(String idCampanha) {
			@SuppressWarnings("unchecked")
			long controFlows = em.createQuery("select count(*) from ControFlow where idCampanha=:idCampanha").setParameter("idCampanha", Long.parseLong(idCampanha)).getMaxResults();
			return controFlows;
			
		}
		
		
}
