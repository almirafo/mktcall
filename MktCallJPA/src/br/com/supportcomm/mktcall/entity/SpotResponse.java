package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;

/**
 * The persistent class for the spot_response database table.
 * 
 */
@Entity
@Table(name = "spot_response")
@NamedQueries({ @NamedQuery(name = "Spot.id", query = "select s from SpotResponse s where s.idSpotResponse = :idSpotResponse"),
				@NamedQuery(name = "Spot.AudioFiles", query = "select s from SpotResponse s where s.spotAudioFile.idSpotAudioFiles = :idSpotAudioFile"),
				@NamedQuery(name = "Spot.byMsisdnCampanha", query = "select s from SpotResponse s where s.msisdnCampanha.idMsisdnCampanha = :idMsisdnCampanha")
	}
)
public class SpotResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_spot_response", unique = true, nullable = false)
	@Index(name = "idx_spotresponse_id")
	private Long idSpotResponse;

	@Index(name = "idx_spotresponse_response")
	@Column(nullable = false, length = 25)
	private String response;

	// bi-directional many-to-one association to MsisdnCampanha
	@ManyToOne
	@JoinColumn(name = "id_msisdn_campanha", nullable = false)
	private MsisdnCampanha msisdnCampanha;

	// bi-directional many-to-one association to SpotAudioFile
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_spot_audio_files", nullable = false)
	private SpotAudioFile spotAudioFile;

	public SpotResponse() {
	}

	public Long getIdSpotResponse() {
		return this.idSpotResponse;
	}

	public void setIdSpotResponse(Long idSpotResponse) {
		this.idSpotResponse = idSpotResponse;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public MsisdnCampanha getMsisdnCampanha() {
		return this.msisdnCampanha;
	}

	public void setMsisdnCampanha(MsisdnCampanha msisdnCampanha) {
		this.msisdnCampanha = msisdnCampanha;
	}

	public SpotAudioFile getSpotAudioFile() {
		return this.spotAudioFile;
	}

	public void setSpotAudioFile(SpotAudioFile spotAudioFile) {
		this.spotAudioFile = spotAudioFile;
	}

}