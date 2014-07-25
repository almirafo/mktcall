package br.com.supportcomm.mktcall.impl.user;

import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.entity.UserType;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "UserBean", mappedName = "MktCallJPA-UserBean")
public class UserBean
        implements UserBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public UserBean() {
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
    public UserAccess persistUserAccess(UserAccess userAccess) {
        em.persist(userAccess);
        return userAccess;
    }

    /**
     * @generated DT_ID=none
     */
    public UserAccess mergeUserAccess(UserAccess userAccess) {
        return em.merge(userAccess);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeUserAccess(UserAccess userAccess) {
        userAccess = em.find(UserAccess.class, userAccess.getIdUserAccess());
        em.remove(userAccess);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<UserAccess> getUserAccessLogin(String login) {
        return em.createNamedQuery("UserAccess.Login").setParameter("login", login).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<UserAccess> getUserAccessLoginPass(String login, String senha) {
        return em.createNamedQuery("UserAccess.LoginPass").setParameter("login", login).setParameter("senha", senha).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<UserAccess> getUserAccessUsuario(Long idUsuario) {
        return em.createNamedQuery("UserAccess.Usuario").setParameter("idUsuario", idUsuario).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<UserAccess> getUserAccessAll() {
        return em.createNamedQuery("UserAccess.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<UserAccess> getUserAccessById(Long id) {
        return em.createNamedQuery("UserAccess.ById").setParameter("id", id).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public UserType persistUserType(UserType userType) {
        em.persist(userType);
        return userType;
    }

    /**
     * @generated DT_ID=none
     */
    public UserType mergeUserType(UserType userType) {
        return em.merge(userType);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeUserType(UserType userType) {
        userType = em.find(UserType.class, userType.getIdUserType());
        em.remove(userType);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<UserType> getUserTypeAll() {
        return em.createNamedQuery("UserType.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<UserType> getUserTypeId(Long idUserType) {
        return em.createNamedQuery("UserType.Id").setParameter("idUserType", idUserType).getResultList();
    }

}
