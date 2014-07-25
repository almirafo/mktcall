package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Index;


/**
 * The persistent class for the spot_audio_files database table.
 * 
 */
@Entity
@Table(name="spot_audio_files")
@NamedQueries({ 
	@NamedQuery(name = "SpotAudioFile.spotFileNameExistente", query = "select s from SpotAudioFile s where s.spotFilename = :nomeAudio"),
    @NamedQuery(name = "SpotAudioFile.ByFileName", query = "select s from SpotAudioFile s where s.spotFilename like :filename"),
    @NamedQuery(name = "SpotAudioFile.BySpotId", query = "select s from SpotAudioFile s where s.spot.idSpot = :idSpot order by s.spotAudioOrder asc, s.operationDate desc"),
    @NamedQuery(name = "SpotAudioFile.ById", query = "select s from SpotAudioFile s where s.idSpotAudioFiles = :id")
			  })

public class SpotAudioFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_spot_audio_files", unique=true, nullable=false)
	@Index(name="idx_spotAudioFile_id")
	private Long idSpotAudioFiles;

	@Index(name = "idx_spotaudiofile_operation_date")
	@Column(name="operation_date")
	private Timestamp operationDate;

	@Index(name = "idx_spotaudiofile_spot_audio_description")
	@Column(name="spot_audio_description", nullable=false, length=50)
	private String spotAudioDescription;

	@Index(name = "idx_spotaudiofile_spot_audio_order")
	@Column(name="spot_audio_order", nullable=false)
	private Integer spotAudioOrder;

	@Index(name = "idx_spotaudiofile_spot_filename")
	@Column(name="spot_filename", nullable=false, length=255)
	private String spotFilename;
	
	@Index(name = "idx_spotaudiofile_digits")
	@Column(name="digits", nullable=false)
	private int digits;
	
	//bi-directional many-to-one association to Spot
	@ManyToOne
	@Index(name = "idx_spotaudiofile_id_spot")
	@JoinColumn(name="id_spot")
	private Spot spot;

	public SpotAudioFile() {
	}

	public Long getIdSpotAudioFiles() {
		return this.idSpotAudioFiles;
	}

	public void setIdSpotAudioFiles(Long idSpotAudioFiles) {
		this.idSpotAudioFiles = idSpotAudioFiles;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public String getSpotAudioDescription() {
		return this.spotAudioDescription;
	}

	public void setSpotAudioDescription(String spotAudioDescription) {
		this.spotAudioDescription = spotAudioDescription;
	}

	public Integer getSpotAudioOrder() {
		return this.spotAudioOrder;
	}

	public void setSpotAudioOrder(Integer spotAudioOrder) {
		this.spotAudioOrder = spotAudioOrder;
	}

	public String getSpotFilename() {
		return this.spotFilename;
	}

	public void setSpotFilename(String spotFilename) {
		this.spotFilename = spotFilename;
	}

	public Spot getSpot() {
		return this.spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

}