package br.com.supportcomm.mktcall.service.listageral;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.ListaGeral;
import br.com.supportcomm.mktcall.impl.listageral.ListaGeralBeanLocal;
import br.com.supportcomm.mktcall.vo.ListaFidelizadaVO;

/**
 * Session Bean implementation class ListaGeralService
 */
@Stateless
@LocalBean
public class ListaGeralService {

	@EJB private ListaGeralBeanLocal listaGeralBean;
	
    /**
     * Default constructor. 
     */
    public ListaGeralService() {
        // TODO Auto-generated constructor stub
    }

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return listaGeralBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public ListaGeral persistListaGeral(ListaGeral listaGeral) {
		// TODO Auto-generated method stub
		return listaGeralBean.persistListaGeral(listaGeral);
	}

	
	public ListaGeral mergeListaGeral(ListaGeral listaGeral) {
		// TODO Auto-generated method stub
		return listaGeralBean.mergeListaGeral(listaGeral);
	}

	
	public void removeListaGeral(ListaGeral listaGeral) {
		// TODO Auto-generated method stub
		listaGeralBean.removeListaGeral(listaGeral);
		
	}

	
	public List<ListaGeral> getListaGeralExistMsisdn(String msisdn) {
		// TODO Auto-generated method stub
		return listaGeralBean.getListaGeralExistMsisdn(msisdn);
	}

	
	public List<ListaGeral> getListaGeralByMsisdn(String msisdn) {
		// TODO Auto-generated method stub
		return listaGeralBean.getListaGeralByMsisdn(msisdn);
	}

	
	public List<ListaGeral> getListaGeralAll() {
		// TODO Auto-generated method stub
		return listaGeralBean.getListaGeralAll();
	}

	public List<ListaGeral> getListaGeralCampanha(Long idCampanha) {
		// TODO Auto-generated method stub
		return listaGeralBean.getListaGeralCampanha(idCampanha);
	}


	public List<ListaFidelizadaVO> getListaGeralCustomizada() {
		// TODO Auto-generated method stub
		return listaGeralBean.getListaGeralCustomizada();
	}
	
	public void removeListaGeralByIdCampanha(Long idCampanha){
		
		listaGeralBean.removeListaGeralByIdCampanha(idCampanha);
	}


}
