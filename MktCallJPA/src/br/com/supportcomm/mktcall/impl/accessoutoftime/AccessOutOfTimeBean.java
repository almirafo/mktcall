package br.com.supportcomm.mktcall.impl.accessoutoftime;

import br.com.supportcomm.mktcall.entity.AccessOutoftime;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "AccessOutOfTimeBean", mappedName = "MktCallJPA-AccessOutOfTimeBean")
public class AccessOutOfTimeBean
        implements AccessOutOfTimeBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public AccessOutOfTimeBean() {
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
    public AccessOutoftime persistAccessOutoftime(AccessOutoftime accessOutoftime) {
        em.persist(accessOutoftime);
        return accessOutoftime;
    }

    /**
     * @generated DT_ID=none
     */
    public AccessOutoftime mergeAccessOutoftime(AccessOutoftime accessOutoftime) {
        return em.merge(accessOutoftime);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeAccessOutoftime(AccessOutoftime accessOutoftime) {
        accessOutoftime = em.find(AccessOutoftime.class, accessOutoftime.getIdList());
        em.remove(accessOutoftime);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
	public List<AccessOutoftime> getAccessOutoftimeByMsisdn(String msisdn) {
        return em.createNamedQuery("AccessOutoftime.ByMsisdn").setParameter("msisdn", msisdn).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
	public List<AccessOutoftime> getAccessOutoftimeAll() {
        return em.createNamedQuery("AccessOutoftime.All").getResultList();
    }

}
