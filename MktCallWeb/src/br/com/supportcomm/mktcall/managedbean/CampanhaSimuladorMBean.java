package br.com.supportcomm.mktcall.managedbean;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Cdr;
import br.com.supportcomm.mktcall.entity.Insertion;
import br.com.supportcomm.mktcall.entity.ListaGeral;
import br.com.supportcomm.mktcall.entity.Region;
import br.com.supportcomm.mktcall.service.agencia.AgenciaService;
import br.com.supportcomm.mktcall.service.anunciante.AnuncianteService;
import br.com.supportcomm.mktcall.service.campanha.CampanhaService;
import br.com.supportcomm.mktcall.service.cdr.CdrService;
import br.com.supportcomm.mktcall.service.insertion.InsertionService;
import br.com.supportcomm.mktcall.service.listageral.ListaGeralService;
import br.com.supportcomm.mktcall.service.msisdncampanha.MsisdnCampanhaService;
import br.com.supportcomm.mktcall.service.region.RegionService;
import br.com.supportcomm.mktcall.service.spot.SpotService;

@ManagedBean
@SessionScoped
public class CampanhaSimuladorMBean extends AbstractManagedBean {

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

	private Logger logger = Logger.getLogger(getClass());
	private List<Campanha> campanhaFinal;
	private Campanha[] campanhasSelecionadas;

	public CampanhaSimuladorMBean() {

	}

	@SuppressWarnings("unused")
	//@PostConstruct
	public List<Campanha> getListaCampanhaSimulador() {

		// String msisdn = "msisdn";
		// String callid = "callid";
		// String cfuCode = "cfucode";
		// String whiteList = "whitelist";

		List<Region> regionList = new ArrayList<Region>();
		List<Campanha> campanhaList = new ArrayList<Campanha>();
		List<Campanha> campanhaFiltradas = new ArrayList<Campanha>();
		List<Campanha> campanhaP0 = new ArrayList<Campanha>();
		List<Campanha> campanhaP1 = new ArrayList<Campanha>();
		List<Campanha> campanhaP2 = new ArrayList<Campanha>();
		List<Campanha> campanhaP3 = new ArrayList<Campanha>();
		List<Campanha> campanhaP4 = new ArrayList<Campanha>();
		List<Campanha> campanhaP5 = new ArrayList<Campanha>();
		List<ListaGeral> listaGeral = new ArrayList<ListaGeral>();

		Timestamp time = new Timestamp(System.currentTimeMillis());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 12:00:00");

		String formatedDate = format.format(time.getTime());

		Timestamp dataHoje = Timestamp.valueOf(formatedDate);

		try {

			/*
			 * Logicas e regras
			 */
			/*
			 * if (!isNullOrBlank(whiteList)) {
			 * 
			 * if (!Boolean.parseBoolean(whiteList)) {
			 * 
			 * List<Cdr> cdr = getCdrList(msisdn);
			 * 
			 * if (!cdr.isEmpty()) {
			 * 
			 * //
			 * 
			 * }
			 * 
			 * } }
			 */
			/*
			 * else {
			 * 
			 * List<Cdr> cdr = getCdrList(msisdn);
			 * 
			 * if (!cdr.isEmpty()) {
			 * 
			 * //
			 * 
			 * }
			 * 
			 * }
			 */
			// listaGeral = listaGeralService.getListaGeralByMsisdn(msisdn);
			/*
			 * if (!listaGeral.isEmpty()) {
			 * 
			 * for (ListaGeral lista : listaGeral) { Campanha campanhaFidelizada
			 * = new Campanha();
			 * 
			 * campanhaFidelizada =
			 * campanhaService.getCampanhaId(lista.getIdCampanha()).get(0);
			 * 
			 * if (verificarCampanhaListaFidelizada(campanhaFidelizada,
			 * dataHoje)) campanhaList.add(campanhaFidelizada); }
			 * 
			 * }
			 */
			/*
			 * else {
			 * 
			 * regionList = regionService.getRegionAdvertisement(cfuCode);
			 * 
			 * for (Region region : regionList) {
			 * 
			 * if (region.getCampanha().getIdCampanha() != null &&
			 * region.getCfu().getIdCfu() != null)
			 * campanhaList.add(region.getCampanha());
			 * 
			 * } }
			 */
			if (campanhaList.isEmpty()) {

				List<Campanha> campanhaListRecovery = new ArrayList<Campanha>();
				campanhaListRecovery = campanhaService.getCampanhaAll();

				Collections.shuffle(campanhaListRecovery);

				campanhaList.addAll(campanhaListRecovery);

				if (campanhaList.isEmpty()) {

					//
				}

			}

			// Filtrar campanhas que estejam dentro da data ativa e com
			// stats 1

			List<Insertion> insertionList = new ArrayList<Insertion>();

			for (Campanha campanhaFiltro : campanhaList) {

				if (campanhaFiltro.getStatus() == 1) {

					if (campanhaFiltro.getEndDatetime().after(dataHoje) || campanhaFiltro.getEndDatetime().equals(dataHoje)) {

						if (campanhaFiltro.getStartDatatime().before(dataHoje) || campanhaFiltro.getStartDatatime().equals(dataHoje)) {

							if (campanhaFiltro.getInsertionReach() <= campanhaFiltro.getInsertionContracted()) {

								insertionList = insertionService.getInsertionCampanha(campanhaFiltro.getIdCampanha());

								campanhaFiltro.setInsertions(insertionList);

								if (!campanhaFiltro.getInsertions().isEmpty()) {

									if (verifyDay(campanhaFiltro)) {
										campanhaFiltradas.add(campanhaFiltro);
									}
								}
								else {
									campanhaFiltradas.add(campanhaFiltro);
								}

							}

						}
					}

				}

			}

			for (Campanha campanhaPrioridade : campanhaFiltradas) {

				switch (campanhaPrioridade.getPriority()) {

				case 0:
					campanhaP0.add(campanhaPrioridade);
					break;
				case 1:
					campanhaP1.add(campanhaPrioridade);
					break;
				case 2:
					campanhaP2.add(campanhaPrioridade);
					break;
				case 3:
					campanhaP3.add(campanhaPrioridade);
					break;
				case 4:
					campanhaP4.add(campanhaPrioridade);
					break;
				case 5:
					campanhaP5.add(campanhaPrioridade);
					break;
				default:
					break;
				}
			}

			if (!campanhaP0.isEmpty()) {
				campanhaFiltradas = new ArrayList<Campanha>();
				campanhaFiltradas.addAll(campanhaP0);
			}
			else if (!campanhaP1.isEmpty()) {
				campanhaFiltradas = new ArrayList<Campanha>();
				campanhaFiltradas.addAll(campanhaP1);
			}
			else if (!campanhaP2.isEmpty()) {
				campanhaFiltradas = new ArrayList<Campanha>();
				campanhaFiltradas.addAll(campanhaP2);
			}
			else if (!campanhaP3.isEmpty()) {
				campanhaFiltradas = new ArrayList<Campanha>();
				campanhaFiltradas.addAll(campanhaP3);
			}
			else if (!campanhaP4.isEmpty()) {
				campanhaFiltradas = new ArrayList<Campanha>();
				campanhaFiltradas.addAll(campanhaP4);
			}
			else if (!campanhaP5.isEmpty()) {
				campanhaFiltradas = new ArrayList<Campanha>();
				campanhaFiltradas.addAll(campanhaP5);
			}

			Collections.shuffle(campanhaFiltradas);

			if (campanhaFiltradas.isEmpty()) {

				//

			}
			else {

				ArrayList<Campanha> campanhaOne = new ArrayList<Campanha>();
				campanhaOne.add(campanhaFiltradas.get(0));

				setCampanhaFinal(campanhaOne);

			}

		}
		catch (Exception e) {

			logger.error(e);
			e.printStackTrace();

		}
		return campanhaFiltradas;

	}

	public List<Cdr> getCdrList(String msisdn) {

		List<Cdr> cdrList = cdrService.getCdrTodayOnce(4, msisdn, 1);

		List<Cdr> cdrListReturn = new ArrayList<Cdr>();

		for (Cdr cdr : cdrList) {

			if (verifyTimestampDay(cdr.getCallDatetime(), new Timestamp(System.currentTimeMillis()))) {
				cdrListReturn.add(cdr);
			}

		}

		return cdrListReturn;

	}

	public boolean verifyTimestampDay(Timestamp dayOne, Timestamp dayTwo) {

		String frist = new SimpleDateFormat("yyyy-MM-dd").format(dayOne);

		String second = new SimpleDateFormat("yyyy-MM-dd").format(dayTwo);

		if (frist.equals(second)) return true;

		return false;

	}

	public boolean verificarCampanhaListaFidelizada(Campanha campanhaFiltro, Timestamp dataHoje) {

		List<Insertion> insertionList = new ArrayList<Insertion>();

		if (campanhaFiltro.getStatus() == 1) {

			if (campanhaFiltro.getEndDatetime().after(dataHoje) || campanhaFiltro.getEndDatetime().equals(dataHoje)) {

				if (campanhaFiltro.getStartDatatime().before(dataHoje) || campanhaFiltro.getStartDatatime().equals(dataHoje)) {

					if (campanhaFiltro.getInsertionReach() <= campanhaFiltro.getInsertionContracted()) {

						insertionList = insertionService.getInsertionCampanha(campanhaFiltro.getIdCampanha());

						campanhaFiltro.setInsertions(insertionList);

						if (!campanhaFiltro.getInsertions().isEmpty()) {

							if (verifyDay(campanhaFiltro)) {
								return true;
							}
						}
						else {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	protected boolean verifyDay(Campanha campanha) {
	/*
			Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
	
			boolean dayOK = false;
	
			for (Insertion insertion : campanha.getInsertions()) {
	
				if (insertion.getDayOfWeek() == Calendar.DAY_OF_WEEK) {
	
					if (insertion.getStartTime().equals(dataDeHoje) || insertion.getStartTime().after(dataDeHoje)) {
						if (insertion.getEndTime().equals(dataDeHoje) || insertion.getEndTime().before(dataDeHoje)) {
							dayOK = true;
						}
					}
				}
	
			}
			return dayOK;
	*/


		Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
		
		boolean dayOK = false;
		Calendar now = Calendar.getInstance();
		now.setTime(dataDeHoje);
		int diaDaSemana =now.get(Calendar.DAY_OF_WEEK);
		
		for (Insertion insertion : campanha.getInsertions()) {
			if (insertion.getDayOfWeek() == diaDaSemana ) {
				
				insertion.getStartTime().toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startTime;
				String endTime;
				String horaDeHoje; 
				startTime =  dataDeHoje.toString().substring(0, 11) + insertion.getStartTime().toString().substring(11,16)+":00";
				endTime   =  dataDeHoje.toString().substring(0, 11) + insertion.getEndTime().toString().substring(11,16)+":00";
				horaDeHoje = dataDeHoje.toString();
				;
				try {
					if (sdf.parse(startTime).equals(sdf.parse(horaDeHoje) ) ||sdf.parse(startTime).before(sdf.parse(horaDeHoje))) {
						if (sdf.parse(endTime).equals(sdf.parse(horaDeHoje) ) ||sdf.parse(endTime).after(sdf.parse(horaDeHoje))) {
							dayOK = true;
						}
					
					}
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				/*if (insertion.getStartTime().equals(dataDeHoje) || insertion.getStartTime().after(dataDeHoje)) {
					if (insertion.getEndTime().equals(dataDeHoje) || insertion.getEndTime().before(dataDeHoje)) {
						dayOK = true;
					}
				}*/
			}

		}
		return dayOK;

	
	}

	public List<Campanha> getCampanhaFinal() {
		return campanhaFinal;
	}

	public void setCampanhaFinal(List<Campanha> campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}
	
	public Campanha[] getCampanhasSelecionadas() {
		return campanhasSelecionadas;
	}

	public void setCampanhasSelecionadas(Campanha[] campanhasSelecionadas) {
		this.campanhasSelecionadas = campanhasSelecionadas;
	}

}
