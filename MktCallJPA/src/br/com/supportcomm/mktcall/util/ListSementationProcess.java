package br.com.supportcomm.mktcall.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
//import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import br.com.supportcomm.mktcall.entity.ListSegmentation;


@Stateless(name = "ListSementationProcess", mappedName = "MktCallJPA-ListSementationProcess")
public class ListSementationProcess {
	
    private static final int MAXDIGIT = 11;
	@PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;
    
	//@SuppressWarnings("deprecation")
	public  void execute(List<ListSegmentation> listSegmentations) {
		 Timestamp intDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis(); 

		try {
/*			Session session = em.unwrap(Session.class);
			SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
			ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
			Connection connection = connectionProvider.getConnection();
*/
			String sql= "insert into list_segmentation(id_list,msisdn,datetime_operation) values(?,?,?)";
//			PreparedStatement ps= connection.prepareStatement(sql);
	
			for(ListSegmentation listSegmentation:listSegmentations){
				
				em.createNativeQuery(sql)
				.setParameter(1, listSegmentation.getId().getIdList())
				.setParameter(2, listSegmentation.getId().getMsisdn().replaceAll("\n", ""))
				.setParameter(3, listSegmentation.getDatetimeOperation()).executeUpdate();
	/*			ps.setLong(1, listSegmentation.getId().getIdList());
				ps.setString(2, listSegmentation.getId().getMsisdn().replaceAll("\n", ""));
				ps.setTimestamp(3, listSegmentation.getDatetimeOperation());
				ps.addBatch();*/
			}
	/*		ps.executeBatch();
			connection.commit();
	*/		
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} 
		Timestamp endDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
	    Long elap = endDate.getTime() - intDate.getTime();

	    System.out.println("Elap time: " + elap);
		
	}
	
	public  void execute(Scanner scanner,Long idList, String path){
		 Timestamp intDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis(); 
		 List<String> msisdnsErr = new ArrayList<>();
		try {
			Session session = em.unwrap(Session.class);
			SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
			ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
			Connection connection = connectionProvider.getConnection();

			String sql= "insert into list_segmentation(id_list,msisdn,datetime_operation) values(?,?,?)";
			PreparedStatement ps= connection.prepareStatement(sql);
			int count =0;
			while(scanner.hasNext()){
				
				String msisdn = scanner.next().replaceAll("\n", "").replaceAll("\r", "").trim();
				
				if (!msisdn.isEmpty() && isNumeric(msisdn.toString()) && !msisdn.startsWith("0") && msisdn.length()==MAXDIGIT){
					ps.setLong(1, idList);
					ps.setString(2,msisdn);
					ps.setTimestamp(3, intDate);
					ps.addBatch();
					if(count++>100000){
						ps.executeBatch();
						connection.commit();
						 System.out.println("commit");
						count=0;
					}
				}else{
					msisdnsErr.add(msisdn);
				}
			}
			
			if(!msisdnsErr.isEmpty()){
				GenerateCsv generateCsv = new GenerateCsv();
				generateCsv.generateCsvFile(msisdnsErr, intDate.toString().concat("_ERR"), path);

			}
			
			
			if (count>0){
				ps.executeBatch();
				connection.commit();
				
			}
			
			ps.executeBatch();
			connection.commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} 
		Timestamp endDate = new Timestamp(System.currentTimeMillis());//tempCalendar.getTimeInMillis();
	    Long elap = endDate.getTime() - intDate.getTime();

	    System.out.println("Elap time: " + elap);

	}
	
	private  boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
}




 
