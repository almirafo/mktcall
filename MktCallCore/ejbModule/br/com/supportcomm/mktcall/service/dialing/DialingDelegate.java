package br.com.supportcomm.mktcall.service.dialing;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.Dialing;
import br.com.supportcomm.mktcall.exception.DialingException;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.model.dao.DialingBeanLocal;


@Stateless
@LocalBean
public class DialingDelegate {
	
	@EJB
	private DialingBeanLocal dialingBean;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
    
    public  Dialing save(Dialing dialing) throws DialingException, MktCallException
    {
    	
        return dialingBean.persist(dialing);
    }

    public  Dialing load(Long id) throws DialingException, MktCallException
    {
       return dialingBean.load(id);
    }

    public  boolean remove(Long id) throws DialingException, MktCallException
    {
      return dialingBean.remove(id);
    }    
    
    public  Dialing update(Dialing dialing) throws DialingException, MktCallException
    {
       return dialingBean.update(dialing);
    }    

    public  List<Dialing> getListToProcess(int maxResults) throws DialingException, MktCallException
    {
    	return dialingBean.getListToProcess(maxResults);
    }	
    
    public List<Dialing> getListProcessed(int hours) throws DialingException, MktCallException{
    	return dialingBean.getListToProcess(hours);
    }
    public  List<Dialing> getListProcessesFailed() throws DialingException, MktCallException
    {
        return dialingBean.getProcessesFailed();
        		 
    }    
    
    public  List<Dialing> listAll() throws DialingException, MktCallException
    {
        return dialingBean.findAll();
    }    
}
