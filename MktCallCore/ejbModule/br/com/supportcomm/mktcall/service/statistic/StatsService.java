package br.com.supportcomm.mktcall.service.statistic;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Statistic;
import br.com.supportcomm.mktcall.impl.statistic.StatisticBeanLocal;

/**
 * Session Bean implementation class StatsService
 */
@Stateless
@LocalBean
public class StatsService {

	@EJB
	private StatisticBeanLocal statisticBean;

	/**
	 * Default constructor.
	 */
	public StatsService() {
		// TODO Auto-generated constructor stub
	}

	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return statisticBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	public Statistic persistStatistic(Statistic statistic) {
		// TODO Auto-generated method stub
		return statisticBean.persistStatistic(statistic);
	}

	public Statistic mergeStatistic(Statistic statistic) {
		// TODO Auto-generated method stub
		return statisticBean.mergeStatistic(statistic);
	}

	public void removeStatistic(Statistic statistic) {
		// TODO Auto-generated method stub
		statisticBean.removeStatistic(statistic);

	}

	public List<Statistic> getStatisticByIdCampanha(Long idCampanha) {
		// TODO Auto-generated method stub
		return statisticBean.getStatisticByIdCampanha(idCampanha);
	}

	public List<Statistic> getStatisticByIdMsisdn(String msisdn) {
		// TODO Auto-generated method stub
		return statisticBean.getStatisticByIdMsisdn(msisdn);
	}

	public List<Statistic> getStatisticByListenComplete(Boolean listenComplete) {
		// TODO Auto-generated method stub
		return statisticBean.getStatisticByListenComplete(listenComplete);
	}

	public List<Statistic> getStatisticByIdCdr(Long idCdr) {
		// TODO Auto-generated method stub
		return statisticBean.getStatisticByIdCdr(idCdr);
	}

	public List<Statistic> getStatisticByEfetividade(Boolean listenComplete, Timestamp startDate, Timestamp endDate, Long idCampanha) {
		// TODO Auto-generated method stub
		return statisticBean.getStatisticByEfetividade(listenComplete, startDate, endDate, idCampanha);
	}

	public List<Statistic> getStatisticByEfetividadeUnicos(Boolean listenComplete, Timestamp startDate, Timestamp endDate, Long idCampanha) {
		// TODO Auto-generated method stub
		return statisticBean.getStatisticByEfetividade(listenComplete, startDate, endDate, idCampanha);
	}

}
