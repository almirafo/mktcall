package br.com.supportcomm.mktcall.impl.agencia;

import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.Campanha;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "AgenciaBean", mappedName = "MktCallJPA-AgenciaBean")
public class AgenciaBean
        implements AgenciaBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public AgenciaBean() {
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
    public Agencia persistAgencia(Agencia agencia) {
        em.persist(agencia);
        return agencia;
    }

    /**
     * @generated DT_ID=none
     */
    public Agencia mergeAgencia(Agencia agencia) {
        return em.merge(agencia);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeAgencia(Agencia agencia) {
        agencia = em.find(Agencia.class, agencia.getIdAgencia());
        em.remove(agencia);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
	public List<Agencia> getAgenciaAnunciante(Long idUserAccess) {
        return em.createNamedQuery("Agencia.Anunciante").setParameter("idUserAccess", idUserAccess).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Agencia> getAgenciaAll() {
        return em.createNamedQuery("Agencia.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Agencia> getAgenciaUsuario(Long idUserAccess) {
        return em.createNamedQuery("Agencia.Usuario").setParameter("idUserAccess", idUserAccess).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Agencia> getAgenciaId(Long id) {
        return em.createNamedQuery("Agencia.Id").setParameter("id", id).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Campanha> getAgenciaCampanha(Long idUserAccess) {
        return em.createNamedQuery("Agencia.Campanha").setParameter("idUserAccess", idUserAccess).getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Agencia> getAgenciaEmail(String email) {
        return em.createNamedQuery("Agencia.email").setParameter("email", email.toLowerCase()).getResultList();
    }

}
