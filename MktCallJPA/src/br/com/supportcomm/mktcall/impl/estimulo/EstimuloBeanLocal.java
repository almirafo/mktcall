package br.com.supportcomm.mktcall.impl.estimulo;

import br.com.supportcomm.mktcall.entity.Estimulo;

import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface EstimuloBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Estimulo persistEstimulo(Estimulo estimulo);

    /**
     * @generated DT_ID=none
     */
    public Estimulo mergeEstimulo(Estimulo estimulo);

    /**
     * @generated DT_ID=none
     */
    public void removeEstimulo(Estimulo estimulo);

}
