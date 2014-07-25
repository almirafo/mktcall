package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.ListDetail;
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
	private ListDetail listaSegmentadaSelecionada;
	private ListDetail[] listaSegmentadaSelecionadas;
	private String acao;
	private final static String[] status,isAtivo;
	private final static String[] statusValue;
	private final static Boolean[] ativoValue;
	
	
	private String fileName,segmentName;

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


	
	
	
	
	private Logger logger = Logger.getLogger(getClass());

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
		return "listaSegmentada";

	}


	public List<String> listaArquivos(){
		
		return listSegmentService.fileList(super.getMessage("localArquivoSegmentado"));
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
					

					Timestamp stamp = new Timestamp(System.currentTimeMillis());
					ListDetail listDetail=  new ListDetail();
					listDetail.setFilename(fileName);
					listDetail.setListName(segmentName);
					listDetail.setListStatus("2");
					listDetail.setListActive(false);
					listDetail.setDatetimeOperation(stamp);
					
					listDetailService.persistListDetail(listDetail);

						
					this.acao = "Arquivo em processamento. Aguarde";
					
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
}
