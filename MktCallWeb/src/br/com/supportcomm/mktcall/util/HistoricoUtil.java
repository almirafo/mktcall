package br.com.supportcomm.mktcall.util;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.managedbean.AbstractManagedBean;

@ManagedBean
public class HistoricoUtil extends AbstractManagedBean{

	@EJB
	private UserHistoryService historyService;

	private UserHistory userHistory;

	private Logger logger = Logger.getLogger(getClass());

	public HistoricoUtil() {

	}

	

}
