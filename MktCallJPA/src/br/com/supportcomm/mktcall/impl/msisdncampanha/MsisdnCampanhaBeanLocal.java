package br.com.supportcomm.mktcall.impl.msisdncampanha;

import br.com.supportcomm.mktcall.entity.MsisdnCampanha;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface MsisdnCampanhaBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public MsisdnCampanha persistMsisdnCampanha(MsisdnCampanha msisdnCampanha);

    /**
     * @generated DT_ID=none
     */
    public MsisdnCampanha mergeMsisdnCampanha(MsisdnCampanha msisdnCampanha);

    /**
     * @generated DT_ID=none
     */
    public void removeMsisdnCampanha(MsisdnCampanha msisdnCampanha);

    /**
     * @generated DT_ID=none
     */
    public List<MsisdnCampanha> getMsisdnCampanhaId(String msisdnId);

    /**
     * @generated DT_ID=none
     */
    public List<MsisdnCampanha> getMsisdnCampanhaIdCampanha(Long idCampanha);

    /**
     * @generated DT_ID=none
     */
    public List<MsisdnCampanha> getMsisdnCampanhaIdCampanhaMsisdn(Long idCampanha, String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<MsisdnCampanha> getMsisdnCampanhaMsisdn(String msisdn);

    /**
     * @generated DT_ID=none
     */
    public List<MsisdnCampanha> getMsisdnCampanhaAdvertisement(String msisdn, String callid);

}
