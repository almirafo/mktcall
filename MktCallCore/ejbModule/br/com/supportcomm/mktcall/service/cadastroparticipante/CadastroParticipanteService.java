package br.com.supportcomm.mktcall.service.cadastroparticipante;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.CadastroParticipante;
import br.com.supportcomm.mktcall.impl.cadastroparticipante.CadastroParticipanteBeanLocal;

/**
 * Session Bean implementation class CadastroParticipanteService
 */
@Stateless
@LocalBean
public class CadastroParticipanteService {

	
	@EJB private CadastroParticipanteBeanLocal cadastroParticipanteBean;
    /**
     * Default constructor. 
     */

	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return cadastroParticipanteBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	public CadastroParticipante persistCadastroParticipante(CadastroParticipante cadastroParticipante) {
		// TODO Auto-generated method stub
		return cadastroParticipanteBean.persistCadastroParticipante(cadastroParticipante);
	}

	public CadastroParticipante mergeCadastroParticipante(CadastroParticipante cadastroParticipante) {
		// TODO Auto-generated method stub
		return cadastroParticipanteBean.mergeCadastroParticipante(cadastroParticipante);
	}

	public void removeCadastroParticipante(CadastroParticipante cadastroParticipante) {
		// TODO Auto-generated method stub
		cadastroParticipanteBean.removeCadastroParticipante(cadastroParticipante);
	}
    
	

}
