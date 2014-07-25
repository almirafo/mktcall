package br.com.supportcomm.mktcall.impl.msisdncampanha;

import br.com.supportcomm.mktcall.entity.MsisdnCampanha;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "MsisdnCampanhaBean", mappedName = "MktCallJPA-MsisdnCampanhaBean")
public class MsisdnCampanhaBean
        implements MsisdnCampanhaBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public MsisdnCampanhaBean() {
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
    public MsisdnCampanha persistMsisdnCampanha(MsisdnCampanha msisdnCampanha) {
        em.persist(msisdnCampanha);
        return msisdnCampanha;
    }

    /**
     * @generated DT_ID=none
     */
    public MsisdnCampanha mergeMsisdnCampanha(MsisdnCampanha msisdnCampanha) {
        return em.merge(msisdnCampanha);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeMsisdnCampanha(MsisdnCampanha msisdnCampanha) {
        msisdnCampanha = em.find(MsisdnCampanha.class, msisdnCampanha.getIdMsisdnCampanha());
        em.remove(msisdnCampanha);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<MsisdnCampanha> getMsisdnCampanhaId(String msisdnId) {
        return em.createNamedQuery("MsisdnCampanha.id").setParameter("msisdnId", msisdnId).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<MsisdnCampanha> getMsisdnCampanhaIdCampanha(Long idCampanha) {
        return em.createNamedQuery("MsisdnCampanha.idCampanha").setParameter("idCampanha", idCampanha).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<MsisdnCampanha> getMsisdnCampanhaIdCampanhaMsisdn(Long idCampanha, String msisdn) {
        return em.createNamedQuery("MsisdnCampanha.idCampanha&msisdn").setParameter("idCampanha", idCampanha).setParameter("msisdn", msisdn).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<MsisdnCampanha> getMsisdnCampanhaMsisdn(String msisdn) {
        return em.createNamedQuery("MsisdnCampanha.msisdn").setParameter("msisdn", msisdn).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<MsisdnCampanha> getMsisdnCampanhaAdvertisement(String msisdn, String callid) {
    	
    	List<MsisdnCampanha> rs =em.createNamedQuery("MsisdnCampanha.advertisement").setParameter("msisdn", msisdn).setParameter("callid", callid).getResultList(); 
        return rs;
    }

}
