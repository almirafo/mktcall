package br.com.supportcomm.mktcall.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Gera arquivo csv com informações de não processados
 * @author almir.oliveira
 *
 */
public class GenerateCsv {
	
	
	
	/**
	 * Gera arquivo csv com informações de não processados
	 * @param msisdnErrs
	 * @param sFileName
	 * @param path
	 */
	 public boolean generateCsvFile(List<String> msisdnErrs, String sFileName, String path)
	   {
		    boolean retorno=true;
			try
			{
			    FileWriter writer = new FileWriter(path.concat(sFileName));
			    for(String msisdnErr: msisdnErrs){
			    	writer.append(msisdnErr);
			    	writer.append(';');
			    	writer.append('\n');
			    }
			    writer.flush();
				writer.close();    
			}
			catch(IOException e)
			{
				 
			     
			     retorno= false;
			}
//			finally{
//				return retorno;
//			}
			return retorno;
	    }
	
	
}
