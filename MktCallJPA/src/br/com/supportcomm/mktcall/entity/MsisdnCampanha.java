package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * The persistent class for the msisdn_campanha database table.
 * 
 */
@Entity
@Table(name = "msisdn_campanha")
@NamedQueries({ @NamedQuery(name = "MsisdnCampanha.id", query = "select m from MsisdnCampanha m where m.callid = :msisdnId "),
		@NamedQuery(name = "MsisdnCampanha.idCampanha", query = "select m from MsisdnCampanha m where m.campanha.idCampanha = :idCampanha "),
		@NamedQuery(name = "MsisdnCampanha.idCampanha&msisdn", query = "select m from MsisdnCampanha m where m.campanha.idCampanha = :idCampanha and m.msisdn = :msisdn "),
		@NamedQuery(name = "MsisdnCampanha.msisdn", query = "select m from MsisdnCampanha m where m.msisdn = :msisdn "),
		@NamedQuery(name = "MsisdnCampanha.advertisement", query = "select m from MsisdnCampanha m where m.msisdn = :msisdn and m.callid = :callid ") 
		
		})
/*
@NamedNativeQueries({
	@NamedNativeQuery(name = "MsisdnCampanha.advertisement", query = "select  from Msisdn_Campanha  where msisdn = :msisdn and  to_char(operation_date, 'DD/MM/YYYY') = :operationDate", 
			  resultSetMapping = "MsisdnCampanha")
})

*/
public class MsisdnCampanha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_msisdn_campanha", unique = true, nullable = false)
	private Long idMsisdnCampanha;

	@Index(name = "idx_msisdncampanha_msisdn")
	@Column(length = 15)
	private String msisdn;

	@Index(name = "idx_msisdncampanha_operation_date")
	@Column(name = "operation_date")
	private Timestamp operationDate;

	// bi-directional many-to-one association to Campanha
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_campanha")
	private Campanha campanha;

	@Index(name = "idx_msisdncampanha_callid")
	@Column(name = "callid")
	private String callid;

	// bi-directional many-to-one association to SpotResponse
	@OneToMany(mappedBy = "msisdnCampanha", orphanRemoval = true)
	private List<SpotResponse> spotResponses;
	
	public MsisdnCampanha() {
	}

	public Long getIdMsisdnCampanha() {
		return this.idMsisdnCampanha;
	}

	public void setIdMsisdnCampanha(Long idMsisdnCampanha) {
		this.idMsisdnCampanha = idMsisdnCampanha;
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

	public Campanha getCampanha() {
		return this.campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public List<SpotResponse> getSpotResponses() {
		return this.spotResponses;
	}

	public void setSpotResponses(List<SpotResponse> spotResponses) {
		this.spotResponses = spotResponses;
	}

	
	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

}
