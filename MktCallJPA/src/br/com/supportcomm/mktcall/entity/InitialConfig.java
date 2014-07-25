package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the initial_config database table.<br/>
 * Tabela de configuração de sistema
 */
@Entity
@Table(name="initial_config")


@NamedQueries({
	@NamedQuery(name = "InitialConfig.getByType", query = "select a from InitialConfig a where a.configType= :type "),
	@NamedQuery(name = "InitialConfig.getEnableFTP", query = "select a from InitialConfig a where a.configType='activate' and a.description = 'URAFTP'"),
	@NamedQuery(name = "InitialConfig.getOriFTP", query = "select a from InitialConfig a where a.configType='ori' and a.description = 'ORI-FTP'"),
	@NamedQuery(name = "InitialConfig.getLoginFTP", query = "select a from InitialConfig a where a.configType= 'loginFTP'"),
})

public class InitialConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_initial_config")
	private Long idInitialConfig;

	@Column(name="config_type")
	private String configType;

	private String description;

	@Column(name="\"IP\"")
	private String ip;

	@Column(name="path_file")
	private String pathFile;

	public InitialConfig() {
	}

	public Long getIdInitialConfig() {
		return this.idInitialConfig;
	}

	public void setIdInitialConfig(Long idInitialConfig) {
		this.idInitialConfig = idInitialConfig;
	}

	public String getConfigType() {
		return this.configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPathFile() {
		return this.pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

}