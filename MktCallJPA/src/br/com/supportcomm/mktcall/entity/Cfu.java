package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cfu database table.
 * 
 */
@Entity
public class Cfu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CFU_IDCFU_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFU_IDCFU_GENERATOR")
	@Column(name="id_cfu")
	private Long idCfu;

	@Column(name="area_code")
	private String areaCode;

	@Column(name="cfu_code")
	private String cfuCode;

	@Column(name="cfu_description")
	private String cfuDescription;

	public Cfu() {
	}

	public Long getIdCfu() {
		return this.idCfu;
	}

	public void setIdCfu(Long idCfu) {
		this.idCfu = idCfu;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCfuCode() {
		return this.cfuCode;
	}

	public void setCfuCode(String cfuCode) {
		this.cfuCode = cfuCode;
	}

	public String getCfuDescription() {
		return this.cfuDescription;
	}

	public void setCfuDescription(String cfuDescription) {
		this.cfuDescription = cfuDescription;
	}

}