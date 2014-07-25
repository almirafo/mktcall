package br.com.supportcomm.mktcall.history;

import br.com.supportcomm.mktcall.entity.UserHistory;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;

@Stateless(name = "UserHistoryBean", mappedName = "MktCallJPA-UserHistoryBean")
public class UserHistoryBean
        implements UserHistoryBeanLocal
{

    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    public UserHistoryBean() {
    }

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

    public UserHistory persistUserHistory(UserHistory userHistory) {
        em.persist(userHistory);
        return userHistory;
    }

    public UserHistory mergeUserHistory(UserHistory userHistory) {
        return em.merge(userHistory);
    }

    public void removeUserHistory(UserHistory userHistory) {
        userHistory = em.find(UserHistory.class, userHistory.getId());
        em.remove(userHistory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserHistory> getUserHistoryAll() {
        return em.createNamedQuery("UserHistory.All").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserHistory> getUserHistoryIdUser(Long idUserAccess) {
        return em.createNamedQuery("UserHistory.idUser").setParameter("idUserAccess", idUserAccess).getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<UserHistory> getUserHistoryByUserIdAndRange(Long idUserAccess, Timestamp startDate, Timestamp endDate) {
        return em.createNamedQuery("UserHistory.byUserIdAndRange").setParameter("idUserAccess", idUserAccess)
        		.setParameter("dataini", startDate).setParameter("datafim", endDate).getResultList();
    } 
    
    @Override
    @SuppressWarnings("unchecked")
    public List<UserHistory> getUserHistoryByLoginAndRange(String login, Timestamp startDate, Timestamp endDate) {
        return em.createNamedQuery("UserHistory.byUserIdAndRange").setParameter("login", login)
        		.setParameter("dataini", startDate).setParameter("datafim", endDate).getResultList();
    }    

    @Override
    @SuppressWarnings("unchecked")
    public List<UserHistory> getUserHistoryIdCampanha(Long idCampanha) {
        return em.createNamedQuery("UserHistory.idCampanha").setParameter("idCampanha", idCampanha).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserHistory> getUserHistoryIdSpot(Long idSpot) {
        return em.createNamedQuery("UserHistory.idSpot").setParameter("idSpot", idSpot).getResultList();
    }

}
