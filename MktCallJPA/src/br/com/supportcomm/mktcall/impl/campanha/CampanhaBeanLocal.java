package br.com.supportcomm.mktcall.impl.campanha;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;
import br.com.supportcomm.mktcall.vo.ListaDuracaoVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeConsolidadaVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeVO;
import br.com.supportcomm.mktcall.vo.ListaInteracaoVO;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface CampanhaBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Campanha persistCampanha(Campanha campanha);

    /**
     * @generated DT_ID=none
     */
    public Campanha mergeCampanha(Campanha campanha);
    public Campanha incremetReachCampanha(Campanha campanha);
    public void desativaCampannha(Campanha campanha);

    /**
     * @generated DT_ID=none
     */
    public void removeCampanha(Campanha campanha);

    /**
     * @generated DT_ID=none
     */
    public List<Campanha> getCampanhaAll();   
    
    /**
     * @generated DT_ID=none
     */
    public List<Campanha> getCampanhaAllNative();

    /**
     * @generated DT_ID=none
     */
    public List<Campanha> getCampanhaId(Long idCampanha);

    /**
     * @generated DT_ID=none
     */
    public List<Campanha> getCampanhaByUsuario(Long idUserAccess);

    /**
     * @generated DT_ID=none
     */
    public List<Campanha> getCampanhaSpot(Long idSpot);
    
    public List<ListaInteracaoVO> getListaInteracao(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal);
    public List<ListaDuracaoVO>   getListaDuracao(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal);
    public List<ListaEfetividadeVO>   getListaEfetividade(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal);
    public List<ListaEfetividadeConsolidadaVO>   getListaEfetividadeConsolidada(Long idCampanha, Timestamp dataInicial,Timestamp dataFinal);

	public List<Campanha> getCampanhaAllNotRegional();

	List<Campanha> getCampanhaByMsisdn(String msisdn);
	public List<ListaCampanhaDialOutVO> getCampanhaDialOutAll();

	List<Campanha> getCampanhabyProcessStatus(String processStatus);

	

	void setCampanhaProcessStatus(Campanha campanha, String processStatus);

	public Campanha verificaSeTemCampanha(Long idList);

}
