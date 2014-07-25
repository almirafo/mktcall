package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.ListaGeral;
import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.service.listageral.ListaGeralService;
import br.com.supportcomm.mktcall.vo.ListaFidelizadaVO;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.util.FileUtil;

/**
 * Classe backingbean de cadastro de spot
 * 
 * 
 * 
 */
@ManagedBean
@SessionScoped
public class ListaFidelizadaMBean extends AbstractManagedBean {

	private final static String[] status;
	private final static Boolean[] statusValue;

	static {
		status = new String[2];
		status[0] = "ativada";
		status[1] = "desativada";

		statusValue = new Boolean[2];
		statusValue[0] = true;
		statusValue[1] = false;

	}
	private SelectItem[] statusOptions;

	@EJB
	private ListaGeralService listaService;

	@EJB
	private CampanhaService campanhaService;
	@EJB
	private UserHistoryService historyService;
	private Campanha campanha;
	private ListaFidelizadaVO listaFidelizadaResult;
	private List<Campanha> campanhaList;
	private List<String> msisdnList;
	private List<ListaFidelizadaVO> listaFidelizadaVO;

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * Chamada inicial da pagina de spots
	 * 
	 * @throws IOException
	 */

	public ListaFidelizadaMBean() {
		statusOptions = createFilterOptions(statusValue, status);
	}

	public String iniciarListaFidelizada() throws IOException {
		return "listaFidelizada";

	}

	public List<ListaFidelizadaVO> getListaFidelizadaVO() {

		listaFidelizadaVO = listaService.getListaGeralCustomizada();

		return listaFidelizadaVO;

	}

	public void excluirListaFidelizada() {

		try {
			listaService.removeListaGeralByIdCampanha(getListaFidelizadaResult().getIdCampanha());
			saveHistory(super.getMessage("listaDeletado") + " -- lista fidelizada --", campanha.getIdCampanha(), null);
			super.addInfo(super.getMessage("listaFidelizadaExcluida"));
		}
		catch (Exception e) {
			super.addError(e.getMessage());
			logger.error("Método xxx - message: " + e.getMessage());

		}

	}

	public void saveListaFidelizada(ActionEvent event) {

		try {

			if (campanha.getIdCampanha() == null) {
				super.addError(super.getMessage("selecioneCampanha"));
				return;
			}

			if (msisdnList.isEmpty()) {
				super.addError(super.getMessage("semResultados"));
				return;
			}

			for (String msisdn : msisdnList) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				ListaGeral lg = new ListaGeral();
				lg.setInsertedDate(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
				lg.setMsisdn(msisdn);
				lg.setIdCampanha(campanha.getIdCampanha());
				listaService.persistListaGeral(lg);

			}

			msisdnList = new ArrayList<String>();
			saveHistory(super.getMessage("listaHistorico") + " -- lista fidelizada --", campanha.getIdCampanha(), null);

			super.addInfo(super.getMessage("listaFidelizadaSalva"));

		}
		catch (Exception e) {
			super.addError(e.getMessage());
			logger.error("Método salvarSpot - message: " + e.getMessage());
			logger.error("Método salvarSpot - cause: " + e.getCause());
		}

	}

	/**
	 * Método de upload de arquivo
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			msisdnList = new ArrayList<String>();
			List<String> lista = FileUtil.readFileTxt(event.getFile().getInputstream());
			int cont = 0;
			for (String s : lista) {
				cont++;
				if (!s.equals("")) {
					try {
						Long.parseLong(s);
						if (s.length() < Integer.parseInt(super.getMessage("msisdnSize"))) {
							if (listaService.getListaGeralExistMsisdn(s).isEmpty()) {
								msisdnList.add(s);
							}
						}
						else {
							throw new MktCallException("Problema ao ler arquivo. Linha " + cont + ".");
						}
					}
					catch (NumberFormatException e) {
						throw new MktCallException("Problema ao ler arquivo. Linha " + cont + ".");
					}

				}
			}
			super.addInfo(super.getMessage("numerosAdicionadosComSucesso"));
		}
		catch (IOException e) {
			super.addError(e.getMessage());
			logger.error("Método salvarSpot - message: " + e.getMessage());
			logger.error("Método salvarSpot - cause: " + e.getCause());
		}
		catch (MktCallException e) {
			super.addError(e.getMessage());
			logger.error("Método salvarSpot - message: " + e.getMessage());
			logger.error("Método salvarSpot - cause: " + e.getCause());
		}
	}

	public void saveHistory(String action, Long idCampanha, Long idSpot) {

		try {

			UserHistory userHistory = new UserHistory();
			userHistory = new UserHistory();
			userHistory.setDateInsered(new Timestamp(System.currentTimeMillis()));
			userHistory.setAction(action);
			userHistory.setIdCampanha(idCampanha);
			userHistory.setIdSpot(idSpot);
			userHistory.setIdUserAccess(super.getUserAccess().getIdUserAccess());
			userHistory.setUserLogin(super.getUserAccess().getLogin());
			userHistory.setUserType(super.getUserAccess().getUserType().getIdUserType());

			historyService.persistUserHistory(userHistory);

		}
		catch (Exception e) {
			logger.error(e);
		}

	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public List<Campanha> getCampanhaList() {
		campanhaList = campanhaService.getCampanhaAll();
		return campanhaList;
	}

	public void setCampanhaList(List<Campanha> campanhaList) {
		this.campanhaList = campanhaList;
	}

	public List<String> getMsisdnList() {
		return msisdnList;
	}

	public void setMsisdnList(List<String> msisdnList) {
		this.msisdnList = msisdnList;
	}

	public ListaFidelizadaVO getListaFidelizadaResult() {
		return listaFidelizadaResult;
	}

	public void setListaFidelizadaResult(ListaFidelizadaVO listaFidelizadaResult) {
		this.listaFidelizadaResult = listaFidelizadaResult;
	}

	public void setListaFidelizadaVO(List<ListaFidelizadaVO> listaFidelizadaVO) {
		this.listaFidelizadaVO = listaFidelizadaVO;
	}

	private SelectItem[] createFilterOptions(Boolean[] values, String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(values[i].toString(), data[i]);
		}

		return options;
	}

	public SelectItem[] getStatusOptions() {
		return statusOptions;
	}

}
