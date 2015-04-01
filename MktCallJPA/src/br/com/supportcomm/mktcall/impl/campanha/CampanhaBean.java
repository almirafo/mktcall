package br.com.supportcomm.mktcall.impl.campanha;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;
import br.com.supportcomm.mktcall.vo.ListaCampanhaVO;
import br.com.supportcomm.mktcall.vo.ListaDuracaoVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeConsolidadaVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeVO;
import br.com.supportcomm.mktcall.vo.ListaInteracaoVO;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "CampanhaBean", mappedName = "MktCallJPA-CampanhaBean")
public class CampanhaBean
        implements CampanhaBeanLocal
{
	private Logger logger = Logger.getLogger(getClass().getName());
    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public CampanhaBean() {
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
    public Campanha persistCampanha(Campanha campanha) {
        em.persist(campanha);
        return campanha;
    }

    /**
     * @generated DT_ID=none
     */
    public Campanha mergeCampanha(Campanha campanha) {
        return em.merge(campanha);
    }

    public void desativaCampannha(Campanha campanha){
    	try {
    		
			em.createQuery("update Campanha c set c.status = 0 where c.idCampanha= :campanha")
			   .setParameter("campanha", campanha.getIdCampanha()).executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * @generated DT_ID=none
     */
    public Campanha incremetReachCampanha(Campanha campanha) {
    	try {
    		
			em.createQuery("update Campanha c set c.insertionReach=( select count(*) from ControlFlow f where f.idCampanha= :campanha) where idCampanha=:campanha")
			   .setParameter("campanha", campanha.getIdCampanha()).executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return campanha;
    }    
    
    
    /**
     * @generated DT_ID=none
     */
    public void removeCampanha(Campanha campanha) {
        campanha = em.find(Campanha.class, campanha.getIdCampanha());
        em.remove(campanha);
    }

    @SuppressWarnings("unchecked")
    public List<Campanha> getCampanhaAll() {
        return em.createNamedQuery("Campanha.All").getResultList();
    }

    
    
    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Campanha> getCampanhaAllNative() {
    	List<ListaCampanhaVO>  listCampanha = em.createNamedQuery("Campanha.AllNative").getResultList();
    	 List<Campanha> lc =new ArrayList<Campanha>();
    	 for(ListaCampanhaVO lcvo: listCampanha){
    		 Campanha c = new Campanha();
    		 Spot spot = new Spot();
    		 c.setCallDuration(lcvo.getCallDuration());
    		 c.setDescription(lcvo.getDescription());
    		 c.setEndDatetime(lcvo.getEndDatetime());
    		 c.setIdCampanha(lcvo.getIdCampanha());
    		 c.setInsertionContracted(lcvo.getInsertionContracted());
    		 c.setInsertionReach(lcvo.getInsertionReach());
    		 c.setName(lcvo.getName());
    		 c.setNextCampaign(lcvo.getNextCampaign());
    		 c.setOnce(lcvo.isOnce());
    		 c.setOperationDate(lcvo.getOperationDate());
    		 c.setPriority(lcvo.getPriority());
    		 c.setStartDatatime(lcvo.getStartDatatime());
    		 c.setStatus(lcvo.getStatus());
    		 spot.setIdSpot(lcvo.getIdSpot());
    		 c.setSpot(spot);
    		 
    		 lc.add(c);
    		 
    	 }
    	 
        return lc;//em.createNamedQuery("Campanha.AllNative").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Campanha> getCampanhaByMsisdn(String msisdn) {
    	List<ListaCampanhaVO>  listCampanha = em.createNamedQuery("Campanha.ByMsisdn").setParameter("msisdn", msisdn).getResultList();
    	 List<Campanha> lc = new ArrayList<Campanha>();
    	 for(ListaCampanhaVO lcvo: listCampanha){
    		 Campanha c = new Campanha();
    		 Spot spot = new Spot();
    		 ListDetail ld = new ListDetail();
    		 c.setCallDuration(lcvo.getCallDuration());
    		 c.setDescription(lcvo.getDescription());
    		 c.setEndDatetime(lcvo.getEndDatetime());
    		 c.setIdCampanha(lcvo.getIdCampanha());
    		 c.setInsertionContracted(lcvo.getInsertionContracted());
    		 c.setInsertionReach(lcvo.getInsertionReach());
    		 c.setName(lcvo.getName());
    		 c.setNextCampaign(lcvo.getNextCampaign());
    		 c.setOnce(lcvo.isOnce());
    		 c.setOperationDate(lcvo.getOperationDate());
    		 c.setPriority(lcvo.getPriority());
    		 c.setStartDatatime(lcvo.getStartDatatime());
    		 c.setStatus(lcvo.getStatus());
    		 spot.setIdSpot(lcvo.getIdSpot());
    		 c.setSpot(spot);
    		 ld.setIdList(lcvo.getIdList());
    		 c.setListDetail(ld);
    		 lc.add(c);   		 
    	 }
    	 
        return lc; //em.createNamedQuery("Campanha.ByMsisdn").getResultList();
    }    

    
    public List<ListaCampanhaDialOutVO> getCampanhaDialOutAll(){
    	return em.createNamedQuery("Campanha.listaCampanhaDialOut").getResultList();
    }
    
    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Campanha> getCampanhaId(Long idCampanha) {
        return em.createQuery("select c from Campanha c where c.idCampanha = :idcampanha").setParameter("idcampanha", idCampanha).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Campanha> getCampanhaByUsuario(Long idUserAccess) {
        return em.createNamedQuery("Campanha.byUsuario").setParameter("idUserAccess", idUserAccess).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Campanha> getCampanhaSpot(Long idSpot) {
        return em.createNamedQuery("Campanha.spot").setParameter("idSpot", idSpot).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<ListaInteracaoVO> getListaInteracao(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal) {
    	
    	Query q =   em.createNamedQuery("Campanha.interacao");
    			q.setParameter("dataini", dataInicial).
    			setParameter("datafim", dataFinal).
    			setParameter("idCampanha", idCampanha);	
    	return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ListaDuracaoVO>   getListaDuracao(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal){
    	Query q =   em.createNamedQuery("Campanha.duracao");
		q.setParameter("dataini", dataInicial).
		setParameter("datafim", dataFinal).
		setParameter("idCampanha", idCampanha);	
		return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<ListaEfetividadeVO>   getListaEfetividade(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal){
    	Query q =   em.createNamedQuery("Campanha.efetividade");
		q.setParameter("dataInicio", dataInicial).
		setParameter("dataFim", dataFinal).
		setParameter("idCampanha", idCampanha);	
		return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<ListaEfetividadeConsolidadaVO>   getListaEfetividadeConsolidada(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal){
    	Query q =   em.createNamedQuery("Campanha.efetividadeConsolidada");
		q.setParameter("dataInicio", dataInicial).
		setParameter("dataFim", dataFinal).
		setParameter("idCampanha", idCampanha);	
		return q.getResultList();
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Campanha> getCampanhaAllNotRegional(){

    	List<ListaCampanhaVO>  listCampanha = em.createNamedQuery("Campanha.AllNativeNotRegional").getResultList();
    	 List<Campanha> lc =new ArrayList<Campanha>();
    	 for(ListaCampanhaVO lcvo: listCampanha){
    		 Campanha c = new Campanha();
    		 Spot spot = new Spot();
    		 c.setCallDuration(lcvo.getCallDuration());
    		 c.setDescription(lcvo.getDescription());
    		 c.setEndDatetime(lcvo.getEndDatetime());
    		 c.setIdCampanha(lcvo.getIdCampanha());
    		 c.setInsertionContracted(lcvo.getInsertionContracted());
    		 c.setInsertionReach(lcvo.getInsertionReach());
    		 c.setName(lcvo.getName());
    		 c.setNextCampaign(lcvo.getNextCampaign());
    		 c.setOnce(lcvo.isOnce());
    		 c.setOperationDate(lcvo.getOperationDate());
    		 c.setPriority(lcvo.getPriority());
    		 c.setStartDatatime(lcvo.getStartDatatime());
    		 c.setStatus(lcvo.getStatus());
    		 spot.setIdSpot(lcvo.getIdSpot());
    		 c.setSpot(spot);
    		 
    		 lc.add(c);
    		 
    	 }
    	 
        return lc;//em.createNamedQuery("Campanha.AllNative").getResultList();
    
    	
    	
    }

	@Override
	public List<Campanha> getCampanhabyProcessStatus(String processStatus) {
		@SuppressWarnings("unchecked")
		List<Campanha> campanhas = em.createQuery("Select c from Campanha c where c.processStatus= :processStatus")
				                           .setParameter("processStatus", processStatus)
				                           .getResultList();
		return campanhas;
	}
   
	@Override
	public void setCampanhaProcessStatus(Campanha campanha, String processStatus){
		em.createQuery("update Campanha set processStatus = :processStatus where idCampanha = :idCampanha")
		.setParameter("processStatus", processStatus)
		.setParameter("idCampanha", campanha.getIdCampanha())
		.executeUpdate();
		
	}
	
	
	@SuppressWarnings("finally")
	public Campanha verificaSeTemCampanha(Long idList){
		Campanha campanhas =  new Campanha();
		try{
		 campanhas = (Campanha) em.createQuery("select c from Campanha c where c.listDetail.idList= :idList1")
                .setParameter("idList1", idList)
                .getResultList().get(0);
		}catch(Exception e){
			logger.warning("Ops nao tem campanha");
		}finally{
			return campanhas;
		}
	}
}
