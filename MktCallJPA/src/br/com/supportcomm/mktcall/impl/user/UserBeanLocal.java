package br.com.supportcomm.mktcall.impl.user;

import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.entity.UserType;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface UserBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public UserAccess persistUserAccess(UserAccess userAccess);

    /**
     * @generated DT_ID=none
     */
    public UserAccess mergeUserAccess(UserAccess userAccess);

    /**
     * @generated DT_ID=none
     */
    public void removeUserAccess(UserAccess userAccess);

    /**
     * @generated DT_ID=none
     */
    public List<UserAccess> getUserAccessLogin(String login);

    /**
     * @generated DT_ID=none
     */
    public List<UserAccess> getUserAccessLoginPass(String login, String senha);

    /**
     * @generated DT_ID=none
     */
    public List<UserAccess> getUserAccessUsuario(Long idUsuario);

    /**
     * @generated DT_ID=none
     */
    public List<UserAccess> getUserAccessAll();

    /**
     * @generated DT_ID=none
     */
    public List<UserAccess> getUserAccessById(Long id);

    /**
     * @generated DT_ID=none
     */
    public UserType persistUserType(UserType userType);

    /**
     * @generated DT_ID=none
     */
    public UserType mergeUserType(UserType userType);

    /**
     * @generated DT_ID=none
     */
    public void removeUserType(UserType userType);

    /**
     * @generated DT_ID=none
     */
    public List<UserType> getUserTypeAll();

    /**
     * @generated DT_ID=none
     */
    public List<UserType> getUserTypeId(Long idUserType);

}
