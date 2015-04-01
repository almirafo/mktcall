package br.com.supportcomm.mktcall.controledialout;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.impl.controledialout.ControleDialOutBeanLocal;
import br.com.supportcomm.mktcall.impl.listdetail.ListDetailBeanLocal;
import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;

/**
 * Session Bean implementation class AgenciaService
 */
@Stateless
@LocalBean
public class ControleDialOutService {

	@EJB private ControleDialOutBeanLocal controleDialOutBean;
	@EJB private ListDetailBeanLocal listDetailBean;
	
    /**
     * Default constructor. 
     */
    public ControleDialOutService() {
        // TODO Auto-generated constructor stub
    }

    public List<ListaCampanhaDialOutVO> getControleDialOutAll(){
    	return controleDialOutBean.getControleDialOutAll();
    }
 
    public void desativarDialOut(long idCampanha){
    	 controleDialOutBean.desativarDialOut(idCampanha);
    }
    public void ativarDialOut(long idCampanha){
    	 controleDialOutBean.ativarDialOut(idCampanha);
    }

	public void insertListOfMsisdnByCampanha(Campanha campanha) {
		controleDialOutBean.insertListOfMsisdnByCampanha(campanha);
		listDetailBean.blockListOfMsisdnByCampanha(campanha);
		
	}
}
