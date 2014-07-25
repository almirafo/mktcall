package br.com.supportcomm.mktcall.impl.listsegment;

import java.util.List;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.entity.ListSegmentation;

/**
 * @generated DT_ID=none
 */
@Local
public interface ListSegmentationBeanLocal
{

    /**
     * Insere o detalhamento da lista
     */
    public ListSegmentation persistListSegmentation(ListSegmentation listSegmentation);

    /**
     * Exclui a Lista
     */
    public void removeListSegmentation(ListSegmentation listSegmentation);

    /**
     * @generated DT_ID=none
     */
    public List<ListSegmentation> getListSegmentationByMsisdn(String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<ListSegmentation> getListSegmentationAll();

    /**
     * @generated DT_ID=none
     */
    public List<ListSegmentation> getListSegmentationCustomizada();

	public void mergeListSegmentation(ListSegmentation listSegmentation);
	public void removeListSegmentationByListDetail(ListSegmentation listSegmentation);

    
}
