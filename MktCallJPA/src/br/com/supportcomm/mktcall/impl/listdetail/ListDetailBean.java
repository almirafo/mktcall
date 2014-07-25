package br.com.supportcomm.mktcall.impl.listdetail;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.supportcomm.mktcall.entity.ListDetail;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "ListDetailBean", mappedName = "MktCallJPA-ListDetailBean")
public class ListDetailBean
        implements ListDetailBeanLocal
{

   
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    public ListDetailBean() {
    }

    public ListDetail persistListDetail(ListDetail listDetail) {
        em.persist(listDetail);
        return listDetail;
    }


    public void mergeListDetail(ListDetail listDetail){
    	// listDetail = em.find(ListDetail.class, listDetail.getIdList());
    	 em.merge(listDetail);
    }
    
    
    public void removeListDetail(ListDetail listDetail) {
        listDetail = em.find(ListDetail.class, listDetail.getIdList());
        em.remove(listDetail);
    }
    
 
    /**
     * @generated DT_ID=none
     */
    public List<ListDetail> getListDetailByMsisdn(String msisdn) {
    	return null;
    }

    @SuppressWarnings("unchecked")
    public List<ListDetail> getListDetailAll() {
        return em.createNamedQuery("ListDetail.All").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ListDetail> getListDetailById(Long idListDetail) {
        return em.createNamedQuery("ListDetail.ById").setParameter("idListDetail", idListDetail).getResultList();
    }    

    
    @SuppressWarnings("unchecked")
    public List<ListDetail> getListDetailCustomizada() {
    	return  em.createNamedQuery("ListDetail.ListaCustomizada").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
	public List<ListDetail> getListDetailCampanha(Long idCampanha) {
    	return em.createNamedQuery("ListDetail.ByIdCampanha").setParameter("idCampanha", idCampanha).getResultList();	
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public ListDetail findListDeailBySegmentName(String segmentName) {
    	List<ListDetail> rs = em.createNamedQuery("ListDetail.findListDeailBySegmentName").setParameter("segmentName", segmentName)
			.getResultList();
	    if (rs.isEmpty())
		    return null;
	 
	    ListDetail listDetail = rs.get(0);
	 
		return listDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ListDetail findListDetailProcessing() {
    	List<ListDetail> rs = em.createNamedQuery("ListDetail.Processing").getResultList();
	    if (rs.isEmpty())
		    return null;
	 
	    ListDetail listDetail = rs.get(0);
	 
		return listDetail;
	}
    

}
