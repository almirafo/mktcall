package br.com.supportcomm.mktcall.impl.accessoutoftime;

import br.com.supportcomm.mktcall.entity.AccessOutoftime;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface AccessOutOfTimeBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public AccessOutoftime persistAccessOutoftime(AccessOutoftime accessOutoftime);

    /**
     * @generated DT_ID=none
     */
    public AccessOutoftime mergeAccessOutoftime(AccessOutoftime accessOutoftime);

    /**
     * @generated DT_ID=none
     */
    public void removeAccessOutoftime(AccessOutoftime accessOutoftime);

    /**
     * @generated DT_ID=none
     */
    public List<AccessOutoftime> getAccessOutoftimeByMsisdn(String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<AccessOutoftime> getAccessOutoftimeAll();

}
