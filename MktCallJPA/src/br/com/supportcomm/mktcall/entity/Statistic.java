package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;

import java.sql.Timestamp;


/**
 * The persistent class for the statistic database table.
 * 
 */
@Entity
@Table(name="statistic")
@NamedQueries({
	@NamedQuery(name = "Statistic.byIdCampanha", query = "select s from Statistic s where s.idCampanha = :idCampanha "),
	@NamedQuery(name = "Statistic.byIdMsisdn", query = "select s from Statistic s where s.msisdn = :msisdn "),
	@NamedQuery(name = "Statistic.byListenComplete", query = "select s from Statistic s where s.listenComplete = :listenComplete "),
	@NamedQuery(name = "Statistic.byIdCdr", query = "select s from Statistic s where s.idCdr = :idCdr "),
	@NamedQuery(name = "Statistic.byEfetividade", query="select s from Statistic s where s.listenComplete = :listenComplete and s.operationDate between :startDate and :endDate and s.idCampanha = :idCampanha" ),
	@NamedQuery(name = "Statistic.byEfetividadeUnicos", query="select distinct s from Statistic s where s.listenComplete = :listenComplete and s.operationDate between :startDate and :endDate and s.idCampanha = :idCampanha" )
 
			  })

public class Statistic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_stat", unique=true, nullable=false)
	private Long idStat;
	
	@Index(name = "idx_statistic_id_campanha")
	@Column(name="id_campanha")
	private Long idCampanha;

	@Index(name = "idx_statistic_id_cdr")
	@Column(name="id_cdr", nullable=false)
	private Long idCdr;

	@Index(name = "idx_statistic_msisdn")
	@Column(length=15)
	private String msisdn;

	@Index(name = "idx_statistic_operation_date")
	@Column(name="operation_date")
	private Timestamp operationDate;
	
	@Index(name = "idx_statistic_listen_complete")
	@Column(name="listen_complete")
	private boolean listenComplete;

	public boolean isListenComplete() {
		return listenComplete;
	}

	public void setListenComplete(boolean listenComplete) {
		this.listenComplete = listenComplete;
	}

	public Statistic() {
	}

	public Long getIdStat() {
		return this.idStat;
	}

	public void setIdStat(Long idStat) {
		this.idStat = idStat;
	}

	public Long getIdCampanha() {
		return this.idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public Long getIdCdr() {
		return this.idCdr;
	}

	public void setIdCdr(Long idCdr) {
		this.idCdr = idCdr;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

}