package br.com.supportcomm.mktcall.service.history;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.history.UserHistoryBeanLocal;

@Stateless
@LocalBean
public class UserHistoryService {

	@EJB
	private UserHistoryBeanLocal userHistoryBean;

	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		return userHistoryBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	public UserHistory persistUserHistory(UserHistory userHistory) {
		return userHistoryBean.persistUserHistory(userHistory);
	}

	public UserHistory mergeUserHistory(UserHistory userHistory) {
		return userHistoryBean.mergeUserHistory(userHistory);
	}

	public void removeUserHistory(UserHistory userHistory) {
		userHistoryBean.removeUserHistory(userHistory);
	}

	public List<UserHistory> getUserHistoryAll() {
		Logger logger = Logger.getLogger(getClass().getName());
		Timestamp intDate = new Timestamp(System.currentTimeMillis());
		List<UserHistory> rs = userHistoryBean.getUserHistoryAll();
		Timestamp endDate = new Timestamp(System.currentTimeMillis());
		Long elap = endDate.getTime() - intDate.getTime();
		logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
		return rs;		
	}

	public List<UserHistory> getUserHistoryIdUser(Long idUserAccess) {
		return userHistoryBean.getUserHistoryIdUser(idUserAccess);
	}

	public List<UserHistory> getUserHistoryIdCampanha(Long idCampanha) {
		return userHistoryBean.getUserHistoryIdCampanha(idCampanha);
	}

	public List<UserHistory> getUserHistoryIdSpot(Long idSpot) {
		return userHistoryBean.getUserHistoryIdSpot(idSpot);
	}
	
	public List<UserHistory> getUserHistoryByUserIdAndRange(Long idUserAccess, Timestamp startDate, Timestamp endDate) {
		return userHistoryBean.getUserHistoryByUserIdAndRange(idUserAccess, startDate, endDate);
	}

}
