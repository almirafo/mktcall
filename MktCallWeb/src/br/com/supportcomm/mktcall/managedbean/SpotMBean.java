package br.com.supportcomm.mktcall.managedbean;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.Anunciante;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.InitialConfig;
import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.entity.SpotAudioFile;
import br.com.supportcomm.mktcall.entity.SpotTipo;
import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.service.agencia.AgenciaService;
import br.com.supportcomm.mktcall.service.anunciante.AnuncianteService;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.service.initialconfig.InitialConfigService;
import br.com.supportcomm.mktcall.service.spot.SpotService;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.util.Constantes;
import br.com.supportcomm.mktcall.util.FileUtil;
import br.com.supportcomm.mktcall.util.ScpTo;

/**
 * Classe backingbean de cadastro de spot
 * 
 * @author andre.vilhalba
 * 
 */
@ManagedBean
@SessionScoped
public class SpotMBean extends AbstractManagedBean {

	@EJB
	private AgenciaService agenciaService;
	@EJB
	private AnuncianteService anuncianteService;
	@EJB
	private SpotService spotService;
	@EJB
	private CampanhaService campanhaService;
	@EJB
	private UserHistoryService historyService;
	
	@EJB InitialConfigService initialConfigService;
	
	private Spot spotSelecionado;
	private List<UploadedFile> listaArquivos;
	private List<SelectItem> listaAnunciantes;
	private Long idAgencia;
	private Long idAnunciante;
	private Logger logger = Logger.getLogger(SpotMBean.class);
	private int audioDigit;
	private int audioOrder = 1;
	private List<SpotAudioFile> spotAudioFileList = new ArrayList<SpotAudioFile>();
	private List<Integer> audioDigitList = new ArrayList<Integer>();
	private List<Integer> audioOrderList = new ArrayList<Integer>();

	
	
	public List<SelectItem> getListaAnunciantes() throws MktCallException {
		this.setListaAnunciantes(new ArrayList<SelectItem>());
		List<Anunciante> listAnunciantes = new ArrayList<Anunciante>();
		listAnunciantes = anuncianteService.getAnuncianteAgencia(this.idAgencia.longValue());
		for (Anunciante an : listAnunciantes) {
			this.listaAnunciantes.add(new SelectItem(an.getIdAnunciante(), an.getRazaoSocial()));
		}
		return listaAnunciantes;
	}

	/**
	 * Método de carregamento da lista da datatable através do tipo do usuário
	 * logado
	 * 
	 * @return
	 */
	public List<Spot> getListaSpots() throws MktCallException {
		List<Spot> lista = new ArrayList<Spot>();
		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			lista = spotService.getSpotAll();
		}
		else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
			lista = spotService.getSpotAgenciaUsuario(super.getUserAccess().getIdUserAccess());
		}
		else {
			lista = spotService.getSpotAgenciaUsuario(super.getUserAccess().getIdUserAccess());
		}
		return lista;
	}

	/**
	 * Carrega lista de tipo de spot da combobox de cadastro
	 * 
	 * @return
	 * @throws MktCallException
	 */
	public List<SelectItem> getListaSpotTipos() throws MktCallException {
		List<SelectItem> lista = new ArrayList<SelectItem>();

		List<SpotTipo> listaSpotTipo = spotService.getSpotTipoAll();
		for (SpotTipo c : listaSpotTipo) {
			lista.add(new SelectItem(new Integer(c.getCodeSpotTipo()), c.getNomeSpotTipo()));
		}
		return lista;
	}

	public SpotMBean() {
		super();

	}

	/**
	 * Chamada inicial da pagina de spots
	 * 
	 * @throws IOException
	 */
	public String iniciarSpot() throws IOException, MktCallException {
		spotSelecionado = new Spot();
		spotSelecionado.setAnunciante(new Anunciante());
		// spotSelecionado.setSpotTipo(new SpotTipo());
		this.preencherIdAgencia();
		
		return "spot";
	}

	/**
	 * método para um novo spot
	 */
	public void iniciar() throws MktCallException {
		this.getListaSpots();
		spotSelecionado = new Spot();
		spotSelecionado.setAnunciante(new Anunciante());
		// spotSelecionado.setSpotTipo(new SpotTipo());
		this.listaArquivos = new ArrayList<UploadedFile>();
		this.idAnunciante = null;
		this.preencherIdAgencia();
	}

	/**
	 * Método de upload de arquivo
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {

		audioOrderList = new ArrayList<Integer>();
		int i = 1;

		if (this.getListaArquivos().isEmpty()) this.listaArquivos = new ArrayList<UploadedFile>();

		super.addWarn(super.getMessage("sucesso") + ": " + event.getFile().getFileName() + ", " + super.getMessage("uploadPronto"));
		boolean nomeExistente = false;
		for (UploadedFile up : this.getListaArquivos()) {
			audioOrderList.add(i);
			if (up.getFileName().replace(" ", "_").equals(event.getFile().getFileName().replace(" ", "_"))) {
				nomeExistente = true;
			}
			i++;
		}

		if (nomeExistente) {
			super.addError(super.getMessage("audioDuplicado"));
			super.setSucesso(false);
		}
		else {
			this.getListaArquivos().add(event.getFile());
		}

	}

	/**
	 * Exclusão lógica, setar o campo status para inativo
	 */
	public String excluirSpot() {
		try {
			List<Campanha> campanhaList = campanhaService.getCampanhaSpot(this.getSpotSelecionado().getIdSpot());

			if (!campanhaList.isEmpty()) {
				super.addError(super.getMessage("campanhaexist"));
				return "spot?faces-redirect=true";
			}

			List<SpotAudioFile> spotAudioList = new ArrayList<SpotAudioFile>();

			spotAudioList = spotService.getSpotAudioFileBySpotId(this.getSpotSelecionado().getIdSpot());

			if (!spotAudioList.isEmpty()) {
				for (SpotAudioFile spotAudio : spotAudioList) {
					spotService.removeSpotAudioFile(spotAudio);

					File file = new File(super.getMessage("localArquivo") + spotAudio.getSpotFilename());
					// File file2 = new
					// File(super.getMessage("localArquivo") + "desativado_"
					// + spotAudio.getSpotFilename());
					file.delete();
					saveHistory(super.getMessage("spotDeletado") +"- Arquivo a ser cancelado -- " + spotAudio.getSpotFilename(), null, this.getSpotSelecionado().getIdSpot() );
				}
			}

			spotService.removeSpot(this.getSpotSelecionado());

			saveHistory(super.getMessage("spotDeletado")  + " " + this.getSpotSelecionado().getSpotName(), null, this.getSpotSelecionado().getIdSpot());

			super.addInfo(super.getMessage("spotExcluido"));
			this.iniciar();
		}
		catch (MktCallException e) {
			super.addError(super.getMessage("problemaSpots"));
			logger.error("Método excluirSpot - message: " + e.getMessage());
		}
		catch (Exception e) {
			super.addError(super.getMessage("problemaSpots"));
			logger.error("Método excluirSpot: " + e);
		}
		
		return "spot?faces-redirect=true";
	}

	/**
	 * Método salvar/alterar o objeto Utiliza o requestContext para fechar ou
	 * não, casa haja problema, o modal de cadastro
	 */
	public String salvarSpot() {
		RequestContext context = RequestContext.getCurrentInstance();
		super.setSucesso(true);
		try {
			
			//verificando internal			
			if(this.getSpotSelecionado().getInternal()){
				this.getSpotSelecionado().setServerSms("");
			}
			//verificando tipo de spot
			if(!this.getSpotSelecionado().getSpotType().equals(Constantes.TIPO_SPOT_SMS_ASSINATURA.getValor())){
				this.getSpotSelecionado().setServerSms("");
				this.getSpotSelecionado().setInternal(false);
				this.getSpotSelecionado().setMessageSms("");
				this.getSpotSelecionado().setProtocol("");
			}
			
			// se o stop tipo for SIMPLES
			if(this.getSpotSelecionado().getSpotType().equals(Constantes.TIPO_SPOT_SIMPLES.getValor())){
				this.getSpotSelecionado().setSpotConfirmDigit(null);
			}		
			
			if (super.isNullOrBlank(this.getSpotSelecionado().getSpotName())) {
				super.setSucesso(false);
				showMessage(super.getMessage("nomeSpot") + " " + super.getMessage("campoObrigatorio"));
			}
			else if ((super.isNullOrBlank(this.getSpotSelecionado().getSpotType()))  || this.getSpotSelecionado().getSpotType().intValue() <= 0) {
				super.setSucesso(false);
				showMessage(super.getMessage("spotTipo") + " " + super.getMessage("campoObrigatorio"));
			}
			else if (super.isNullOrEmpty(this.getListaArquivos()) && super.isNullOrBlank(this.getSpotSelecionado().getIdSpot())) {
				super.setSucesso(false);
				showMessage(super.getMessage("file") + " " + super.getMessage("campoObrigatorio"));
			}
			else if (!this.getSpotSelecionado().getSpotType().equals(Constantes.TIPO_SPOT_SIMPLES.getValor()) && (this.getSpotSelecionado().getSpotConfirmDigit().isEmpty() || this.getSpotSelecionado().getSpotConfirmDigit().equals(""))) {
				super.setSucesso(false);
				showMessage(super.getMessage("digitoConfirmacao") + " " + super.getMessage("campoObrigatorio"));									
			} else if (this.getSpotSelecionado().getSpotType().equals(Constantes.TIPO_SPOT_SMS_ASSINATURA.getValor()) 
					&& this.getSpotSelecionado().getProtocol().equals("0")) {
			 	super.setSucesso(false);
				showMessage(super.getMessage("metodo") + " " + super.getMessage("campoObrigatorio"));
			} else if (this.getSpotSelecionado().getSpotType().equals(Constantes.TIPO_SPOT_SMS_ASSINATURA.getValor())
					&& this.getSpotSelecionado().getInternal().equals(false) && this.getSpotSelecionado().getServerSms().isEmpty()) {
				super.setSucesso(false);
				showMessage(super.getMessage("serverSMS") + " " + super.getMessage("campoObrigatorio"));	
			}
			else {
				this.getSpotSelecionado().setAnunciante(anuncianteService.getAnuncianteId(Long.parseLong(this.idAnunciante.toString())).get(0));
				boolean spotFilenameExistente = false;

				if (super.isNullOrBlank(this.getSpotSelecionado().getIdSpot())) {

					if (!spotService.getSpotByName(this.getSpotSelecionado().getSpotName()).isEmpty()) {
						super.setSucesso(false);
						showMessage(super.getMessage("nomeexist") + " " + super.getMessage("campoObrigatorio"));
						return "";
					}

					if (!super.isNullOrEmpty(this.getListaArquivos())) {
						for (int i = 0; i < this.getListaArquivos().size(); i++) {
							this.getListaArquivos().get(0).getFileName().replace(" ", "_");

							if (!spotService.getSpotAudioFileSpotFileNameExistente(this.getListaArquivos().get(i).getFileName()).isEmpty()) {
								spotFilenameExistente = true;
							}
						}
					}

					if (spotFilenameExistente) {
						super.addError(super.getMessage("audioDuplicado"));
						super.setSucesso(false);
						return "";
					}

					this.getSpotSelecionado().setOperationDate(new Timestamp(new Date().getTime()));
					this.spotSelecionado = spotService.persistSpot(this.getSpotSelecionado());
					saveHistory(super.getMessage("spotSalvo2") + " " + this.spotSelecionado.getSpotName(), null, this.spotSelecionado.getIdSpot() );
				}
				else {
					this.spotSelecionado = spotService.mergeSpot(this.getSpotSelecionado());
					saveHistory(super.getMessage("spotHistorico") + " " + this.spotSelecionado.getSpotName(), null, this.spotSelecionado.getIdSpot() );
				}
				if (!super.isNullOrEmpty(this.getListaArquivos())) {
					for (int i = 0; i < this.getListaArquivos().size(); i++) {
						UploadedFile up = this.getListaArquivos().get(i);
						SpotAudioFile audioFile = new SpotAudioFile();
						audioFile.setDigits(2);
						audioFile.setOperationDate(new Timestamp(new Date().getTime()));
						audioFile.setSpot(getSpotSelecionado());
						audioFile.setSpotAudioDescription("desc spot");
						audioFile.setSpotAudioOrder(i + 1);
						audioFile.setSpotFilename(up.getFileName().replaceAll(" ", "_"));
						spotService.mergeSpotAudioFile(audioFile);
						String fileNameReplaced = up.getFileName().replaceAll(" ", "_");
						FileUtil.writeToFile(super.getMessage("localArquivo") + fileNameReplaced, up.getInputstream(), true);
						saveHistory(super.getMessage("spotHistorico") +"- Novo Arquivo -- " + fileNameReplaced, null, this.getSpotSelecionado().getIdSpot() );
						
						//replicateByFTP(up.getFileName().replaceAll(" ", "_"),super.getMessage("localArquivo"));
					}
				}
				this.iniciar();
				logger.info("Spot salvo com sucesso! " + this.spotSelecionado.getSpotName());
				showMessage(super.getMessage("spotSalvo"), FacesMessage.SEVERITY_INFO);
			}
		}
		catch (IOException e) {
			super.setSucesso(false);
			showMessage(super.getMessage("problemaSpots"));
			logger.error("Método salvarSpot - message: " + e.getMessage());
			spotService.removeSpot(this.getSpotSelecionado());
			return "spot?faces-redirect=false";
		}
		catch (MktCallException e) {
			super.setSucesso(false);
			showMessage(super.getMessage("problemaSpots"));
			logger.error("Método salvarSpot - message: " + e.getMessage());
			spotService.removeSpot(this.getSpotSelecionado());
			return "spot?faces-redirect=false";
		}
		context.addCallbackParam("sucesso", super.isSucesso());
		
		return "spot?faces-redirect=true";
	}
	/**
	 * replica por FTP o arquivo de audio configurado para tocar<br/>
	 * no spot.<br/>
	 * @param file
	 * @param filePath
	 */
	private void replicateByFTP(String file, String filePath) {
		if(initialConfigService.getEnableFTP()){
			List<InitialConfig>  initialConfigFTPs = initialConfigService.getInitialConfigByType("file");
			for(InitialConfig initialConfigFTP : initialConfigFTPs){
				ScpTo scpTo = new ScpTo();
				String loginFtp = initialConfigService.getLoginFTP();
				String[] l= loginFtp.split("/");
				String user = l[0];
				String pass = l[1];
				
				
				scpTo.execute(file, user,pass , initialConfigFTP.getIp(), initialConfigService.getOriFTP().concat("/"), initialConfigFTP.getPathFile().concat("/"));
			}
		}
		
		
	}

	private void showMessage(String messageToShow) {
	    showMessage(messageToShow, FacesMessage.SEVERITY_ERROR);										
	}
	
	private void showMessage(String messageToShow, Severity severity) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity,messageToShow,""));
	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);											
	}	

	/**
	 * Método para bloquear o cadastro de valor de spot, sendo somente feito
	 * pelo tipo de usuário administrador
	 * 
	 * @return
	 */
	public boolean getReadOnlyValorTarifa() {
		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			return false;
		}
		return true;
	}

	public List<SelectItem> getProtocolo() {
		
		List<SelectItem> listItems = new ArrayList<SelectItem>();
		
		if(!super.isNullOrBlank(this.getSpotSelecionado().getSpotType())){
			if(this.getSpotSelecionado().getSpotType() == 3){
				listItems.add(new SelectItem(1, "get"));
				listItems.add(new SelectItem(2, "post"));
				
			}
		}
		
		return listItems;
		
	}
	
	
	/**
	 * Método de carregamento da lista do combobox de agências através do tipo
	 * do usuário logado
	 * 
	 * @return
	 */
	public List<SelectItem> getListaAgencias() throws MktCallException {
		List<Agencia> lista = new ArrayList<Agencia>();
		List<SelectItem> listItems = new ArrayList<SelectItem>();
		this.setListaAnunciantes(new ArrayList<SelectItem>());
		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			lista = agenciaService.getAgenciaAll();
		}
		else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
			lista = agenciaService.getAgenciaUsuario(this.getUserAccess().getIdUserAccess());
			// this.idAgencia = lista.get(0).getIdAgencia();
		}
		else {
			// lista.add(this.getUserAccess().getAnunciantes().get(0).getAgencia());
			/*
			 * this.idAgencia = this.getUserAccess() .getAnunciantes() .get(0)
			 * .getAgencia() .getIdAgencia();
			 */
		}
		for (Agencia a : lista) {
			listItems.add(new SelectItem(a.getIdAgencia(), a.getRazaoSocial()));
		}
		return listItems;
	}

	public void visualizarArquivos(ActionEvent event) {

		setSpotAudioFileList(spotService.getSpotAudioFileBySpotId(this.getSpotSelecionado().getIdSpot()));

	}

	public void salvarAquivos() {

		try {
			for (SpotAudioFile spotAudioFile : this.spotAudioFileList) {
				spotAudioFile.setSpotFilename(spotAudioFile.getSpotFilename().replaceAll(" ", "_"));
				spotService.mergeSpotAudioFile(spotAudioFile);
				saveHistory(super.getMessage("arquivoModificado") + "- Arquivo -- " + spotAudioFile.getSpotFilename(), null, this.getSpotSelecionado().getIdSpot() );
			}

			super.addInfo(super.getMessage("spotSalvo"));
			
			

		}
		catch (Exception e) {
			logger.error("Método salvarAquivos - message: " + e);
		}
	}

	/**
	 * método utilizado para preencher automaticamente, dependendo do tipo de
	 * usuário logado, o id da agência e/ou id do anunciante a serem tilizados
	 */
	public void preencherIdAgencia() throws MktCallException {

		this.idAgencia = 0L;

		try {

			if (!super.isNullOrBlank(this.getSpotSelecionado().getAnunciante().getIdAnunciante()) && this.getSpotSelecionado().getAnunciante().getIdAnunciante().intValue() > 0) {
				this.idAnunciante = this.getSpotSelecionado().getAnunciante().getIdAnunciante();
				this.idAgencia = this.getSpotSelecionado().getAnunciante().getAgencia().getIdAgencia();
			}
			else {
				if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
					this.idAgencia = getUserAccess().getIdUserAccess();
				}
				else if (super.getUserType() == Constantes.USUARIO_ANUNCIANTE.getValor()) {
					this.idAnunciante = this.getSpotSelecionado().getAnunciante().getIdAnunciante();
					this.idAgencia = this.getSpotSelecionado().getAnunciante().getAgencia().getIdAgencia();
				}
			}
		}
		catch (Exception e) {
			logger.error("Método preencherIdAgencia - message: " + e.getMessage());			
//			if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
//				/*
//				 * this.idAgencia = super.getUserAccess() .getAgencias() .get(0)
//				 * .getIdAgencia();
//				 */
//			}
//			else if (super.getUserType() == Constantes.USUARIO_ANUNCIANTE.getValor()) {
//
//				/*
//				 * this.idAgencia = super.getUserAccess() .getAnunciantes()
//				 * .get(0) .getAgencia() .getIdAgencia();
//				 */
//			}
		}

	}
	
	public void saveHistory(String action, Long idCampanha, Long idSpot) {

		try {		
			UserHistory userHistory = new UserHistory();
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

	public void setListaAnunciantes(List<SelectItem> listaAnunciantes) {
		this.listaAnunciantes = listaAnunciantes;
	}

	public Spot getSpotSelecionado() {
		return spotSelecionado;
	}

	public void setSpotSelecionado(Spot spotSelecionado) {
		this.spotSelecionado = spotSelecionado;
	}

	public int getAudioOrder() {
		return audioOrder;
	}

	public void setAudioOrder(int audioOrder) {
		this.audioOrder = audioOrder;
	}

	public int getAudioDigit() {
		return audioDigit;
	}

	public void setAudioDigit(int audioDigit) {
		this.audioDigit = audioDigit;
	}

	public List<Integer> getAudioDigitList() {
		return audioDigitList;
	}

	public void setAudioDigitList(List<Integer> audioDigitList) {
		this.audioDigitList = audioDigitList;
	}

	public List<Integer> getAudioOrderList() {
		return audioOrderList;
	}

	public void setAudioOrderList(List<Integer> audioOrderList) {
		this.audioOrderList = audioOrderList;
	}

	public List<SpotAudioFile> getSpotAudioFileList() {

		if (this.spotAudioFileList.isEmpty()) {
			this.spotAudioFileList = spotService.getSpotAudioFileBySpotId(this.getSpotSelecionado().getIdSpot());
			return this.spotAudioFileList;
		}
		else {
			return this.spotAudioFileList;
		}

	}

	public void setSpotAudioFileList(List<SpotAudioFile> spotAudioFileList) {
		this.spotAudioFileList = spotAudioFileList;
	}

	public List<UploadedFile> getListaArquivos() {
		return listaArquivos;
	}

	public void setListaArquivos(List<UploadedFile> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public Long getIdAnunciante() {
		return idAnunciante;
	}

	public void setIdAnunciante(Long idAnunciante) {
		this.idAnunciante = idAnunciante;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}


	
}
