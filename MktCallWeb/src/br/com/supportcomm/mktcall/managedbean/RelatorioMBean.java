package br.com.supportcomm.mktcall.managedbean;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Cdr;
import br.com.supportcomm.mktcall.service.agencia.AgenciaService;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.msisdncampanha.MsisdnCampanhaService;
import br.com.supportcomm.mktcall.service.spot.SpotService;
import br.com.supportcomm.mktcall.service.statistic.StatsService;
import br.com.supportcomm.mktcall.vo.ListaDuracaoVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeConsolidadaVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeVO;
import br.com.supportcomm.mktcall.vo.ListaInteracaoVO;
import br.com.supportcomm.mktcall.exception.MktCallException;
import br.com.supportcomm.mktcall.report.classes.RelatorioDuracaoVO;
import br.com.supportcomm.mktcall.report.classes.RelatorioEfetividadeVO;
import br.com.supportcomm.mktcall.report.classes.RelatorioIntegracaoVO;
import br.com.supportcomm.mktcall.report.util.JasperFactory;
import br.com.supportcomm.mktcall.report.util.TypeReportEnum;
import br.com.supportcomm.mktcall.util.Constantes;

@ManagedBean
@SessionScoped
public class RelatorioMBean extends AbstractManagedBean {
	private List<String> columns;
	private Date dataRelatorioInicio;
	private Date dataRelatorioFim;
	private Date dataEstimulo;
	private Long impactados;
	private Long efetivos;
	private Date dataEstimuloInicio;
	private Date dataEstimuloFim;
	private List<Cdr> listaCdrs;
	private String ddd;
	private DualListModel<Campanha> campanhas;
	private JasperFactory jf = new JasperFactory();
	private Boolean consolidado;

	private List<Campanha> campanhaFrom;
	private List<Campanha> campanhaTo;

	@EJB
	private CampanhaService campanhaService;

	@EJB
	private CdrService cdrService;

	@EJB
	private StatsService statsService;

	@EJB
	private AgenciaService agenciaService;

	@EJB
	private SpotService spotService;

	@EJB
	private MsisdnCampanhaService msisdnCampanhaService;

	private Logger logger = Logger.getLogger(RelatorioMBean.class);

	public void reset() {
		this.dataEstimulo = null;
		this.dataEstimuloFim = null;
		this.dataEstimuloInicio = null;
		this.impactados = null;
		this.efetivos = null;
		this.columns = new ArrayList<String>();
	}

	public String resetar(CloseEvent closeEvent) throws IOException {
		this.reset();
		return "relatorios";
	}

	// Navegação
	public String relatorioEfetividade() {
		return "relatorioEfetividade";
	}

	public String relatorioIntegracao() {
		return "relatorioIntegracao";
	}

	public String relatorioDuracao() {
		return "relatorioDuracao";
	}

	public TypeReportEnum getReportType(ActionEvent event) {

		String action = (String) event.getComponent().getAttributes().get("filetype");

		switch (action) {
		case "PDF":

			return TypeReportEnum.PDF;
		case "XLS":

			return TypeReportEnum.XLS;

		case "HTML":

			return TypeReportEnum.HTML;

		case "DOC":

			return TypeReportEnum.DOC;

		default:
			return TypeReportEnum.PDF;
		}

	}

	/**
	 * Relatorio de Duração
	 * 
	 * @param event
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarRelatorioDuracao(ActionEvent event) {

		try {

			if (super.validarDatas(getDataRelatorioInicio(), getDataRelatorioFim())) {

				List<RelatorioDuracaoVO> listaRelatorioDuracaoVO = new ArrayList<RelatorioDuracaoVO>();
				for (Campanha campanha : this.campanhas.getTarget()) {

					RelatorioDuracaoVO relatorioDuracaoVO = new RelatorioDuracaoVO();
					List<ListaDuracaoVO> duracao = campanhaService.getListaDuracao(campanha.getIdCampanha(), new Timestamp(getDataRelatorioInicio().getTime()), new Timestamp(getDataRelatorioFim()
							.getTime()));
					if (!duracao.isEmpty()) {
						relatorioDuracaoVO.setNomeCampanha(duracao.get(0).getNome());
						relatorioDuracaoVO.setTotalSegundos(duracao.get(0).getTotalsegundos());
						relatorioDuracaoVO.setMedia(duracao.get(0).getMedia());
						relatorioDuracaoVO.setQuantidade(duracao.get(0).getQuantidade());
						relatorioDuracaoVO.setDataRelatorioInicio(super.getDataFormatada(getDataRelatorioInicio()));
						relatorioDuracaoVO.setDataRelatorioFim(super.getDataFormatada(getDataRelatorioFim()));

						listaRelatorioDuracaoVO.add(relatorioDuracaoVO);
					}
				}

				if (!listaRelatorioDuracaoVO.isEmpty()) {
					jf.compilar("relatorioDuracao", new HashMap(), getReportType(event), listaRelatorioDuracaoVO);
				}
				else {
					super.addInfo(super.getMessage("semdados"));
				}

			}

		}
		catch (Exception e) {
			logger.info("");
			logger.error(e);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioIntegracao(ActionEvent event) {

		try {

			if (super.validarDatas(getDataRelatorioInicio(), getDataRelatorioFim())) {

				List<RelatorioIntegracaoVO> listaRelatorioIntegracaoVO = new ArrayList<RelatorioIntegracaoVO>();
				for (Campanha campanha : this.campanhas.getTarget()) {

					RelatorioIntegracaoVO relatorioIntegracaoVO = new RelatorioIntegracaoVO();
					List<ListaInteracaoVO> interacao = campanhaService.getListaInteracao(campanha.getIdCampanha(), new Timestamp(getDataRelatorioInicio().getTime()), new Timestamp(
							getDataRelatorioFim().getTime()));

					if (!interacao.isEmpty()) {

						relatorioIntegracaoVO.setComIntegracao(interacao.get(0).getComInteracao());
						relatorioIntegracaoVO.setSemIntegracao(interacao.get(0).getSemInteracao());
						relatorioIntegracaoVO.setNomeCampanha(campanha.getName());
						relatorioIntegracaoVO.setQuantidadeLigacoesUnicas(interacao.get(0).getLiguniq());
						relatorioIntegracaoVO.setDataRelatorioInicio(super.getDataFormatada(getDataRelatorioInicio()));
						relatorioIntegracaoVO.setDataRelatorioFim(super.getDataFormatada(getDataRelatorioFim()));

						listaRelatorioIntegracaoVO.add(relatorioIntegracaoVO);

					}
				}

				if (!listaRelatorioIntegracaoVO.isEmpty()) {
					jf.compilar("relatorioInteracao", new HashMap(), getReportType(event), listaRelatorioIntegracaoVO);
				}
				else {
					super.addInfo(super.getMessage("semdados"));
				}

			}

		}
		catch (Exception e) {
			logger.error(e);
			super.addError(super.getMessage("problemaRelatorio"));
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioEfetividade(ActionEvent event) {
		try {

			if (super.validarDatas(getDataRelatorioInicio(), getDataRelatorioFim())) {

				List<RelatorioEfetividadeVO> listaEfetividade = new ArrayList<RelatorioEfetividadeVO>();

				List<ListaEfetividadeVO> efetividade = new ArrayList();
				List<ListaEfetividadeConsolidadaVO> efetividadeConsolidada = new ArrayList();

				for (Campanha campanha : this.campanhas.getTarget()) {

					if (getConsolidado()) {
						efetividade = campanhaService.getListaEfetividade(campanha.getIdCampanha(), new Timestamp(getDataRelatorioInicio().getTime()), new Timestamp(getDataRelatorioFim().getTime()));

						if (!efetividade.isEmpty()) {

							RelatorioEfetividadeVO relatorioEfetividade = new RelatorioEfetividadeVO();

							relatorioEfetividade.setTotalExibicoes(efetividade.get(0).getTotalExibicoes());
							relatorioEfetividade.setUnicos(efetividade.get(0).getUsuariosUnicos());
							relatorioEfetividade.setOuviuCompleto(efetividade.get(0).getOuviuCompleto());
							relatorioEfetividade.setOuviuIncompleto(efetividade.get(0).getOuviuIncompleto());
							relatorioEfetividade.setNomeCampanha(campanha.getName());
							relatorioEfetividade.setDataRelatorioInicio(super.getDataFormatada(getDataRelatorioInicio()));
							relatorioEfetividade.setDataRelatorioFim(super.getDataFormatada(getDataRelatorioFim()));

							listaEfetividade.add(relatorioEfetividade);

						}

					}
					else {

						efetividadeConsolidada = campanhaService.getListaEfetividadeConsolidada(campanha.getIdCampanha(), new Timestamp(getDataRelatorioInicio().getTime()), new Timestamp(
								getDataRelatorioFim().getTime()));

						if (!efetividadeConsolidada.isEmpty()) {

							for (ListaEfetividadeConsolidadaVO consolidada : efetividadeConsolidada) {

								RelatorioEfetividadeVO relatorioEfetividade = new RelatorioEfetividadeVO();

								relatorioEfetividade.setTotalExibicoes(consolidada.getTotalExibicoes());
								relatorioEfetividade.setUnicos(consolidada.getUsuariosUnicos());
								relatorioEfetividade.setOuviuCompleto(consolidada.getOuviuCompleto());
								relatorioEfetividade.setOuviuIncompleto(consolidada.getOuviuIncompleto());
								relatorioEfetividade.setDataCampanha(consolidada.getData());
								relatorioEfetividade.setNomeCampanha(campanha.getName());
								relatorioEfetividade.setDataRelatorioInicio(super.getDataFormatada(getDataRelatorioInicio()));
								relatorioEfetividade.setDataRelatorioFim(super.getDataFormatada(getDataRelatorioFim()));

								listaEfetividade.add(relatorioEfetividade);

							}

						}
					}

				}

				if (!listaEfetividade.isEmpty()) {
					jf.compilar(getConsolidado() ? "relatorioEfetividadeConsolidada" : "relatorioEfetividade", new HashMap(), getReportType(event), listaEfetividade);
				}
				else {
					super.addInfo(super.getMessage("semdados"));
				}

				
				setDataRelatorioInicio(null);
				setDataRelatorioFim(null);

			}
		}
		catch (Exception e) {
			logger.error(e);
			super.addError(super.getMessage("problemaRelatorio"));
		}

	}

	/**
	 * Chamada inicial da pagina de relatÃ³rios
	 * 
	 */
	public String iniciar() throws IOException, MktCallException {

		campanhaTo = new ArrayList<Campanha>();

		this.campanhas = new DualListModel<Campanha>(new ArrayList<Campanha>(), new ArrayList<Campanha>());

		campanhaFrom = new ArrayList<Campanha>();

		if (super.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor()) {
			campanhaFrom = campanhaService.getCampanhaAll();
		}

		else if (super.getUserType() == Constantes.USUARIO_AGENCIA.getValor()) {
			campanhaFrom = agenciaService.getAgenciaCampanha(super.getUserAccess().getIdUserAccess());
		}

		else if (super.getUserType() == Constantes.USUARIO_ANUNCIANTE.getValor()) {
			// FIXME
			// listaCampanhas =
			// campanhaService.getCampanhaUsuario(super.getUserAccess().getIdUserAccess());
		}

		this.campanhas.setSource(campanhaFrom);
		this.campanhas.setTarget(campanhaTo);

		return "relatorios";

	}

	public void validarDatas() {
		if (super.validarDatas(this.dataRelatorioInicio, this.dataRelatorioFim)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(this.dataRelatorioFim);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			super.getHttpServletRequest().getSession().setAttribute("dataRelatorioInicio", this.dataRelatorioInicio);
			super.getHttpServletRequest().getSession().setAttribute("dataRelatorioFim", cal.getTime());
			super.getHttpServletRequest().getSession().setAttribute("ddd", this.ddd);
			if (super.isNullOrBlank(this.getCampanhas().getTarget())) {
				super.getHttpServletRequest().getSession().setAttribute("campanhas", this.getCampanhas().getSource());
			}
			else {
				super.getHttpServletRequest().getSession().setAttribute("campanhas", this.getCampanhas().getTarget());
			}
		}
		else {
			super.getHttpServletRequest().getSession().removeAttribute("dataRelatorioInicio");
			super.getHttpServletRequest().getSession().removeAttribute("dataRelatorioFim");
			super.getHttpServletRequest().getSession().removeAttribute("ddd");
			super.getHttpServletRequest().getSession().removeAttribute("campanhas");
		}
	}

	public String timeStampToString(Timestamp data) {

		String dataFormat = "";

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

			java.util.Date parsedDate = dateFormat.parse(data.toString());

			java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");

			dataFormat = formatado.format(timestamp);

		}
		catch (Exception e) {
			logger.error(e);
		}

		return dataFormat;

	}

	public RelatorioMBean() {
		super();
		this.columns = new ArrayList<String>();
	}

	public void onTabChange(TabChangeEvent event) {
		this.reset();
	}

	public DualListModel<Campanha> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(DualListModel<Campanha> campanhas) {
		this.campanhas = campanhas;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public List<Cdr> getListaCdrs() {
		return listaCdrs;
	}

	public void setListaCdrs(List<Cdr> listaCdrs) {
		this.listaCdrs = listaCdrs;
	}

	public Date getDataRelatorioInicio() {
		return dataRelatorioInicio;
	}

	public void setDataRelatorioInicio(Date dataRelatorioInicio) {
		this.dataRelatorioInicio = dataRelatorioInicio;
	}

	public Date getDataRelatorioFim() {
		return dataRelatorioFim;
	}

	public void setDataRelatorioFim(Date dataRelatorioFim) {
		this.dataRelatorioFim = dataRelatorioFim;
	}

	public Date getDataEstimulo() {
		return dataEstimulo;
	}

	public void setDataEstimulo(Date dataEstimulo) {
		this.dataEstimulo = dataEstimulo;
	}

	public Long getImpactados() {
		return impactados;
	}

	public void setImpactados(Long impactados) {
		this.impactados = impactados;
	}

	public Long getEfetivos() {
		return efetivos;
	}

	public void setEfetivos(Long efetivos) {
		this.efetivos = efetivos;
	}

	public Date getDataEstimuloInicio() {
		return dataEstimuloInicio;
	}

	public void setDataEstimuloInicio(Date dataEstimuloInicio) {
		this.dataEstimuloInicio = dataEstimuloInicio;
	}

	public Date getDataEstimuloFim() {
		return dataEstimuloFim;
	}

	public void setDataEstimuloFim(Date dataEstimuloFim) {
		this.dataEstimuloFim = dataEstimuloFim;
	}

	public List<String> getColumns() {
		return columns;
	}

	public Boolean getConsolidado() {
		return consolidado;
	}

	public void setConsolidado(Boolean consolidado) {
		this.consolidado = consolidado;
	}
}
