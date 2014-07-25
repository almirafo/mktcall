package br.com.supportcomm.mktcall.model.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.Dialing;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.model.dao.GenericDAO;

@Local
public interface DialingBeanLocal extends GenericDAO<Dialing>
{
    public List<Dialing> getListToProcess(int maxResults) throws MktCallException;
    public List<Dialing> getListProcessed(int hours) throws MktCallException;

    public List<Dialing> getProcessesFailed() throws MktCallException;
}
