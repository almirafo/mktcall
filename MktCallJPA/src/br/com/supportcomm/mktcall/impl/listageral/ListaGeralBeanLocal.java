package br.com.supportcomm.mktcall.impl.listageral;

import java.util.List;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.ListaGeral;
import br.com.supportcomm.mktcall.vo.ListaFidelizadaVO;


/**
 * @generated DT_ID=none
 */
@Local
public interface ListaGeralBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public ListaGeral persistListaGeral(ListaGeral listaGeral);

    /**
     * @generated DT_ID=none
     */
    public ListaGeral mergeListaGeral(ListaGeral listaGeral);

    /**
     * @generated DT_ID=none
     */
    public void removeListaGeral(ListaGeral listaGeral);

    /**
     * @generated DT_ID=none
     */
    public List<ListaGeral> getListaGeralExistMsisdn(String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<ListaGeral> getListaGeralByMsisdn(String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<ListaGeral> getListaGeralAll();

    /**
     * @generated DT_ID=none
     */
    public List<ListaGeral> getListaGeralCampanha(Long idCampanha);
    
    public List<ListaFidelizadaVO> getListaGeralCustomizada();
    
    public void removeListaGeralByIdCampanha(Long idCampanha);
    
}
