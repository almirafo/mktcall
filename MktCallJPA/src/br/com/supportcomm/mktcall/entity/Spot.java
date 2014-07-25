package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * The persistent class for the spot database table.
 * 
 */
@Entity
@Table(name = "spot")
@NamedQueries({ @NamedQuery(name = "Spot.Usuario", query = "select s from Spot s where s.anunciante.userAccess.idUserAccess = :idUsuario"),
		@NamedQuery(name = "Spot.Anunciante", query = "select s from Spot s where s.anunciante.idAnunciante = :idAnunciante"),
		@NamedQuery(name = "Spot.AgenciaUsuario", query = "select s from Spot s where s.anunciante.agencia.userAccess.idUserAccess= :idUsuario"),
		@NamedQuery(name = "Spot.ById", query = "select s from Spot s where s.idSpot = :id"),
		@NamedQuery(name = "Spot.All", query = "select s from Spot s"),
		@NamedQuery(name = "Spot.Campanha", query = "select s from Spot s, Campanha c where c.spot.idSpot = s.idSpot and c.idCampanha = :idCampanha"),
		@NamedQuery(name= "Spot.ByName", query="select s from Spot s where s.spotName = :spotName")})
public class Spot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Index(name = "id_spot")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_spot", unique = true, nullable = false)
	private Long idSpot;

	@Index(name = "idx_spot_message_sms")
	@Column(name = "message_sms", length = 255)
	private String messageSms;

	@Index(name = "idx_spot_operation_date")
	@Column(name = "operation_date")
	private Timestamp operationDate;

	@Index(name = "idx_spot_price")
	@Column(precision = 19, scale = 2)
	private BigDecimal price;

	@Index(name = "idx_spot_server_sms")
	@Column(name = "server_sms", length = 255)
	private String serverSms;

	@Index(name = "idx_spot_spot_confirm_digit")
	@Column(name = "spot_confirm_digit", length = 2)
	private String spotConfirmDigit;

	@Index(name = "idx_spot_spot_name")
	@Column(name = "spot_name", length = 255)
	private String spotName;

	@Index(name = "idx_spot_spot_type")
	@Column(name = "spottype")
	private Integer spotType;
	
	@Index(name = "idx_spot_protocol")
	@Column(name="protocol")
	private String protocol;
	
	@Column(name="internal")
	private Boolean internal;
	
	// bi-directional many-to-one association to Campanha
	@OneToMany(mappedBy = "spot")
	private List<Campanha> campanhaSpot;

	// bi-directional many-to-one association to Anunciante
	@ManyToOne
	@JoinColumn(name = "id_anunciante")
	private Anunciante anunciante;

	// bi-directional many-to-one association to SpotAudioFile
	@OneToMany(mappedBy = "spot", cascade = CascadeType.ALL)
	private List<SpotAudioFile> spotAudioFiles;

	public Spot() {
	}

	public Long getIdSpot() {
		return this.idSpot;
	}

	public void setIdSpot(Long idSpot) {
		this.idSpot = idSpot;
	}

	public String getMessageSms() {
		return this.messageSms;
	}

	public void setMessageSms(String messageSms) {
		this.messageSms = messageSms;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getServerSms() {
		return this.serverSms;
	}

	public void setServerSms(String serverSms) {
		this.serverSms = serverSms;
	}

	public String getSpotConfirmDigit() {
		return this.spotConfirmDigit;
	}

	public void setSpotConfirmDigit(String spotConfirmDigit) {
		this.spotConfirmDigit = spotConfirmDigit;
	}

	public String getSpotName() {
		return this.spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public Anunciante getAnunciante() {
		return this.anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public List<SpotAudioFile> getSpotAudioFiles() {
		return this.spotAudioFiles;
	}

	public void setSpotAudioFiles(List<SpotAudioFile> spotAudioFiles) {
		this.spotAudioFiles = spotAudioFiles;
	}

	public Integer getSpotType() {
		return spotType;
	}

	public void setSpotType(Integer spotType) {
		this.spotType = spotType;
	}

	public List<Campanha> getCampanhaSpot() {
		return campanhaSpot;
	}

	public void setCampanhaSpot(List<Campanha> campanhaSpot) {
		this.campanhaSpot = campanhaSpot;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Boolean getInternal() {
		return internal;
	}

	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

}