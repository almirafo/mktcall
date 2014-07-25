package br.com.supportcomm.mktcall.impl.listsegment;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.hibernate.FlushMode;

import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.entity.ListSegmentation;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "ListSegmentationBean", mappedName = "MktCallJPA-ListSegmentationBean")
public class ListSegmentationBean
        implements ListSegmentationBeanLocal
{

   
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    public ListSegmentationBean() {
    }



    public ListSegmentation persistListSegmentation(ListSegmentation listSegmentation) {
    	 FlushModeType flushModeType = FlushModeType.AUTO;
    	em.setFlushMode(FlushModeType.AUTO);
        em.persist(listSegmentation);
        return listSegmentation;
    }


    public void mergeListSegmentation(ListSegmentation listSegmentation){
    	em.merge(listSegmentation);
    }
    
    
    public void removeListSegmentation(ListSegmentation listSegmentation) {
        listSegmentation = em.find(ListSegmentation.class, listSegmentation.getId());
        em.remove(listSegmentation);
    }
    

    public void removeListSegmentationByListDetail(ListSegmentation listSegmentation) {
    	 em.createNamedQuery("ListSegmentation.RemoveByListDetail").setParameter("idListDetail", listSegmentation.getId().getIdList()).executeUpdate();
    }

    
    
    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<ListSegmentation> getListSegmentationByMsisdn(String msisdn) {
    	return null;
    }

    @SuppressWarnings("unchecked")
    public List<ListSegmentation> getListSegmentationAll() {
        return em.createNamedQuery("ListSegmentation.All").getResultList();
    }

    
    @SuppressWarnings("unchecked")
    public List<ListSegmentation> getListSegmentationCustomizada() {
    	return  em.createNamedQuery("ListSegmentation.ListaCustomizada").getResultList();
    }

}
