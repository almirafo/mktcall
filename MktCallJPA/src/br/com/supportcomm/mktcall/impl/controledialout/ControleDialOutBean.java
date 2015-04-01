package br.com.supportcomm.mktcall.impl.controledialout;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.enums.ProcessStatus;
import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "ControleDialOutBean", mappedName = "MktCallJPA-ControleDialOutBean")
public class ControleDialOutBean
        implements ControleDialOutBeanLocal
{

   
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    public ControleDialOutBean() {
    }

        
 
    /**
     * @generated DT_ID=none
     */
    public List<ListDetail> getListDetailByMsisdn(String msisdn) {
    	return null;
    }

    

    


	@Override
	public void ativarDialOut(long idCampanha) {
		em.createQuery("update Campanha set processStatus = '3' where idCampanha = :idCampanha")
		.setParameter("idCampanha", idCampanha).executeUpdate();
		
	}

	@Override
	public void desativarDialOut(long idCampanha) {
		em.createQuery("update Campanha set processStatus = '0' where idCampanha = :idCampanha")
		.setParameter("idCampanha", idCampanha).executeUpdate();
		
	}

	@Override
	public List<ListaCampanhaDialOutVO> getControleDialOutAll() {
		return em.createNamedQuery("Campanha.listaCampanhaDialOut").getResultList();
	}



	@Override
	public void insertListOfMsisdnByCampanha(Campanha campanha) {
/*		String sqlString = "insert into dialing " + 
	   "(msisdn_dialing,attempts,datetime_inserted,datetime_last_operation,datetime_schedule,response_code,response_message,status,action,id_list,id_campanha) " + 
	   "select ls.msisdn , "+
       "0 attempts, "+
       "now() datetime_inserted, "+
       "now() datetime_last_operation, "+
       "now() datetime_schedule, "+
       "'' response_code, "+
       "'' response_message, "+
       "0 status, "+
       "'DIAL' , "+
       " ls.id_list , " +
       " c.id_campanha " +
       "			from list_segmentation ls  "+
       "       inner join list_detail ld on (ls.id_list = ld.id_list) "+
       "       inner join campanha c on(ld.id_list = c.id_list)" +
       " where c.id_campanha = :idcampanha and c.process_status= :processStatus ";
*/		
		
		String sqlString = "insert into dialing " + 
				   "(msisdn_dialing,attempts,datetime_inserted,datetime_last_operation,datetime_schedule,response_code,response_message,status,action,id_list,id_campanha) " + 
				   "select ls.msisdn , "+
			       "0 attempts, "+
			       "now() datetime_inserted, "+
			       "now() datetime_last_operation, "+
			       "now() datetime_schedule, "+
			       "'' response_code, "+
			       "'' response_message, "+
			       " 0 status, "+
			       " 'DIAL'  , "+
			       " ls.id_list , " +
			       " c.id_campanha " +
			       "			from list_segmentation ls  "+
			       "       inner join list_detail ld on (ls.id_list = ld.id_list) "+
			       "       inner join campanha c on(ld.id_list = c.id_list)" +
			       " where c.id_campanha = :idcampanha and c.process_status= :processStatus  and not exists (select * from blacklist where msisdn = ls.msisdn) ";
		
		
		
		
		
		em.createNativeQuery(sqlString)
		   .setParameter("idcampanha", campanha.getIdCampanha())
		   .setParameter("processStatus", ProcessStatus.PROCESSANDO.getValue())
		   .executeUpdate();
		
	}
    
	

}
