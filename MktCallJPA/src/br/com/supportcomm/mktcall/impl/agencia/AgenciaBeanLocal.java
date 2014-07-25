package br.com.supportcomm.mktcall.impl.agencia;

import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.Campanha;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface AgenciaBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Agencia persistAgencia(Agencia agencia);

    /**
     * @generated DT_ID=none
     */
    public Agencia mergeAgencia(Agencia agencia);

    /**
     * @generated DT_ID=none
     */
    public void removeAgencia(Agencia agencia);

    /**
     * @generated DT_ID=none
     */
    public List<Agencia> getAgenciaAnunciante(Long idUserAccess);

    /**
     * @generated DT_ID=none
     */
    public List<Agencia> getAgenciaAll();

    /**
     * @generated DT_ID=none
     */
    public List<Agencia> getAgenciaUsuario(Long idUserAccess);

    /**
     * @generated DT_ID=none
     */
    public List<Agencia> getAgenciaId(Long id);

    /**
     * @generated DT_ID=none
     */
    public List<Campanha> getAgenciaCampanha(Long idUserAccess);

	List<Agencia> getAgenciaEmail(String email);

}
