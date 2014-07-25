package br.com.supportcomm.mktcall.impl.cdr;

import br.com.supportcomm.mktcall.entity.Cdr;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface CdrBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Cdr persistCdr(Cdr cdr);

    /**
     * @generated DT_ID=none
     */
    public Cdr mergeCdr(Cdr cdr);

    /**
     * @generated DT_ID=none
     */
    public void removeCdr(Cdr cdr);

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrUsuario(Long idUserAccess);

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrAgencia(Long idAgencia);

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrAgenciaUsuario(Long idUserAccess);

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrByMsisdn(String msisdnOriginator);

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrTempoMedioChamada(java.sql.Timestamp dataInicio, java.sql.Timestamp dataFim, String ddd);

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrAll();

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrTodayOnceDay(Integer duration, String msisdnOriginator, int dialstatus, java.sql.Timestamp dataAtual);

    /**
     * @generated DT_ID=none
     */
    public List<Cdr> getCdrTodayOnce(Integer duration, String msisdnOriginator, int dialstatus);

	public Cdr getCdrByCallId(String callid);

}
