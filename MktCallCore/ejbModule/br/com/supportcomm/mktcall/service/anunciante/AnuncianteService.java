package br.com.supportcomm.mktcall.service.anunciante;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Anunciante;
import br.com.supportcomm.mktcall.impl.anunciante.AnuncianteBeanLocal;

/**
 * Session Bean implementation class AnuncianteService
 */
@Stateless
@LocalBean
public class AnuncianteService {

	@EJB private AnuncianteBeanLocal anuncianteBean;
	
    /**
     * Default constructor. 
     */
    public AnuncianteService() {
        // TODO Auto-generated constructor stub
    }

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return anuncianteBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public Anunciante persistAnunciante(Anunciante anunciante) {
		// TODO Auto-generated method stub
		return anuncianteBean.persistAnunciante(anunciante);
	}

	
	public Anunciante mergeAnunciante(Anunciante anunciante) {
		// TODO Auto-generated method stub
		return anuncianteBean.mergeAnunciante(anunciante);
	}

	
	public void removeAnunciante(Anunciante anunciante) {
		// TODO Auto-generated method stub
		anuncianteBean.removeAnunciante(anunciante);
	}

	
	public List<Anunciante> getAnuncianteAgencia(Long idAgencia) {
		// TODO Auto-generated method stub
		return anuncianteBean.getAnuncianteAgencia(idAgencia);
	}

	
	public List<Anunciante> getAnuncianteUserAccessAgencia(Long idUserAccess) {
		// TODO Auto-generated method stub
		return anuncianteBean.getAnuncianteUserAccessAgencia(idUserAccess);
	}

	
	public List<Anunciante> getAnuncianteAll() {
		// TODO Auto-generated method stub
		return anuncianteBean.getAnuncianteAll();
	}

	
	public List<Anunciante> getAnuncianteId(Long id) {
		// TODO Auto-generated method stub
		return anuncianteBean.getAnuncianteId(id);
	}

	public List<Anunciante> getAnuncianteEmail(String email) {
		return anuncianteBean.getAnuncianteEmail(email);
	}	

}
