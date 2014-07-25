package br.com.supportcomm.mktcall.impl.estimulo;

import br.com.supportcomm.mktcall.entity.Estimulo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "EstimuloBean", mappedName = "MktCallJPA-EstimuloBean")
public class EstimuloBean
        implements EstimuloBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public EstimuloBean() {
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
    public Estimulo persistEstimulo(Estimulo estimulo) {
        em.persist(estimulo);
        return estimulo;
    }

    /**
     * @generated DT_ID=none
     */
    public Estimulo mergeEstimulo(Estimulo estimulo) {
        return em.merge(estimulo);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeEstimulo(Estimulo estimulo) {
        estimulo = em.find(Estimulo.class, estimulo.getIdEstimulo());
        em.remove(estimulo);
    }

}
