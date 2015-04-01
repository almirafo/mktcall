package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.fileupload.FileUpload;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.entity.ListSegmentation;
import br.com.supportcomm.mktcall.entity.ListSegmentationPK;
import br.com.supportcomm.mktcall.service.config.ConfigService;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.service.listdetail.ListDetailService;
import br.com.supportcomm.mktcall.service.listsegment.ListSegmentService;
//import br.com.supportcomm.mktcall.tools.ListSegmentStatus;


/**
 * Classe backingbean de cadastro de spot
 * 
 * 
 * 
 */
@ManagedBean(name="listaSegmentadaMBean")
@SessionScoped
public class ListaSegmentadaMBean extends AbstractManagedBean {
	private static final String DIGITOS = "1234567890";
	private ListDetail listaSegmentadaSelecionada;
	private ListDetail[] listaSegmentadaSelecionadas;
	private String acao;
	private final static String[] status,isAtivo;
	private final static String[] statusValue;
	private final static Boolean[] ativoValue;
	private static final int MAXDIGIT11 = 11;
	private static final int MAXDIGIT10 = 10;
	
	private String fileName,segmentName,origem;
	private FileUpload fup;
	static {
		status = new String[3];
		status[0] = "Processo Finalizado";
		status[1] = "Processo Abortado";
		status[2] = "Processo em Andamento";
		
		statusValue = new String[3];
		statusValue[0] = "0";
		statusValue[1] = "1";
		statusValue[2] = "2";
		
		isAtivo = new String[2];
		isAtivo[0] = "Ativa";
		isAtivo[1] = "Inativa";

		ativoValue = new Boolean[2];
		ativoValue[0] = true;
		ativoValue[1] = false;		
		

	}
	private SelectItem[] statusOptions;
	private SelectItem[] ativoOptions;


	

	@EJB
	private UserHistoryService historyService;
	


	
	@EJB private ListSegmentService listSegmentService; 
	@EJB private ListDetailService listDetailService; 
	@EJB private ConfigService configService;

	
	
	
	
	private Logger logger = Logger.getLogger(getClass());
	private UploadedFile Arquivo;



	/**
	 * Chamada inicial da pagina de spots
	 * 
	 * @throws IOException
	 */

	public ListaSegmentadaMBean() {
		statusOptions = createFilterOptionsStatus(statusValue, status);
		ativoOptions  = createFilterOptions(ativoValue, isAtivo);
		
	}

	public String iniciarListaSegmentada() throws IOException {
		
		this.setOrigem("1");
		this.listDetailService.getListDetailAll();
		this.setFileName("");
		this.setSegmentName("");
		return "listaSegmentada";

	}


	public List<String> listaArquivos(){
		
		return listSegmentService.fileList(configService.getValueByIndentify("localArquivoSegmentado"));
	}

	public List<ListDetail> getListDetailAll(){
		return listDetailService.getListDetailAll();
	}
	
	
	public void excluirLista(ActionEvent event){
		
		try {
			listSegmentService.excluirLista(this.listaSegmentadaSelecionada);
			this.acao="Exclusão feita com Sucesso!!";
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "- message: " + e.getMessage());
			if(e.getCause().getCause().getMessage()!=null && e.getCause().getCause().getMessage().contains( "ConstraintViolationException"));
				super.addError("Erro :" + "Existe Campanha Associada a esta Lista");
				this.acao="Erro :" + "Existe Campanha Associada a esta Lista";
		}
		
		
	}
	
	
	public void alterarLista(ActionEvent event){
		ListDetail listDetail1 =   listDetailService.findListDeailBySegmentName(listaSegmentadaSelecionada.getListName());
		if(listDetail1!=null){
			if(listDetail1.getIdList() != listaSegmentadaSelecionada.getIdList() && listaSegmentadaSelecionada.getListName().equalsIgnoreCase(listDetail1.getListName())  ){
			
				super.addError("Nome do Segmento já Existe");
				this.acao = "Nome do Segmento já Existe";
			}else{
				listSegmentService.alterarLista(this.listaSegmentadaSelecionada);
				this.acao="Alteração feita com Sucesso!!";
			}
			
		}else{
			listSegmentService.alterarLista(this.listaSegmentadaSelecionada);
			this.acao="Alteração feita com Sucesso!!";
		}	
	}
	
	
	public void processarArquivoLocal(FileUploadEvent  event){
		try{
					InputStream inputStream = event.getFile().getInputstream();
		            int read = 0;
		            StringBuffer sb = new StringBuffer();
		            List<String> msisdnsErr =  new ArrayList<String>();
		            this.setArquivo(event.getFile());	
		            while ((read = inputStream.read()) != -1) {
		            	System.out.println(Character.toString ((char) read));
		            	if (DIGITOS.contains(Character.toString ((char) read ))){
							sb.append( Character.toString ((char) read));
						}else if (Character.toString ((char) read ).contains(";")){
							System.out.println(sb.toString());
							if (    sb.length() ==0 && !isNumeric(sb.toString()) 
								&&  sb.toString().startsWith("0") 
								&&  !(sb.length() ==MAXDIGIT11)){
								msisdnsErr.add(sb.toString());
							}
							sb = new StringBuffer();
						}
		            }
		            inputStream.close();
		            if(!msisdnsErr.isEmpty()){
		            	this.acao = "Arquivo com "+ msisdnsErr.size() +"msisdn com Erro(s)";
		            }else{
		            	this.acao ="Arquivo Preparado";
		            }
		            
		            
		}catch(Exception e){
			this.acao="Erro :" + e.getMessage();
			logger.error(this.getClass().getName() + "- message: " + e.getMessage());
		}
	}
	
	public void processarArquivo(ActionEvent event){
//		ListSegmentStatus listSegmentStatus= new ListSegmentStatus();
		
		super.setSucesso(true);

		try {
			
			if (super.isNullOrBlank(this.segmentName.trim())) {
				this.acao="Nome da Lista" + " " + super.getMessage("campoObrigatorio");

			}else {
			
				ListDetail listDetail1 =   listDetailService.findListDeailBySegmentName(segmentName);
				if(listDetail1!=null){
					this.acao = "Nome do Segmento já Existe";
				}else{
					
					
					int read=0;
					InputStream inputStream = null;
					UploadedFile up = null;
					StringBuffer sb = new StringBuffer();
					

					Timestamp stamp = new Timestamp(System.currentTimeMillis());
					ListDetail listDetail=  new ListDetail();
					listDetail.setListName(segmentName);
					listDetail.setListActive(false);
					listDetail.setDatetimeOperation(stamp);

					if (this.getOrigem().equals("1")) {
						listDetail.setFilename(fileName);
						listDetail.setListStatus("2");
					}else{
						listDetail.setFilename(this.getArquivo().getFileName());
						listDetail.setListStatus("9");
						inputStream = this.getArquivo().getInputstream();
						up = this.getArquivo();
					}
					
					listDetailService.persistListDetail(listDetail);
					
					List<ListSegmentation> listSegmentations = new ArrayList<>();

					
					
					if (!this.getOrigem().equals("1")){
						while ((read = inputStream.read()) != -1) {
							
							if (DIGITOS.contains(Character.toString ((char) read ))){
								sb.append( Character.toString ((char) read));
							}else if (Character.toString ((char) read ).contains(";")){
								System.out.println(sb.toString());
								
								ListSegmentation ls = new ListSegmentation();
								ls.setCustom1(sb.toString());
								
								ListSegmentationPK lpk = new ListSegmentationPK();
								lpk.setIdList(listDetail.getIdList());
								lpk.setMsisdn(sb.toString());
								ls.setId( lpk);
								ls.setDatetimeOperation(stamp);
								// So vai processar os msisdns sem problemas
								if (    !(sb.length() ==0 && !isNumeric(sb.toString()) 
										&&  sb.toString().startsWith("0") 
										&&  !(sb.length() ==MAXDIGIT11 || sb.length() ==MAXDIGIT10)) || sb.toString().startsWith("192.")){
									listSegmentations.add(ls);
									}
								
								sb = new StringBuffer();
							}
						}
						
						listSegmentService.listSegmentationProcess(listSegmentations);
						
					    listDetail.setListActive(true);
						listDetail.setListStatus("0");
						listDetailService.mergeListDetail(listDetail);
					}
					this.acao = this.getOrigem().equals("1")? "Arquivo em processamento. Aguarde":"Dados prontos para serem utilizados";
					
					this.setOrigem("1");
					this.setFileName("");
					this.setSegmentName("");
					this.setFup(new FileUpload());
				}
			}
			 
		} catch (Exception e) {
			
			
			this.acao="Erro :" + e.getMessage();
			logger.error(this.getClass().getName() + "- message: " + e.getMessage());
			
		}
	}
	


	private SelectItem[] createFilterOptions(Boolean[] values, String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Todos");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(values[i].toString(), data[i]);
		}

		return options;
	}

	
	private SelectItem[] createFilterOptionsStatus(String[] values, String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Todos");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(values[i].toString(), data[i]);
		}

		return options;
	}
	
	
	
	public SelectItem[] getStatusOptions() {
		return statusOptions;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	
	public ListDetail getListaSegmentadaSelecionada() {
		
		return this.listaSegmentadaSelecionada;
	}

	public void setListaSegmentadaSelecionada(ListDetail listaSegmentadaSelecionada) {
		
		this.listaSegmentadaSelecionada = listaSegmentadaSelecionada;
	}
	
	
//-------------------------------
//	private  boolean isNumeric(String str)
//	{
//	    for (char c : str.toCharArray())
//	    {
//	        if (!Character.isDigit(c)) return false;
//	    }
//	    return true;
//	}

	


	public ListDetail[] getListaSegmentadaSelecionadas() {
		return listaSegmentadaSelecionadas;
	}

	public void setListaSegmentadaSelecionadas(
			ListDetail[] listaSegmentadaSelecionadas) {
		this.listaSegmentadaSelecionadas = listaSegmentadaSelecionadas;
	}

	public SelectItem[] getAtivoOptions() {
		return ativoOptions;
	}

	public void setAtivoOptions(SelectItem[] ativoOptions) {
		this.ativoOptions = ativoOptions;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}	
	
	
	public UploadedFile getArquivo() {
		return Arquivo;
	}

	public void setArquivo(UploadedFile Arquivo) {
		this.Arquivo = Arquivo;
	}
	
	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	private  boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}

	public FileUpload getFup() {
		return fup;
	}

	public void setFup(FileUpload fup) {
		this.fup = fup;
	}
}
