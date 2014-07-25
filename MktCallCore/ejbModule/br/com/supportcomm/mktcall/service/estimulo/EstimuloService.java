package br.com.supportcomm.mktcall.service.estimulo;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Estimulo;
import br.com.supportcomm.mktcall.impl.estimulo.EstimuloBeanLocal;

/**
 * Session Bean implementation class EstimuloService
 */
@Stateless
@LocalBean
public class EstimuloService {

	@EJB private EstimuloBeanLocal estimuloBean;
	
    /**
     * Default constructor. 
     */
    public EstimuloService() {
        // TODO Auto-generated constructor stub
    }

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return estimuloBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public Estimulo persistEstimulo(Estimulo estimulo) {
		// TODO Auto-generated method stub
		return estimuloBean.persistEstimulo(estimulo);
	}

	
	public Estimulo mergeEstimulo(Estimulo estimulo) {
		// TODO Auto-generated method stub
		return estimuloBean.mergeEstimulo(estimulo);
	}

	
	public void removeEstimulo(Estimulo estimulo) {
		// TODO Auto-generated method stub
		estimuloBean.removeEstimulo(estimulo);
	}

	
   

}
