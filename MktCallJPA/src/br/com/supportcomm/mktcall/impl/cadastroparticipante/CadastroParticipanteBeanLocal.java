package br.com.supportcomm.mktcall.impl.cadastroparticipante;

import br.com.supportcomm.mktcall.entity.CadastroParticipante;

import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface CadastroParticipanteBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public CadastroParticipante persistCadastroParticipante(CadastroParticipante cadastroParticipante);

    /**
     * @generated DT_ID=none
     */
    public CadastroParticipante mergeCadastroParticipante(CadastroParticipante cadastroParticipante);

    /**
     * @generated DT_ID=none
     */
    public void removeCadastroParticipante(CadastroParticipante cadastroParticipante);

}
