package br.com.supportcomm.mktcall.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ListaCampanhaDialOutVO {

	@Id
	private Long idCampanha;
	
	private String nomeCampanha;
	private Long   contratado;  
	private Long   completos;  
	private Long   incompletos;  

	private String processStatus;
	
	public Long getIdCampanha() {
		return idCampanha;
	}
	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public Long getContratado() {
		return contratado;
	}
	public void setContratado(Long contratado) {
		this.contratado = contratado;
	}
	public Long getCompletos() {
		return completos;
	}
	public void setCompletos(Long completos) {
		this.completos = completos;
	}
	public Long getIncompletos() {
		return incompletos;
	}
	public void setIncompletos(Long incompletos) {
		this.incompletos = incompletos;
	}

	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getNomeCampanha() {
		return nomeCampanha;
	}
	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}
	
	
	
}
