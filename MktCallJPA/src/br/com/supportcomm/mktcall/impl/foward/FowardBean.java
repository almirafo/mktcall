package br.com.supportcomm.mktcall.impl.foward;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.supportcomm.mktcall.entity.Foward;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "Foward", mappedName = "MktCallJPA-Foward")
public class FowardBean
        implements FowardBeanLocal
{

	
	private Logger logger = Logger.getLogger(getClass().getName());
	
    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public FowardBean() {
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
    public Foward mergeFoward(Foward foward) {
        return em.merge(foward);
    }

    public Foward persistFoward(Foward foward) {
    	em.persist(foward);
    	return foward;
    }
    
    /**
     * @generated DT_ID=none
     */
    public void removeFoward(Foward foward) {
        foward = em.find(Foward.class, foward.getIdFoward());
        em.remove(foward);
    }

	@Override
	public void removeFowardByCampanha(Long idCampanha) {

		try {
			em.createQuery(
					"delete from Foward f where f.idCampanha = :idCampanha").setParameter("idCampanha", idCampanha)
					.executeUpdate();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erro ao remover dados MDivulga");
		}
		
	}

	@SuppressWarnings("finally")
	@Override
	public Foward getFowardByCampanha(Long idCampanha) {
		Foward foward=new Foward();
		try {
			foward = (Foward) em.createQuery("select f from Foward f where f.idCampanha = :idCampanha").setParameter("idCampanha", idCampanha).getSingleResult();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}finally{
			return foward==null? new Foward(): foward;
		}
	}

	

	

		
		
		
}
