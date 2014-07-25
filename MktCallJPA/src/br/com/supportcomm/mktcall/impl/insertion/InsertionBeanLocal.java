package br.com.supportcomm.mktcall.impl.insertion;

import br.com.supportcomm.mktcall.entity.Insertion;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface InsertionBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Insertion persistInsertion(Insertion insertion);

    /**
     * @generated DT_ID=none
     */
    public Insertion mergeInsertion(Insertion insertion);

    /**
     * @generated DT_ID=none
     */
    public void removeInsertion(Insertion insertion);

    /**
     * @generated DT_ID=none
     */
    public List<Insertion> getInsertionAll();

    /**
     * @generated DT_ID=none
     */
    public List<Insertion> getInsertionId(Long idInsertion);

    /**
     * @generated DT_ID=none
     */
    public List<Insertion> getInsertionCampanha(Long idCampanha);

    /**
     * @generated DT_ID=none
     */
    public void removeInsertionByIdCampanha(Long idCampanha);

}
