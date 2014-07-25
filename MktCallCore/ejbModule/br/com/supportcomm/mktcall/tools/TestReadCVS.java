package br.com.supportcomm.mktcall.tools;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;



@Stateless
@LocalBean
public class TestReadCVS {

	@EJB
	static ReadCSV readCSV;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	FileWalker fileWalker = new FileWalker();
	  List<String>	files =fileWalker.walk("C:/users/portais/4040/Arquivos/");
	  GenerateCsv  generateCsv=  new GenerateCsv();
	  
	  List <String> msisdnErrs =  new ArrayList<>();
	  
	  for (Long i=0l;i<1000000;i++){
		  
		  String iSTR ="1"+ String.format("%010d", i);
		  System.out.println();
		  msisdnErrs.add(iSTR);
	  }
	  
	  
	  
	  generateCsv.generateCsvFile(msisdnErrs, "gerado5.csv", "C:/users/portais/4040/Arquivos/");
	  
	  for(String file:files){
		  System.out.println("arquivo: ".concat(file));
	  }
	  
	   
	}

}
