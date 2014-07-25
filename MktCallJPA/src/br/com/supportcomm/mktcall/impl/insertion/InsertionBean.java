package br.com.supportcomm.mktcall.impl.insertion;

import java.sql.Timestamp;
import java.util.List;
//import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.supportcomm.mktcall.entity.Insertion;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "InsertionBean", mappedName = "MktCallJPA-InsertionBean")
public class InsertionBean
        implements InsertionBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;
    
	//private Logger logger = Logger.getLogger(getClass().getName());    

    /**
     * @generated DT_ID=none
     */
    public InsertionBean() {
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
    public Insertion persistInsertion(Insertion insertion) {
    	
        em.persist(insertion);

        return insertion;
    }

    /**
     * @generated DT_ID=none
     */
    public Insertion mergeInsertion(Insertion insertion) {
        return em.merge(insertion);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeInsertion(Insertion insertion) {
        insertion = em.find(Insertion.class, insertion.getIdInsertion());
        em.remove(insertion);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Insertion> getInsertionAll() {
        return em.createNamedQuery("Insertion.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Insertion> getInsertionId(Long idInsertion) {
        return em.createNamedQuery("Insertion.Id").setParameter("idInsertion", idInsertion).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Insertion> getInsertionCampanha(Long idCampanha) {
    	Timestamp intDate = new Timestamp(System.currentTimeMillis());
		List<Insertion> rs = em.createNamedQuery("Insertion.Campanha").setParameter("idCampanha", idCampanha).getResultList();
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		
		Long elap = endTime.getTime() - intDate.getTime();
	//	logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
        
		return rs;
    }

    /**
     * @generated DT_ID=none
     */
    public void removeInsertionByIdCampanha(Long idCampanha) {
    	 List<Insertion> insertion = getInsertionCampanha(idCampanha);
    	
    	 
         for (Insertion insertionRemove : insertion) {
        	 insertionRemove = em.find(Insertion.class, insertionRemove.getIdInsertion());
        	// em.remove(insertionRemove);
        	 em.createQuery("Delete from Insertion c where c.idInsertion = :idIsertion").setParameter("idIsertion", insertionRemove.getIdInsertion()).executeUpdate();
		} 
         
   
    }

}
