package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.supportcomm.mktcall.vo.ListaCampanhaDialOutVO;
import br.com.supportcomm.mktcall.vo.ListaCampanhaVO;
import br.com.supportcomm.mktcall.vo.ListaDuracaoVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeConsolidadaVO;
import br.com.supportcomm.mktcall.vo.ListaEfetividadeVO;
import br.com.supportcomm.mktcall.vo.ListaInteracaoVO;

/**
 * The persistent class for the campanha database table.
 * 
 */
@Entity
@Table(name = "campanha")



@NamedQueries({ @NamedQuery(name = "Campanha.All", query = "select c from Campanha c"), 
	            @NamedQuery(name = "Campanha.Id", query = "select c from Campanha c where c.idCampanha = :idCampanha"),
		        @NamedQuery(name = "Campanha.byUsuario", query = "select c from Campanha c where c.userAccess.idUserAccess = :idUserAccess"),
		        @NamedQuery(name = "Campanha.spot", query = "select c from Campanha c where c.spot.idSpot = :idSpot")
})
@NamedNativeQueries({
	    @NamedNativeQuery(name="Campanha.AllNative", query = "select id_campanha ,"+
															  " call_duration ,"+
															  " description ,"+
															  " end_datetime ,"+
															  " insertion_contracted ,"+
															  " insertion_reach ,"+
															  " name,"+
															  " next_campaign ,"+
															  " once ,"+
															  " operation_date,"+
															  " priority,"+
															  " start_datatime ,"+
															  " id_spot ,"+
															  " status ,"+
															  " id_list from Campanha ",resultSetMapping="campanha"),
															  
		@NamedNativeQuery(name="Campanha.AllNativeNotRegional", query = "select id_campanha ,"+
																		  " call_duration ,"+
																		  " description ,"+
																		  " end_datetime ,"+
																		  " insertion_contracted ,"+
																		  " insertion_reach ,"+
																		  " name,"+
																		  " next_campaign ,"+
																		  " once ,"+
																		  " operation_date,"+
																		  " priority,"+
																		  " start_datatime ,"+
																		  " id_spot ,"+
																		  " status ,"+
															              " id_list from Campanha where id_campanha not in(Select r.id_campanha from Region r) ",resultSetMapping="campanha"),
															  
															  
															  
															  

		@NamedNativeQuery(name = "Campanha.interacao", query = "SELECT " + "campanha.name as name, " + " (select count(a.msisdn) " + " from ( "
				+ "  Select distinct  msisdn from msisdn_campanha where " + "  msisdn_campanha.operation_date between :dataini and :datafim "
				+ "  and  msisdn_campanha.id_campanha =:idCampanha ) a)  as liguniq,  " + "sum( case when spot_response.response=  '@' then 1 else 0 end) as semInteracao, "
				+ "sum( case when spot_response.response<> '@' then 1 else 0 end) as comInteracao " + "FROM " + "  public.msisdn_campanha," + "  public.campanha, " + "  public.spot_response "
				+ "WHERE " + "  msisdn_campanha.id_campanha = :idCampanha and" + "  msisdn_campanha.id_campanha = campanha.id_campanha AND "
				+ "  msisdn_campanha.id_msisdn_campanha = spot_response.id_msisdn_campanha and " + " ( msisdn_campanha.operation_date between :dataini and :datafim)  " + "group by name", resultSetMapping = "interacao"),			
				
		@NamedNativeQuery(name = "Campanha.efetividade", query = " Select c.name as campanha, " + 
																 " count(*) as totalExibicoes, " + 
																 " count(distinct msisdn) as usuariosUnicos, " +
																 " sum(Case When listen_complete = 't' Then 1 else 0 End) As ouviuCompleto, " +
																 " sum(Case When listen_complete = 'f' Then 1 else 0 End) As ouviuIncompleto " +
																 " from " + 
																 " statistic s, " +
																 " campanha c " +
																 " where " +
																 " c.id_campanha = :idCampanha " +
																 " and s.id_campanha = c.id_campanha " +
																 " and s.operation_date between :dataInicio and :dataFim " +
																 " group by c.name order by campanha ", 
																  resultSetMapping = "efetividade"),
																  
	   @NamedNativeQuery(name = "Campanha.efetividadeConsolidada", query = " Select c.name as campanha, " + 
																" count(*) as totalExibicoes, " + 
																" count(distinct msisdn) as usuariosUnicos, " +
																" sum(Case When listen_complete = 't' Then 1 else 0 End) As ouviuCompleto, " +
																" sum(Case When listen_complete = 'f' Then 1 else 0 End) As ouviuIncompleto," +
																" to_char(s.operation_date, 'DD/MM/YYYY') as data " +
																" from " + 
																" statistic s, " +
																" campanha c " +
																" where " +
																" c.id_campanha = :idCampanha " +
																" and s.id_campanha = c.id_campanha " +
																" and s.operation_date between :dataInicio and :dataFim " +
																" group by to_char(s.operation_date, 'DD/MM/YYYY'), " +
																" c.name order by data, campanha ", 
																 resultSetMapping = "efetividadeConsolidada"),  														  

		@NamedNativeQuery(name = "Campanha.duracao", query = "SELECT " + "campanha.id_campanha    campanha, " + "campanha.name           nome    ,  " + "count(cdr.duration)     quantidade, "
				+ "avg(cdr.duration)     media, " + "sum(cdr.duration)       totalsegundos " + "FROM  " + "public.campanha inner join " + "public.cdr " + "on( "
				+ "campanha.id_campanha = cdr.id_campanha) " + "Where  campanha.id_campanha = :idCampanha " + "and  campanha.id_campanha  in (select id_campanha from msisdn_campanha "
				+ "where id_campanha = :idCampanha " + "and msisdn_campanha.operation_date between :dataini and :datafim  ) " + "group by " + "campanha.id_campanha, " + "campanha.name ", resultSetMapping = "duracao"),

		@NamedNativeQuery(name = "Campanha.ByMsisdn", query = " select c.id_campanha ,"+
															  " c.call_duration ,"+
															  " c.description ,"+
															  " c.end_datetime ,"+
															  " c.insertion_contracted ,"+
															  " c.insertion_reach ,"+
															  " c.name,"+
															  " c.next_campaign ,"+
															  " c.once ,"+
															  " c.operation_date,"+
															  " c.priority,"+
															  " c.start_datatime ,"+
															  " c.id_spot ,"+
															  " c.status ,"+
															  " c.id_list from Campanha c, list_detail ld, list_segmentation ls " +
		                                                      " where c.id_list = ld.id_list and ld.id_list = ls.id_list and ls.msisdn = :msisdn " +
				                                              " and ld.list_active = 'TRUE' and c.status = 1", resultSetMapping = "ByMsisdn"),
	    
	    
	    
	    
	    
	    
	    /*@NamedNativeQuery(name = "Campanha.listaCampanhaDialOut", query="Select c.name               as campanha  ,"+
															  " c.id_campanha        as idCampanha, "+   
															  " insertion_contracted as contratado, "+
															  " insertion_reach      as completos ,"+
															  " (select count(s.*) from statistic s where s.listen_complete = false and s.id_campanha = c.id_campanha) as incompletos,"+
															  " (Case When c.process_status is null Then '0' else c.process_status End)      as processStatus"+
															  " from  "+
															  " campanha c "+
															  " where id_list is not  null order by c.status desc",resultSetMapping = "listaCampanhaDialOutVO")
	    */
	    
				                                              
				                 @NamedNativeQuery(name = "Campanha.listaCampanhaDialOut", query="  Select c.name               as campanha  , " +
				                                          "    c.id_campanha        as idCampanha, " +    
				                                          "    insertion_contracted as contratado, " +
				                                          "    (select count(s.*) from statistic s where s.listen_complete = true and s.id_campanha = c.id_campanha)  as completos , " +
				                                          "    (select count(s.*) from statistic s where s.listen_complete = false and s.id_campanha = c.id_campanha) as incompletos, " +
				                                          "    (Case When c.process_status is null Then '0'  " +
				                                          "          When (select sum(case when response_code in('2','5' ) then 1 else 0 end) " + 
				                                          "               from dialing where id_list in (select c.id_list from campanha c inner join list_segmentation ls on (c.id_list = ls.id_list) and c.id_campanha = " + 
				                                          "               dialing.id_campanha) and   id_campanha= c.id_campanha)  = (Select count(*) from list_segmentation ls inner join campanha c2 on (ls.id_list=c2.id_list) and  c2.id_campanha = c.id_campanha)          then  '1' " +
				                                          "          else c.process_status End)      as processStatus " +
				                                        " from  campanha c " +
				                                        " where id_list is not  null and status=1 order by c.status desc " ,resultSetMapping = "listaCampanhaDialOutVO")
	    

})
@SqlResultSetMappings({

	    @SqlResultSetMapping(name="campanha", entities=@EntityResult(entityClass=ListaCampanhaVO.class)),

		@SqlResultSetMapping(name="ByMsisdn", entities=@EntityResult(entityClass=ListaCampanhaVO.class)),
	
		@SqlResultSetMapping(name = "interacao", entities = { @EntityResult(entityClass = ListaInteracaoVO.class, fields = { @FieldResult(name = "name", column = "name"),
				@FieldResult(name = "liguniq", column = "liguniq"), @FieldResult(name = "semInteracao", column = "semInteracao"), @FieldResult(name = "comInteracao", column = "comInteracao") }) }),

		@SqlResultSetMapping(name = "duracao", entities = { @EntityResult(entityClass = ListaDuracaoVO.class, fields = { @FieldResult(name = "campanha", column = "campanha"),
				@FieldResult(name = "nome", column = "nome"), @FieldResult(name = "quantidade", column = "quantidade"), @FieldResult(name = "media", column = "media"),
				@FieldResult(name = "totalsegundos", column = "totalsegundos") }) }),

		@SqlResultSetMapping(name = "efetividade", entities = { @EntityResult(entityClass = ListaEfetividadeVO.class, fields = { @FieldResult(name = "nomeCampanha", column = "campanha"),
				@FieldResult(name = "totalExibicoes", column = "totalExibicoes"), @FieldResult(name = "usuariosUnicos", column = "usuariosUnicos"), @FieldResult(name = "ouviuCompleto", column = "ouviuCompleto"),
				@FieldResult(name = "ouviuIncompleto", column = "ouviuIncompleto") }) }),
				
		@SqlResultSetMapping(name = "efetividadeConsolidada", entities = { @EntityResult(entityClass = ListaEfetividadeConsolidadaVO.class, fields = { @FieldResult(name = "nomeCampanha", column = "campanha"),
				@FieldResult(name = "totalExibicoes", column = "totalExibicoes"), @FieldResult(name = "usuariosUnicos", column = "usuariosUnicos"), @FieldResult(name = "ouviuCompleto", column = "ouviuCompleto"),
				@FieldResult(name = "ouviuIncompleto", column = "ouviuIncompleto"), @FieldResult(name = "data", column = "data"), }) }),
				
		@SqlResultSetMapping(name = "listaCampanhaDialOutVO", 
		                     entities = { @EntityResult(entityClass = ListaCampanhaDialOutVO.class, 
		                                  fields = { @FieldResult(name = "idCampanha"     , column = "idCampanha"),
		                    	 					 @FieldResult(name = "nomeCampanha"   , column = "campanha"),
			                                         @FieldResult(name = "contratado"     , column = "contratado"), 
			                                         @FieldResult(name = "completos"      , column = "completos"), 
			                                         @FieldResult(name = "incompletos"    , column = "incompletos"),
			                                         @FieldResult(name = "processStatus"  , column = "processStatus") 
			                                        }) 
		                                 }),
				
				
				

})
public class Campanha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_campanha", unique = true, nullable = false)
	@Index(name = "idx_campanha_id")
	private Long idCampanha;

	@Index(name = "idx_campanha_call_duration")
	@Column(name = "call_duration")
	private Integer callDuration;

	@Index(name = "idx_campanha_description")
	@Column(length = 255)
	private String description;

	@Index(name = "idx_campanha_end_datetime")
	@Column(name = "end_datetime")
	private Timestamp endDatetime;

	@Index(name = "idx_campanha_insertion_contracted")
	@Column(name = "insertion_contracted", nullable = false)
	private Long insertionContracted;

	@Index(name = "idx_campanha_insertion_reach")
	@Column(name = "insertion_reach", nullable = false)
	private Long insertionReach;

	
	
	@Column(name = "insertion_reach_complement", nullable = false)
	private Long insertionReachComplement;

	
	@Index(name = "idx_campanha_name")
	@Column(length = 255)
	private String name;

	@Index(name = "idx_campanha_next_campaign")
	@Column(name = "next_campaign")
	private Boolean nextCampaign;

	@Index(name = "idx_campanha_operation_date")
	@Column(name = "operation_date")
	private Timestamp operationDate;

	@Index(name = "idx_campanha_priority")
	private Integer priority;

	@Index(name = "idx_campanha_start_datatime")
	@Column(name = "start_datatime")
	private Timestamp startDatatime;

	@Index(name = "idx_campanha_status")
	@Column(name = "status")
	private Integer status;

	@Index(name = "idx_campanha_once")
	@Column(name = "once")
	private boolean once;

	// bi-directional many-to-one association to Insertion
	@OneToMany(mappedBy = "campanhaInsertion", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Insertion> insertions;

	// bi-directional many-to-one association to Insertion
	@ManyToOne
	@JoinColumn(name = "id_spot")
	private Spot spot;

	@OneToMany(mappedBy = "campanhaAgencia")
	private List<Agencia> agencia;

	@ManyToOne
	@JoinColumn(name = "id_user_access")
	private UserAccess userAccess;
	
	// bi-directional many-to-one association to Insertion
	@ManyToOne
	@JoinColumn(name = "id_list")
	@Index(name = "idx_campanha_id_list")
	private ListDetail listDetail;	

	//refere-se ao status de processamento dos dados para inserir no dialout
	// 0 ou null - não iniciado
	// 1 - terminado
	// 2 - em processamento
	@Column(name = "process_status",length=1)
	private String processStatus;
	
	
	public Campanha() {
	}

	public Long getIdCampanha() {
		return this.idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public Integer getCallDuration() {
		return this.callDuration;
	}

	public void setCallDuration(Integer callDuration) {
		this.callDuration = callDuration;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Long getInsertionContracted() {
		return this.insertionContracted;
	}

	public void setInsertionContracted(Long insertionContracted) {
		this.insertionContracted = insertionContracted;
	}

	public Long getInsertionReach() {
		return this.insertionReach;
	}

	public void setInsertionReach(Long insertionReach) {
		this.insertionReach = insertionReach;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getNextCampaign() {
		return this.nextCampaign;
	}

	public void setNextCampaign(Boolean nextCampaign) {
		this.nextCampaign = nextCampaign;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Timestamp getStartDatatime() {
		return this.startDatatime;
	}

	public void setStartDatatime(Timestamp startDatatime) {
		this.startDatatime = startDatatime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Insertion> getInsertions() {
		return this.insertions;
	}

	public void setInsertions(List<Insertion> insertions) {
		this.insertions = insertions;
	}

	public boolean getOnce() {
		return once;
	}

	public void setOnce(boolean once) {
		this.once = once;
	}

	public List<Agencia> getAgencia() {
		return agencia;
	}

	public void setAgencia(List<Agencia> agencia) {
		this.agencia = agencia;
	}

	public UserAccess getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(UserAccess userAccess) {
		this.userAccess = userAccess;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public ListDetail getListDetail() {
		return listDetail;
	}

	public void setListDetail(ListDetail listDetail) {
		this.listDetail = listDetail;
	}

	public Long getInsertionReachComplement() {
		return insertionReachComplement;
	}

	public void setInsertionReachComplement(Long insertionReachComplement) {
		this.insertionReachComplement = insertionReachComplement;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
}