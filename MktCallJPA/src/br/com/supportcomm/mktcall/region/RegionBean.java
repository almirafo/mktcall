package br.com.supportcomm.mktcall.region;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Region;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "RegionBean", mappedName = "MktCallJPA-RegionBean")
public class RegionBean
        implements RegionBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public RegionBean() {
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
    public Region persistRegion(Region region) {
        em.persist(region);
        return region;
    }

    /**
     * @generated DT_ID=none
     */
    public Region mergeRegion(Region region) {
        return em.merge(region);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeRegion(Region region) {
        region = em.find(Region.class, region.getIdRegion());
        em.remove(region);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Region> getRegionByAreaCode(AreaCode areaCode) {
    	List<Region> rs =  em.createNamedQuery("Region.getByAreaCode").setParameter("areaCode", areaCode).getResultList();
        return rs;
    }

    
    @SuppressWarnings("unchecked")
	public List<Region> getRegionByCampanha(Campanha campanha){
    	return em.createNamedQuery("Region.getByCampanha").setParameter("idCampanha", campanha.getIdCampanha()).getResultList();
    }
    
    
}
