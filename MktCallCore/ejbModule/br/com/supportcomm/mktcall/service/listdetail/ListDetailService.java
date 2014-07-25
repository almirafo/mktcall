package br.com.supportcomm.mktcall.service.listdetail;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.impl.listdetail.ListDetailBeanLocal;

/**
 * Session Bean implementation class AgenciaService
 */
@Stateless
@LocalBean
public class ListDetailService {

	@EJB private ListDetailBeanLocal listDetailBean;
	
    /**
     * Default constructor. 
     */
    public ListDetailService() {
        // TODO Auto-generated constructor stub
    }

    public List<ListDetail> getListDetailAll(){
    	return listDetailBean.getListDetailAll();
    }
    
    public List<ListDetail> getListDetailById(Long idListDetail){
    	return listDetailBean.getListDetailById(idListDetail);
    }    

	public void persistListDetail(ListDetail listDetail) {
		listDetailBean.persistListDetail(listDetail);	
	}

	public void mergeListDetail(ListDetail listDetail) {
		ListDetail rs= (ListDetail) listDetailBean.getListDetailById(listDetail.getIdList()).get(0);
		rs.setListActive(listDetail.getListActive());
		rs.setListStatus(listDetail.getListStatus());
		listDetailBean.mergeListDetail(listDetail);	
	}

	public List<ListDetail> getListDetailCampanha(Long idCampanha) {
		return listDetailBean.getListDetailCampanha(idCampanha);
	}
	
	public ListDetail findListDeailBySegmentName(String segmentName) {
		return listDetailBean.findListDeailBySegmentName( segmentName);
	}	
	
	
	public ListDetail findListDetailProcessing(){
		return listDetailBean.findListDetailProcessing( );
}	
	
}
