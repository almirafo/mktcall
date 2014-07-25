package br.com.supportcomm.mktcall.impl.anunciante;

import br.com.supportcomm.mktcall.entity.Anunciante;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface AnuncianteBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Anunciante persistAnunciante(Anunciante anunciante);

    /**
     * @generated DT_ID=none
     */
    public Anunciante mergeAnunciante(Anunciante anunciante);

    /**
     * @generated DT_ID=none
     */
    public void removeAnunciante(Anunciante anunciante);

    /**
     * @generated DT_ID=none
     */
    public List<Anunciante> getAnuncianteAgencia(Long idAgencia);

    /**
     * @generated DT_ID=none
     */
    public List<Anunciante> getAnuncianteUserAccessAgencia(Long idUserAccess);

    /**
     * @generated DT_ID=none
     */
    public List<Anunciante> getAnuncianteAll();

    /**
     * @generated DT_ID=none
     */
    public List<Anunciante> getAnuncianteId(Long id);

	List<Anunciante> getAnuncianteEmail(String email);

}
