package br.com.supportcomm.mktcall.history;

import br.com.supportcomm.mktcall.entity.UserHistory;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface UserHistoryBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public UserHistory persistUserHistory(UserHistory userHistory);

    /**
     * @generated DT_ID=none
     */
    public UserHistory mergeUserHistory(UserHistory userHistory);

    /**
     * @generated DT_ID=none
     */
    public void removeUserHistory(UserHistory userHistory);

    /**
     * @generated DT_ID=none
     */
    public List<UserHistory> getUserHistoryAll();

    /**
     * @generated DT_ID=none
     */
    public List<UserHistory> getUserHistoryIdUser(Long idUserAccess);
    
    public List<UserHistory> getUserHistoryByUserIdAndRange(Long idUserAccess, Timestamp startDate, Timestamp endDate);

    /**
     * @generated DT_ID=none
     */
    public List<UserHistory> getUserHistoryIdCampanha(Long idCampanha);

    /**
     * @generated DT_ID=none
     */
    public List<UserHistory> getUserHistoryIdSpot(Long idSpot);

	List<UserHistory> getUserHistoryByLoginAndRange(String login,
			Timestamp startDate, Timestamp endDate);

}
