package br.com.supportcomm.mktcall.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.supportcomm.mktcall.entity.Cdr;


public class CreateClass {

	

	public static void main(String[] args) {
		
	
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MktCallJPA");
		EntityManager em = emf.createEntityManager();

		try {

			Integer duration =4; 
			String msisdnOriginator = "1396466696";
			int dialstatus=1; 
			Timestamp dataAtual=new Timestamp(System.currentTimeMillis());
			
			Date date = new Date(dataAtual.getTime());
		
			List<Cdr> rs =em.createNamedQuery("Cdr.TodayOnceDay")
					        .setParameter("duration", duration)
					        .setParameter("msisdnOriginator", msisdnOriginator)
					        .setParameter("dialstatus", dialstatus)
					        .setParameter("diadehoje", date)
					        .getResultList();

			CreateClass cc = new CreateClass();
			if(!rs.isEmpty()){
				for ( int i=0; i<rs.size();i++) {
					Timestamp ts = rs.get(i).getCallDatetime() ;
					if (cc.verifyTimestampDay(ts, new Timestamp(System.currentTimeMillis()))) {
						System.out.println(rs.get(i).getCallDatetime());
					}
				}
			}
			
			
			
			
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			emf.close();
		}

		System.out.println("It is over");
	}

	
	public boolean verifyTimestampDay(Timestamp dayOne, Timestamp dayTwo) {
		String frist = new SimpleDateFormat("yyyy-MM-dd").format(dayOne);
		String second = new SimpleDateFormat("yyyy-MM-dd").format(dayTwo);
		if (frist.equals(second)) {
			return true;
		}
		return false;

	}
	
}
