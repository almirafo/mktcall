package br.com.suppportcomm.mktcall.impl.foward;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.supportcomm.mktcall.entity.Foward;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "FowardBean", mappedName = "MktCallJPA-FowardBean")
public class FowardBean
        implements FowardBeanLocal
{

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

	@Override
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Foward getFoward(Foward foward) {
		return em.find(Foward.class,foward.getIdFoward() );
		 
	}

	@Override
	public Foward getFowardByCampaingCode(String code) {
		Foward foward = (Foward) em.createQuery("select f from Foward f where c.idCampanha = :code").setParameter("code", code).getSingleResult();
		return foward;
	}

	@Override
	public Foward persistFoward(Foward foward) {
		em.persist(foward);
		return foward;
	}

	@Override
	public Foward removeFoward(Foward foward) {
		em.remove(foward);
		return foward;
	}

	@Override
	public Foward mergeFoward(Foward foward) {
		em.merge(foward);
		return foward;
	}

    
	
}
