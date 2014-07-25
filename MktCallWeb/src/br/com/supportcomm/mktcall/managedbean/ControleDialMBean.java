package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.controledialout.ControleDialOutService;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;



/**
 * Classe backingbean de cadastro de spot
 * 
 * 
 * 
 */
@ManagedBean(name="controleDialMBean")
@SessionScoped
public class ControleDialMBean extends AbstractManagedBean {
	private ListaCampanhaDialOutVO controleDialSelecionada;
	private ListaCampanhaDialOutVO[] controleDialSelecionadas;
	private String acao;
	private final static String[] status,isAtivo;
	private final static String[] statusValue;
	private final static Boolean[] ativoValue;
	
	
	
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
	

	@EJB private ControleDialOutService controleDialOutService; 
	
	@EJB private CampanhaService campanhaService;
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * Chamada inicial da pagina de spots
	 * 
	 * @throws IOException
	 */

	public ControleDialMBean() {
		statusOptions = createFilterOptionsStatus(statusValue, status);
		ativoOptions  = createFilterOptions(ativoValue, isAtivo);
		
	}

	public String iniciarDial() throws IOException {
		return "controleDial";

	}


	public List<ListaCampanhaDialOutVO> getCampanhaAll(){
		return controleDialOutService.getControleDialOutAll();
	}
	
	

	
	
	public void alterarLista(ActionEvent event){
		
		Campanha campanha =   campanhaService.getCampanhaId(controleDialSelecionada.getIdCampanha()).get(0);
		
		if(campanha!=null){
			controleDialOutService.ativarDialOut(campanha.getIdCampanha());
			this.acao="Sucesso!!";
		} else{
			this.acao="Ops algum problema!!";
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

	public ListaCampanhaDialOutVO getControleDialSelecionada() {
		return controleDialSelecionada;
	}

	public void setControleDialSelecionada(
			ListaCampanhaDialOutVO controleDialSelecionada) {
		this.controleDialSelecionada = controleDialSelecionada;
	}

	public ListaCampanhaDialOutVO[] getControleDialSelecionadas() {
		return controleDialSelecionadas;
	}

	public void setControleDialSelecionadas(
			ListaCampanhaDialOutVO[] controleDialSelecionadas) {
		this.controleDialSelecionadas = controleDialSelecionadas;
	}	
}
