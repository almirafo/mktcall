package br.com.supportcomm.mktcall.impl.statistic;

import br.com.supportcomm.mktcall.entity.Statistic;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "StatisticBean", mappedName = "MktCallJPA-StatisticBean")
public class StatisticBean
        implements StatisticBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public StatisticBean() {
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
    public Statistic persistStatistic(Statistic statistic) {
        em.persist(statistic);
        return statistic;
    }

    /**
     * @generated DT_ID=none
     */
    public Statistic mergeStatistic(Statistic statistic) {
        return em.merge(statistic);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeStatistic(Statistic statistic) {
        statistic = em.find(Statistic.class, statistic.getIdStat());
        em.remove(statistic);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Statistic> getStatisticByIdCampanha(Long idCampanha) {
        return em.createNamedQuery("Statistic.byIdCampanha").setParameter("idCampanha", idCampanha).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Statistic> getStatisticByIdMsisdn(String msisdn) {
        return em.createNamedQuery("Statistic.byIdMsisdn").setParameter("msisdn", msisdn).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Statistic> getStatisticByListenComplete(Boolean listenComplete) {
        return em.createNamedQuery("Statistic.byListenComplete").setParameter("listenComplete", listenComplete).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Statistic> getStatisticByIdCdr(Long idCdr) {
        return em.createNamedQuery("Statistic.byIdCdr").setParameter("idCdr", idCdr).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Statistic> getStatisticByEfetividade(Boolean listenComplete, java.sql.Timestamp startDate, java.sql.Timestamp endDate, Long idCampanha) {
        return em.createNamedQuery("Statistic.byEfetividade").setParameter("listenComplete", listenComplete).setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("idCampanha", idCampanha).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Statistic> getStatisticByEfetividadeUnicos(Boolean listenComplete, java.sql.Timestamp startDate, java.sql.Timestamp endDate, Long idCampanha) {
        return em.createNamedQuery("Statistic.byEfetividadeUnicos").setParameter("listenComplete", listenComplete).setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("idCampanha", idCampanha).getResultList();
    }

}
