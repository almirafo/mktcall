package br.com.supportcomm.mktcall.service.accessoutoftime;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.AccessOutoftime;
import br.com.supportcomm.mktcall.impl.accessoutoftime.AccessOutOfTimeBeanLocal;

/**
 * Session Bean implementation class AccessOutOfTimeBean
 */
@Stateless
@LocalBean
public class AccessOutOfTimeService  {

	@EJB private AccessOutOfTimeBeanLocal accessBean;
	
    /**
     * Default constructor. 
     */
    public AccessOutOfTimeService() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see AccessOutOfTimeBeanLocal#mergeAccessOutoftime(AccessOutoftime)
     */
    public AccessOutoftime mergeAccessOutoftime(AccessOutoftime accessOutoftime) {
        // TODO Auto-generated method stub
			return accessBean.mergeAccessOutoftime(accessOutoftime);
    }

	/**
     * @see AccessOutOfTimeBeanLocal#persistAccessOutoftime(AccessOutoftime)
     */
    public AccessOutoftime persistAccessOutoftime(AccessOutoftime accessOutoftime) {
        // TODO Auto-generated method stub
			return accessBean.persistAccessOutoftime(accessOutoftime);
    }

	/**
     * @see AccessOutOfTimeBeanLocal#removeAccessOutoftime(AccessOutoftime)
     */
    public void removeAccessOutoftime(AccessOutoftime accessOutoftime) {
        // TODO Auto-generated method stub
    	accessBean.removeAccessOutoftime(accessOutoftime);
    }

	/**
     * @see AccessOutOfTimeBeanLocal#queryByRange(String, int, int)
     */
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        // TODO Auto-generated method stub
			return accessBean.queryByRange(jpqlStmt, firstResult, maxResults);
    }

}
