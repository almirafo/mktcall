package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the config database table.
 * 
 */
@NamedQueries({ 
	@NamedQuery(name = "Config.All", query = "select a from Config a")
	
  })

@Entity
@Cacheable(value=true)
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_config")
	private Long idConfig;

	private String indentify;

	private String value;

	public Long getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(Long idConfig) {
		this.idConfig = idConfig;
	}

	public String getIndentify() {
		return indentify;
	}

	public void setIndentify(String indentify) {
		this.indentify = indentify;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}