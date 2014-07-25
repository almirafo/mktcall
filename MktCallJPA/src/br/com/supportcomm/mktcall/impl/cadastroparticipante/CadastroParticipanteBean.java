package br.com.supportcomm.mktcall.impl.cadastroparticipante;

import br.com.supportcomm.mktcall.entity.CadastroParticipante;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "CadastroParticipanteBean", mappedName = "MktCallJPA-CadastroParticipanteBean")
public class CadastroParticipanteBean
        implements CadastroParticipanteBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public CadastroParticipanteBean() {
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
    public CadastroParticipante persistCadastroParticipante(CadastroParticipante cadastroParticipante) {
        em.persist(cadastroParticipante);
        return cadastroParticipante;
    }

    /**
     * @generated DT_ID=none
     */
    public CadastroParticipante mergeCadastroParticipante(CadastroParticipante cadastroParticipante) {
        return em.merge(cadastroParticipante);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeCadastroParticipante(CadastroParticipante cadastroParticipante) {
        cadastroParticipante = em.find(CadastroParticipante.class, cadastroParticipante.getIdCadastroParticipante());
        em.remove(cadastroParticipante);
    }

}
