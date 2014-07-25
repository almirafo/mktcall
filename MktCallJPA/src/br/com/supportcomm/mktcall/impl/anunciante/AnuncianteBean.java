package br.com.supportcomm.mktcall.impl.anunciante;

import br.com.supportcomm.mktcall.entity.Anunciante;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "AnuncianteBean", mappedName = "MktCallJPA-AnuncianteBean")
public class AnuncianteBean
        implements AnuncianteBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public AnuncianteBean() {
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
    public Anunciante persistAnunciante(Anunciante anunciante) {
        em.persist(anunciante);
        return anunciante;
    }

    /**
     * @generated DT_ID=none
     */
    public Anunciante mergeAnunciante(Anunciante anunciante) {
        return em.merge(anunciante);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeAnunciante(Anunciante anunciante) {
        anunciante = em.find(Anunciante.class, anunciante.getIdAnunciante());
        em.remove(anunciante);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Anunciante> getAnuncianteAgencia(Long idAgencia) {
        return em.createNamedQuery("Anunciante.Agencia").setParameter("idAgencia", idAgencia).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Anunciante> getAnuncianteUserAccessAgencia(Long idUserAccess) {
        return em.createNamedQuery("Anunciante.UserAccessAgencia").setParameter("idUserAccess", idUserAccess).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Anunciante> getAnuncianteAll() {
        return em.createNamedQuery("Anunciante.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Anunciante> getAnuncianteId(Long id) {
        return em.createNamedQuery("Anunciante.Id").setParameter("id", id).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Anunciante> getAnuncianteEmail(String email) {
        return em.createNamedQuery("Anunciante.email").setParameter("email", email.toLowerCase()).getResultList();
    }
}
