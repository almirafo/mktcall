package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;


/**
 * The persistent class for the spot_tipo database table.
 * 
 */
@Entity
@Table(name="spot_tipo")
@NamedQueries({ 
	@NamedQuery(name = "spotTipo.All", query = "select s from SpotTipo s order by s.codeSpotTipo"),
	@NamedQuery(name = "spotTipo.AllNotObsolete", query = "select s from SpotTipo s where s.spotAction <> '0' order by s.codeSpotTipo"),
    @NamedQuery(name = "spotTipo.Id", query = "select s from SpotTipo s where s.idSpotTipo = :id") 
			  })

public class SpotTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_spot_tipo", unique=true, nullable=false)
	@Index(name="idx_spotTipo_id")
	private Long idSpotTipo;

	@Index(name = "idx_spottipo_code_spot_tipo")
	@Column(name="code_spot_tipo")
	private int codeSpotTipo;

	@Index(name = "idx_spottipo_nome_spot_tipo")
	@Column(name="nome_spot_tipo", length=30)
	private String nomeSpotTipo;

	@Index(name = "idx_spottipo_spot_action")
	@Column(name="spot_action", length=255)
	private String spotAction;

	public SpotTipo() {
	}

	public Long getIdSpotTipo() {
		return this.idSpotTipo;
	}

	public void setIdSpotTipo(Long idSpotTipo) {
		this.idSpotTipo = idSpotTipo;
	}

	public int getCodeSpotTipo() {
		return this.codeSpotTipo;
	}

	public void setCodeSpotTipo(int codeSpotTipo) {
		this.codeSpotTipo = codeSpotTipo;
	}

	public String getNomeSpotTipo() {
		return this.nomeSpotTipo;
	}

	public void setNomeSpotTipo(String nomeSpotTipo) {
		this.nomeSpotTipo = nomeSpotTipo;
	}

	public String getSpotAction() {
		return this.spotAction;
	}

	public void setSpotAction(String spotAction) {
		this.spotAction = spotAction;
	}

}