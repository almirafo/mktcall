package br.com.supportcomm.mktcall.impl.listageral;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
//import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.supportcomm.mktcall.entity.ListaGeral;
import br.com.supportcomm.mktcall.vo.ListaFidelizadaVO;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "ListaGeralBean", mappedName = "MktCallJPA-ListaGeralBean")
public class ListaGeralBean
        implements ListaGeralBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;
    
   // private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * @generated DT_ID=none
     */
    public ListaGeralBean() {
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
    public ListaGeral persistListaGeral(ListaGeral listaGeral) {
        em.persist(listaGeral);
        return listaGeral;
    }

    /**
     * @generated DT_ID=none
     */
    public ListaGeral mergeListaGeral(ListaGeral listaGeral) {
        return em.merge(listaGeral);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeListaGeral(ListaGeral listaGeral) {
        listaGeral = em.find(ListaGeral.class, listaGeral.getIdList());
        em.remove(listaGeral);
    }
    
    public void removeListaGeralByIdCampanha(Long idCampanha){
    	em.createNamedQuery("ListaGeral.RemoveListByIdCampanha").setParameter("idCampanha", idCampanha).executeUpdate();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<ListaGeral> getListaGeralExistMsisdn(String msisdn) {
        return em.createNamedQuery("ListaGeral.existMsisdn").setParameter("msisdn", msisdn).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<ListaGeral> getListaGeralByMsisdn(String msisdn) {
    	Timestamp intDate = new Timestamp(System.currentTimeMillis());
		
    	List<Long> ListcampanhaId = em.createNamedQuery("ListaGeral.ByMsisdn").setParameter("msisdn", msisdn).getResultList();
    	List<ListaGeral> lg =new ArrayList<ListaGeral>();
    	
    	for (Long campanhaId:ListcampanhaId ){
    		ListaGeral l = new ListaGeral();
    		l.setIdCampanha(campanhaId);
    		lg.add(l);
    	}
    	Timestamp endDate = new Timestamp(System.currentTimeMillis());
		Long elap = endDate.getTime() - intDate.getTime();

		//logger.debug("Elaptime "+ this.getClass().getName() +"getListaGeralByMsisdn -------->" + elap);
    	
        return lg;//em.createNamedQuery("ListaGeral.ByMsisdn").setParameter("msisdn", msisdn).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<ListaGeral> getListaGeralAll() {
        return em.createNamedQuery("ListaGeral.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<ListaGeral> getListaGeralCampanha(Long idCampanha) {
        return em.createNamedQuery("ListaGeral.Campanha").setParameter("idCampanha", idCampanha).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<ListaFidelizadaVO> getListaGeralCustomizada() {
    	return  em.createNamedQuery("ListaGeral.ListaCustomizada").getResultList();
    }

}
