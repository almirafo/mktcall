package br.com.supportcomm.mktcall.model.daoimp;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.constants.StatusProcessing;
import br.com.supportcomm.mktcall.entity.Dialing;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.model.dao.DialingBeanLocal;
@Stateless(name = "DialingBean", mappedName = "DialingJPA-DialingBean")
public class DialingBean implements DialingBeanLocal{
	Logger logger = Logger.getLogger(getClass());
	
    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;
	
	@Override
	public Dialing persist(Dialing dedication) throws MktCallException {
		em.persist(dedication);
		return dedication;
	}

	@Override
	public List<Dialing> persistAll(List<Dialing> dialing)
			throws MktCallException {
		for(Dialing dedication : dialing){
			this.persist(dedication);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dialing update(Dialing dedication) throws MktCallException {
		em.merge(dedication);
		return dedication;
	}

	@Override
	public List<Dialing> update(List<Dialing> dialings)
			throws MktCallException {
		for(Dialing dialing : dialings){
			this.update(dialing);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(List<Dialing> dialings) throws MktCallException {
		for(Dialing dialing : dialings){
			//Dialing dialing1 =this.getById(dialing.getId());
			this.remove(dialing.getId());
		}
		return false;
	}

	@Override
	public boolean remove(Long id) throws MktCallException {
		Dialing dedication = em.find(Dialing.class, id);
		em.remove(dedication);
		return false;
	}

	@Override
	public List<Dialing> findAll() throws MktCallException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dialing load(Long id) throws MktCallException {
		Dialing dedication = em.find(Dialing.class, id);
		return dedication;
	}


	@Override
	public List<Dialing> getListProcessed(int hours) throws MktCallException{
        try
        {
        	// Status dialing shows that a new record has been posted and has to be dialed.
            Integer status_dialing = StatusProcessing.SUCCESS.value();
            // Status decidation shows that a dedication is active (vs. inactive) and it can be considered for dialing.
            Integer status_dedication = 0;
            String  description_dedication = "DIAL";

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Dialing> cq = cb.createQuery(Dialing.class);

            Root<Dialing> root = cq.from(Dialing.class);


            Predicate byStatus           = cb.equal(root.get("status"), status_dialing);
            
            //-----------------------------------
    		Date currentDate = new Date();
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(currentDate);
    		cal.add(Calendar.HOUR, -1 * hours);
    		Date oneHourBack = cal.getTime();        	
        	//-----------------------------------
        	
        	Predicate byDatetimeScheduled = cb.lessThanOrEqualTo(root.<Timestamp>get("datetimeScheduled"), oneHourBack);

            cq.where(byStatus, byDatetimeScheduled);
            List<Dialing> results;
            TypedQuery<Dialing> tq = em.createQuery(cq);
            results = tq.getResultList();


            return results;
        }
        catch (NoResultException e)
        {
            logger.info("No records to process");

            return null;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause());

            throw new MktCallException(e);
        }
        
	}

	

	
	@Override
	public List<Dialing> getListToProcess(int maxResults) throws MktCallException {
        try
        {
        	// Status dialing shows that a new record has been posted and has to be dialed.
            Integer status_dialing = StatusProcessing.NEW_REGISTER.value();
            // Status decidation shows that a dedication is active (vs. inactive) and it can be considered for dialing.
            String respocndeCode = "2";

            
            Query query = em.createQuery("select d from Dialing d where d.status = :status and" +
            		" d.responseCode <> :responseCode and" +
            		" d.datetimeScheduled <= :now and" +
            		" d.attempts < 4")
            		.setParameter("status", status_dialing)
            		.setParameter("responseCode", respocndeCode)
            		.setParameter("now", new Timestamp(new java.util.Date().getTime()));
            		
            
            
            
            
            List<Dialing> results;
            if (maxResults>0){
                //TypedQuery<Dialing> tq = em.createQuery(cq).setMaxResults(maxResults);
                results = query.setMaxResults(maxResults).getResultList();
            }
            else{
                //TypedQuery<Dialing> tq = em.createQuery(cq);
                results = query.getResultList();
            }
            return results;
        }
        catch (NoResultException e)
        {
            logger.info("No records to process");

            return null;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause());

            throw new MktCallException(e);
        }
        
	}

	@Override
	public List<Dialing> getProcessesFailed() throws MktCallException {
        try
        {
            // Status decidation shows that a dedication is active (vs. inactive) and it can be considered for dialing.
            Integer status_dedication = 1;

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Dialing> cq = cb.createQuery(Dialing.class);

            Root<Dialing> root = cq.from(Dialing.class);


            Predicate byStatusError = cb.equal(root.get("status"), StatusProcessing.ERROR.value());
            Predicate byStatusInternalError = cb.equal(root.get("status"), StatusProcessing.INTERNAL_ERROR.value());
            Predicate byStatusTimeout = cb.equal(root.get("status"), StatusProcessing.TIMEOUT.value());
            Predicate byStatusAuthentication = cb.equal(root.get("status"), StatusProcessing.AUTHENTICATION_FAILED_ERROR.value());
        	Timestamp now = new Timestamp(new java.util.Date().getTime());
            Predicate byDatetimeScheduled = cb.lessThanOrEqualTo(root.<Timestamp>get("datetimeScheduled"), now);            
            

            cq.where( byDatetimeScheduled, cb.or(byStatusError, byStatusInternalError, byStatusTimeout, byStatusAuthentication));

            TypedQuery<Dialing> tq = em.createQuery(cq);

            List<Dialing> results = tq.getResultList();

            logger.info("Were found " + results.size() + " failed record(s)");


            return results;
        }
        catch (NoResultException e)
        {
            logger.info("No records to process");

            return null;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause());

            throw new MktCallException(e);
        }
       
    }

}
