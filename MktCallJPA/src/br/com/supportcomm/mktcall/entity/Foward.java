package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the foward database table.
 * 
 */
@Entity
public class Foward implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FOWARD_IDFOWARD_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FOWARD_IDFOWARD_GENERATOR")
	@Column(name="id_foward")
	private Long idFoward;

	@Column(name="audio_filename")
	private String audioFilename;

	@Column(name="audio_position")
	private String audioPosition;

	private String cfu;

	@Column(name="foward_enabled")
	private Boolean fowardEnabled;

	@Column(name="id_campanha")
	private Long idCampanha;

	public Foward() {
	}

	public Long getIdFoward() {
		return this.idFoward;
	}

	public void setIdFoward(Long idFoward) {
		this.idFoward = idFoward;
	}

	public String getAudioFilename() {
		return this.audioFilename;
	}

	public void setAudioFilename(String audioFilename) {
		this.audioFilename = audioFilename;
	}

	public String getAudioPosition() {
		return this.audioPosition;
	}

	public void setAudioPosition(String audioPosition) {
		this.audioPosition = audioPosition;
	}

	public String getCfu() {
		return this.cfu;
	}

	public void setCfu(String cfu) {
		this.cfu = cfu;
	}

	public Boolean getFowardEnabled() {
		return this.fowardEnabled;
	}

	public void setFowardEnabled(Boolean fowardEnabled) {
		this.fowardEnabled = fowardEnabled;
	}

	public Long getIdCampanha() {
		return this.idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

}