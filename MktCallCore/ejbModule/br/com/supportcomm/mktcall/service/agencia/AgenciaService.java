package br.com.supportcomm.mktcall.service.agencia;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.impl.agencia.AgenciaBeanLocal;

/**
 * Session Bean implementation class AgenciaService
 */
@Stateless
@LocalBean
public class AgenciaService {

	@EJB private AgenciaBeanLocal agenciaBean;
	
    /**
     * Default constructor. 
     */
    public AgenciaService() {
        // TODO Auto-generated constructor stub
    }

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return agenciaBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public Agencia persistAgencia(Agencia agencia) {
		// TODO Auto-generated method stub
		return agenciaBean.persistAgencia(agencia);
	}

	
	public Agencia mergeAgencia(Agencia agencia) {
		// TODO Auto-generated method stub
		return agenciaBean.mergeAgencia(agencia);
	}

	
	public void removeAgencia(Agencia agencia) {
		// TODO Auto-generated method stub
		agenciaBean.removeAgencia(agencia);
	}

	
	public List<Agencia> getAgenciaAnunciante(Long idUserAccess) {
		// TODO Auto-generated method stub
		return agenciaBean.getAgenciaAnunciante(idUserAccess);
	}

	public List<Agencia> getAgenciaAll() {
		// TODO Auto-generated method stub
		return agenciaBean.getAgenciaAll();
	}

	public List<Agencia> getAgenciaUsuario(Long idUserAccess) {
		// TODO Auto-generated method stub
		return agenciaBean.getAgenciaUsuario(idUserAccess);
	}

	
	public List<Agencia> getAgenciaId(Long id) {
		// TODO Auto-generated method stub
		return agenciaBean.getAgenciaId(id);
	}
	
	public List<Campanha> getAgenciaCampanha(Long idUserAccess) {
		// TODO Auto-generated method stub
		return agenciaBean.getAgenciaCampanha(idUserAccess);
	}

	public List<Agencia> getAgenciaEmail(String email) {
		return agenciaBean.getAgenciaEmail(email);
	}


}
