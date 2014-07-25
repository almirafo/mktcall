package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import br.com.supportcomm.mktcall.service.foward.FowardService;
import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.Anunciante;
import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Foward;
import br.com.supportcomm.mktcall.entity.Insertion;
import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.entity.Region;
import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.entity.UserHistory;
import br.com.supportcomm.mktcall.service.agencia.AgenciaService;
import br.com.supportcomm.mktcall.service.anunciante.AnuncianteService;
import br.com.supportcomm.mktcall.service.areacode.AreaCodeService;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.history.UserHistoryService;
import br.com.supportcomm.mktcall.service.insertion.InsertionService;
import br.com.supportcomm.mktcall.service.listageral.ListaGeralService;
import br.com.supportcomm.mktcall.service.listdetail.ListDetailService;
import br.com.supportcomm.mktcall.service.msisdncampanha.MsisdnCampanhaService;
import br.com.supportcomm.mktcall.service.region.RegionService;
import br.com.supportcomm.mktcall.service.spot.SpotService;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.util.CampanhaTimes;
import br.com.supportcomm.mktcall.util.Constantes;

/**
 * Classe backingbean de cadastro de campanha
 * 
 * @author eduardo.zimerer
 * 
 */

@ManagedBean
@javax.faces.bean.SessionScoped
public class CampanhaMBean extends AbstractManagedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String[] status;
	private final static Integer[] statusValue;

	static {
		status = new String[2];
		status[0] = "Ativas";
		status[1] = "Inativas";

		statusValue = new Integer[2];
		statusValue[0] = 1;
		statusValue[1] = 0;

	}
	
	private SelectItem[] statusOptions;
	
	private Campanha campanhaSelecionada;
	private List<Insertion> insertion;

	private Campanha[] campanhasSelecionadas;
	private Long idAnunciante;
	private boolean proximaCampanha;
	private Long idSpot;
	private Long idAgencia;
	private Long idListDetail;	
	
	private String audioPosition ;
	private String fowardCFU ;
	private boolean fowardEnabled;
	
	private List<SelectItem> listaAnunciantes;
	private List<SelectItem> listaSpot;
	private List<SelectItem> listaDuration;
	private List<SelectItem> listaPrioridade;	
	private List<SelectItem> listDetail;	
	private List<Campanha> listaCampanhas;	
	private Logger logger = Logger.getLogger(getClass().getName());
	private Date inicioCampanha = new Date();
	private Date fimCampanha = new Date();
	private Integer callDuration;
	private CampanhaTimes campanhaTimes = new CampanhaTimes();
	private DualListModel<AreaCode> areaCodes = new DualListModel<>();
	private DataTable campanhaList = new DataTable();
	private String daysOfWeek[] = {"Errado", "Domingo", "Segunda-feira", "Terça-feira",
            "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"};


	@EJB
	private SpotService spotService;

	@EJB
	private AnuncianteService anuncianteService;

	@EJB
	private CampanhaService campanhaService;

	@EJB
	private AgenciaService agenciaService;

	@EJB
	private InsertionService insertionService;

	@EJB
	private MsisdnCampanhaService msisdnService;

	@EJB
	private CdrService cdrService;

	@EJB
	private ListaGeralService listaGeralService;

	@EJB
	private RegionService regionService;

	@EJB
	private UserHistoryService historyService;
	
	@EJB
	private AreaCodeService areaCodeService;
	
	@EJB
	private ListDetailService listDetailService;
	
	@EJB
	private FowardService fowardService;

	
	public List <SelectItem> getListaCallDuration() throws MktCallException {
		
		listaDuration = new ArrayList<SelectItem>();
		listaDuration.add(new SelectItem(60, "60 seg"));
		listaDuration.add(new SelectItem(90, "90 seg"));		
		listaDuration.add(new SelectItem(120, "120 seg"));
		listaDuration.add(new SelectItem(180, "180 seg"));
		return listaDuration;
	}
	
	public List <SelectItem> getListaPrioridade() throws MktCallException {
		
		listaPrioridade = new ArrayList<SelectItem>();
		listaPrioridade.add(new SelectItem(1, "1"));
		listaPrioridade.add(new SelectItem(2, "2"));
		listaPrioridade.add(new SelectItem(3, "3"));
		listaPrioridade.add(new SelectItem(4, "4"));
		listaPrioridade.add(new SelectItem(5, "5"));		
		return listaPrioridade;
	}	
	
	

	
	public List<SelectItem> getListaSpot() throws MktCallException {
		try {

			listaSpot = new ArrayList<SelectItem>();
			List<Spot> listaSpots = new ArrayList<Spot>();

			if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
				listaSpots = spotService.getSpotAgenciaUsuario(super.getUserAccess().getIdUserAccess());
			}

			if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
				listaSpots = spotService.getSpotAll();
			}

			if (super.getUserType() == Constantes.USUARIO_ANUNCIANTE.getValor()) {

				listaSpots = spotService.getSpotAnunciante(this.idAnunciante);
			}

			for (Spot s : listaSpots) {
				this.listaSpot.add(new SelectItem(s.getIdSpot(), s.getSpotName()));
			}

		}
		catch (Exception e) {
			logger.error("Erro ao obter lista de Spots");
			logger.error(e.getMessage());
		}

		return listaSpot;
	}
	
	public List<SelectItem> getListDetail() throws MktCallException {
		this.listDetail = new ArrayList<SelectItem>();
		try {
			List<ListDetail> listDetails = listDetailService.getListDetailAll();
			for (ListDetail ld : listDetails) {
				this.listDetail.add(new SelectItem(ld.getIdList(), ld.getListName()));
			}
		} catch (Exception e) {
			logger.error("Erro ao obter lista dos Detalhes.");
			logger.error(e.getMessage());
		}
		return this.listDetail;
	}	

	public List<SelectItem> getListaAnunciantes() throws MktCallException {
		this.listaAnunciantes = new ArrayList<SelectItem>();
		try {
			List<Anunciante> listAnunciantes = anuncianteService.getAnuncianteAll();
			for (Anunciante an : listAnunciantes) {
				this.listaAnunciantes.add(new SelectItem(an.getIdAnunciante(), an.getRazaoSocial()));
			}
		} catch (Exception e) {
			logger.error("Erro ao obter lista de Anunciantes.");
			logger.error(e.getMessage());
		}
		return this.listaAnunciantes;
	}

	/**
	 * Método de carregamento da lista da datatable através do tipo do usuário
	 * logado
	 * 
	 * @return
	 */
	public List<Campanha> getListaCampanhas() throws MktCallException {
        if (super.isNullOrEmpty(listaCampanhas)) {
			listaCampanhas = new ArrayList<Campanha>();	
			try {
				if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
		
					listaCampanhas = campanhaService.getCampanhaAll();
				}
		
				if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
		
					listaCampanhas = agenciaService.getAgenciaCampanha(super.getUserAccess().getIdUserAccess());
		
				}
			} catch (Exception e) {
				logger.error("Erro ao obter lista de Campanhas.");			
				logger.error(e.getMessage());
			}
        }

		return listaCampanhas;
	}

	public CampanhaMBean() {
		statusOptions = createFilterOptions(statusValue, status);
		List<AreaCode> acs = new ArrayList<AreaCode>();
		
        List<AreaCode> source = acs;  
        List<AreaCode> target = acs;
		areaCodes = new DualListModel<AreaCode>(source, target);
		  
		
		
	}

	/**
	 * Método para desativar campanhas, podendo ser uma lista
	 */
	public void pararCampanhas(ActionEvent e) throws MktCallException {
		try {
			if (!super.isNullOrBlank(this.campanhasSelecionadas)) {
				if (this.campanhasSelecionadas.length > 0) {
					for (Campanha c : this.campanhasSelecionadas) {
						c.setStatus(Constantes.STATUS_INATIVO.getValor());
						campanhaService.mergeCampanha(c);
						saveHistory(super.getMessage("campanhaHistorico") + c.getName(), c.getIdCampanha(), null);
					}
					super.addInfo(super.getMessage("campanhasInativas"));
				}
				else {
					super.addError(super.getMessage("selecioneCampanha"));
				}
			}
			else {
				super.addError(super.getMessage("selecioneCampanha"));
			}
		}
		catch (Exception ex) {
			super.addError(super.getMessage("selecioneCampanha"));
			logger.error(ex);
		}
	}


	
	/**
	 * Método para desativar campanhas, podendo ser uma lista
	 */
	public void pararCampanha() throws MktCallException {
		try {
			this.getCampanhaSelecionada().setStatus(Constantes.STATUS_INATIVO.getValor());
			
			campanhaService.mergeCampanha(this.getCampanhaSelecionada());
			saveHistory(super.getMessage("campanhaHistorico") + this.getCampanhaSelecionada().getName(), this.getCampanhaSelecionada().getIdCampanha(), null);
			super.addInfo(super.getMessage("campanhasInativas"));
		}
		catch (Exception ex) {
			super.addError(super.getMessage("selecioneCampanha"));
			logger.error(ex);
		}
	}
	
	
	
	
	/**
	 * Exclusão lógica, setar o campo status para inativo
	 */
	public void excluirCampanha(ActionEvent e) throws MktCallException {

		try {
			this.getCampanhaSelecionada().setStatus(Constantes.STATUS_INATIVO.getValor());
			campanhaService.mergeCampanha(this.getCampanhaSelecionada());
			super.addInfo(super.getMessage("campanhaExcluida"));
			saveHistory(super.getMessage("campanhaHistorico") + this.getCampanhaSelecionada().getName(), this.getCampanhaSelecionada().getIdCampanha(), null);
		}
		catch (Exception ex) {
			super.addError(super.getMessage("erroExcluir"));
			logger.error(ex);
		}

	}

	
	public void excluirCampanha() throws MktCallException {

		try {
			this.getCampanhaSelecionada().setStatus(Constantes.STATUS_INATIVO.getValor());
			campanhaService.mergeCampanha(this.getCampanhaSelecionada());
			super.addInfo(super.getMessage("campanhaExcluida"));
			saveHistory(super.getMessage("campanhaHistorico") + this.getCampanhaSelecionada().getName(), this.getCampanhaSelecionada().getIdCampanha(), null);
		}
		catch (Exception ex) {
			super.addError(super.getMessage("erroExcluir"));
			logger.error(ex);
		}
	}
	
	
	public  void listOfRegions(Campanha campanha){
		List<AreaCode> sourceAreaCode  = new ArrayList<AreaCode>();
		List<AreaCode> targetAreaCode = new ArrayList<AreaCode>();
		
		if ((campanha != null) && (campanha.getIdCampanha()!=null )){
		    sourceAreaCode  = areaCodeService.getAreaCodeGetNotSelected(campanha);
		    targetAreaCode = areaCodeService.getAreaCodeGetSelected(campanha);
		}
		else{
			sourceAreaCode  = areaCodeService.getAreaCodeAll();
			targetAreaCode = areaCodeService.getAreaCodeGetSelected(campanha);
		}
		
		this.areaCodes = new DualListModel<AreaCode>(sourceAreaCode, targetAreaCode);
		
	}
	
	
	public boolean getChangeSpot() {

		if ((!super.isNullOrBlank(this.campanhaSelecionada)) && (!super.isNullOrBlank(this.campanhaSelecionada.getSpot()))) {
			return true;
		}

		return false;
	}

	/**
	 * Método salvar/alterar o objeto Utiliza o requestContext para fechar ou
	 * não, casa haja problema, o modal de cadastro
	 */
	public String salvarCampanha() throws MktCallException {
		
		super.setSucesso(true);
		if (super.isNullOrBlank(this.getCampanhaSelecionada().getName())) {
			super.addError(super.getMessage("nomeCampanha") + " " + super.getMessage("campoObrigatorio"));
			super.setSucesso(false);
		}
		else if (super.isNullOrBlank(getInicioCampanha())) {
			super.addError(super.getMessage("dataInicio") + ": " + super.getMessage("campoObrigatorio"));
			super.setSucesso(false);
		}
		else if (super.isNullOrBlank(getFimCampanha())) {
			super.addError(super.getMessage("dataFim") + ": " + super.getMessage("campoObrigatorio"));
			super.setSucesso(false);
		}
		else if (getInicioCampanha().after(getFimCampanha())) {
			super.addError(super.getMessage("dataInicio") + ": " + super.getMessage("dataInicioMaiorFim"));
			super.setSucesso(false);
		}
		else if (getFimCampanha().before(getInicioCampanha())) {
			super.addError(super.getMessage("dataFim") + ": " + super.getMessage("dataFimMenorInicio"));
			super.setSucesso(false);
		}
		else if (super.isNullOrBlank(this.getIdSpot() == 0)) {
			super.addError(super.getMessage("Spot") + " " + super.getMessage("campoObrigatorio"));
			super.setSucesso(false);
		}
		else if (super.isNullOrBlank(this.getCallDuration() == 0)) {
			super.addError(super.getMessage("duracao") + " " + super.getMessage("campoObrigatorio"));
			super.setSucesso(false);
		}
		else if (super.isNullOrBlank(this.getCampanhaSelecionada().getPriority() == 0)) {
			super.addError(super.getMessage("prioridade") + " " + super.getMessage("campoObrigatorio"));
			super.setSucesso(false);
		}		
		else {
			try {
				if(this.campanhaSelecionada!=null){
				if (super.isSucesso()) {
					String action = super.getMessage("campanhaHistorico");
					if (super.isNullOrBlank(this.campanhaSelecionada.getIdCampanha())) {
						action = super.getMessage("campanhaSalva2");
					}

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

					if (super.isNullOrBlank(this.campanhaSelecionada.getInsertionReach())) {
						this.campanhaSelecionada.setInsertionReach(0L);
					}

					this.campanhaSelecionada.setSpot(spotService.getSpotById(getIdSpot()).get(0));
					this.campanhaSelecionada.setUserAccess(super.getUserAccess());
					this.campanhaSelecionada.setStartDatatime(Timestamp.valueOf(sdf.format(getInicioCampanha().getTime())));
					this.campanhaSelecionada.setEndDatetime(Timestamp.valueOf(sdf.format(getFimCampanha().getTime())));
					this.campanhaSelecionada.setOperationDate(new Timestamp(System.currentTimeMillis()));
					this.campanhaSelecionada.setCallDuration(this.getCallDuration());
					if (super.isNullOrBlank(getIdListDetail())) {
						this.campanhaSelecionada.setListDetail(null);
					} else {
					    this.campanhaSelecionada.setListDetail(listDetailService.getListDetailById(getIdListDetail()).get(0));
					}

					this.campanhaSelecionada = campanhaService.mergeCampanha(this.campanhaSelecionada);
					saveHistory(action + " " + this.campanhaSelecionada.getName(), this.campanhaSelecionada.getIdCampanha(), null);
					try {
					    gravaInsertions();
					} catch (MktCallException e){
						super.addError(super.getMessage("problemaInsertions"));
						super.setSucesso(false);
						logger.error("Método salvarCampanha. Problema gravando Insertions - message: " + e.getMessage());	
					//	return "campanha?faces-redirect=false";
					}
					try {
					    gravaRegions();
					} catch (Exception e) {
						super.addError(super.getMessage("problemaRegions"));
						super.setSucesso(false);
						logger.error("Método salvarCampanha. Problema gravando Regions - message: " + e.getMessage());
					//	return "campanha?faces-redirect=false";
					}
					campanhaTimes = new CampanhaTimes();

				    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,super.getMessage("campanhaSalva"),""));
				    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true); //	

				}
				}
				//----------------------------------  gravar Mdivunga----------------------------------------
				Foward foward = fowardService.getFowardByCampanha(this.campanhaSelecionada.getIdCampanha());
				if (foward==null){
					//vamu persistir
					if( this.fowardEnabled){
						if ( !this.audioPosition.isEmpty() && !this.fowardCFU.isEmpty()){
						foward.setAudioPosition(this.audioPosition);
						foward.setCfu(this.fowardCFU);
						foward.setIdCampanha(this.campanhaSelecionada.getIdCampanha());
						foward.setFowardEnabled( this.fowardEnabled);
						fowardService.persistFoward(foward);
						}else{
							super.addError(super.getMessage("problemasFoward"));
							super.setSucesso(false);
							logger.error("Método salvarCampanha.");	
							return "campanha?faces-redirect=false";
						}
					}
				}else{
					//vamu atualizar
					if( this.fowardEnabled ){
						if ( !this.audioPosition.isEmpty() && !this.fowardCFU.isEmpty()){
							foward.setAudioPosition(this.audioPosition);
							foward.setCfu(this.fowardCFU);
							foward.setIdCampanha(this.campanhaSelecionada.getIdCampanha());
							foward.setFowardEnabled( this.fowardEnabled);
							fowardService.mergeFoward(foward);
						}else{
							super.addError(super.getMessage("problemasFoward"));
							super.setSucesso(false);
							logger.error("Método salvarCampanha.");	
							return "campanha?faces-redirect=false";
						}
						
					}else{
						fowardService.removeFowardByCampanha(this.campanhaSelecionada.getIdCampanha());

					}
				}
				//------------------------------------------------------------------------------------------	
				
				this.audioPosition="";
				this.fowardCFU="";
				this.fowardEnabled=false;
				
				this.campanhaSelecionada = new Campanha();
				listaCampanhas = null;
				return "campanha?faces-redirect=true";

			}
			catch (Exception e) {
				super.addError(super.getMessage("problemaCampanhas"));
				super.setSucesso(false);
				logger.error("Método salvarCampanha - message: " + e.getMessage());
				return "campanha?faces-redirect=false";
			}
		}
		this.campanhaSelecionada = new Campanha();
		return "campanha";

	}
	
    public void limparSelecionada(CloseEvent  close){
    	this.campanhaSelecionada = new Campanha();
    }


	private void gravaRegions() {
		List<Region> regions = regionService.getRegionByCampanha(this.campanhaSelecionada);
		for(Region region:regions){
			regionService.removeRegion(region);
			saveHistory(super.getMessage("regiaoCampanhaDeletada") + " DDD: " + region.getAreaCode().getCode().toString(), this.campanhaSelecionada.getIdCampanha(), null);						
		}
		
		
		for(AreaCode areaCode : areaCodes.getTarget()){
			
			Region region =  new Region();
			region.setIdCampanha(this.campanhaSelecionada.getIdCampanha());
			region.setAreaCode(areaCode);
			regionService.persistRegion(region);
			saveHistory(super.getMessage("regiaoCampanhaSalva") + " DDD: " + region.getAreaCode().getCode().toString(), this.campanhaSelecionada.getIdCampanha(), null);			
		}
		listOfRegions(this.campanhaSelecionada);
	}


	private void gravaInsertions() throws MktCallException {
		Insertion insertion = new Insertion();
		List<Insertion> insertionList = new ArrayList<Insertion>();

		if (campanhaTimes.isDomingoId()) {
			insertion = new Insertion();
			insertion.setCampanhaInsertion(this.campanhaSelecionada);
			insertion.setDayOfWeek(Constantes.DOMINGO.getValor());
			checkHourSequence(campanhaTimes.getDomingoInicio(), campanhaTimes.getDomingoFim(), Constantes.DOMINGO.getDescricao());
			if (!super.isNullOrBlank(campanhaTimes.getDomingoInicio()) && !super.isNullOrBlank(campanhaTimes.getDomingoFim())) {
				insertion.setStartTime(new Timestamp(campanhaTimes.getDomingoInicio().getTime()));
				insertion.setEndTime(new Timestamp(campanhaTimes.getDomingoFim().getTime()));
			} else {
				setDefaultHours(insertion);  // Sets 00:00 as starting hour and 23:59 as ending hour.
			}
			insertionList.add(insertion);
		}
		if (campanhaTimes.isSegundaId()) {
			insertion = new Insertion();
			insertion.setCampanhaInsertion(this.campanhaSelecionada);
			insertion.setDayOfWeek(Constantes.SEGUNDA.getValor());
			checkHourSequence(campanhaTimes.getSegundaInicio(), campanhaTimes.getSegundaFim(), Constantes.SEGUNDA.getDescricao());
			if (!super.isNullOrBlank(campanhaTimes.getSegundaInicio()) && !super.isNullOrBlank(campanhaTimes.getSegundaFim())) {
				insertion.setStartTime(new Timestamp(campanhaTimes.getSegundaInicio().getTime()));
				insertion.setEndTime(new Timestamp(campanhaTimes.getSegundaFim().getTime()));
			} else {
				setDefaultHours(insertion);  // Sets 00:00 as starting hour and 23:59 as ending hour.
			}			
			insertionList.add(insertion);
		}
		if (campanhaTimes.isTercaId()) {
			insertion = new Insertion();
			insertion.setCampanhaInsertion(this.campanhaSelecionada);
			insertion.setDayOfWeek(Constantes.TERCA.getValor());
			checkHourSequence(campanhaTimes.getTercaInicio(), campanhaTimes.getTercaFim(), Constantes.TERCA.getDescricao());
			if (!super.isNullOrBlank(campanhaTimes.getTercaInicio()) && !super.isNullOrBlank(campanhaTimes.getTercaFim())) {
				insertion.setStartTime(new Timestamp(campanhaTimes.getTercaInicio().getTime()));	
				insertion.setEndTime(new Timestamp(campanhaTimes.getTercaFim().getTime()));
			} else {
				setDefaultHours(insertion);  // Sets 00:00 as starting hour and 23:59 as ending hour.
			}						
			insertionList.add(insertion);
		}
		if (campanhaTimes.isQuartaId()) {
			insertion = new Insertion();
			insertion.setCampanhaInsertion(this.campanhaSelecionada);
			insertion.setDayOfWeek(Constantes.QUARTA.getValor());
			checkHourSequence(campanhaTimes.getQuartaInicio(), campanhaTimes.getQuartaFim(), Constantes.QUARTA.getDescricao());
			if (!super.isNullOrBlank(campanhaTimes.getQuartaInicio()) && !super.isNullOrBlank(campanhaTimes.getQuartaFim())) {
				insertion.setStartTime(new Timestamp(campanhaTimes.getQuartaInicio().getTime()));
				insertion.setEndTime(new Timestamp(campanhaTimes.getQuartaFim().getTime()));
			} else {
				setDefaultHours(insertion);  // Sets 00:00 as starting hour and 23:59 as ending hour.
			}						
			insertionList.add(insertion);
		}
		if (campanhaTimes.isQuintaId()) {
			insertion = new Insertion();
			insertion.setCampanhaInsertion(this.campanhaSelecionada);
			insertion.setDayOfWeek(Constantes.QUINTA.getValor());
			checkHourSequence(campanhaTimes.getQuintaInicio(), campanhaTimes.getQuintaFim(), Constantes.QUINTA.getDescricao());
			if (!super.isNullOrBlank(campanhaTimes.getQuintaInicio()) && !super.isNullOrBlank(campanhaTimes.getQuintaFim())) {
				insertion.setStartTime(new Timestamp(campanhaTimes.getQuintaInicio().getTime()));	
				insertion.setEndTime(new Timestamp(campanhaTimes.getQuintaFim().getTime()));
			} else {
				setDefaultHours(insertion);  // Sets 00:00 as starting hour and 23:59 as ending hour.
			}					
			insertionList.add(insertion);
		}
		if (campanhaTimes.isSextaId()) {
			insertion = new Insertion();
			insertion.setCampanhaInsertion(this.campanhaSelecionada);
			insertion.setDayOfWeek(Constantes.SEXTA.getValor());
			checkHourSequence(campanhaTimes.getSextaInicio(), campanhaTimes.getSextaFim(), Constantes.SEXTA.getDescricao());
			if (!super.isNullOrBlank(campanhaTimes.getSextaInicio()) && !super.isNullOrBlank(campanhaTimes.getSextaFim())) {
				insertion.setStartTime(new Timestamp(campanhaTimes.getSextaInicio().getTime()));
				insertion.setEndTime(new Timestamp(campanhaTimes.getSextaFim().getTime()));
			} else {
				setDefaultHours(insertion);  // Sets 00:00 as starting hour and 23:59 as ending hour.
			}								
			insertionList.add(insertion);
		}
		if (campanhaTimes.isSabadoId()) {
			insertion = new Insertion();
			insertion.setCampanhaInsertion(this.campanhaSelecionada);
			insertion.setDayOfWeek(Constantes.SABADO.getValor());
			checkHourSequence(campanhaTimes.getSabadoInicio(), campanhaTimes.getSabadoFim(), Constantes.SABADO.getDescricao());
			if (!super.isNullOrBlank(campanhaTimes.getSabadoInicio()) && !super.isNullOrBlank(campanhaTimes.getSabadoFim())) {
				insertion.setStartTime(new Timestamp(campanhaTimes.getSabadoInicio().getTime()));
				insertion.setEndTime(new Timestamp(campanhaTimes.getSabadoFim().getTime()));
			} else {
				setDefaultHours(insertion);  // Sets 00:00 as starting hour and 23:59 as ending hour.
			}								
			insertionList.add(insertion);
		}

		insertionService.removeInsertionByIdCampanha(this.campanhaSelecionada.getIdCampanha());
		
		for (Insertion insertionSave : insertionList) {
			insertionService.persistInsertion(insertionSave);
			saveHistory(super.getMessage("horaCampanhaSalva") + " dia: " + daysOfWeek[insertionSave.getDayOfWeek()] + "; hora inicio: " 
			+ insertionSave.getStartTime().toString().substring(11,16) + " hora fim: " + insertionSave.getEndTime().toString().substring(11,16), this.campanhaSelecionada.getIdCampanha(), null);
		}
	}
	
	private void checkHourSequence (Date horaInicio, Date horaFim, String diaSemana) throws MktCallException {
		// Se uma das duas horas (começo ou fim) não estiver especificada, uma exception será lançada.
		// Ou ambos as horas (começo e fim) estiverem especificadas, ou nenhuma precisará ser especificada.
		// É possivel especificar um dia da semana e não especificar as horas de começo e fim.
		if ((super.isNullOrBlank(horaInicio) && (!super.isNullOrBlank(horaFim))) 
				|| ((!super.isNullOrBlank(horaInicio)) && super.isNullOrBlank(horaFim))) {
			throw new MktCallException("Horário de começo o fim do dia " + diaSemana + " não foi especificado.");
		}
		if (!super.isNullOrBlank(horaInicio) && !super.isNullOrBlank(horaFim)) {
			if (horaInicio.getTime() > horaFim.getTime()) {
				throw new MktCallException("Horário de começo da campanha para o dia " + diaSemana + " tem que ser menor do horário de fim.");
			}
		}	
	}
	
	private void setDefaultHours(Insertion insertion) {
		insertion.setStartTime(Timestamp.valueOf(Constantes.HORA_INICIO.getDescricao()));
		insertion.setEndTime(Timestamp.valueOf(Constantes.HORA_FIM.getDescricao()));		
	}

	/**
	 * Método para nova campanha
	 */
	public void novaCampanha(ActionEvent event) {

		campanhaSelecionada = new Campanha();
		campanhaTimes = new CampanhaTimes();
		this.callDuration = 0;

		String action = (String) event.getComponent().getId();
		this.idAgencia = 0L;
		this.idAnunciante = 0L;		
		if (action.equals("novaCampanha")) {
			listOfRegions(null);
			setInicioCampanha(new Timestamp(System.currentTimeMillis()));
			setFimCampanha(new Timestamp(System.currentTimeMillis()));
		}

		idSpot = null;
		idListDetail = null;
		idAnunciante = null;
		this.fowardCFU="";
		this.fowardEnabled=false;
		this.audioPosition="";

	}

	/**
	 * Chamada inicial da pagina de campanhas
	 * 
	 * @throws IOException
	 */

	//@PostConstruct
	public String iniciarCampanha() throws IOException {
		campanhaSelecionada = new Campanha();
      
		idSpot = null;
		idListDetail = null;
		idAnunciante = null;
		//this.preencherCampanha(campanhaSelecionada);
		

		return "campanha";

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

        try {
			if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
	
				lista = agenciaService.getAgenciaAll();
	
			}
			else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
	
				lista.add((Agencia) agenciaService.getAgenciaUsuario(getUserAccess().getIdUserAccess()));
			}
			for (Agencia a : lista) {
				listItems.add(new SelectItem(a.getIdAgencia(), a.getRazaoSocial()));
			}
        } catch (Exception e) {
			logger.error("Erro ao obter lista de Agencias (CampanhaMBean).");			
			logger.error(e.getMessage());        	
        }
		return listItems;
	}

	/**
	 * método utilizado para preencher automaticamente, dependendo do tipo de
	 * usuário logado, o id da agência e/ou id do anunciante a serem tilizados
	 */
	public void preencherCampanha(Campanha campanha) {
		
	  	if (super.isNullOrBlank(campanha)) {
		   this.campanhaSelecionada = campanha; 
		} else {
		   this.campanhaSelecionada = campanhaService.getCampanhaId(campanha.getIdCampanha()).get(0); 
		}
		
		//logger.debug(campanha.getName());
		this.idAgencia = 0L;
		this.idAnunciante = 0L;
		this.idListDetail = 0L;

		try {
            if (!super.isNullOrBlank(this.campanhaSelecionada)){
				listOfRegions(campanha);
				idSpot = spotService.getSpotCampanha(this.campanhaSelecionada.getIdCampanha()).get(0).getIdSpot();
				List<ListDetail> tmpListDetail = listDetailService.getListDetailCampanha(this.campanhaSelecionada.getIdCampanha());
				if (!(super.isNullOrEmpty(tmpListDetail))) {
				    idListDetail = tmpListDetail.get(0).getIdList(); 
				}
				campanha = campanhaService.getCampanhaId(this.campanhaSelecionada.getIdCampanha()).get(0);
				this.callDuration = campanha.getCallDuration();
				List<Insertion> insertionList = new ArrayList<Insertion>();
				insertionList = insertionService.getInsertionCampanha(this.campanhaSelecionada.getIdCampanha());
				setInicioCampanha(this.campanhaSelecionada.getStartDatatime());
				setFimCampanha(this.campanhaSelecionada.getEndDatetime());
				
				campanhaTimes = new CampanhaTimes();
	
				for (Insertion insertion : insertionList) {
	
					switch (insertion.getDayOfWeek()) {
					case 1:
						campanhaTimes.setDomingoId(true);
						campanhaTimes.setDomingoInicio(insertion.getStartTime());
						campanhaTimes.setDomingoFim(insertion.getEndTime());
						break;
					case 2:
						campanhaTimes.setSegundaId(true);
						campanhaTimes.setSegundaInicio(insertion.getStartTime());
						campanhaTimes.setSegundaFim(insertion.getEndTime());
						break;
					case 3:
						campanhaTimes.setTercaId(true);
						campanhaTimes.setTercaInicio(insertion.getStartTime());
						campanhaTimes.setTercaFim(insertion.getEndTime());
						break;
					case 4:
						campanhaTimes.setQuartaId(true);
						campanhaTimes.setQuartaInicio(insertion.getStartTime());
						campanhaTimes.setQuartaFim(insertion.getEndTime());
						break;
					case 5:
						campanhaTimes.setQuintaId(true);
						campanhaTimes.setQuintaInicio(insertion.getStartTime());
						campanhaTimes.setQuintaFim(insertion.getEndTime());
						break;
					case 6:
						campanhaTimes.setSextaId(true);
						campanhaTimes.setSextaInicio(insertion.getStartTime());
						campanhaTimes.setSextaFim(insertion.getEndTime());
						break;
					case 7:
						campanhaTimes.setSabadoId(true);
						campanhaTimes.setSabadoInicio(insertion.getStartTime());
						campanhaTimes.setSabadoFim(insertion.getEndTime());
						break;
	
					default:
						break;
					}
	
				}
	
				// dados do Mdivulga
				
				Foward foward =fowardService.getFowardByCampanha(campanha.getIdCampanha());
				
				if(foward.getFowardEnabled()!=null){
					this.audioPosition =  foward.getAudioPosition();
					this.fowardCFU     =  foward.getCfu();
					this.fowardEnabled = foward.getFowardEnabled();
					
				}else{
					this.audioPosition =  "";
					this.fowardCFU     =  "";
					this.fowardEnabled = false;
					
				}
				
				
				if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
	
					this.idAgencia = agenciaService.getAgenciaUsuario(getUserAccess().getIdUserAccess()).get(0).getUserAccess().getIdUserAccess();
	
				}
	
				if (super.getUserType() == Constantes.USUARIO_ANUNCIANTE.getValor()) {
	
					Anunciante anunciante = anuncianteService.getAnuncianteUserAccessAgencia(getUserAccess().getIdUserAccess()).get(0);
	
					this.idAnunciante = anunciante.getIdAnunciante();
	
					this.idAgencia = anunciante.getAgencia().getIdAgencia();
	
				}
            }
		}
		catch (Exception e) {
			logger.error("Método preencherCampanha - message: " + e.getMessage());
		}
	}

	private void saveHistory(String action, Long idCampanha, Long idSpot) {
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

	public SelectItem[] getMyBooleanValues() {
		return new SelectItem[] { new SelectItem(Boolean.TRUE, "Sim"), new SelectItem(Boolean.FALSE, "Não") };
	}

	public Date getInicioCampanha() {
		return inicioCampanha;
	}

	public void setInicioCampanha(Date inicioCampanha) {
		this.inicioCampanha = inicioCampanha;
	}

	public Date getFimCampanha() {
		return fimCampanha;
	}

	public void setFimCampanha(Date fimCampanha) {
		this.fimCampanha = fimCampanha;
	}

	public Campanha[] getCampanhasSelecionadas() {
		return campanhasSelecionadas;
	}

	public void setCampanhasSelecionadas(Campanha[] campanhasSelecionadas) {
		this.campanhasSelecionadas = campanhasSelecionadas;
	}

	public Campanha getCampanhaSelecionada() {
		
		return campanhaSelecionada;
	}

	public void setCampanhaSelecionada(Campanha campanhaSelecionada) {
		this.preencherCampanha(campanhaSelecionada);
		this.campanhaSelecionada = campanhaSelecionada;
	}

	public void setListaSpot(List<SelectItem> listaSpot) {
		this.listaSpot = listaSpot;
	}

	public void setListaAnunciantes(List<SelectItem> listaAnunciantes) {
		this.listaAnunciantes = listaAnunciantes;
	}

	public Long getIdAnunciante() {
		return idAnunciante;
	}

	public void setIdAnunciante(Long idAnunciante) {
		this.idAnunciante = idAnunciante;
	}

	public Long getIdSpot() {
		return idSpot;
	}

	public void setIdSpot(Long idSpot) {
		this.idSpot = idSpot;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public boolean isProximaCampanha() {
		return proximaCampanha;
	}

	public void setProximaCampanha(boolean proximaCampanha) {
		this.proximaCampanha = proximaCampanha;
	}

	public List<Insertion> getInsertion() {
		return insertion;
	}

	public void setInsertion(List<Insertion> insertion) {
		this.insertion = insertion;
	}

	public CampanhaTimes getCampanhaTimes() {
		return campanhaTimes;
	}

	public void setCampanhaTimes(CampanhaTimes campanhaTimes) {
		this.campanhaTimes = campanhaTimes;
	}

	private SelectItem[] createFilterOptions(Integer[] values, String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Todos");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem( String.valueOf(values[i]), data[i]);
		}

		return options;
	}

	public SelectItem[] getStatusOptions() {
		return statusOptions;
	}


	public Integer getCallDuration() {
		return callDuration;
	}

	public void setCallDuration(Integer callDuration) {
		this.callDuration = callDuration;
	}

	public DualListModel<AreaCode> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(DualListModel<AreaCode> areaCodes) {
		this.areaCodes = areaCodes;
	}

	public DataTable getCampanhaList() {
		return campanhaList;
	}

	public void setCampanhaList(DataTable campanhaList) {
		this.campanhaList = campanhaList;
	}
	
	public String editar(SelectEvent  rowSelect){		
		return "campanhaEditar";
	}

	public Long getIdListDetail() {
		return idListDetail;
	}

	public void setIdListDetail(Long idListDetail) {
		this.idListDetail = idListDetail;
	}

	public void setListDetail(List<SelectItem> listDetail) {
		this.listDetail = listDetail;
	}

	public String getAudioPosition() {
		return audioPosition;
	}

	public void setAudioPosition(String audioPosition) {
		this.audioPosition = audioPosition;
	}

	public String getFowardCFU() {
		return fowardCFU;
	}

	public void setFowardCFU(String fowardCFU) {
		this.fowardCFU = fowardCFU;
	}

	public boolean isFowardEnabled() {
		return fowardEnabled;
	}

	public void setFowardEnabled(boolean fowardEnabled) {
		this.fowardEnabled = fowardEnabled;
	}
	
}
