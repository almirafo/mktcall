package br.com.supportcomm.mktcall.service.user;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.entity.UserType;
import br.com.supportcomm.mktcall.impl.user.UserBeanLocal;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService {

    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub
    }
    
    @EJB private UserBeanLocal userBean;

	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return userBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	
	public UserAccess persistUserAccess(UserAccess userAccess) {
		// TODO Auto-generated method stub
		return userBean.persistUserAccess(userAccess);
	}

	
	public UserAccess mergeUserAccess(UserAccess userAccess) {
		// TODO Auto-generated method stub
		return userBean.mergeUserAccess(userAccess);
	}

	
	public void removeUserAccess(UserAccess userAccess) {
		// TODO Auto-generated method stub
		userBean.removeUserAccess(userAccess);
	}

	
	public List<UserAccess> getUserAccessLogin(String login) {
		// TODO Auto-generated method stub
		return userBean.getUserAccessLogin(login);
	}

	
	public List<UserAccess> getUserAccessLoginPass(String login, String senha) {
		// TODO Auto-generated method stub
		return userBean.getUserAccessLoginPass(login, senha);
	}

	
	public List<UserAccess> getUserAccessUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		return userBean.getUserAccessUsuario(idUsuario);
	}

	
	public List<UserAccess> getUserAccessAll() {
		// TODO Auto-generated method stub
		return userBean.getUserAccessAll();
	}

	
	public List<UserAccess> getUserAccessById(Long id) {
		// TODO Auto-generated method stub
		return userBean.getUserAccessById(id);
	}

	
	public UserType persistUserType(UserType userType) {
		// TODO Auto-generated method stub
		return userBean.persistUserType(userType);
	}

	
	public UserType mergeUserType(UserType userType) {
		// TODO Auto-generated method stub
		return userBean.mergeUserType(userType);
	}

	
	public void removeUserType(UserType userType) {
		// TODO Auto-generated method stub
		userBean.removeUserType(userType);
	}

	
	public List<UserType> getUserTypeAll() {
		// TODO Auto-generated method stub
		return userBean.getUserTypeAll();
	}

	
	public List<UserType> getUserTypeId(Long idUserType) {
		// TODO Auto-generated method stub
		return userBean.getUserTypeId(idUserType);
	}

	

}
