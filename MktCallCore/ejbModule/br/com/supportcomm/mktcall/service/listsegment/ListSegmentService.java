package br.com.supportcomm.mktcall.service.listsegment;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.entity.ListSegmentation;
import br.com.supportcomm.mktcall.entity.ListSegmentationPK;
import br.com.supportcomm.mktcall.impl.listdetail.ListDetailBeanLocal;
import br.com.supportcomm.mktcall.impl.listsegment.ListSegmentationBeanLocal;
import br.com.supportcomm.mktcall.tools.FileWalker;
import br.com.supportcomm.mktcall.tools.FilesUtil;
import br.com.supportcomm.mktcall.tools.GenerateCsv;
import br.com.supportcomm.mktcall.tools.ListSegmentStatus;
import br.com.supportcomm.mktcall.tools.ReadCSV;
import br.com.supportcomm.mktcall.util.ListSementationProcess;

/**
 * Session Bean implementation class AgenciaService
 */
@Stateless
public class ListSegmentService {

	@EJB private ListDetailBeanLocal listDetailBeanLocal;
	@EJB private ListSegmentationBeanLocal listSegmentationBeanLocal;
	@EJB private ListSementationProcess listSegmentationProcess;
    /**
     * Default constructor. 
     */
    public ListSegmentService() {
        // TODO Auto-generated constructor stub
    }

    
    public List<String> fileList(String path){
    	FileWalker fileWalker = new FileWalker();
    	
    	return fileWalker.walk(path);
    }
    
	public boolean generateCsvFile(List<String> msisdnErrs, String sFileName, String path){
		GenerateCsv generateCsv =  new GenerateCsv();
		return generateCsv.generateCsvFile(msisdnErrs, sFileName, path);
	}
	
	/**
	 * Método salva as informações em tabelas, <br/>
	 * gera eventual arquivo de não processados e move para pasta de processandos. 
	 * @return
	 * @throws FileNotFoundException 
	 */
	public ListSegmentStatus saveListSegment(String path, String fileName, String segmentName) throws FileNotFoundException{

		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		
		ListSegmentStatus listSegmentStatus = new ListSegmentStatus();
		ReadCSV readCSV =  new ReadCSV();
		readCSV.readFile(path, fileName);
		List<String> msisdnsErr = new ArrayList<>();
		List<String> msisdns = new ArrayList<>();
		readCSV.execute(msisdns);
		
		
		ListDetail listDetail=  new ListDetail();
		listDetail.setFilename(fileName);
		listDetail.setListName(segmentName);
		listDetail.setListStatus("2");
		listDetail.setListActive(false);
		listDetail.setDatetimeOperation(stamp);
		listDetailBeanLocal.persistListDetail(listDetail);
		
		for (String msisdn:msisdns){
			msisdn =msisdn.replaceAll("\r\n", "").trim();
			if (msisdn.length()>11 || !isNumeric(msisdn.toString()) || msisdn.startsWith("0")){
				msisdnsErr.add(msisdn);
			}else{
				
				
				ListSegmentation listSegmentation =  new ListSegmentation();
				ListSegmentationPK listSegmentationPK =  new ListSegmentationPK();
				listSegmentationPK.setIdList(listDetail.getIdList());
				listSegmentationPK.setMsisdn(msisdn);
				listSegmentation.setId(listSegmentationPK);
				listSegmentationBeanLocal.persistListSegmentation(listSegmentation);

			}
			
			if(!msisdnsErr.isEmpty()){
				GenerateCsv generateCsv = new GenerateCsv();
				generateCsv.generateCsvFile(msisdnsErr, fileName.concat("_ERR"), path);

			}
		}
		
		FilesUtil filesUtil = new FilesUtil();
		filesUtil.moveFile(path.concat(fileName), path.concat("Processados/").concat(fileName));
		
		listSegmentStatus.setNumRowsNotProcess(msisdnsErr.size());
		listSegmentStatus.setProcessado(true);
		listSegmentStatus.setNumRowsProcess(msisdns.size() - msisdnsErr.size() );
		
		listDetail.setListActive(true);
		listDetail.setListStatus("0");
		listDetailBeanLocal.mergeListDetail(listDetail);
		return listSegmentStatus;
	}

	public void excluirLista(ListDetail listDetail) throws Exception{
		
		ListSegmentation listSegmentation = new ListSegmentation();
		ListSegmentationPK id = new ListSegmentationPK();
		id.setIdList(listDetail.getIdList());
		listSegmentation.setId(id);
		listSegmentationBeanLocal.removeListSegmentationByListDetail(listSegmentation);
		listDetailBeanLocal.removeListDetail(listDetail);
	
	}
	
	public void alterarLista(ListDetail listDetail) {
		listDetailBeanLocal.mergeListDetail(listDetail);
		
	}
	
	private  boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}


	public void persistListSegmentation(ListSegmentation listSegmentation) {
		listSegmentationBeanLocal.persistListSegmentation(listSegmentation);
		
	}


	public void persistListSegmentation(List<ListSegmentation>listSegmentations) {
		for(ListSegmentation listSegmentation:listSegmentations){
			listSegmentationBeanLocal.persistListSegmentation(listSegmentation);
		}
		
	}

	public void listSegmentationProcess(List<ListSegmentation>listSegmentations) {
		
		listSegmentationProcess.execute(listSegmentations);
		
	}

	
	public void listSegmentationProcess(Scanner scanner,Long idList, String path) {
		
		listSegmentationProcess.execute(scanner, idList,path);
		
	}
	
	
}
