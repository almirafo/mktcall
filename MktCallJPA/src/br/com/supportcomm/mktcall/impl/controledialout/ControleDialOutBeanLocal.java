package br.com.supportcomm.mktcall.impl.controledialout;

import java.util.List;

import javax.ejb.Local;


import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;

/**
 * @generated DT_ID=none
 */
@Local
public interface ControleDialOutBeanLocal
{

    /**
     * Insere o detalhamento da lista
     */
    public void ativarDialOut(long idCampanha);

    /**
     * Desativar Dialout
     */
    public void desativarDialOut(long idCampanha);


    /**
     * @generated DT_ID=none
     */
    public List<ListaCampanhaDialOutVO> getControleDialOutAll();

	public void insertListOfMsisdnByCampanha(Campanha campanha);

   
}
