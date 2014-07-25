package br.com.supportcomm.mktcall.impl.cdr;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.supportcomm.mktcall.entity.Cdr;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "CdrBean", mappedName = "MktCallJPA-CdrBean")
public class CdrBean
        implements CdrBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public CdrBean() {
    }

    /**
     * @generated DT_ID=none
     */
    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public Cdr persistCdr(Cdr cdr) {
        em.persist(cdr);
        return cdr;
    }

    /**
     * @generated DT_ID=none
     */
    public Cdr mergeCdr(Cdr cdr) {
        return em.merge(cdr);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeCdr(Cdr cdr) {
        cdr = em.find(Cdr.class, cdr.getIdCdr());
        em.remove(cdr);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Cdr> getCdrUsuario(Long idUserAccess) {
        return em.createNamedQuery("Cdr.Usuario").setParameter("idUserAccess", idUserAccess).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Cdr> getCdrAgencia(Long idAgencia) {
        return em.createNamedQuery("Cdr.Agencia").setParameter("idAgencia", idAgencia).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Cdr> getCdrAgenciaUsuario(Long idUserAccess) {
        return em.createNamedQuery("Cdr.AgenciaUsuario").setParameter("idUserAccess", idUserAccess).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Cdr> getCdrByMsisdn(String msisdnOriginator) {
        return em.createNamedQuery("Cdr.ByMsisdn").setParameter("msisdnOriginator", msisdnOriginator).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Cdr> getCdrTempoMedioChamada(java.sql.Timestamp dataInicio, java.sql.Timestamp dataFim, String ddd) {
        return em.createNamedQuery("Cdr.tempoMedioChamada").setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).setParameter("ddd", ddd).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Cdr> getCdrAll() {
        return em.createNamedQuery("Cdr.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Cdr> getCdrTodayOnce(Integer duration, String msisdnOriginator, int dialstatus) {
        return em.createNamedQuery("Cdr.TodayOnce").setParameter("duration", duration).setParameter("msisdnOriginator", msisdnOriginator).setParameter("dialstatus", dialstatus).getResultList();
    }

	@Override
	public List<Cdr> getCdrTodayOnceDay(Integer duration, String msisdnOriginator,
			int dialstatus, Timestamp dataAtual) {
		
		
		Date date = new Date(dataAtual.getTime());
	
		List<Cdr> rs =em.createNamedQuery("Cdr.TodayOnceDay")
				        .setParameter("duration", duration)
				        .setParameter("msisdnOriginator", msisdnOriginator)
				        .setParameter("dialstatus", dialstatus)
				        .setParameter("diadehoje", date)
				        .getResultList();
		
		return rs;
	}

	@SuppressWarnings("finally")
	@Override
	public Cdr getCdrByCallId(String callid) {
		// TODO Auto-generated method stub
		Cdr cdr = new Cdr();
		 try{
		 cdr = (Cdr) em.createNamedQuery("Cdr.ByCallId").setParameter("callId", callid).getResultList().get(0);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 return cdr;
		 }
	}

}
