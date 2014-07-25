package br.com.supportcomm.mktcall.tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class ReadCSV {
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
    	System.out.println("iniciando leitura de dados");
        Scanner scanner = new Scanner(new File( this.path.concat(this.fileName)));
        scanner.useDelimiter(";");
        while(scanner.hasNext()){
        	String msisdn =scanner.next(); 
        	msisdns.add(msisdn);
           
        }
        System.out.println("fim leitura de dados");
        scanner.close();
        
    }

    public Scanner getList() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File( this.path.concat(this.fileName)));
        scanner.useDelimiter(";");
        return scanner;
    }
    
}
