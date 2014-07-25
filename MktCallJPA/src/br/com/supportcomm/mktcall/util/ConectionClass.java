package br.com.supportcomm.mktcall.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;


public class ConectionClass {

	

	public static void main(String[] args) {/*
		
	
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MktCallJPA");
		EntityManager em = emf.createEntityManager();

		try {
			emf.getProperties();

			Session session = em.unwrap(Session.class);
			SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
			ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
			Connection connection = connectionProvider.getConnection();
			ReadCSV readCSV =  new ReadCSV();
			readCSV.readFile("C:/users/portais/4040/Arquivos/","gerado2.csv");
			List<String> msisdns =new ArrayList<>();
			readCSV.execute(msisdns);
			int i=0;
			Statement stmt = null;
			stmt = connection.createStatement();
			for(String msisdn:msisdns){
				EntityTransaction t = em.getTransaction();
				t.begin();
				stmt.executeUpdate("insert into list_seg(list_id,msisdn) values('"+ msisdn +"')");
				t.commit();
				//connection.commit();
				System.out.println(i++);
			}
			
			// CopyManager copyManager = new CopyManager((BaseConnection) connection);

	       //     FileReader fileReader = new FileReader("C:/users/portais/4040/Arquivos/"+"gerado2.csv");
	           
	            
	       //     copyManager.copyIn("COPY list_seg FROM STDIN WITH DELIMITER ';'", fileReader );
	        
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			emf.close();
		}

		System.out.println("It is over");
	*/}

	
}




 class ReadCSV {
	private String path, fileName;
	/*public  ReadCSV(String path, String fileName){
		this.path = path;
		this.fileName = fileName;
	}
	*/
	public void readFile(String path, String fileName){
		this.path = path;
		this.fileName = fileName;
	} 
	
	/**
	 * Retorna uma lista de msisdn
	 * @return
	 * @throws FileNotFoundException
	 */
    public void execute(List<String> msisdns) throws FileNotFoundException {
    	
        Scanner scanner = new Scanner(new File( this.path.concat(this.fileName)));
        scanner.useDelimiter("\r\n");
        while(scanner.hasNext()){
        	String msisdn =scanner.next(); 
        	msisdns.add(msisdn);
           // System.out.println(msisdn);
        }
        scanner.close();
        
    }

}
