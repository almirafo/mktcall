package br.com.supportcomm.mktcall.service.insertion;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Insertion;
import br.com.supportcomm.mktcall.impl.insertion.InsertionBeanLocal;

@Stateless
@LocalBean
public class InsertionService {

	@EJB private InsertionBeanLocal insertionBean;
	
	public InsertionService(){
		
	}
	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return insertionBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	public Insertion persistInsertion(Insertion insertion) {
		// TODO Auto-generated method stub
		return insertionBean.persistInsertion(insertion);
	}

	public Insertion mergeInsertion(Insertion insertion) {
		// TODO Auto-generated method stub
		return insertionBean.mergeInsertion(insertion);
	}

	public void removeInsertion(Insertion insertion) {
		// TODO Auto-generated method stub
		insertionBean.removeInsertion(insertion);
	}

	public List<Insertion> getInsertionAll() {
		// TODO Auto-generated method stub
		return insertionBean.getInsertionAll();
	}

	public List<Insertion> getInsertionId(Long idInsertion) {
		// TODO Auto-generated method stub
		return insertionBean.getInsertionId(idInsertion);
	}

	public List<Insertion> getInsertionCampanha(Long idCampanha) {
		// TODO Auto-generated method stub
		return insertionBean.getInsertionCampanha(idCampanha);
	}

	public void removeInsertionByIdCampanha(Long idCampanha) {
		// TODO Auto-generated method stub
		insertionBean.removeInsertionByIdCampanha(idCampanha);
		
	}

}
