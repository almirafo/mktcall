package br.com.supportcomm.mktcall.impl.listdetail;

import java.util.List;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.ListDetail;

/**
 * @generated DT_ID=none
 */
@Local
public interface ListDetailBeanLocal
{

    /**
     * Insere o detalhamento da lista
     */
    public ListDetail persistListDetail(ListDetail listDetail);

    /**
     * Exclui a Lista
     */
    public void removeListDetail(ListDetail listDetail);

    /**
     * @generated DT_ID=none
     */
    public List<ListDetail> getListDetailByMsisdn(String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<ListDetail> getListDetailAll();

    /**
     * @generated DT_ID=none
     */
    public List<ListDetail> getListDetailCustomizada();

	public void mergeListDetail(ListDetail listDetail);

	List<ListDetail> getListDetailById(Long idListDetail);

	List<ListDetail> getListDetailCampanha(Long idCampanha);

	ListDetail findListDeailBySegmentName(String segmentName);
	
	ListDetail findListDetailProcessing();

	void blockListOfMsisdnByCampanha(Campanha campanha);
   
}
