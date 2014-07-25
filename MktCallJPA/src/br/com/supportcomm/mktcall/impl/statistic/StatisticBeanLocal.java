package br.com.supportcomm.mktcall.impl.statistic;

import br.com.supportcomm.mktcall.entity.Statistic;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface StatisticBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Statistic persistStatistic(Statistic statistic);

    /**
     * @generated DT_ID=none
     */
    public Statistic mergeStatistic(Statistic statistic);

    /**
     * @generated DT_ID=none
     */
    public void removeStatistic(Statistic statistic);

    /**
     * @generated DT_ID=none
     */
    public List<Statistic> getStatisticByIdCampanha(Long idCampanha);

    /**
     * @generated DT_ID=none
     */
    public List<Statistic> getStatisticByIdMsisdn(String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<Statistic> getStatisticByListenComplete(Boolean listenComplete);

    /**
     * @generated DT_ID=none
     */
    public List<Statistic> getStatisticByIdCdr(Long idCdr);

    /**
     * @generated DT_ID=none
     */
    public List<Statistic> getStatisticByEfetividade(Boolean listenComplete, java.sql.Timestamp startDate, java.sql.Timestamp endDate, Long idCampanha);

    /**
     * @generated DT_ID=none
     */
    public List<Statistic> getStatisticByEfetividadeUnicos(Boolean listenComplete, java.sql.Timestamp startDate, java.sql.Timestamp endDate, Long idCampanha);

}
